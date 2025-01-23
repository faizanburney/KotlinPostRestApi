package com.hatchways.blogposts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hatchways.blogposts.BlogPostsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = BlogPostsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Sql(
    scripts = {"/cleanup.sql"},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthControllerTest {

  @Autowired MockMvc mockMvc;

  @Test
  public void testLoginSuccess() throws Exception {
    mockMvc
        .perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"thomas\",\"password\":\"123456\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").exists())
        .andExpect(jsonPath("$.username").value("thomas"))
        .andExpect(jsonPath("$.id").value(1))
        .andReturn();
  }
}
