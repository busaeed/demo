package com.alt6wer.demo.model;

import java.io.Serializable;
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
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Role implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private int id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String roleName;
    
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Member> members;
        
    public Role(String roleName) {
        this.roleName = roleName;
    }

}
