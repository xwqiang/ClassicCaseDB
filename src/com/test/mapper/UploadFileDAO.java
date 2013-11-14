package com.test.mapper;

import java.util.List;

import com.test.model.UploadFile;

public interface UploadFileDAO {

	int insertUploadFile(UploadFile uploadFile);

	UploadFile getFileById(int id);

	List<UploadFile> getUploadFileList();

	int deleteUploadFileById(int id);

}
