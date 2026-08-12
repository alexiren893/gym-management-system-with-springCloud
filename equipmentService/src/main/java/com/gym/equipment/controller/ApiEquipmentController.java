package com.gym.equipment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.equipment.pojo.Equipment;
import com.gym.equipment.service.EquipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equipment")
public class ApiEquipmentController {
    private final EquipmentService equipmentService;
    public ApiEquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @GetMapping("/selEquipment")
    public Map<String, Object> selEquipment() {
        List<Equipment> equipmentList = equipmentService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("equipmentList", equipmentList);
        return map;
    }

    @PostMapping("/addEquipment")
    public Map<String, Object> addEquipment(Equipment equipment) {
        Boolean success = equipmentService. save(equipment);
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        return map;
    }

    @PostMapping("/updateEquipment")
    public Map<String, Object> updateEquipment(Equipment equipment) {
        equipmentService.updateById(equipment);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/toUpdateEquipment")
    public Map<String, Object> toUpdateEquipment(@RequestParam("equipmentId") Integer equipmentId) {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<Equipment>().
                select("equipment_id", "equipment_name", "equipment_location", "equipment_status", "equipment_message").
                eq("equipment_id",equipmentId);
        List<Equipment> equipmentList = equipmentService.list(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("equipmentList", equipmentList);
        return map;
    }

    @PostMapping("/delEquipment")
    public Map<String, Object> delEquipment(@RequestParam("equipmentId") Integer equipmentId) {
        equipmentService.removeById(equipmentId);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}
