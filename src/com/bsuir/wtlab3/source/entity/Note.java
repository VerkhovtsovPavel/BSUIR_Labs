package com.bsuir.wtlab3.source.entity;

import java.util.Date;

public class Note {
	private String topic;
	private Date createDate;
	private String eMail;
	private String text;
	
	public Note(String topic, String eMail, Date date, String text) {
		this.topic = topic;
		this.eMail = eMail;
		this.createDate = date;
		this.text = text;
		
	}
	public String getTopic() {
		return topic;
	}

	public Date getCreateDate() {
		return createDate;
	}
	
	public String getEMail() {
		return eMail;
	}

	public String getText() {
		return text;
	}

	public String getCompressedFormat() {
		return String.format("%s:%s:%s:%s",this.topic,this.eMail,this.createDate,this.text);
	}
}
