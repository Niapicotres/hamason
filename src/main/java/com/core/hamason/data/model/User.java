package com.core.hamason.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "USERS")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	
	private String email;
	private String password;
	private String fullname;
	private LocalDate expiryDateAccount;
	private LocalDate expiryDateCredentials;
	private Boolean enabled;
	private Boolean lockedAccount;

	@ManyToMany
	@JoinTable(name = "USERS_HAS_ROLES")
	private Set<Role> roleSet;

	@Override
	public int hashCode() {
		return Objects.hash(email, enabled, expiryDateAccount, expiryDateCredentials, fullname, lockedAccount, password,
				roleSet, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(username, other.username);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Declare new list of granted authorities
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
		// Get roles from authenticated user
		this.getRoleSet().stream()
			.map(x -> x.getRolename())
			.forEach(x -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(x)));
		// Show granted authorities
		log.info("Roles from " + this.getUsername() + ": " +
			simpleGrantedAuthorityList.stream()	
				.map(x -> x.getAuthority())
				.collect(Collectors.joining("|", "{", "}"))
				);
		//
		return simpleGrantedAuthorityList;
	}

	@Override
	public boolean isAccountNonExpired() {
		//return UserDetails.super.isAccountNonExpired();
		return this.getExpiryDateAccount().isAfter(LocalDate.now()); 
	}

	@Override
	public boolean isAccountNonLocked() {
		//return UserDetails.super.isAccountNonLocked();
		return !this.getLockedAccount();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//return UserDetails.super.isCredentialsNonExpired();
		return this.getExpiryDateCredentials().isAfter(LocalDate.now());
	}

	@Override
	public boolean isEnabled() {
		//return UserDetails.super.isEnabled();
		return this.getEnabled();
	}

	
	
	
}
