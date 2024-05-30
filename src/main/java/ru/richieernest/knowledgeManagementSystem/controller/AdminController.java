package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.dto.formula.FormulaDto;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.EmployeeAndRole;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.User;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.UserRole;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.service.AdminService;
import ru.richieernest.knowledgeManagementSystem.service.FormulaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RolesAllowed({"ROLE_ADMIN"})
public class AdminController {
    
    private final AdminService adminService;
    private final FormulaService formulaService;

    @PostMapping("/test")
    public void test(@RequestBody FormulaDto formulaApi){
    }

    @PostMapping("/viewEmp")
    public List<EmployeeAndRole> viewEmp(){
        return adminService.findAllEmployees();
    }

    @PostMapping("/deleteEmp")
    public ResponseEntity<Void> deleteEmp(@RequestBody String id){
        adminService.deleteEmployee(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/role")
    public ResponseEntity<Void> changeRole(@RequestBody UserRole userRole){
        adminService.changeUserRole(userRole);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/regAdmin")
    public String registration(@RequestBody User user){
        adminService.createAdmin(user);
        return "admin";
    }
}
