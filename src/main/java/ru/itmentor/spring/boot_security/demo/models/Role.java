package ru.itmentor.spring.boot_security.demo.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String roleName;

    @Column
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return null;
    }
}
