package com.gym.FeignClient;
import com.gym.dto.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/member")
@FeignClient("memberService")
public interface MemberFeignClient {

    @GetMapping("/memberLogin")
    Member memberSelect(@RequestParam String memberAccount, @RequestParam String memberPassword);

    @GetMapping("/selMember")
    List<Member> selMember();
}
