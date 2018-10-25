package com.example.rmmservices.controller;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceCommandControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "test")
    @Test
    @Sql({"/schema-postgresql.sql", "/data-postgresql.sql"})
    public void testAShouldRegisterNewAccountService() throws Exception {
        mvc.perform(post("/customer/1/services/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"serviceId\": 4, \"customerId\": 1}"))
                .andExpect(status().isOk());

    }

    @WithMockUser(value = "test")
    @Test
    @Sql({"/schema-postgresql.sql", "/data-postgresql.sql"})
    public void testBShouldRespondeErrorWhenAccountServiceExists() throws Exception {
        mvc.perform(post("/customer/1/services/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"serviceId\": 1, \"customerId\": 1}"))
                .andExpect(status().isOk());

    }

    @WithMockUser(value = "test")
    @Test
    @Sql({"/schema-postgresql.sql", "/data-postgresql.sql"})
    public void testCShouldDeleteAccountDevice() throws Exception {
        mvc.perform(post("/customer/1/services/3/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
