package com.xin.funnyjokes.models;

import java.util.Date;

import com.xin.funnyjokes.utils.FunnyJokesUtils;

public class BaseObject {
   	protected String id;
   	protected String etag;

	protected Date updated;
   	protected Date created;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public void setUpdated(String updated) {
		this.updated = FunnyJokesUtils.getDateFromString(updated);
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public void setCreated(String created) {
		this.created = FunnyJokesUtils.getDateFromString(created);
	}
}
