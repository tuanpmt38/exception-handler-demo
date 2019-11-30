package com.example.demo.service.impl;

import com.example.demo.dto.NewAddDto;
import com.example.demo.dto.NewEditDto;
import com.example.demo.entity.News;
import com.example.demo.exception.BizException;
import com.example.demo.repository.NewRepository;
import com.example.demo.service.NewService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class NewServiceImpl implements NewService {

  @Autowired
  NewRepository repository;

  @Override
  public News save(NewAddDto newAddDto) {

    News news = new News();
    repository.findByTitle(newAddDto.getTitle())
        .orElseThrow(
            () -> new BizException().buildDataConflictException(
                Collections.singletonList("Exception.conflict")));

    BeanUtils.copyProperties(newAddDto, news);
    return repository.save(news);
  }

  @Override
  public News edit(NewEditDto newEditDto) {

    News newsOld = repository.findById(newEditDto.getId())
        .orElseThrow(
            () -> new BizException().buildResourceNotFoundException(
                Collections.singletonList("Exception.not-found")));
    checkValidateField(newEditDto.getImageUrl(), newEditDto.getContent());
    BeanUtils.copyProperties(newEditDto, newsOld);
    return repository.save(newsOld);
  }

  private void checkValidateField(String imageUrl, String content){
    List<String> failReason = new ArrayList<>();
    repository.findByImageUrl(imageUrl).ifPresent(
        user -> failReason.add("err.user.imageUrl-already-exists"));
    repository.findByContent(content).ifPresent(
        user -> failReason.add("err.new.content-already-exists"));


    if (!CollectionUtils.isEmpty(failReason)) {
      throw new BizException().buildDataConflictException(failReason);
    }
  }
}
