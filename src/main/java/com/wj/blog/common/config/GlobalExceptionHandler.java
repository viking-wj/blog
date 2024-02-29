package com.wj.blog.common.config;

import com.wj.blog.common.enums.ErrorCodeEnum;
import com.wj.blog.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author wj
 * @date 2023/02/28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理所有RequestBody注解参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return Result.fail(ErrorCodeEnum.W205.getCode(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理所有RequestParam注解数据验证异常
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.warn("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return Result.fail(ErrorCodeEnum.W205.getCode(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param e NullPointerException
     * @return
     * @description 空指针异常定义为前端传参错误，返回400
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result<String> nullPointerException(NullPointerException e) {
        log.error("空指针异常 NullPointerException ", e);
        return Result.fail(ErrorCodeEnum.W400.getCode(), ErrorCodeEnum.W400.getMessage());
    }

    /**
     * 处理404异常
     *
     * @param e NoHandlerFoundException
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<String> noHandlerFoundException(HttpServletRequest req, Exception e) {
        log.error("404异常 NoHandlerFoundException, method = {}, path = {} ", req.getMethod(), req.getServletPath(), e);
        return Result.fail(ErrorCodeEnum.W404.getCode(), ErrorCodeEnum.W404.getMessage());
    }

    /**
     * 处理请求方式错误(405)异常
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> httpRequestMethodNotSupportedException(HttpServletRequest req, Exception e) {
        log.error("请求方式错误(405)异常 HttpRequestMethodNotSupportedException, method = {}, path = {}", req.getMethod(), req.getServletPath(), e);
        return Result.fail(ErrorCodeEnum.W405.getCode(), ErrorCodeEnum.W405.getMessage());
    }

    /**
     * 处理其他异常
     *
     * @param e otherException
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        log.error("未知异常 exception = {}", e.getMessage(), e);
        return Result.fail(ErrorCodeEnum.W500.getCode(), ErrorCodeEnum.W500.getMessage());
    }
}
