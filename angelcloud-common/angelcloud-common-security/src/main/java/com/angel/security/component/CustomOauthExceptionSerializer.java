package com.angel.security.component;

import com.angel.base.constant.ServerResponse;
import com.angel.security.exception.CustomOauthException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

import java.io.IOException;

/**
 * 异常格式化
 * @Author angel
 * @Date 19-11-12
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    @SneakyThrows
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider){
        ServerResponse response =
                ServerResponse.createByErrorCodeMessage
                        (e.getHttpErrorCode(), e.getMessage());
        jsonGenerator.writeObject(response);
    }
}
