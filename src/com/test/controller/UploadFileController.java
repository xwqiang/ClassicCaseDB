package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.UploadFile;
import com.test.model.UserInfo;
import com.test.service.UploadFileService;
@Controller
public class UploadFileController {
	@Autowired
	private UploadFileService uploadFileService;
	private final String FILEPATH ="document/doc";
	
	@RequestMapping(value="listUploadFile")
	public String listUploadFile(ModelMap modelMap){
		List<UploadFile> uploadFileList = uploadFileService.getUploadFileList();
		modelMap.addAttribute("uploadFileList",uploadFileList);
		return "manager/uploadFile/listUploadFile";
	}
	
	@RequestMapping(value="showUploadFile")
	public String showUploadFile(){
		return "manager/file/addFileUpload";
	}
	
	@RequestMapping(value="deleteUploadFile")
	public String deleteUploadFile(int id,ModelMap modelMap){
		UploadFile file = uploadFileService.getFileById(id);
		boolean flag = uploadFileService.deleteUploadFileById(id);
		if(flag){
			File f = new File(file.getFile_path()+"/"+file.getFile_name());
			if(f.exists()){
				f.delete();
			}
			modelMap.addAttribute("message","删除成功").addAttribute("url","listUploadFile.do");
		}else{
			modelMap.addAttribute("message","删除失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="uploadFile")
	public String uploadFile(@RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest request, ModelMap modelMap){
		HttpSession session = request.getSession();
		String file_path = session.getServletContext().getRealPath("/"+FILEPATH);
		String user_id = ((UserInfo) session.getAttribute("USER")).getUser_id();
		String file_type = request.getParameter("file_type");
		String file_name = file.getOriginalFilename();
		String file_desc = request.getParameter("file_desc");
		long size = file.getSize()/1024;// 单位Ｋ
		if(size>1000*100){
			modelMap.addAttribute("message","文件大小不能超过100M");
			return "forward";
		}
		int start = file_name.lastIndexOf(".");
		String suffix = file_name.substring(start,file_name.length());
		file_name = new Date().getTime()+suffix;
		File f = new File(file_path);
		if(!f.exists()){
			f.mkdirs();
		}
		File targetFile = new File(file_path,file_name);
		//保存
		 try{
			 file.transferTo(targetFile);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 UploadFile uploadFile = new UploadFile();
		 uploadFile.setFile_name(file_name);
		 uploadFile.setFile_path(file_path);
		 uploadFile.setFile_type(Integer.parseInt(file_type));
		 uploadFile.setFile_desc(file_desc);
		 uploadFile.setUser_id(user_id);
		 boolean flag = uploadFileService.insertUploadFile(uploadFile);
		 if(flag){
			 modelMap.addAttribute("message","上传成功").addAttribute("url","listUploadFile.do");
		 }else{
			 modelMap.addAttribute("message","上传失败");
		 }
		 return "forward";
	}
	
	@RequestMapping("downLoadUploadFile.do")
	public void downloadDoc(int id,HttpServletRequest request, ModelMap modelMap,HttpServletResponse response){
		  response.setContentType("application/x-download"); 
	      UploadFile uploadFile = uploadFileService.getFileById(id);
	      String fileName = null;
	      OutputStream out = null; 
	      FileInputStream in = null; 
	      try {
	        	 fileName = URLEncoder.encode(uploadFile.getFile_name(),"UTF-8"); 
	   	      	 response.addHeader("Content-Disposition","attachment;filename=" + fileName); 
	        	  out = response.getOutputStream(); 
				  in = new FileInputStream(uploadFile.getFile_path()+"/"+uploadFile.getFile_name()); 
				  byte[] b = new byte[1024]; 
				  int i = 0; 
				  while((i = in.read(b)) > 0){ 
				      out.write(b, 0, i); 
				  } 
				  out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(in != null) { 
		              try {in.close();in = null; } catch (IOException e) {e.printStackTrace();} 
		        } 
		        if(out != null)  { 
		              try {out.close();out = null; } catch (IOException e) {e.printStackTrace();}
		        } 
			}
	}
	
}