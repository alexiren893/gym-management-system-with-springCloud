package com.gym.user.FeignClient;

import com.gym.user.dto.Equipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("EquipmentService")
public interface EquipmentFeignClient {
    @GetMapping("/api/equipment/selEquipment")
    List<Equipment> selEquipment();
}
