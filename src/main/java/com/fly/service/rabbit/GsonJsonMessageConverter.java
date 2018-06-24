package com.fly.service.rabbit;

import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author fly
 * mq gson 转换器
 */
@Component
public class GsonJsonMessageConverter extends AbstractJsonMessageConverter {

    private static Gson gson = new Gson();
    private static ClassMapper classMapper =  new DefaultClassMapper();

    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) {
        byte[] bytes = null;
        try {
            String jsonString = gson.toJson(o);
            bytes = jsonString.getBytes(getDefaultCharset());
        }
        catch (IOException e) {
            throw new MessageConversionException(
                    "Failed to convert Message content", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(getDefaultCharset());
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }
        classMapper.fromClass(o.getClass(),messageProperties);
        return new Message(bytes, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        Object content = null;
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String contentType = properties.getContentType();
            if (contentType != null && contentType.contains("json")) {
                String encoding = properties.getContentEncoding();
                if (encoding == null) {
                    encoding = getDefaultCharset();
                }
                try {
                    Class<?> targetClass = getClassMapper().toClass(
                            message.getMessageProperties());
                    content = convertBytesToObject(message.getBody(),
                            encoding, targetClass);
                }
                catch (IOException e) {
                    throw new MessageConversionException(
                            "Failed to convert Message content", e);
                }
            }
        }
        if (content == null) {
            content = message.getBody();
        }
        return content;
    }

    private Object convertBytesToObject(byte[] body, String encoding,
                                        Class<?> clazz) throws UnsupportedEncodingException {
        String contentAsString = new String(body, encoding);
        return gson.fromJson(contentAsString, clazz);
    }

}
