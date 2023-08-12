package com.nextuple.walletApp.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nextuple.walletApp.backend.entity.request.LoginDetails;
import com.nextuple.walletApp.backend.entity.request.UserInfo;
import com.nextuple.walletApp.backend.exceptions.UserAlreadyExists;
import com.nextuple.walletApp.backend.exceptions.UserNotFound;
import com.nextuple.walletApp.backend.exceptions.WrongPassword;
import com.nextuple.walletApp.backend.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private AuthenticationController authenticationController;
    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    //Signup Details
    private UserInfo userInfo=new UserInfo("tushartg600@gmail.com","Tushar","Gupta", "12345678");
    //Login Details
    private LoginDetails loginDetails=new LoginDetails("tushartg600@gmail.com","12345678");

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    //User Registered Successfully
    @Test
    void addUser1() throws Exception {
        when(authenticationService.addUser(userInfo)).thenReturn("User Registered Successfully");
        String content=objectWriter.writeValueAsString(userInfo);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("User Registered Successfully")));
    }

    //User Already Exists
    @Test
    void addUser2() throws Exception {
        when(authenticationService.addUser(userInfo)).thenThrow(UserAlreadyExists.class);
        String content=objectWriter.writeValueAsString(userInfo);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(406));
    }

    //Successfully Logged In
    @Test
    void findUser1() throws Exception {
        when(authenticationService.findUser(loginDetails)).thenReturn("Successfully Logged In");
        String content=objectWriter.writeValueAsString(loginDetails);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$",is("Successfully Logged In")));
    }

    //Wrong Password
    @Test
    void findUser2() throws Exception {
        LoginDetails loginDetails=new LoginDetails("tushartg600@gmail.com","12345678");
        when(authenticationService.findUser(loginDetails)).thenThrow(WrongPassword.class);
        String content=objectWriter.writeValueAsString(loginDetails);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().is(401));
    }

    //User Not Found
    @Test
    void findUser3() throws Exception {
        LoginDetails loginDetails=new LoginDetails("tushartg600@gmail.com","12345678");
        when(authenticationService.findUser(loginDetails)).thenThrow(UserNotFound.class);
        String content=objectWriter.writeValueAsString(loginDetails);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().is(404));
    }
}