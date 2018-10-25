package com.example.rmmservices.controller;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceCommandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Sql({"/schema-postgresql.sql", "/data-postgresql.sql"})
    public void testAShouldRegisterNewAccountDevice() throws Exception {
        mvc.perform(post("/customer/1/devices/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"systemName\": \"MACSERVER01\", \"deviceTypeId\": 3}"))
                .andExpect(status().isOk());

    }

    @Test
    @Sql({"/schema-postgresql.sql", "/data-postgresql.sql"})
    public void testBShouldUpdateAccountDevice() throws Exception {
        mvc.perform(post("/customer/1/devices/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"systemName\": \"CHANGED_WSERVER01\", \"deviceTypeId\": 1}"))
                .andExpect(status().isOk());

    }

    @Test
    @Sql({"/schema-postgresql.sql", "/data-postgresql.sql"})
    public void testCShouldDeleteAccountDevice() throws Exception {
        mvc.perform(post("/customer/1/devices/2/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
