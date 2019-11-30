package com.example.demo.controller;

import com.example.demo.dto.NewAddDto;
import com.example.demo.dto.NewEditDto;
import com.example.demo.entity.News;
import com.example.demo.service.NewService;
import com.example.demo.utils.EntityValidationUtils;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/new")
public class NewController {

  @Autowired
  NewService service;

  @PostMapping
  public ResponseEntity<News> addNews(@Valid @RequestBody NewAddDto newAddDto,
      BindingResult bindingResult) {

    EntityValidationUtils.processBindingResults(bindingResult);
    return ResponseEntity.ok(service.save(newAddDto));

  }

  @PutMapping
  public ResponseEntity<News> editNew(@Valid @RequestBody NewEditDto newEditDto,
      BindingResult bindingResult) {
    EntityValidationUtils.processBindingResults(bindingResult);
    return ResponseEntity.ok(service.edit(newEditDto));


  }

}
