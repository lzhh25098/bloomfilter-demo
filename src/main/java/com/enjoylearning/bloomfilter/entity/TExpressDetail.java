package com.enjoylearning.bloomfilter.entity;

import java.io.Serializable;

public class TExpressDetail  implements Serializable{
    private String id;

    private String expressid;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}