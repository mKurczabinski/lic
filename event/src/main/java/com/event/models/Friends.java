package com.event.models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Friends {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	
	private int friendId;

	private boolean sendInvite;
	
	private boolean acceptInvite;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public boolean isSendInvite() {
		return sendInvite;
	}

	public void setSendInvite(boolean sendInvite) {
		this.sendInvite = sendInvite;
	}

	public boolean isAcceptInvite() {
		return acceptInvite;
	}

	public void setAcceptInvite(boolean acceptInvite) {
		this.acceptInvite = acceptInvite;
	}
	
	

}
