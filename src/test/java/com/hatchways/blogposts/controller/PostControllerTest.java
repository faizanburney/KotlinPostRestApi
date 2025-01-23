package com.hatchways.blogposts.controller;

import static com.hatchways.blogposts.config.SecurityConfig.AUTHENTICATION_HEADER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hatchways.blogposts.BlogPostsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = BlogPostsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@Import(ControllerTestConfiguration.class)
@Sql(
    scripts = {"/data.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(
    scripts = {"/cleanup.sql"},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PostControllerTest {
  @Autowired MockMvc mockMvc;

  @Autowired TestUtil testUtil;

  @Value("classpath:posts.json")
  Resource postsJsonFile;

  @Test
  public void testGetPostsForUserSuccess() throws Exception {
    String postsData = new String(postsJsonFile.getInputStream().readAllBytes());
    String token = testUtil.getUserToken("thomas");
    mockMvc
        .perform(get("/api/posts?authorIds=1").header(AUTHENTICATION_HEADER, token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(postsData))
        .andReturn();
  }

}