package com.comeon.ernesto.game.util;

import com.comeon.ernesto.game.model.api.GeneralResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HelperUtil {

    public static GeneralResponse buildGeneralResponse(){
        return new GeneralResponse().code("200");
    }

    public static ObjectMapper getObjectMapper()
    {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        objectMapper.setDateFormat(df);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
