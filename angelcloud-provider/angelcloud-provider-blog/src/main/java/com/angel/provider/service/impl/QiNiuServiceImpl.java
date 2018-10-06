package com.angel.provider.service.impl;

import com.angel.config.properties.AngelcloudProperties;
import com.angel.config.properties.QiNiuProperties;
import com.angel.provider.service.IQiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;

/**
 * Author: Angel
 * Date: 2018/9/24.
 * Description:
 */
@Service
public class QiNiuServiceImpl implements IQiNiuService, InitializingBean {

    @Resource
    private UploadManager uploadManager;

    @Resource
    private BucketManager bucketManager;

    @Resource
    private Auth auth;

    @Resource
    private AngelcloudProperties angelcloudProperties;

    private StringMap putPolicy;

    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, null, getUploadToken());
        int retry = 0;
        // 如果失败 重新上传 重试3次
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        return response;
    }

    @Override
    public Response uploadFile(InputStream inputStream) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
            retry++;
        }
        return response;
    }

    @Override
    public Response delete(String key) throws QiniuException {
        QiNiuProperties qiNiuProperties = angelcloudProperties.getQiniu();
        Response response = bucketManager.delete(qiNiuProperties.getBucket(), key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(qiNiuProperties.getBucket(), key);
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        QiNiuProperties qiNiuProperties = angelcloudProperties.getQiniu();
        return this.auth.uploadToken(qiNiuProperties.getBucket(), null, 3600, putPolicy);
    }
}
