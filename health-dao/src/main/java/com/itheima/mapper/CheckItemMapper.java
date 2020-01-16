package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

public interface CheckItemMapper {

	void add(CheckItem checkItem);

	Page<CheckItem> selectByCondition(String queryString);

	void deleteById(Integer id);

	long findCountByCheckItemId(Integer id);

	void edit(CheckItem checkItem);

	CheckItem findById(Integer id);

}
