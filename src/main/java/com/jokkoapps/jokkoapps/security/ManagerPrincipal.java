package com.jokkoapps.jokkoapps.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jokkoapps.jokkoapps.model.Manager;

public class ManagerPrincipal implements UserDetails{
	
    private Long id;

    private String firstname;
    
    private String lastname;

    private String username;
    
    private String phone;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;
    
    public ManagerPrincipal(Long id, String fisrtname, String lasttname, String username, String email, String password, String phone) {
        this.id = id;
        this.firstname = fisrtname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static ManagerPrincipal create(Manager manager) {

        return new ManagerPrincipal(
        		manager.getId(),
        		manager.getFirstname(),
        		manager.getLastname(),
        		manager.getUsername(),
        		manager.getEmail(),
        		manager.getPassword(),
        		manager.getPhone()
        );
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerPrincipal that = (ManagerPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
