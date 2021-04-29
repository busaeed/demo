package com.alt6wer.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Entity
@Immutable
@Subselect("select * from last_post")
@Table(name = "last_post")
@Setter
@Getter
public class LastPost implements Serializable {
	
	private int topicId;
	private String topicTitle;
	private LocalDateTime lastPostDateTime;
	private int memberId;
	private String username;
	private Integer replyId;
	private int pageNumber;
	private String shortTopicTitle;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
	@ForeignKey(name= "none")
	@Id
	private Forum forum;

	@Override
	public String toString() {
		return "LastPost [topicId=" + topicId + ", topicTitle=" + topicTitle + ", lastPostDateTime=" + lastPostDateTime
				+ ", memberId=" + memberId + ", username=" + username + ", replyId=" + replyId + ", pageNumber="
				+ pageNumber + ", shortTopicTitle=" + shortTopicTitle + "]";
	}
	
	/*
SELECT * from (
    SELECT 
        topic.id topicId,
        topic.title topicTitle,
    	member.id memberId,
    	member.username username,
        topic.createdAt lastPostDateTime,
        topic.forum_id forum_id,
        null replyId,
        1 pageNumber,
    	IF(LENGTH(topic.title)>25, CONCAT(SUBSTRING(topic.title, 1, 22), '...'), topic.title) shortTopicTitle
    FROM topic
    LEFT JOIN member ON (topic.member_id = member.id)
    UNION
    SELECT
        reply1.topic_id,
        topic1.title,
        member.id,
    	member.username,
        reply1.createdAt,
        topic1.forum_id,
        reply1.id,
        CEIL((SELECT COUNT(*) FROM reply WHERE topic_id = reply1.topic_id)/10),
    	IF(LENGTH(topic1.title)>25, CONCAT(SUBSTRING(topic1.title, 1, 22), '...'), topic1.title) shortTopicTitle
    FROM reply reply1
    LEFT JOIN topic topic1 ON (reply1.topic_id = topic1.id)
    LEFT JOIN member ON (reply1.member_id = member.id)
    ORDER BY lastPostDateTime DESC
) theTable
GROUP BY theTable.forum_id
	*/
	
}
