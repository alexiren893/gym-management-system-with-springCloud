package com.gym.employee.mapper;

import com.gym.employee.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2026-08-02
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    Boolean insertEmployee(Employee employee);

}
