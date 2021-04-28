package com.alt6wer.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "spring_session")
@NoArgsConstructor
@Getter
@Setter
public class Session implements Serializable {
	
	@Id
	@Column(name = "PRIMARY_ID")
	private String primaryId;
	
	@Column(name = "SESSION_ID")
	private String sessionId;
	
	@Column(name = "current_page")
	private String currentPage;
	
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;
	
	@Column(name = "LAST_ACCESS_TIME")
	private long lastAccessTime;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
	private Member member;
}
