package com.csi.service;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void signUp(Employee employee);

    public void saveBulkOfData(List<Employee> employees);

    public boolean signIn(String empEmailId , String empPassword);

    public Employee getDataById(int empId);

    public  List<Employee> getAllData();

    public List<Employee> getDataByName(String empName);

    public Employee getDataByContactNumber(long empContactNumber);

    public Employee getDataByEmailId(String empEmailId);

    public List<Employee> filterDataBySalary(double empSalary);

    public  List<Employee> sortByName();

    public List<Employee> sortByAge();

    public List<Employee> sortBySalary();

    public  List<Employee> sortByDOB();

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();
}
