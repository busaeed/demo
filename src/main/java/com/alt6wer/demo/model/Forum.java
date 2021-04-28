package com.alt6wer.demo.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Forum {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forum_seq")
    private int id;
    
    @Column(nullable = false, length=50)
    private String name;
    
    @Column(length=200)
    private String description;
    
    @Column(length=200)
    private String icon;
    
    private int ordering;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
    
    @OneToOne(mappedBy = "forum", fetch = FetchType.EAGER)
    private LastPost lastPost;
    
    @OneToMany(mappedBy = "forum", fetch = FetchType.LAZY)
    private List<Topic> topics;
    
    @Formula("(SELECT COUNT(t.id) FROM Topic t where t.forum_id = id)")
    private int numberOfTopics;
    
    @Formula("(SELECT COUNT(r.id) FROM Reply r where exists (select 1 from Topic t where r.topic_id = t.id and t.forum_id = id))")
    private int numberOfReplies;

    public Forum(String name, String description, String icon, int order, Category category) {
        super();
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.ordering = order;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Forum [id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", ordering="
                + ordering + ", category=" + category.getId() + "]";
    }

}
