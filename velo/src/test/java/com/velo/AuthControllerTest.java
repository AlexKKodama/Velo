package com.velo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.velo.account.domain.User;
import com.velo.account.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class AuthControllerTest {
    @Container
    @ServiceConnection
    @SuppressWarnings("unused")
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgis/postgis:18-3.6").asCompatibleSubstituteFor("postgres"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldRegisterUser() throws Exception {
        mockMvc.perform(
            post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser",
                        "email": "user@test.com",
                        "password": "password"
                    }
                """)
        )
        .andExpect(status().isCreated());

        User user = userRepository
            .findByEmail("user@test.com")
            .orElseThrow();

        assertEquals("testuser", user.getUsername());
        assertEquals("user@test.com", user.getEmail());
        assertNotEquals("password", user.getHash());
    }

    @Test
    void shouldLoginuser() throws Exception {
        mockMvc.perform(
            post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "testuser2",
                        "email": "user2@test.com",
                        "password": "password"
                    }
                """)
        )
        .andExpect(status().isCreated());

        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "email": "user2@test.com",
                        "password": "password"
                    }
                """)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").exists())
        .andExpect(jsonPath("$.accessToken").isNotEmpty())
        .andExpect(jsonPath("$.refreshToken").exists())
        .andExpect(jsonPath("$.refreshToken").isNotEmpty());

        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "email": "user2@test.com",
                        "password": "wrongpassword"
                    }
                """)
        )
        .andExpect(status().isUnauthorized());

    }

}
