package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.model.Solution;



public interface SolutionDAO {
	public List<Solution> getSolutionListByMenuId(@Param(value="menu_id")int menu_id,@Param(value="start")int start,@Param(value="end")int end);

	public int updateSolutionById(Solution solution);

	public int insertSolution(Solution solution);

	public int deleteSolutionById(int id);

	public List<Solution> searchSolutions(List<String> keylist);

	public List<Solution> getHighRankingsolutions(int i);

	public Solution getsolutionById(int id);

	public int countSolutionListByMenuId(@Param(value="args") Map<String, Object> args);
}
