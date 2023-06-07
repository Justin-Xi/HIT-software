package com.example.hitsoftware.vo;

public class Result<T> {
    public int code;
    public String msg;
    public T data;

    private Result(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> Result success(T data){
        Result result =new Result(0,"success",data);
        return result;
    }
    public static <T> Result success(){
        Result result =new Result(0,"success",null);
        return result;
    }

    public static <T> Result fail(T data){
        Result result =new Result(1,"failed",data);
        return result;
    }
    public static <T> Result fail(){
        Result result =new Result(1,"failed",null);
        return result;
    }

    public static <T> Result fail(String str){
        Result result =new Result(1,str,null);
        return result;
    }
}
