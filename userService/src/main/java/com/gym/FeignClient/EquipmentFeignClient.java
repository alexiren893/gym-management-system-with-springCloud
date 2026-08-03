package com.gym.FeignClient;

import com.gym.dto.Equipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/equipment")
@FeignClient("EquipmentService")
public interface EquipmentFeignClient {
    @GetMapping("/selEquipment")
    List<Equipment> selEquipment();
}
