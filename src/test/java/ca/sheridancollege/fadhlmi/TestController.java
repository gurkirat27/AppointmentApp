package ca.sheridancollege.sin10461;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import ca.sheridancollege.fadhlmi.bean.Appointment;
import ca.sheridancollege.fadhlmi.database.DatabaseAccess;


@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private DatabaseAccess databaseAccess;
	
	@Test
	public void testLoadingIndex() throws Exception {
		this.mockMvc.perform(post("/")).andExpect(status().isOk()).andExpect(view().name("index.html"));
	}
	
	@Test
	public void testSaveAppointment() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("firstName","a");
		this.mockMvc.perform(post("/save").params(requestParams)).andExpect(status().isOk())
				.andExpect(view().name("index.html"));
	}

	@Test
	public void testSaveAppointmentWithDatabase() {
		Appointment aa = new Appointment();
		int originalsize = databaseAccess.getApps().size();

		databaseAccess.insertApp(aa);
		int foundsize = databaseAccess.getApps().size();

		// test
		assertThat(foundsize).isEqualTo(originalsize + 1);
	}
	
	
	@Test
	public void testEditWithPathVariables() throws Exception {
		this.mockMvc.perform(get("/edit/{id}", 1)).andExpect(status().isOk())
				.andExpect(view().name("edit_form.html"));
	}
	
	@Test
	public void testDelWithPathVariables() throws Exception {
		this.mockMvc.perform(get("/delete/{id}", 1)).andExpect(status().isOk())
				.andExpect(view().name("index.html"));
	}
}
