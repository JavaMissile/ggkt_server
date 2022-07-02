package com.atguigu.ggkt.result;

import lombok.Data;

/**
 * @author Missile
 * @Date 2022-06-29-10:52
 */
//统一返回结果类
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private Boolean ok;

    public Result(){}


    public static<T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage("成功");
        result.setData(data);
        result.setOk(true);
        return  result;
    }


    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage("失败");
        result.setData(data);
        result.setOk(false);
        return  result;
    }


    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
