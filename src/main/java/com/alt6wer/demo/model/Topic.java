package com.alt6wer.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
    private int id;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(nullable = false, length = 10000)
    private String content;
    
    @Column(nullable = false)
    @ColumnDefault("1")
    private boolean unreadPost = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    private Forum forum;
    
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Reply> replies;

    public Topic(String title, String content, Member member, Forum forum) {
        super();
        this.title = title;
        this.content = content;
        this.member = member;
        this.forum = forum;
    }

    @Override
    public String toString() {
        return "Topic [id=" + id + ", creationDateTime=" + createdAt + ", updateDateTime=" + updatedAt
                + ", title=" + title + ", content=" + content + ", unreadPost=" + unreadPost + ", member=" + member.getId()
                + ", forum=" + forum.getId() + ", replies=" + replies + "]";
    }

}
