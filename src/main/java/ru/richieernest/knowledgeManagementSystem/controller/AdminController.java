package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/panel/users")
//@RolesAllowed({"ROLE_ADMIN"})
public class AdminController {
    
    private final AdminService adminService;
    private final FormulaService formulaService;

    @GetMapping("/")
    public List<EmployeeAndRole> viewEmp(){
        return adminService.findAllEmployees();
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateEmp(@RequestBody List<UserRole> users){
        adminService.updateUsers(users);
        return ResponseEntity.ok().build();
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
