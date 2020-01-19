package com.health.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.service.SetmealService;
import com.itheima.entity.PageResult;
import com.itheima.entity.RedisConstant;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.Setmeal;

import redis.clients.jedis.JedisPool;

@Service
public class SetmealServiceImpl implements SetmealService{

	@Autowired
	private SetmealMapper setmealMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;

	//新增套餐
	@Override
	public void add(Setmeal setmeal, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		setmealMapper.add(setmeal);
		if (checkgroupIds!=null&&checkgroupIds.length>0) {
			//绑定套餐和检查组的多对多关系
			setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
		}
		//将图片名称保存到Redis
		savePic2Redis(setmeal.getImg());
	}
	
	//将图片名称保存到Redis
	private void savePic2Redis(String pic){
	    redisTemplate.boundHashOps("imageAll").put(RedisConstant.SETMEAL_PIC_RESOURCES, pic);
	}


	//绑定套餐和检查组的多对多关系
	private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
		for (Integer checkgroupId : checkgroupIds) {
			Map<String,Integer> map = new HashMap<>();
			map.put("setmeal_id",id);
			map.put("checkgroup_id",checkgroupId);
			setmealMapper.setSetmealAndCheckGroup(map);
		}
	}

	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage, pageSize);
		Page<Setmeal> page = setmealMapper.selectByCondition(queryString);
		return new PageResult(page.getTotal(), page.getResult());
	}
}
