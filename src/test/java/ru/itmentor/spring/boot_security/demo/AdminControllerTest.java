package ru.itmentor.spring.boot_security.demo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.itmentor.spring.boot_security.demo.dao.UsersDao;
import ru.itmentor.spring.boot_security.demo.models.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersDao usersDao;

    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void getUserById() {
        long id = 1L;
        User user = new User();
        user.setId(id);
        Mockito.when(usersDao.getUserById(id)).thenReturn(user);

        mockMvc.perform(get("/api/admin/{id}", id))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void getWatermelonException() {
        long notExistedId = 100500L;
        User user = new User();
        user.setId(notExistedId);

        Mockito.when(usersDao.getUserById(notExistedId)).thenReturn(user);

        mockMvc.perform(get("/api/admin/{id}", "abc"))
                .andExpect(status().isBadRequest());
    }
}