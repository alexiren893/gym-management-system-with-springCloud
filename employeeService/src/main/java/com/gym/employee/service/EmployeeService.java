package com.gym.employee.service;

import com.gym.employee.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2026-08-02
 */
public interface EmployeeService extends IService<Employee> {
    Boolean insertEmployee(Employee employee);
}
