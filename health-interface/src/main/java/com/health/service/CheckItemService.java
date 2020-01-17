package com.health.service;

import java.util.List;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;

public interface CheckItemService {

	void add(CheckItem checkItem);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

	void delete(Integer id);

	void edit(CheckItem checkItem);

	CheckItem findById(Integer id);

	List<CheckItem> findAll();

}
