package com.gyy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.domain.ResultInfo;


public class JsonUtil {
    /**
     * 返回转换后的json字符串
     * @param info
     * @return
     * @throws JsonProcessingException
     */
    public static String toJson(ResultInfo info) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(info);
    }
}
