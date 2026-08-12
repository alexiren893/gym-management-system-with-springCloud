package com.gym.user.FeignClient;
import com.gym.user.dto.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("memberService")
public interface MemberFeignClient {

    @PostMapping("/api/member/memberLogin")
    Member memberSelect(@RequestParam("memberAccount") String memberAccount, @RequestParam("memberPassword") String memberPassword);

    @GetMapping("/api/member/selMember")
    List<Member> selMember();
}
