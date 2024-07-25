package com.github.resalner.javapractice.model;

import java.util.Set;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Persistable<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private Role role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private UserInfo info;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Order> orders;

	@Transient
	private boolean isNew = true;

	@Override
	public boolean isNew() {
		return this.isNew || this.id == null;
	}
}
