package cn.edu.lingnan.goodf.common.xss;

import cn.edu.lingnan.goodf.common.utils.R;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class GetError {

    public GetError() {
    }

    public R getError(BindingResult result){
        if(result.hasErrors()){
            Map<String,String> map = new HashMap<>();
            //1、获取校验的错误结果
            result.getFieldErrors().forEach((item)->{
                //FieldError 获取到错误提示
                String message = item.getDefaultMessage();
//                //获取错误的属性的名字
//                String field = item.getField();
                map.put("message" , message);
            });
            return R.error(2001 , map.get("message"));
        }
        return null;
    }

}
