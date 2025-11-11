package org.example.berserkdle.controllers;

import org.example.berserkdle.dtos.AbstractDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InterfaceAPIController<D extends AbstractDTO> {
    @GetMapping("/get/all")
    List<D> getAll();

    @PostMapping("create")
    HttpStatus create(@RequestBody D DTO);
}
