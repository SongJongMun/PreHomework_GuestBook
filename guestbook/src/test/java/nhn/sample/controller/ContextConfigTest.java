package nhn.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class ContextConfigTest {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
     
    @Test
    public void openBoardListTest() throws Exception{
        this.mockMvc.perform(
                get("/sample/openBoardList.do"))
            .andDo(print())
    		.andExpect(status().isOk())         // 요청을 보낼때의 데이터와 리턴된 결과를 한눈에 파악할 수 있는 메소드.
    		.andExpect(model().attributeExists("list"));
    }
    
    @Test
    public void insertBoardTest() throws Exception{
        this.mockMvc.perform(
                post("/sample/insertBoard.do")
                .param("TITLE", "JUnit test")
                .param("WRITER", "JUnit")
                .param("CONTENTS", "JUnit Contents")
                .param("EMAIL", "JUnit@JUnit.com")
                .param("PASSWORD", "abcdefg"))
            .andDo(print())
    		.andExpect(redirectedUrl("/sample/openBoardList.do"));
        
        this.mockMvc.perform(
                post("/sample/insertBoard.do")
                .param("TITLE", "JUnit test")
                .param("WRITER", "JUnit")
                .param("CONTENTS", "JUnit Contents")
                .param("EMAIL", "JUnit")
                .param("PASSWORD", "abcdefg"))
            .andDo(print())
    		.andExpect(status().isOk())         
    		.andExpect(forwardedUrl("/sample/boardWrite"));
    }
}
