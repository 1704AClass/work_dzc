package com.itheima.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

public interface CheckItemMapper {

	void add(CheckItem checkItem);

	Page<CheckItem> selectByCondition(String queryString);

	void deleteById(Integer id);

	long findCountByCheckItemId(Integer id);

	void edit(CheckItem checkItem);

	CheckItem findById(Integer id);

	@Select(value="select * from t_checkitem")
	List<CheckItem> findAll();

}
