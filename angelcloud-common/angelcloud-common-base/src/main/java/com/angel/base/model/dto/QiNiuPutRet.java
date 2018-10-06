package com.angel.base.model.dto;

/**
 * 七牛云返回数据
 *
 * @Author: Angel
 * @Date: 2018/10/5.
 * @Description:
 */
public final class QiNiuPutRet {
    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
