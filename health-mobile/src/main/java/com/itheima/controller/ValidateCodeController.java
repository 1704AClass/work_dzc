package com.itheima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;

//短信验证码
@RequestMapping("/validateCode")
public class ValidateCodeController {

	@Autowired
	private RedisTemplate redisTemplate;
	
	//验证码
		@RequestMapping("/send4Order")
		private Result send4Order(String telephone) {
			//使用工具类来生成4位验证码
			String code = ValidateCodeUtils.generateValidateCode(4)+"";
			try {
				SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code);
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
			}
			redisTemplate.boundHashOps("SmsTelephone").put(RedisMessageConstant.SENDTYPE_ORDER, code);
			return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
		}
}
