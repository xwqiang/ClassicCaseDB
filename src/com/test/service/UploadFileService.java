package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.UploadFileDAO;
import com.test.model.UploadFile;
@Service
public class UploadFileService {
	@Autowired
	private UploadFileDAO uploadFileDAO;

	public boolean insertUploadFile(UploadFile uploadFile) {
		int flag = uploadFileDAO.insertUploadFile(uploadFile);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public UploadFile getFileById(int id) {
		return uploadFileDAO.getFileById(id);
	}

	public List<UploadFile> getUploadFileList() {
		return uploadFileDAO.getUploadFileList();
	}

	public boolean deleteUploadFileById(int id) {
		int flag = uploadFileDAO.deleteUploadFileById(id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}
}