package com.health.service;

import java.util.List;

import com.itheima.pojo.Member;

public interface MemberService {

	void add(Member member);

	Member findByTelephone(String telephone);

	List<Integer> findMemberCountByMonth(List<String> list);

}
