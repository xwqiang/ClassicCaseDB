package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.model.Notice;



public interface NoticeDAO {

	List<Notice> getNoticeList(@Param(value="start") int start,@Param(value="end") int end);

	int countNoticeList(Map<String, Object> argsmap);

	int insertNotice(Notice notice);

	Notice getNoticeById(int id);

	int updateNoticeById(Notice notice);

	int deleteNoticeById(int id);
}
