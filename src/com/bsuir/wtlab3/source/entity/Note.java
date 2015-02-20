package com.bsuir.wtlab3.source.entity;

import java.util.Date;

public class Note {
	private String topic;
	private Date createDate;
	private String eMail;
	private String text;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCompressedFormat() {
		return String.format("%s:%s:%s:%s",this.topic,this.eMail,this.createDate,this.text);
	}
}
