package com.idexcel.adminservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDate;
import java.util.Arrays;

import com.idexcel.adminservice.controller.AdminServiceRestController;
import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.Admin;
import com.idexcel.adminservice.service.AdminService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminServiceRestController.class)
public class ControllerTesting {
	
	
	@MockBean
	private AdminService adminService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAdminsTest() throws Exception {
		
		
		when(adminService.findAllAdmins()).thenReturn(Arrays.asList(new Admin("ABC", 
				new Address("wordsworth ct","herndon","VA","20171","USA"),
				"active","yagnik","Bhatt",LocalDate.parse("2019-07-09"),LocalDate.parse("2019-07-09")),
								new Admin("Karan", 
						new Address("Athwa lines","Surat","Gujarat","224541","India"),
						"new","Karan","Karan",LocalDate.parse("2019-07-09"),LocalDate.parse("2019-07-09"))));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders");
		
		
		String jsonContent = "[" + 
				"{" + 
				"\"name\": \"ABC\"," + 
				"\"address\": {" + 
				"\"street\": \"wordsworth ct\"," + 
				"\"city\": \"herndon\"," + 
				"\"state\": \"VA\"," + 
				"\"zipCode\": \"20171\"," + 
				"\"country\": \"USA\"" + 
				"}," + 
				"\"status\": \"active\"," + 
				"\"createdBy\": \"yagnik\"," + 
				"\"updatedBy\": \"Bhatt\"," + 
				"\"createdDate\": \"2019-07-09\"," + 
				"\"updatedDate\": \"2019-07-09\"" + 
				"}," + 
				"{" + 
				"\"name\": \"Karan\"," + 
				"\"address\": {" + 
				"\"street\": \"Athwa lines\"," + 
				"\"city\": \"Surat\"," + 
				"\"state\": \"Gujarat\"," + 
				"\"zipCode\": \"224541\"," + 
				"\"country\": \"India\"" + 
				"}," + 
				"\"status\": \"new\"," + 
				"\"createdBy\": \"Karan\"," + 
				"\"updatedBy\": \"Karan\"," + 
				"\"createdDate\": \"2019-07-09\"," + 
				"\"updatedDate\": \"2019-07-09\"" + 
				"}" + 
				"]" + 
				"";
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(jsonContent)).andReturn();
		
		
	}
	
	@Test
	public void postAdminTest() throws Exception {
		
		Admin admin = new Admin("XYZ", new Address("Laurel Ave", "Hayward", "CA", "94541", "USA"),
				"active", "Yagnik", "Yagnik", LocalDate.parse("2019-07-09"),  LocalDate.parse("2019-07-09"));
		
		when(adminService.saveAdmin(admin)).thenReturn("2565");
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders").contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		
		//Re
	}

	@Test
	public void getAdminsByIdTest() throws Exception {
		
		Admin admin = new Admin("ABC", 
				new Address("wordsworth ct","herndon","VA","20171","USA"),
				"active","yagnik","bhatt",LocalDate.parse("2019-07-09"),LocalDate.parse("2019-07-09"));
		admin.setId("123");
		
		when(adminService.findById("123")).thenReturn(admin);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/lenders/123");
		
		
		String jsonContent = "{" + 
				"\"id\": \"123\"," + 
				"\"name\": \"ABC\"," + 
				"\"address\": {" + 
				"\"street\": \"wordsworth ct\"," + 
				"\"city\": \"herndon\"," + 
				"\"state\": \"VA\"," + 
				"\"zipCode\": \"20171\"," + 
				"\"country\": \"USA\"" + 
				"}," + 
				"\"status\": \"active\"," + 
				"\"createdBy\": \"yagnik\"," + 
				"\"updatedBy\": \"bhatt\"," + 
				"\"createdDate\": \"2019-07-09\"," + 
				"\"updatedDate\": \"2019-07-09\"" + 
				"}";
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(jsonContent)).andReturn();
		
		
	}
	
	@Test
	public void deleteAdminTest() throws Exception {
		
		String id = "123";
		adminService.deleteById(id);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/lenders/123");
		
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void patchAdminById() throws Exception{
		String _id = "12345678";
		Admin admin =  new Admin("ABC", 
				new Address("wordsworth ct","herndon","VA","20171","USA"),
				"active","yagnik","bhatt",LocalDate.parse("2019-07-09"),LocalDate.parse("2019-07-09"));
		admin.setId("12345678");
		
		adminService.updateAdmin(admin);
		
		
		RequestBuilder request = MockMvcRequestBuilders.patch("/api/Lenders/12345678/status");
				
		mockMvc.perform(request)
			   .andExpect(status().isNotFound())
			   .andReturn();

		
	}
}
