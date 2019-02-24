package com.angel.provider.web.feign;

import com.angel.base.constant.ResponseCode;
import com.angel.base.constant.ServerResponse;
import com.angel.base.enums.ErrorCodeEnum;
import com.angel.base.model.dto.QiNiuPutRet;
import com.angel.base.service.ServiceResult;
import com.angel.provider.exceptions.UserBizException;
import com.angel.provider.service.BlogUploadFeignApi;
import com.angel.provider.service.IQiNiuService;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Angel
 * @Date: 2018/10/6.
 * @Description:
 */
@RestController
@Api("feign远程调用博客文章上传接口")
public class BlogUploadFeignClient implements BlogUploadFeignApi {

    /**
     * 七牛云上传服务
     */
    @Autowired
    private IQiNiuService qiNiuService;

    /**
     * gson
     */
    @Autowired
    private Gson gson;

    /**
     * 上传文件到七牛云
     * @param file file
     * @return 返回qiniu结果集
     */
    @Override
    @ApiOperation(httpMethod = "POST", value = "上传文件")
    public ServerResponse<QiNiuPutRet> uploadFile(@RequestParam("file") MultipartFile file) {
        //判断file是否为空
        if (file.isEmpty()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        try {
            // 获取inputstream流
            InputStream inputStream = file.getInputStream();

            //调用qiNiuService 服务
            Response response = qiNiuService.uploadFile(inputStream);

            //判断返回结果是否ok
            if (response.isOK()) {
                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ServerResponse.createBySuccess(ret);
            } else {
                return ServerResponse.createByErrorCodeMessage(response.statusCode, response.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.OPC10040002.code(),ErrorCodeEnum.OPC10040002.msg());
        }
    }
}
