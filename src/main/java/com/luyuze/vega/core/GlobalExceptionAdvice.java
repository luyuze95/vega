package com.luyuze.vega.core;

import com.luyuze.vega.core.configuration.ExceptionCodeConfiguration;
import com.luyuze.vega.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration exceptionCode;

    /**
     * 处理所有系统中的未知异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, Exception e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        System.out.println(e);
        return new UnifyResponse(9999, "服务器异常", method + " " + requestUrl);
    }

    /**
     * 处理我们主动抛出的HttpException
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest request, HttpException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(),
                exceptionCode.getMessage(e.getCode()),
                method + " " + requestUrl);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 设置返回状态码
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        // 返回一个response实体
        return new ResponseEntity<UnifyResponse>(message, headers, httpStatus);
    }

    /**
     * 处理url路径参数和url查询参数校验异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse handleUrlValidation(HttpServletRequest request,
                                             ConstraintViolationException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuffer message = new StringBuffer();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            message.append(constraintViolation.getMessage()).append(";");
        }
        return new UnifyResponse(10001, message.toString(), method + " " + requestUrl);
    }

    /**
     * 处理请求body里的参数验证异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse handleBeanValidation(HttpServletRequest request,
                                              MethodArgumentNotValidException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);
        return new UnifyResponse(10001, message, method + " " + requestUrl);
    }

    /**
     * 拼接参数校验异常产生的字符串
     *
     * @param errors
     * @return
     */
    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error -> errorMsg.append(error.getDefaultMessage()).append(";"));
        return errorMsg.toString();
    }
}
