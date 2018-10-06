package com.angel.provider.service;

import com.angel.ApplicationTests;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @Author: Angel
 * @Date: 2018/9/24.
 * @Description:
 */
public class IQiNiuServiceTest extends ApplicationTests {

    @Autowired
    private IQiNiuService qiNiuService;

    @Test
    public void uploadFile() {
        String fileName = "E:/fileResources/angelcloud/temp/timg.jpg";
        File file = new File(fileName);

        Assert.assertTrue(file.exists());
        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
