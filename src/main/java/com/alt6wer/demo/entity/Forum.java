package com.alt6wer.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="forums")
public class Forum {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forum_seq")
    private int id;
    
    @Column(nullable = false, length=50)
    private String name;
    
    @Column(nullable = true, length=200)
    private String description;
    
    @Column(nullable = true, length=200)
    private String icon;
    
    @Column(nullable = true, name = "`order`")
    private int order;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
    
    @SuppressWarnings("unused")
    protected Forum() {}

    public Forum(String name, String description, String icon, int order, Category category) {
        super();
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.order = order;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Forum [id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", order="
                + order + "]";
    }

}
