package com.gym.user.FeignClient;

import com.gym.user.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@FeignClient("employeeService")
public interface EmployeeFeignClient {
    @GetMapping("/api/employee/selEmployee")
    List<Employee> selEmployee();
}
