package com.github.resalner.javapractice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "users_info")
@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
 
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private boolean gender;
    
    @Column(name = "email")
    private String email;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Transient
	private boolean isNew = true;

	@Override
	public boolean isNew() {
		return this.isNew || this.id == null;
	}
}
