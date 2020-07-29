package com.boss.trainee.rbac.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author: Jianbinbing
 * @Date: 2020/7/28 9:52
 */
@Slf4j
public class JacksonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String BeanToJson(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    public static <T> T JsonToBean(String jsonStr, Class<T> objClass)
            throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(jsonStr, objClass);
    }

}
