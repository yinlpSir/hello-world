package com.hngy.lms.exception.handler;

import com.hngy.lms.exception.LmsException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 处理所有不可知的异常类型
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        return buildErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }
    /**
     * Handle bad credentials exception.
     *
     * @param ex
     *          the exception
     * @return the error response entity
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(EncryptionOperationNotPossibleException.class)
    public ResponseEntity<Object> handleEncryptionOperationNotPossible(EncryptionOperationNotPossibleException e){
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalState(IllegalStateException e){
        return buildErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(LmsException.class)
    public  ResponseEntity handleLmsException(LmsException e){
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
    }

    /**
     * Handle access denied exception.
     *
     * @param ex
     *          the exception
     * @return the error response entity
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    /**
     * Handle the other exceptions.
     *
     * @param throwable
     *          the throwable
     * @return the error response entity
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleOtherException(Throwable throwable) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    /**
     *  重写 ResponseEntityExceptionHandler 中 处理 MethodArgumentNotValidException 异常的方法
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result=ex.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        result.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(errorMap);
    }

//  ConstraintViolationException 验证失败的异常
    //UsernameNotFoundException

    //DataIntegrityViolationException 数据完整性异常
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException e){
        return buildErrorResponse(HttpStatus.BAD_REQUEST,"数据输入有误！");
    }

    /**
     *  构建 统一 错误 响应实体
     * @param status 状态码
     * @param message 错误信息
     * @return
     */
    private static ResponseEntity<Object> buildErrorResponse(HttpStatus status,String message){
        Map<String,Object> responseBody=new HashMap<>();
        responseBody.put("message",message);
        return new ResponseEntity<>(responseBody,status);//Create a ResponseEntity with a body and status code.
    }

}
