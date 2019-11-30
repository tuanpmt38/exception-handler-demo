package com.example.demo.utils;


import com.example.demo.exception.BizException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;

/**
 * Created by Tuanpm on 27/11/2019
 */
public class EntityValidationUtils {

  public static void processBindingResults(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> errors = new ArrayList<>();
      bindingResult.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
      throw new BizException().buildConstraintViolationException(errors);
    }
  }

}
