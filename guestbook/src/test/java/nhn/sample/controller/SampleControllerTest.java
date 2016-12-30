package nhn.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import nhn.sample.dao.SampleDAO;
import nhn.sample.service.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
 
@RunWith(MockitoJUnitRunner.class)
public class SampleControllerTest {
     
    // @Mock 을 이용하여 실제로 DB와 연동하지 않으면서 UserDao를 호출할 수 있는 상태가 된다.
    @Mock
    private SampleDAO userDao;
    
    @Mock
    private SampleServiceImpl service;
     
    // 테스트 대상 코드에서 사용하는 UserDao, 즉, 의존관계가 있는 클래스를 서로 엮어준다.
    @InjectMocks
    private SampleController userController;
     
    private MockMvc mockMvc;
 
    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(userController).build();
    }
     
    @Test
    public void test() throws Exception{
        this.mockMvc.perform(
                get("/sample/openBoardList.do"))
            .andDo(print())
    		.andExpect(status().isOk())         // 요청을 보낼때의 데이터와 리턴된 결과를 한눈에 파악할 수 있는 메소드.
    		.andExpect(model().attributeExists("list"));
    }
}