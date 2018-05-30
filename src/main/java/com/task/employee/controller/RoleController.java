package com.task.employee.controller;

import com.task.employee.dto.RoleDTO;
import com.task.employee.service.RoleService;
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
@RequestMapping("/api/role")

public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoleDTO>> findAll() {
        List<RoleDTO> roleList = roleService.findAll();
        return new ResponseEntity<>(roleList, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createRole(@Valid @RequestBody RoleDTO roleDTO, UriComponentsBuilder uriComponentsBuilder, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Long roleId = roleService.createRole(roleDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/role/{id}").buildAndExpand(roleId).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(params = {"search", "value"}, method = RequestMethod.GET)
    public ResponseEntity<RoleDTO> searchRole(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "value", required = false) String value) {
        RoleDTO searchResults = roleService.search(value, search);
        return new ResponseEntity<>(searchResults, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity editRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        RoleDTO editRoleDTO = roleService.update(id, roleDTO);
        return new ResponseEntity(editRoleDTO, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
