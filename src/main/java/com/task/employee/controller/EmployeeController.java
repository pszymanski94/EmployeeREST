package com.task.employee.controller;

import com.task.employee.dto.EmployeeDTO;
import com.task.employee.repository.EmployeeRepository;
import com.task.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        List<EmployeeDTO> employeesList = employeeService.findAll();
        return new ResponseEntity<>(employeesList, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createEmployee(@Valid @RequestBody EmployeeDTO employeeDto, UriComponentsBuilder uriComponentsBuilder, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Long employeeId = employeeService.createEmployee(employeeDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(employeeId).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(params = {"search", "value"}, method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTO>> searchEmployee(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "value", required = false) String value) {
        List<EmployeeDTO> searchResults = employeeService.search(value, search);
        return new ResponseEntity<>(searchResults, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity editEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        EmployeeDTO editEmployeeDTO = employeeService.update(id, employeeDTO);
        return new ResponseEntity(editEmployeeDTO, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}