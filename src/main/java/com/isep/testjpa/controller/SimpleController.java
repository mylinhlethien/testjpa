package com.isep.testjpa.controller;

import com.isep.testjpa.repository.EmpRepository;
import com.isep.testjpa.model.Emp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SimpleController {
	
	@Autowired
   private EmpRepository empRepository;

   @RequestMapping(value="/", method= RequestMethod.GET)
   public String hello(@Param("name") String name) {
   return "Hello " + name;
   }

   @RequestMapping(value="/employees", method= RequestMethod.GET)
   public List<Emp> getEmployees() {
       return empRepository.findAll();
   }
   
   @GetMapping("/employees/{empno}")
   public Emp getIDEmployees(@PathVariable Long empno) {
   	Optional<Emp> Emp = empRepository.findById(empno);
   	return Emp.get();
   }
   
   @DeleteMapping("/deleteemployees/{empno}")
   public void deleteEmployees(@PathVariable Long empno) {
   	empRepository.deleteById(empno);
   }

   @PostMapping(value="/employees")
   public Emp addEmployee(@RequestBody Emp emp) {
     return empRepository.save(emp);
   }
   
   @PutMapping("/employees/{empno}")
   public ResponseEntity<Object> updateEmployee(@RequestBody Emp emp, @PathVariable Long empno) {

   	emp.setEmpno(empno);
   	
   	empRepository.save(emp);

   	return ResponseEntity.noContent().build();
   }

}
