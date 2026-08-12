package com.gym.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.member.pojo.Member;
import com.gym.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {
    private final MemberService memberService;

    public ApiMemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/selMember")
    public Map<String, Object> selMember() {
        List<Member> memberList = memberService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("memberList", memberList);
        return map;
    }

    @PostMapping("/memberLogin")
    public Member memberSelect(@RequestParam("memberAccount") String memberAccount,@RequestParam("memberPassword") String memberPassword) {
        Member member = memberService.memberSelect(memberAccount,memberPassword);
        if(member==null){
            return null;
        }
        return member;
    }

    @PostMapping("/addMember")
    public Map<String, Object> addMember(Member member) {
        StringBuilder account1= new StringBuilder("1011");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            account1.append(random.nextInt(10));
        }
        member.setMemberAccount(Integer.parseInt(account1.toString()));
        Date date = new Date();
        Boolean success = memberService.save(member);
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        return map;
    }

    @PostMapping("/updateMember")
    public Map<String, Object> updateMember(Member member) {
        memberService.updateById(member);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/toUpdateMember")
    public Map<String, Object> toUpdateMember(@RequestParam("memberAccount") Integer memberAccount) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>().
                select("member_account", "member_name", "member_gender", "member_age", "card_time", "card_class", "card_next_class").
                eq("member_account",memberAccount);
        Member member = memberService.getOne(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("member", member);
        return map;
    }

    @PostMapping("/delMember")
    public Map<String, Object> delMember(@RequestParam("memberAccount") Integer memberAccount) {
        memberService.removeById(memberAccount);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}
