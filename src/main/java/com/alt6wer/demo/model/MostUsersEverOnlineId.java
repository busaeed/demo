package com.alt6wer.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class MostUsersEverOnlineId implements Serializable {
	
	private LocalDateTime dateTime;
	
	private int numberOfUsers;

	public MostUsersEverOnlineId(LocalDateTime dateTime, int numberOfUsers) {
		super();
		this.dateTime = dateTime;
		this.numberOfUsers = numberOfUsers;
	}

}
