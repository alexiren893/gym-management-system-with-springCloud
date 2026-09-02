package com.gym.user.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.user.FeignClient.EmployeeFeignClient;
import com.gym.user.FeignClient.EquipmentFeignClient;
import com.gym.user.dto.Employee;
import com.gym.user.dto.Equipment;
import com.gym.user.dto.Member;
import com.gym.user.pojo.Admin;
import com.gym.user.service.AdminService;
import com.gym.user.FeignClient.MemberFeignClient;
import com.gym.user.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import util.Jwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class APIController {

    private static final String SESSION_ADMIN = "admin";
    private static final String SESSION_MEMBER = "member";
    private final MemberFeignClient memberFeignClient;
    private final EquipmentFeignClient equipmentFeignClient;
    private final EmployeeFeignClient employeeFeignClient;
    private final UserServiceImpl userServiceImpl;
    private final AdminService adminService;
    private static final Jwt jwt = new Jwt();

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(loginSuccess(null));
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<Map<String, Object>> adminLogin(@RequestParam("adminAccount") String adminAccount, @RequestParam("adminPassword") String adminPassword, HttpSession httpSession) {
        Admin adminLogin = adminService.adminLogin(adminAccount,adminPassword);
        if (adminLogin==null) {
            return loginFail();
    }
        putAdminMainDataInSession(httpSession, adminLogin);
        return ResponseEntity.ok(loginSuccess(null));

}

    @PostMapping("/userLogin")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestParam("memberAccount") String memberAccount,@RequestParam("memberPassword") String memberPassword, HttpSession httpSession) {
        Member userLogin = memberFeignClient.memberSelect(memberAccount,memberPassword);
        if (userLogin==null) {
            return loginFail();
        }
        putUserMainDataInSession(httpSession, userLogin);
        return ResponseEntity.ok(loginSuccess(userLogin.getMemberAccount()));

    }
    @GetMapping("/toUserMain")
    public ResponseEntity<Map<String, Object>> toUserMain(HttpSession session) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("member",session.getAttribute("member"));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/toAdminMain")
    public ResponseEntity<Map<String, Object>> toAdminMain(HttpSession session) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("memberTotal", session.getAttribute("memberCount"));
        body.put("humanTotal", session.getAttribute("totalCount"));
        body.put("equipmentTotal", session.getAttribute("equipmentCount"));
        body.put("employeeTotal", session.getAttribute("employeeCount"));
        return ResponseEntity.ok(body);
    }



private static Map<String, Object> loginSuccess(Integer memberId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        map.put("token", Jwt.creatToken(memberId));
        return map;
}
private static ResponseEntity<Map<String, Object>> loginFail() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", false);
        map.put("message", "用户名或密码错误");
        return ResponseEntity.badRequest().body(map);
}

private void putAdminMainDataInSession(HttpSession session, Admin admin) {
    session.setAttribute(SESSION_ADMIN, admin);
    List<Member> memberList = new ObjectMapper().convertValue(memberFeignClient.selMember().get("memberList"), new TypeReference<List<Member>>(){});
    Integer memberCount = memberList.size();
    List<Equipment> equipmentList = new ObjectMapper().convertValue(equipmentFeignClient.selEquipment().get("equipmentList"), new TypeReference<List<Equipment>>(){});
    Integer equipmentCount = equipmentList.size();
    List<Employee> employeeList = new ObjectMapper().convertValue(employeeFeignClient.selEmployee().get("employeeList"), new TypeReference<List<Employee>>(){});
    Integer employeeCount = employeeList.size();
    Integer totalCount = memberCount + employeeCount;
    session.setAttribute("totalCount", totalCount);
    session.setAttribute("memberCount", memberCount);
    session.setAttribute("equipmentCount", equipmentCount);
    session.setAttribute("employeeCount", employeeCount);
}

    private void putUserMainDataInSession(HttpSession session, Member member) {
        session.setAttribute(SESSION_MEMBER, member);
        session.setAttribute("member", member);
        session.setAttribute("memberName", member.getMemberName());
        session.setAttribute("memberAccount", member.getMemberAccount());
        session.setAttribute("memberGender", member.getMemberGender());
        session.setAttribute("memberAge", member.getMemberAge());
        session.setAttribute("memberHeight", member.getMemberHeight());
        session.setAttribute("memberWeight", member.getMemberWeight());
        session.setAttribute("memberPhone", member.getMemberPhone());
    }


}
