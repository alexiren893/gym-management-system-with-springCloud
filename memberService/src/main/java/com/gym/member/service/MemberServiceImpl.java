package com.gym.member.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.member.mapper.MemberMapper;
import com.gym.member.pojo.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    @Override
    public Member memberSelect(String memberAccount,String memberPassword) {
        return memberMapper.userSelect(memberAccount,memberPassword);
    }
}
