package com.core.hamason.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "USERS")  // La tabla para toda la jerarquía
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Estrategia de herencia
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING) // Diferencia User de Customer
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    // Implementación de los métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.roleSet.stream().map(role -> authorities.add(new SimpleGrantedAuthority(role.getRolename())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.expiryDateAccount.isAfter(LocalDate.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.lockedAccount;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.expiryDateCredentials.isAfter(LocalDate.now());
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

	
}