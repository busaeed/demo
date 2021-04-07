package com.alt6wer.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private int id;
    
    @Column(nullable = false, length=50)
    private String name;
    
    @Column(nullable = true, name = "`order`")
    private int order;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Forum> forums;
    
    @SuppressWarnings("unused")
    protected Category() {}

    public Category(String name, int order) {
        super();
        this.name = name;
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", order=" + order + ", forums=" + forums + "]";
    }

}
