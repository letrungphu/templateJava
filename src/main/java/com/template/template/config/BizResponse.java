package com.template.template.config;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BizResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private int total;
    private String emoji;
    private Map<String, Object> etc;
    private Collection data;
    private String message;
    private Collection esData;
    private int esTotal;
    private String error;
    private BizResponse.IBMessage result;

    public BizResponse() {
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public <T> Collection<T> getData() {
        if (this.data == null) {
            this.data = new ArrayList();
        }

        return this.data;
    }

    public BizResponse addList(List list) {
        if (this.data == null) {
            this.data = new ArrayList();
        }

        this.data.clear();
        this.data.addAll(list);
        this.total = list.size();
        this.emoji = "(✿◕‿◕✿)";
        return this;
    }

    public BizResponse addEtcData(String key, Object value) {
        if (this.etc == null) {
            this.etc = new HashMap();
        }

        this.etc.put(key, value);
        return this;
    }

    public BizResponse addAllEtcData(Map<String, Object> map) {
        if (this.etc == null) {
            this.etc = new HashMap();
        }

        this.etc.putAll(map);
        return this;
    }

    public BizResponse.IBMessage getResult() {
        return this.result;
    }

    public void setResult(BizResponse.IBMessage result) {
        this.result = result;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<String, Object> getEtc() {
        return this.etc;
    }

    public void setEtc(Map<String, Object> etc) {
        this.etc = etc;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEsTotal() {
        return this.esTotal;
    }

    public void setEsTotal(int esTotal) {
        this.esTotal = esTotal;
    }

    class IBMessage {
        private String code;
        private String message;

        IBMessage() {
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

