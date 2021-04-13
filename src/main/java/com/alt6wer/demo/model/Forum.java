package com.alt6wer.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    public Forum(String name, String description, String icon, int order) {
        super();
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.ordering = order;
    }

    @Override
    public String toString() {
        return "Forum [id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", ordering="
                + ordering + ", category=" + category.getId() + "]";
    }

}
