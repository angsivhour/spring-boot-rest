package com.sivhour.springbootdemo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sivhour.springbootdemo.entity.Department;
import com.sivhour.springbootdemo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	@Cacheable(cacheNames = "department", key = "#departmentId")
	public Department fetchDepartmentById(Long departmentId) {
		System.out.println("called fetchDepartmentById() -----------");
		return departmentRepository.findById(departmentId).get();
	}


	@Override
	@CacheEvict(cacheNames = "department", key = "#departmentId")
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
		
	}

	@Override
	@CachePut(cacheNames = "department", key = "#departmentId")
	public Department updateDepartment(Long departmentId, Department department) {
		Department depDB = departmentRepository.findById(departmentId).get();
		
		if (Objects.nonNull(department.getDepartmentName()) &&
				!"".equalsIgnoreCase(department.getDepartmentName())) {
			depDB.setDepartmentName(department.getDepartmentName());
		}
		
		if (Objects.nonNull(department.getDepartmentCode()) &&
				!"".equalsIgnoreCase(department.getDepartmentCode())) {
			depDB.setDepartmentName(department.getDepartmentCode());
		}
		
		if (Objects.nonNull(department.getDepartmentAddress()) &&
				!"".equalsIgnoreCase(department.getDepartmentAddress())) {
			depDB.setDepartmentName(department.getDepartmentAddress());
		}
		return departmentRepository.save(depDB);
	}

	@Override
	@Cacheable(cacheNames = "department", key = "#departmentName")
	public Department fetchDepartmentByName(String departmentName) {
		System.out.println("called fetchDepartmentByName() -------");
		return departmentRepository.findByDepartmentName(departmentName);
	}
	
	
	
}
