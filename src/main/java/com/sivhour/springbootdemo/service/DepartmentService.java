package com.sivhour.springbootdemo.service;

import java.util.List;

import com.sivhour.springbootdemo.entity.Department;

public interface DepartmentService {

	public Department saveDepartment(Department department);
	public List<Department> fetchDepartmentList();
	public Department fetchDepartmentById(Long departmentId);
	public void deleteDepartmentById(Long departmentId);
	public Department updateDepartment(Long departmentId, Department department);
	public Department fetchDepartmentByName(String departmentName);
}
