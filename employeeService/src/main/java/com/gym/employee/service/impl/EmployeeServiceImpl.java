package com.gym.employee.service.impl;

import com.gym.employee.pojo.Employee;
import com.gym.employee.mapper.EmployeeMapper;
import com.gym.employee.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2026-08-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Override
    public Boolean insertEmployee(Employee employee) {
        EmployeeMapper employeeMapper = this.baseMapper;
        return employeeMapper.insertEmployee(employee);
    }
}
