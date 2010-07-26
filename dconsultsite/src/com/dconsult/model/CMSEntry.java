package com.dconsult.model;

import java.util.Date;

public class CMSEntry {
	private Date entryCreated;
	private String title;
	private String body;
	public Date getEntryCreated() {
		return entryCreated;
	}
	public void setEntryCreated(Date entryCreated) {
		this.entryCreated = entryCreated;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
