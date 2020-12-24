package com.yqf.common.core.result;

/**
 * @author yqf
 * @date 2020-12-23
 **/
public interface IResultCode {

    /**
     * 获取操作码
     * @return s
     * */
    String getCode();

    /**
     * 获取返回信息
     * @return s
     * */
    String getMsg();

}
