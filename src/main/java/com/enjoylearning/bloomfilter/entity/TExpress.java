package com.enjoylearning.bloomfilter.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TExpress implements Serializable{
    private String id;

    private String sender;

    private String adress;

    private Date createtime;
    
    private List<TExpressDetail> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public List<TExpressDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TExpressDetail> details) {
		this.details = details;
	}
    
    
}