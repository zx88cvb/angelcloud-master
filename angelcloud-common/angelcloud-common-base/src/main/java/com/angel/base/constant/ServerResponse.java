package com.angel.base.constant;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/7.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  //为null字段不显示
public class ServerResponse<T> implements Serializable {
    private int state;
    private String msg;
    private T data;

    public ServerResponse(int state) {
        this.state = state;
    }

    public ServerResponse(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public ServerResponse(int state, String msg, T data) {

        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public ServerResponse(int state, T data) {

        this.state = state;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return  this.state==ResponseCode.SUCCESS.getCode();
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String msg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String msg){
        return new ServerResponse<T>(errorCode,msg);
    }
}
