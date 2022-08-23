package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component // it will communicate with our database
public class EmployeeDaoImpl implements EmployeeDao{

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void signUp(Employee employee) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(employee);
        transaction.commit();

    }

    @Override
    public void saveBulkOfData(List<Employee> employees) {

        Session session = factory.openSession();

        for (Employee employee: employees) {
            Transaction transaction = session.beginTransaction();

            session.save(employee);
            transaction.commit();
        }

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        for (Employee employee: getAllData()){
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Employee getDataById(int empId) {

        Session session = factory.openSession();
        Employee employee = (Employee) session.get(Employee.class , empId);
        return employee;
    }

    @Override
    public List<Employee> getAllData() {

        Session session = factory.openSession();
        List<Employee> employeeList = session.createQuery("form Employee").list();
        return employeeList;
    }

    @Override
    public List<Employee> getDataByName(String empName) {
        return getAllData().stream().filter(emp->emp.getEmpName().equals(empName)).collect(Collectors.toList());
    }

    @Override
    public Employee getDataByContactNumber(long empContactNumber) {

        Employee emp=null;
        for (Employee employee :getAllData()){
            if (employee.getEmpContactNumber()==empContactNumber) {
                emp=employee;
            }
        }
        return emp;
    }

    @Override
    public Employee getDataByEmailId(String empEmailId) {

        Employee emp=null;
        for (Employee employee :getAllData()){
            if (employee.getEmpEmailId().equals(empEmailId)) {
                emp=employee;
            }
        }
        return emp;
    }

    @Override
    public List<Employee> filterDataBySalary(double empSalary) {
        return getAllData().stream().filter(emp->emp.getEmpSalary()>=empSalary).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortByName() {
        return getAllData().stream().sorted((e1,e2)->e1.getEmpName().toLowerCase().compareTo(e2.getEmpName().toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortByAge() {
        return getAllData().stream().sorted(Comparator.comparingLong(Employee::getEmpAge)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortBySalary() {
        return getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortByDOB() {
        return getAllData().stream().sorted((e1, e2)->e1.getEmpDOB().compareTo(e2.getEmpDOB())).collect(Collectors.toList());
    }

    @Override
    public void updateData(int empId, Employee employee) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee1 = (Employee) session.get(Employee.class , empId);

        if (employee1.getEmpId()==empId) {

            employee1.setEmpName(employee.getEmpName());
            employee1.setEmpAge(employee.getEmpAge());
            employee1.setEmpAddress(employee.getEmpAddress());
            employee1.setEmpDOB(employee.getEmpDOB());
            employee1.setEmpEmailId(employee.getEmpEmailId());
            employee1.setEmpSalary(employee.getEmpSalary());
            employee1.setEmpContactNumber(employee.getEmpContactNumber());

            session.update(employee1);
            transaction.commit();
        }

    }

    @Override
    public void deleteDataById(int empId) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = (Employee) session.get(Employee.class , empId);

        session.delete(employee);
        transaction.commit();

    }

    @Override
    public void deleteAllData() {

        Session session = factory.openSession();
        for (Employee employee: getAllData()) {
            Transaction transaction = session.beginTransaction();

            session.delete(employee);
            transaction.commit();
        }

    }
}
