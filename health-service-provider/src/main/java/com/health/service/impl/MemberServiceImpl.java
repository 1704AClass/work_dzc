package com.health.service.impl;

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
	
	
}
