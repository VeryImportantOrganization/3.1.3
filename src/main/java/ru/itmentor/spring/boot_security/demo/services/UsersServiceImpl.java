package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.UsersDao;
import ru.itmentor.spring.boot_security.demo.entities.Role;
import ru.itmentor.spring.boot_security.demo.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersDao usersDao;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public void addUser(User user) {
        usersDao.addUser(user);
    }

    @Override
    public void editUser(User user, long id) {
        usersDao.editUser(user, id);
    }

    @Override
    public void removeUser(long id) {
        usersDao.removeUser(id);
    }

    @Override
    public User getUserById(long id) {
        return usersDao.getUserById(id);
    }

    @Override
    public List<User> usersList() {
        return usersDao.getAllUsers();
    }

    @Override
    public User getUserByName(String name) {
        return usersDao.getUserByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = getUserByName(name);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь " + name + " не найден"));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassw(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
