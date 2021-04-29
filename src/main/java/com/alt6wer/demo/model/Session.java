package com.alt6wer.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRIMARY_ID")
	private String primaryId;
	
	@Column(name = "SESSION_ID")
	private String sessionId;
	
	@Column(name = "LAST_ACCESS_TIME")
	private long lastAccessTime;
	
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;
	
	@Column(name = "current_page", length = 100)
	private String currentPage;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
	private Member member;
}
