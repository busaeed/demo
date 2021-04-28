package com.alt6wer.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "most_users_ever_online")
@Setter
@Getter
@NoArgsConstructor
@ToString
@IdClass(MostUsersEverOnlineId.class)
public class MostUsersEverOnline {
	
	@Id
	private LocalDateTime dateTime;
	
	@Id
	private int numberOfUsers;

	public MostUsersEverOnline(LocalDateTime dateTime, int numberOfUsers) {
		super();
		this.dateTime = dateTime;
		this.numberOfUsers = numberOfUsers;
	}

}
