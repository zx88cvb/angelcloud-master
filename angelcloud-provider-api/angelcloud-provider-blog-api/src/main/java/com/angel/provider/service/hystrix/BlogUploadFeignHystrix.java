package com.angel.provider.service.hystrix;

import com.angel.base.constant.ServerResponse;
import com.angel.base.model.dto.QiNiuPutRet;
import com.angel.provider.service.BlogUploadFeignApi;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Angel
 * @Date: 2018/10/6.
 * @Description:
 */
public class BlogUploadFeignHystrix implements BlogUploadFeignApi {
    @Override
    public ServerResponse<QiNiuPutRet> uploadFile(@RequestParam("file") MultipartFile file) {
        return null;
    }
}
