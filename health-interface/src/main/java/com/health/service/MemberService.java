package com.health.service;

import com.itheima.pojo.Member;

public interface MemberService {

	void add(Member member);

	Member findByTelephone(String telephone);

}
