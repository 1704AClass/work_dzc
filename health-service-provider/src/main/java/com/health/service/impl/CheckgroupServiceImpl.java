package com.health.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.service.CheckgroupService;
import com.itheima.entity.PageResult;
import com.itheima.mapper.CheckgroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

@Service
public class CheckgroupServiceImpl implements CheckgroupService{

	@Autowired
	private CheckgroupMapper checkgroupMapper;
	
	//添加检查组合，同时需要设置检查组合和检查项的关联关系
	@Override
	public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		checkgroupMapper.add(checkGroup);
		setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
	}

	//设置检查组合和检查项的关联关系
	public void setCheckGroupAndCheckItem(Integer checkgroupid, Integer[] checkitemIds) {
		if (checkitemIds!=null && checkitemIds.length>0) {
			for (Integer checkitemId : checkitemIds) {
				Map<String,Integer> map = new HashMap<>();
				map.put("checkgroup_id", checkgroupid);
				map.put("checkitem_id", checkitemId);
				checkgroupMapper.setCheckGroupAndCheckItem(map);
			}
		}
	}

	@Override
	public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage, pageSize);
		Page<CheckGroup> page = checkgroupMapper.findPage(queryString);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public CheckGroup findById(Integer id) {
		// TODO Auto-generated method stub
		return checkgroupMapper.findById(id);
	}

	
	@Override
	public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
		// TODO Auto-generated method stub
		return checkgroupMapper.findCheckItemIdsByCheckGroupId(id);
	}

	//编辑检查组，同时需要更新和检查项的关联关系
	@Override
	public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		//根据检查组id删除中间表数据（清理原有关联关系）
		checkgroupMapper.deleteAssociation(checkGroup.getId());
		//向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
		setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
		//更新检查组基本信息
		checkgroupMapper.edit(checkGroup);
	}

	@Override
	public List<CheckGroup> findAll() {
		// TODO Auto-generated method stub
		return checkgroupMapper.findAll();
	}
}
