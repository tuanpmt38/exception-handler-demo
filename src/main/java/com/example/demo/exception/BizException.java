package com.example.demo.exception;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BizException extends RuntimeException {

  ErrType errType = ErrType.BAD_REQUEST;
  List<String> errors;

  public BizException(List<String> errors, ErrType errType) {
    this.errors = errors;
    this.errType = errType;
  }

  public BizException buildConstraintViolationException(List<String> errors) {
    return getBizException(errors, ErrType.BAD_REQUEST);
  }

  public BizException buildResourceNotFoundException(List<String> errors) {
    return getBizException(errors, ErrType.NOT_FOUND);
  }

  public BizException buildDataConflictException(List<String> errors) {
    return getBizException(errors, ErrType.CONFLICT);
  }

  public BizException buildUnsupportedOperationException(List<String> errors) {
    return getBizException(errors, ErrType.UNSUPPORTED_OPERATION);
  }

  private BizException getBizException(List<String> errors, ErrType errType) {
    return new BizException(errors, errType);
  }

  @Getter
  enum ErrType {
    BAD_REQUEST,
    NOT_FOUND,
    CONFLICT,
    UNSUPPORTED_OPERATION;

  }

}
