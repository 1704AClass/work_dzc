package com.itheima.mapper;

import java.util.List;
import java.util.Map;

import com.itheima.pojo.Order;

public interface OrderMapper {

	List<Order> findByCondition(Order order);

	void add(Order order);

	Map findById4Detail(Integer id);

	Integer findOrderCountByDate(String today);

	Integer findOrderCountAfterDate(String thisWeekMonday);

	Integer findVisitsCountByDate(String today);

	Integer findVisitsCountAfterDate(String thisWeekMonday);

	List<Map> findHotSetmeal();

}
