package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RolesDao {
    void addRole(Role role);
    void editRole(Role role);
    void removeRole(long id);
    Role getRoleById(long id);
    List<Role> getAllRoles();
}
