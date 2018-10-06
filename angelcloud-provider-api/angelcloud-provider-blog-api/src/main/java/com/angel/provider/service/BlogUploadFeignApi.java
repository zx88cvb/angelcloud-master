package com.angel.provider.service;

import com.angel.base.constant.ServerResponse;
import com.angel.base.model.dto.QiNiuPutRet;
import com.angel.provider.service.hystrix.BlogUploadFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博客文章模块上传
 * @Author: Angel
 * @Date: 2018/10/6.
 * @Description:
 */
@FeignClient(name = "angelcloud-provider-blog",fallback = BlogUploadFeignHystrix.class)
public interface BlogUploadFeignApi {

    /**
     * 上传文件到七牛云
     * @param file file
     * @return 返回qiniu结果集
     */
    @PostMapping(value = "/oss/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ServerResponse<QiNiuPutRet> uploadFile(@RequestParam("file") MultipartFile file);
}
