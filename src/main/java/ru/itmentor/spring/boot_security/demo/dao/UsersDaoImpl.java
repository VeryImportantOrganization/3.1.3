package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.entities.User;
import ru.itmentor.spring.boot_security.demo.exceptions.DemoAppException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user, long id) {
        User editingUser = getUserById(id);
        editingUser.setName(user.getName());
        editingUser.setSurname(user.getSurname());
        editingUser.setDob(user.getDob());
        entityManager.merge(editingUser);
    }

    @Override
    public void removeUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new DemoAppException("User not found", HttpStatus.NOT_FOUND);
        } else {
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.name = (:name)"
                        , User.class)
                .setParameter("name", name).getSingleResult();
    }
}
