package com.backend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.service.MemberService;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Reference
	private MemberService memberService;
	
	/*
	 * 会员数量统计
	 * @return
	 */
	@RequestMapping("/getMemberReport")
	public Result getMemberReport(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, -12);
		
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			calendar.add(calendar.MONTH, 1);
			list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("months", list);
		
		List<Integer> memberCount = memberService.findMemberCountByMonth(list);
		map.put("memberCount", memberCount);
		return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS, map);
	}
}
