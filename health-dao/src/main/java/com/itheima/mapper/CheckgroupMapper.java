package com.itheima.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

public interface CheckgroupMapper {

	void setCheckGroupAndCheckItem(Map<String, Integer> map);

	void add(CheckGroup checkGroup);

	Page<CheckGroup> findPage(String queryString);

	@Select("select * from t_checkgroup where id=#{id}")
	CheckGroup findById(Integer id);

	@Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id}")
	List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

	@Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
	void deleteAssociation(Integer id);

	void edit(CheckGroup checkGroup);

}
