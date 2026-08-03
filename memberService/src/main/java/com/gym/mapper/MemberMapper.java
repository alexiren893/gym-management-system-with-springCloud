package com.gym.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.pojo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper extends BaseMapper<Member> {
    Boolean insertMember(Member member);
    Member userSelect(@Param("memberAccount") String memberAccount,@Param("memberPassword") String memberPassword);
}