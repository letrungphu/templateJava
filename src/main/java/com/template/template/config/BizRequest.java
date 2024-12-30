package com.template.template.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BizRequest implements Serializable {
    private int cmd;
    private HashMap<String, Object> data;

    public BizRequest() {
    }

    public int getCommand() {
        return this.cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public HashMap<String, Object> getData() {
        return this.data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public Object get(String key) {
        return this.data.get(key);
    }

    public <T> T get(String key, Class<T> clz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T t = mapper.convertValue(this.data.get(key), clz);
        return t;
    }

    public <T> List<T> getList(String key, Class<T> clz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<T> lst = (List)mapper.convertValue(this.data.get(key), mapper.getTypeFactory().constructCollectionType(List.class, clz));
        return lst;
    }

}