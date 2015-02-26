package com.bsuir.wtlab3.source.entity;


public class Note {
	private String topic;
	private String createDate;
	private String eMail;
	private String text;

	
	public Note(String topic, String eMail, String date, String text) {
		this.topic = topic;
		this.eMail = eMail;
		this.createDate = date;
		this.text = text;	
	}
	
	public String getTopic() {
		return topic;
	}

	public String getCreateDate() {
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
	
	@Override
	public String toString(){
		return this.topic+"\ndate:\t"+this.createDate+"\ne-mail:\t"+this.eMail+"\n"+this.text+"\n--";
	}
	
}
