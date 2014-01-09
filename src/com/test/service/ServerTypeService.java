package com.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.ServerTypeDAO;
import com.test.model.ServerType;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class ServerTypeService extends IPageService {
	@Autowired
	private ServerTypeDAO serverTypeDAO;
	private Page page;
	public List<ServerType> getServerTypeList(){
		if(page!=null){
			return serverTypeDAO.getServerTypeList(page.getStart(),page.getEnd());
		}else{
			return serverTypeDAO.getServerTypeList(0,0);
		}
	}
	
	@Override
	public int getTatal(Map<String, Object> p) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object pagination(int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}