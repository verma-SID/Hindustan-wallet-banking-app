package com.nextuple.walletApp.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AdminService adminService;
    @InjectMocks
    private AdminController adminController;
    private ObjectMapper objectMapper=new ObjectMapper();
    private ObjectWriter objectWriter=objectMapper.writer();

    //Id for testing
    private String id="tushartg600@gmail.com";

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(adminController).build();
    }

    //User Successfully Deleted
    @Test
    void deleteUser1() throws Exception {
        when(adminService.deleteUser(id)).thenReturn("User Successfully Deleted");
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.delete("/delete/tushartg600@gmail.com");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(202))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("User Successfully Deleted")));
    }

    //User Not Found
    @Test
    void deleteUser2() throws Exception {
        when(adminService.deleteUser(id)).thenThrow(UserNotFound.class);
        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.delete("/delete/tushartg600@gmail.com");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(404));}

}