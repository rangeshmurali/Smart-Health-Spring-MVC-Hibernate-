package com.me.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "MESSAGEID")
	private int messageId;
	
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "FROMUSER")
	private String fromUser;
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "MESSAGEDATE")
	private String messageDate;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	
	
	
	

}
