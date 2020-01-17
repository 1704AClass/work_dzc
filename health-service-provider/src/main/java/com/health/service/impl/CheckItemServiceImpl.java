package com.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.service.CheckItemService;
import com.itheima.entity.PageResult;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckItem;

@Service
public class CheckItemServiceImpl implements CheckItemService{

	@Autowired
	private CheckItemMapper checkItemMapper;
	
	@Override
	public void add(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkItemMapper.add(checkItem);
	}

	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage, pageSize);
		Page<CheckItem> page = checkItemMapper.selectByCondition(queryString);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		//检查当前检查项和检查组关联
		long count = checkItemMapper.findCountByCheckItemId(id);
		if (count>0) {
			//当前检查项被引用，不能删除
			throw new RuntimeException("当前检查项被引用，不能删除");
		}
		checkItemMapper.deleteById(id);
	}

	@Override
	public void edit(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkItemMapper.edit(checkItem);
	}

	@Override
	public CheckItem findById(Integer id) {
		// TODO Auto-generated method stub
		return checkItemMapper.findById(id);
	}

	@Override
	public List<CheckItem> findAll() {
		// TODO Auto-generated method stub
		return checkItemMapper.findAll();
	}

}


/*<!-- https://github.com/1704AClass/work_dzc.git -->
<!-- git remote add origin https://github.com/1704AClass/work_dzc.git
git push -u origin master -->*/