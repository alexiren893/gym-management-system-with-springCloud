package com.gym.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.pojo.Employee;
import com.gym.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class ApiEmployeeController {

    private final EmployeeService employeeService;
    public ApiEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/selEmployee")
    public Map<String, Object> selEmployee() {
        List<Employee> employeeList = employeeService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("employeeList", employeeList);
        return map;
    }
    @PostMapping("/addEmployee")
    public Map<String, Object> addEmployee(Employee employee) {
        StringBuilder account1= new StringBuilder("1001");
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            account1.append(random.nextInt(10));
        }
        employee.setEmployeeAccount(Integer.parseInt(account1.toString()));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        employee.setEntryTime(simpleDateFormat.format(date));
        Boolean success = employeeService.insertEmployee(employee);
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        return map;
    }

    @PostMapping("/updateEmployee")
    public Map<String, Object> updateEmployee(Employee employee) {
        employeeService.updateById(employee);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @GetMapping("/toUpdateEmployee")
    public Map<String, Object> toUpdateEmployee(@RequestParam("employeeAccount") Integer employeeAccount) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>().
                select("employee_account", "employee_name", "employee_gender", "employee_age", "entry_time", "staff", "employee_message").
                eq("employee_account",employeeAccount);
        List<Employee> employeeList = employeeService.list(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("employeeList", employeeList);
        return map;
    }

    @PostMapping("/delEmployee")
    public Map<String, Object> delEmployee(@RequestParam("employeeAccount") Integer employeeAccount) {
        employeeService.removeById(employeeAccount);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}
