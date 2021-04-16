package com.alt6wer.demo.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.alt6wer.demo.validator.IdenticalPasswords;
import com.alt6wer.demo.validator.UniqueEmail;
import com.alt6wer.demo.validator.UniqueUsername;
import com.alt6wer.demo.validator.ValidEmail;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@IdenticalPasswords
public class Member implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private int id;
    
    @Column(nullable = false, unique = true, length = 50)
    @Size(min = 3, max = 50)
    @UniqueUsername
    private String username;
    
    @Column(nullable = false, unique = true, length = 100)
    @UniqueEmail
    @ValidEmail
    private String email;
    
    @Transient
    @Size(min = 6, max = 60)
    private String password;
    
    @Transient
    @NotBlank
    private String confirmedPassword;
    
    @Column(name = "password", nullable = false, length = 60)
    private String hashedPassword;
    
    @Column(nullable = false)
    @ColumnDefault("0")
    private boolean active;
    
    @Column(length = 64)
    private String verificationCode;
    
    @Column(nullable = false)
    private LocalDateTime registeredAt;
    
    private Long lastActivity = null;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Topic> topics;
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Reply> replies;

    public Member(String username, String email, String password, Role role) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", confirmedPassword=" + confirmedPassword + ", hashedPassword=" + hashedPassword + ", active="
				+ active + ", verificationCode=" + verificationCode + ", registeredAt=" + registeredAt
				+ ", lastActivity=" + lastActivity + ", role=" + role.getRoleName() + "]";
	}
    
}
