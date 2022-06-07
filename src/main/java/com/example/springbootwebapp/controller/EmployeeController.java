package com.example.springbootwebapp.controller;


import com.example.springbootwebapp.model.Employee;
import com.example.springbootwebapp.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository eRepo;

    //下拉選單
    static List<String> departmentlist =null;

    static {
        departmentlist = new ArrayList<>();
        departmentlist.add("行政");
        departmentlist.add("總務");
        departmentlist.add("資訊");
        departmentlist.add("主計");
        departmentlist.add("主管級別");
        departmentlist.add("人事");
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        List<Employee> listUsers = eRepo.findAll();
        model.addAttribute("employees", listUsers);
        return "list-employees";
    }

    @PostMapping("/addEmployeeForm")
    public String AddEmployee(Employee employee) {
        eRepo.save(employee);
        return "redirect:/index";
    }

    @GetMapping("/EmployeeForm")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departmentlist",departmentlist);
        return "EmployeeForm";
    }

    @GetMapping("/showUpdateForm/{employeeId}")
    public String showUpdateForm(Model model, @PathVariable Integer employeeId) {
        Employee employee = eRepo.findById(employeeId).get();
        model.addAttribute("employee", employee);
        model.addAttribute("departmentlist",departmentlist);
        return "updateEmployeeForm";
    }
    @GetMapping("deleteEmployee/{employeeId}")
   public String deleteEmployee(@PathVariable Integer employeeId){
        eRepo.deleteById(employeeId);
        return "redirect:/index";
    }


}



