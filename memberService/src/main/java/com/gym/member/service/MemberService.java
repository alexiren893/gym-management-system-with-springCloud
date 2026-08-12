package com.gym.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.member.pojo.Member;

public interface MemberService extends IService<Member> {
    Member memberSelect(String memberAccount,String memberPassword);
}
