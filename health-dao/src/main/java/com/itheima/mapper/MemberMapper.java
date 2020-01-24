package com.itheima.mapper;

import com.itheima.pojo.Member;

public interface MemberMapper {

	Member findByTelephone(String telephone);

	void add(Member member);

	Integer findMemberCountBeforeDate(String m);

	Integer findMemberCountByDate(String today);

	Integer findMemberTotalCount();

	Integer findMemberCountAfterDate(String thisWeekMonday);

}
