package com.health.service;

import java.util.List;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

public interface CheckgroupService {

	void add(CheckGroup checkGroup, Integer[] checkitemIds);

	PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

	CheckGroup findById(Integer id);

	List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

	void edit(CheckGroup checkGroup, Integer[] checkitemIds);

	List<CheckGroup> findAll();

}
