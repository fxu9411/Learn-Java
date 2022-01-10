package com.example.tacocloud;

import com.example.tacocloud.Data.IngredientRepository;
import com.example.tacocloud.Data.OrderRepository;
import com.example.tacocloud.Data.TacoRepository;
import com.example.tacocloud.Data.UserRepository;
import com.example.tacocloud.Service.OrderAdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Note: Most of these mocks are here to avoid autowiring issues. They aren't
    //       actually used in the course of the home page test, so their behavior
    //       isn't important. They just need to exist so autowiring can take place.

    @MockBean
    private OrderAdminService adminService;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private TacoRepository designRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")));
    }

}
