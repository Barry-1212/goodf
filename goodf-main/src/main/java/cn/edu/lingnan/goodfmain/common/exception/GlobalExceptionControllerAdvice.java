package cn.edu.lingnan.goodf.common.exception;

import cn.edu.lingnan.goodf.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionControllerAdvice {

    //捕获MethodArgumentNotValidException类型的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String , String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField() , fieldError.getDefaultMessage());
        });
        return R.error(2001 , "请求参数不合法").put("data" , map);
    }


    @ExceptionHandler(BindException.class)
    public R handleValidation(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String , String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField() , fieldError.getDefaultMessage());
        });
        return R.error(2001 , "请求参数不合法").put("data" , map);
    }

    //兜底
//    @ExceptionHandler(Exception.class)
//    public R handlerException(Exception e){
//        log.error("异常：{}"+e);
//        return R.error(10000,"未知的系统异常").put("data",e.getMessage());
//    }
}
