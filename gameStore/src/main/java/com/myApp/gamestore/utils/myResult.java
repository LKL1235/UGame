package com.myApp.gamestore.utils;
import lombok.Data;

/**
 * @description:
 * @author:25445
 * @date:2022/11/24 19:03
 **/

@Data
public class myResult {
    // 结果状态码
    private Integer code;
    // 结果消息
    private String msg;
    // 结果数据
    private Object data;

    public myResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public myResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "Result{" +
                "code=" + this.code +
                ", msg='" + this.msg + '\'' +
                ", data=" + this.data +
                '}';
    }
}
