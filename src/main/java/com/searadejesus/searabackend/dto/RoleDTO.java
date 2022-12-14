package com.searadejesus.searabackend.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.searadejesus.searabackend.entities.Role;

public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoleDTO)) {
            return false;
        }
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
