package com.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.NoticeDAO;
import com.test.model.Notice;
import com.test.model.UserInfo;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class NoticeService extends IPageService{

	@Autowired
	private NoticeDAO noticeDAO;
	private Page page;
	public List<Notice> getNoticeList() {
		if(page!=null){
			return noticeDAO.getNoticeList(page.getStart(),page.getEnd());
		}else{
			return noticeDAO.getNoticeList(0,0);
		}
	}


	/**
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@Override
	public NoticeService pagination(int pageSize,String...args){
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize,args);
		return this;
	}

	@Override
	public int getTatal(Map<String,Object> argsmap) {
		return noticeDAO.countNoticeList(argsmap);
	}


	public boolean insertNotice(Notice notice) {
		HttpSession session = SystemSession.getSession();
		String user_id = ((UserInfo) session.getAttribute("USER")).getUser_id();
		notice.setOperator_id(user_id);
		int flag = noticeDAO.insertNotice(notice);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}


	public Notice getNoticeById(int id) {
		Notice notice = noticeDAO.getNoticeById(id);
		return notice;
	}


	public boolean updateNoticeById(Notice notice) {
		HttpSession session = SystemSession.getSession();
		String user_id = ((UserInfo) session.getAttribute("USER")).getUser_id();
		notice.setOperator_id(user_id);
		int flag = noticeDAO.updateNoticeById(notice);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}


	public boolean deleteNoticeById(int id) {
		int flag = noticeDAO.deleteNoticeById(id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

}