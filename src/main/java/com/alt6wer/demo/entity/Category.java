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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="categories")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public Category(String name, int order) {
        super();
        this.name = name;
        this.order = order;
    }

}
