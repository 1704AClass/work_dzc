package com.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.service.CheckItemService;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

	@Reference
	private CheckItemService checkItemService;
	
	//新增
	@RequestMapping("/add")
	public Result add(@RequestBody CheckItem checkItem){
		try {
			checkItemService.add(checkItem);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
		}
		return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
	}
	
	//分页查询
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
		PageResult pageResult = checkItemService.pageQuery(
				queryPageBean.getCurrentPage(),
				queryPageBean.getPageSize(),
				queryPageBean.getQueryString()
				);
		return pageResult;
	}
	
	//删除
	@RequestMapping("/delete")
	public Result delete(Integer id){
		try {
			checkItemService.delete(id);
		}catch(RuntimeException e){
			return new Result(false, e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
		}
		return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
	}
	
	//编辑
	@RequestMapping("/edit")
	public Result edit(@RequestBody CheckItem checkItem){
		try {
			checkItemService.edit(checkItem);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
		}
		return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
	}
	
	//回显
	@RequestMapping("/findById")
	public Result findById(Integer id){
		try {
			CheckItem checkItem = checkItemService.findById(id);
			return new com.itheima.entity.Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS, checkItem);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
		}
	}
	
	//查找所有检查项数据
	@RequestMapping("/findAll")
	public Result findAll(){
		List<CheckItem> list = checkItemService.findAll();
		if (list!=null && list.size()>0) {
			return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS, list);
		}else{
			return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
		}
	}
}
