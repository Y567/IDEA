package com.gyy.domain;

import java.io.Serializable;

/**
 * 用于封装后端返回前端的数据,包含实体类信息以及响应的成功与否
 */
public class ResultInfo implements Serializable {

    private Object data;     //实体类数据，也是最重要的数据
    private boolean flag;    //用于标志响应成功与否
    private String errorMsg; //用于保存错误信息

    //无参构造方法
    public ResultInfo() {
    }

    //用于返回成功数据的构造
    public ResultInfo(boolean flag) {
        this.flag = flag;
    }
    public ResultInfo(Object data, boolean flag) {
        this.data = data;
        this.flag = flag;
    }

    //用于返回失败数据的构造
    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
