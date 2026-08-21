package com.gym.user.controller;

import com.gym.user.pojo.ClassOrder;
import com.gym.user.pojo.ClassTable;
import com.gym.user.service.ClassOrderService;
import com.gym.user.service.ClassTableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/class")
public class ApiClassController {
    private final ClassOrderService classOrderService;
    private final ClassTableService classTableService;

    public ApiClassController(ClassOrderService classOrderService,ClassTableService classTableService) {
        this.classOrderService = classOrderService;
        this.classTableService = classTableService;
    }

    @GetMapping("/selClass")
    public ResponseEntity<Map<String, Object>> selClass(){
        Map<String, Object> map = new HashMap<>();
        List<ClassTable> classTables = classTableService.list();
        if (!classTables.isEmpty()){
            map.put("success", true);
            map.put("classList", classTables);
            return ResponseEntity.ok(map);
        }
        return null;
    }

    @GetMapping("/selClassOrder")
    public ResponseEntity<Map<String, Object>> selClassOrder(@RequestParam("classId") Integer classId){
        Map<String, Object> map = new HashMap<>();
        List<ClassOrder> classOrders = classOrderService.selectByClassOrderId(classId);
        if (!classOrders.isEmpty()){
            map.put("success", true);
            map.put("classOrderList", classOrders);
            return ResponseEntity.ok(map);
        }
        return null;

    }

    @PostMapping("/delClass")
    public ResponseEntity<Map<String, Object>> delClass(@RequestParam("classId") Integer classId){
        Map<String, Object> map = new HashMap<>();
        Boolean del = classTableService.removeById(classId);
        if (del){
            map.put("success", true);
            return ResponseEntity.ok(map);
        }
        return null;
    }

    @PostMapping("/addClass")
    public ResponseEntity<Map<String, Object>> addClass(ClassTable classTable, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Boolean add = classOrderService.insertByClassTableWithMemberName
                (classTable,(String)session.getAttribute("memberName"),(Integer)session.getAttribute("memberAccount"));
        if (add){
            map.put("success", true);
            return ResponseEntity.ok(map);
        }
        return null;
    }
}

