package com.example.demo.service;

import com.example.demo.dto.NewAddDto;
import com.example.demo.dto.NewEditDto;
import com.example.demo.entity.News;

public interface NewService {

  News save (NewAddDto newAddDto);

  News edit (NewEditDto newEditDto);
}
