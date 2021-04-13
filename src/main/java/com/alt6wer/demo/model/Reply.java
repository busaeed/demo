package com.alt6wer.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Reply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_sq")
    private int id;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @Column(length = 100)
    private String title;
    
    @Column(nullable = false, length = 10000)
    private String content;
    
    @Column(nullable = false)
    @ColumnDefault("1")
    private boolean unreadPost = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="topic_id")
    private Topic topic;

    public Reply(String title, String content, Member member, Topic topic) {
        super();
        this.title = title;
        this.content = content;
        this.member = member;
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Reply [id=" + id + ", creationDateTime=" + createdAt + ", updateDateTime=" + updatedAt
                + ", title=" + title + ", content=" + content + ", unreadPost=" + unreadPost + ", member=" + member.getId()
                + ", topic=" + topic.getId() + "]";
    }
    
}
