package com.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.service.CheckgroupService;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;

@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {

	@Reference
	private CheckgroupService checkgroupService;
	
	@RequestMapping("/add")
	public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
		try {
			checkgroupService.add(checkGroup,checkitemIds);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
		}
		return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
	}
	
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
		PageResult pageResult = checkgroupService.findPage(
				queryPageBean.getCurrentPage(),
				queryPageBean.getPageSize(),
				queryPageBean.getQueryString()
				);
		return pageResult;
	}
	
	@RequestMapping("/findById")
	public Result findById(Integer id){
		CheckGroup checkGroup = checkgroupService.findById(id);
		if(checkGroup != null){
		Result result = new Result(true,
		MessageConstant.QUERY_CHECKGROUP_SUCCESS);
		result.setData(checkGroup);
		return result;
		}
		return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
	}
	
	//根据检查组合id查询对应的所有检查项id
	@RequestMapping("/findCheckItemIdsByCheckGroupId")
	public Result findCheckItemIdsByCheckGroupId(Integer id){
	try{
	List<Integer> checkitemIds = checkgroupService.findCheckItemIdsByCheckGroupId(id);
	return new
	Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
	}catch (Exception e){
	e.printStackTrace();
	return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
	}
	}
	//编辑
	@RequestMapping("/edit")
	public Result edit(@RequestBody CheckGroup checkGroup,Integer[]
	checkitemIds){
	try {
		checkgroupService.edit(checkGroup,checkitemIds);
	}catch (Exception e){
	return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
	}
	return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
	}
}
