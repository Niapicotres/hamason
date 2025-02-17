package com.core.hamason.data.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ROLES")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String rolename;
	
	@ManyToMany
	private Set<User> userSet;
	
	public Role(String rolename) {
        this.rolename = rolename;
    }
	
}
