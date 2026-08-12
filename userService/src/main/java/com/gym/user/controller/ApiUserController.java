package com.gym.user.controller;

import com.gym.user.dto.Member;
import com.gym.user.pojo.ClassTable;
import com.gym.user.service.ClassOrderService;
import com.gym.user.service.ClassTableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    private static final String SESSION_MEMBER = "member";
    private final ClassTableService classTableService;
    private final ClassOrderService classOrderService ;

    public ApiUserController(ClassTableService classTableService, ClassOrderService classOrderService) {
        this.classTableService = classTableService;
        this.classOrderService = classOrderService;
    }



    @GetMapping("/toApplyClass")
    public ResponseEntity<Map<String, Object>> toApplyClass() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("classList", classTableService.list());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/applyClass")
    public ResponseEntity<Map<String, Object>> applyClass(@RequestParam("classId") Integer classId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        ClassTable classTableServiceById = classTableService.getById(classId);
        Member memberInfo = (Member) session.getAttribute("member");
        classOrderService.insertByClassTableWithMemberName(classTableServiceById, memberInfo.getMemberName(), memberInfo.getMemberAccount());
        result.put("success", true);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/toUserClass")
    public ResponseEntity<Map<String, Object>> toUserClass(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("classOrderList", classOrderService.listByMemberAccount((Integer) session.getAttribute("memberAccount")));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delUserClass")
    public ResponseEntity<Map<String, Object>> delUserClass(@RequestParam("classOrderId") Integer classId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        classOrderService.deleteByClassOrderIdWithMemberAccount(classId, (Integer) session.getAttribute("memberAccount"));
        result.put("success", true);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/toUserInfo")
    public ResponseEntity<Map<String, Object>> toUserInfo(HttpSession session) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("member", session.getAttribute("member"));
        body.put("memberName", session.getAttribute("memberName"));
        body.put("memberAccount", session.getAttribute("memberAccount"));
        body.put("memberGender", session.getAttribute("memberGender"));
        body.put("memberAge", session.getAttribute("memberAge"));
        body.put("memberAddress", session.getAttribute("memberAddress"));
        body.put("memberHeight", session.getAttribute("memberHeight"));
        body.put("memberWeight", session.getAttribute("memberWeight"));
        body.put("memberPhone", session.getAttribute("memberPhone"));
        body.put("memberEmail", session.getAttribute("memberEmail"));
        body.put("cardTime", session.getAttribute("cardTime"));
        body.put("cardClass", session.getAttribute("cardClass"));
        body.put("cardNextClass", session.getAttribute("cardNextClass"));
        return ResponseEntity.ok(body);
    }





    private static Map<String, Object> loginSuccess() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        return map;
    }
    private static ResponseEntity<Map<String, Object>> loginFail() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", false);
        map.put("message", "用户名或密码错误");
        return ResponseEntity.badRequest().body(map);
    }
}
