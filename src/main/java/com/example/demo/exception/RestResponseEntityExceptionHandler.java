package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private MessageSource messageSource;

  /**
   * Instantiates a new Rest response entity exception handler.
   *
   * @param messageSource the message source
   */
  public RestResponseEntityExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * Handler business exception response entity.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(BizException.class)
  public ErrorResponse handlerBusinessException(BizException ex, HttpServletRequest request) {
    HttpStatus httpStatus;
    switch (ex.errType) {
      case BAD_REQUEST:
        httpStatus = HttpStatus.BAD_REQUEST;
        break;
      case CONFLICT:
        httpStatus = HttpStatus.CONFLICT;
        break;
      case NOT_FOUND:
        httpStatus = HttpStatus.NOT_FOUND;
        break;
      case UNSUPPORTED_OPERATION:
        httpStatus = HttpStatus.FORBIDDEN;
        break;
      default:
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    List<Error> errors = new ArrayList<>();
    ex.getErrors().forEach(error -> {
      errors.add(Error.builder().code(error)
          .message(messageSource.getMessage(error, null, LocaleContextHolder.getLocale()))
          .build());
    });

    return ErrorResponse.builder().httpStatus(httpStatus.value()).errors(errors)
        .url(request.getRequestURL().toString()).timestamp(LocalDateTime.now()).build();
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponse handleSystemError(HttpServletRequest request) {
    List<Error> errors = new ArrayList<>();
    errors.add(Error.builder().code("err.server.internal-server-error")
        .message(messageSource
            .getMessage("err.internal-server-error", null, LocaleContextHolder.getLocale()))
        .build());
    return ErrorResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .errors(errors).url(request.getRequestURL().toString()).timestamp(
            LocalDateTime.now()).build();
  }

}
