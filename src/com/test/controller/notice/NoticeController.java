package com.test.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.Notice;
import com.test.service.NoticeService;
@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="/showNoticeList")
	public String showNoticeList(HttpServletResponse response,ModelMap modelMap) {
		List<Notice> noticeList = noticeService.pagination(10).getNoticeList();
		modelMap.addAttribute("noticeList",noticeList);
		return "manager/notice/listNotice";
	}
	
	@RequestMapping(value="showInsertNotice")
	public String showInsertNotice(ModelMap modelMap) {
		return "manager/notice/addNotice";
	}
	
	@RequestMapping(value="insertNotice")
	public String insertNotice(String title,String content,HttpServletResponse response,ModelMap modelMap) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		boolean flag = noticeService.insertNotice(notice);
		if(flag){
			modelMap.addAttribute("message","新建成功").addAttribute("url","showNoticeList.do");
		}else{
			modelMap.addAttribute("message","新建失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="showUpdateNotice")
	public String showUpdateNotice(int id,ModelMap modelMap) {
		Notice notice = noticeService.getNoticeById(id);
		modelMap.addAttribute("notice",notice);
		return "manager/notice/modNotice";
	}
	
	@RequestMapping(value="updateNotice")
	public String updateNotice(int id,String title,String content,ModelMap modelMap) {
		Notice notice = noticeService.getNoticeById(id);
		notice.setTitle(title);
		notice.setContent(content);
		boolean flag = noticeService.updateNoticeById(notice);
		if(flag){
			modelMap.addAttribute("message","修改成功").addAttribute("url","showNoticeList.do");
		}else{
			modelMap.addAttribute("message","修改失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="/deleteNotice")
	public String deleteNotice(int id,ModelMap modelMap) {
		boolean flag = noticeService.deleteNoticeById(id);
		if(flag){
			modelMap.addAttribute("message","删除成功").addAttribute("url","showNoticeList.do");
		}else{
			modelMap.addAttribute("message","删除失败");
		}
		return "forward";
	}
	
}