package com.health.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.service.MemberService;
import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import com.itheima.utils.MD5Utils;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void add(Member member) {
		// TODO Auto-generated method stub
		if (member.getPassword()!=null) {
			member.setPassword(MD5Utils.md5(member.getPassword()));
		}
		memberMapper.add(member);
	}

	@Override
	public Member findByTelephone(String telephone) {
		// TODO Auto-generated method stub
		return memberMapper.findByTelephone(telephone);
	}

	//根据月份统计会员数量
	@Override
	public List<Integer> findMemberCountByMonth(List<String> list) {
		List<Integer> month = new ArrayList<>();
		for(String m : list){
		m = m + ".31";//格式：2019.04.31
		Integer count = memberMapper.findMemberCountBeforeDate(m);
		month.add(count);
		}
		return month;
	}
	
	
}
