package com.example.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Employee;

@Service
public class EmployeeServices {
    public List<Employee> GetEmployees()
    {
        List<Employee> lstEmps= new ArrayList<>();
        for(int x=0;x<100;x++) 
        {
            lstEmps.add(new Employee("Emp "+x,"Role "+x));
        }
        return lstEmps;
    }
}
