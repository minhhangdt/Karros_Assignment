package com.example.demo.test.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(value = {
		DbUnitTestExecutionListener.class }, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class GpsIntegrationTest {
	ObjectMapper mapper = new ObjectMapper();

	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Value("${spring.data.rest.basePath}")
	private String apiVersion;

	@Before
	public void setUp() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testLatestList() throws Exception {
		mockMvc.perform(get(apiVersion + "/gps/get-lastest-track" + "?top=20")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(20)));
	}

	@Test
	public void testGetDetail() throws Exception {
		mockMvc.perform(get(apiVersion + "/gps/view-detail/100")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testUploadGpxFile() throws Exception {
		String fileName = "sample.gpx";
		InputStream file = new ClassPathResource("sample/sample.gpx").getInputStream();
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileName, "multipart/form-data", file);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload(apiVersion + "/gps/upload")
				.file(mockMultipartFile);
		this.mockMvc.perform(builder).andExpect(status().is2xxSuccessful());
	}

}
