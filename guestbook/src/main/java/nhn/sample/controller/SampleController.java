package nhn.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nhn.sample.service.SampleService;

import nhn.common.common.CommandMap;

/**
 * 
 * @author skuug
 * Annotation Controller : 스프링 프레임워크에 현재 클래스가 컨트롤러 라는 것을 명시함
 * 컨트롤러(Controller)는 웹 클라이언트에서 들어온 요청을 해당 비지니스 로직을 호출하고, 수행결과와 함께 응답을 해주는 Dispatcher 역할을 한다. 
 * 클래스의 선언부에 @Controller 어노테이션(Annotation)을 이용하여, Controller 객체임을 선언한다. 
 */

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * Service 영역의 접근을 위한 선언
	 * SampeServiceImpl을 생성하고 그 Service를 "sampleService"라는 이름으로 선언을 하였는데, 이를 사용하기 위한 선언.
	 * @Resource어노테이션을 통해서 필요한 빈(bean)을 수동으로 등록하는 것. 
	 * 수동으로 등록할 빈의 이름이 "sampleService"이고, 이는 @Service("sampleService")라고 선언했을 때의 그 이름인것을 확인
	 */
	@Resource(name = "sampleService")
	private SampleService sampleService;

	
	public void setSampleService(SampleService sampleService){
		this.sampleService = sampleService;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return mv(ModelAndView Class Instance)
	 * @throws Exception
	 * 
	 * Annotation Requestmapping : DisptacherServlet은  해당 어노테이션을 기준으로 어떤 컨트롤러의 메소드가 호출되어야 할지 결정
	 * value="/sample/openSampleList.do"는 프로젝트가 실행될 주소를 의미한다.
	 */
	@RequestMapping(value="/sample/openSampleList.do")
    public ModelAndView openSampleList(Map<String,Object> commandMap) throws Exception{
        //생성자 인자 : 해당 컨트롤러가 실행되고 나서 보여줄 View를 설정
		ModelAndView mv = new ModelAndView("");
        log.debug("인터셉터 테스트");
         
        return mv;
    }
	
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * @RequestMapping은 요청 URL을 의미, /sample/openBoardList.do 라는 주소를 호출하게 되면, 이 주소는 @RequestMapping 어노테이션과 매핑되어, 해당 메서드가 실행된다.
	 * 정상적인 호출시 List 객체를 포함한 ModelAndView 객체를 반환한다.
	 * 
	 */
	@RequestMapping(value = "/sample/openBoardList.do")
	public ModelAndView openBoardList(Map<String, Object> commandMap) throws Exception {
		//화면에 보여줄 jsp파일을 의미
		ModelAndView mv = new ModelAndView("/sample/boardList");

		/**
		 * 글번호, 글제목, 작성일 등의 내용을 Map에 저장, Map은 다시 키(key)와 값(value)로 구분되어지는데, 각각의 컬럼인 글번호, 글제목, 작성일 등의 키와 실제 값이 저장된다.
		 * Service에서 비즈니스 로직을 호출하여 처리할 수 있도록 함.
		 * 서비스로직의 결과를 ModelAndView 객체에 담아서 클라이언트, 즉 jsp에서 그 결과를 사용할 수 있도록 한다.
		 */
		List<Map<String, Object>> list = sampleService.selectBoardList(commandMap);
		mv.addObject("list", list);

		return mv;
	}
	
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * openSampleBoardList함수에서 전달인자를 CommandMap으로 수정
	 * 
	 */
	@RequestMapping(value="/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("");
	     
	    if(commandMap.isEmpty() == false){
	        Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
	        Entry<String,Object> entry = null;
	        while(iterator.hasNext()){
	            entry = iterator.next();
	            log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
	        }
	    }
	    return mv;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return ModelAndView
	 * @throws Exception
	 * 
	 * 방명록 작성 페이지를 반환한다.
	 */
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardWrite");
	     
	    return mv;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return ModelAndView
	 * @throws Exception
	 * 
	 * post 형식으로 전달받은 인자를 이용하여 Query문 실행
	 * commandMap - IDX, TITLE, CONTENTS, EMAIL, PASSWORD
	 * 정상적인 실행 후 방명록 목록으로 redirect한다.
	 * 비정상적인 실행이거나 이메일이 형식에 맞지 않을 경우 현재 페이지를 다시 Load한다.
	 */
	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
	    
	    if(isCommandMapValid(commandMap, "INSERT") && this.isEmailValid(commandMap)){
	    	sampleService.insertBoard(commandMap.getMap());
	    } else {
	    	mv = new ModelAndView("/sample/boardWrite");
	    }
	    
	    return mv;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * 게시물 상세 페이지 반환
	 * commandMap - IDX
	 * 반환과 동시에 post DB에 Select문 수행을 통한 정보들을 Map에 저장, 페이지에 인자로 전달.
	 * 
	 */
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardDetail");
	    
	    isCommandMapValid(commandMap, "DETAIL");
	    
	    printCommandMap(commandMap);
	    
	    Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
	    
	    printSelectQueryMap(map);
	    
	    mv.addObject("map", map);
	     
	    return mv;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * 게시물을 삭제하고 게시물 리스트 페이지로 redirect
	 * commandMap - IDX, EMAIL, PASSWORD
	 * Query문을 통해 EMail과 PassWord의 일치를 확인
	 * Service(server)와 Javascript(client)를 통해 Email과 Password가 입력되었는지를 확인
	 * 비정상적인 실행이거나 이메일이 형식에 맞지 않을 경우 현재 페이지를 다시 Load한다.
	 */
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
	    
	    if(isCommandMapValid(commandMap, "DELETE") && isEmailValid(commandMap)){
	    	sampleService.deleteBoard(commandMap.getMap());
	    } else {
	    	mv = new ModelAndView("/sample/boardModify");
	    }
	    return mv;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * 게시물 제목 및 내용을 Update하기 위한 페이지 반환
	 * 기존 정보를 표시하기 위해 Select문 수행 후 정보 표현
	 * openBoardDetail 함수와 동일 기능 수행
	 * 
	 */
	@RequestMapping(value="/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardUpdate");
	     
	    Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
	    mv.addObject("map", map);
	     
	    return mv;
	}
	 
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * 수정한 게시물의 제목 및 내용 수정 후, 게시물 상세 페이지 반환
	 * commandMap - IDX, TITLE, CONTENTS
	 * 현재 사용안함
	 */
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");

	    if(isEmailValid(commandMap)) sampleService.updateBoard(commandMap.getMap());
	    
	    
	    mv.addObject("IDX", commandMap.get("IDX"));
	    return mv;
	}
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * 수정한 게시물의 제목 및 내용 수정 후, 게시물 상세 페이지 반환
	 * commandMap - IDX, TITLE, CONTENTS, EMAIL, PASSWORD
	 * 비정상적인 실행이거나 이메일이 형식에 맞지 않을 경우 현재 페이지를 다시 Load한다.
	 */
	@RequestMapping(value="/sample/modifyBoard.do")
	public ModelAndView modifyBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/boardList");

	    if(isCommandMapValid(commandMap, "MODIFY") && isEmailValid(commandMap)){
	    	Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		    mv.addObject("map", map);
	    } else {
	    	mv = new ModelAndView("/sample/boardModify");
	    }
	    
	    return mv;
	}
	
	public boolean isEmailValid(CommandMap commandMap){
		String address = new String((String)commandMap.get("EMAIL"));
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher mathcher = pattern.matcher(address);
        
        return mathcher.matches();
	}
	
	public boolean isCommandMapValid(CommandMap commandMap, String command){
		boolean vaildResult = true;
		
		//case INSERT	- 	   WRITER,TITLE, CONTENTS, EMAIL, PASSWORD
		//case MODIFY 	- IDX, 		  TITLE, CONTENTS, EMAIL, PASSWORD 
		//case DELETE 	- IDX, 						   EMAIL, PASSWORD
		//case DETAIL 	- IDX
		
		Map<String,Object> aMap = commandMap.getMap().entrySet().stream().filter(x->{
			return x.getValue() != null && (((String)x.getValue()).length() != 0);
		}).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		
		if(command.equals("DETAIL")){
			vaildResult &= checkIndexVaild(aMap);
		} else {
			vaildResult &= checkAccoutVaild(aMap);
		}
		
		if(command.equals("INSERT")){
			vaildResult &= checkWriterVaild(aMap);
			vaildResult &= checkContentsVaild(aMap);
		} else if(command.equals("MODIFY")) {
			vaildResult &= checkIndexVaild(aMap);
			vaildResult &= checkContentsVaild(aMap);
		} else if(command.equals("DELETE")){
			vaildResult &= checkIndexVaild(aMap);
		}
		
		return vaildResult;
	}
	
	public boolean checkIndexVaild(Map<String,Object> aMap){
		if(!aMap.containsKey("IDX") || ((String)aMap.get("IDX")).isEmpty() ) 				return false;
		
		return true;
	}
	
	public boolean checkContentsVaild(Map<String,Object> aMap){
		if(!aMap.containsKey("TITLE") || ((String)aMap.get("TITLE")).isEmpty() ) 			return false;
		if(!aMap.containsKey("CONTENTS") || ((String)aMap.get("CONTENTS")).isEmpty() ) 		return false;
		
		return true;
	}

	public boolean checkWriterVaild(Map<String,Object> aMap){
		if(!aMap.containsKey("WRITER") || ((String)aMap.get("WRITER")).isEmpty() ) 			return false;
		
		return true;
	}
	
	public boolean checkAccoutVaild(Map<String,Object> aMap){
		if(!aMap.containsKey("EMAIL") || ((String)aMap.get("EMAIL")).isEmpty() ) 			return false;
		if(!aMap.containsKey("PASSWORD") || ((String)aMap.get("PASSWORD")).isEmpty() ) 		return false;
		
		return true;
	}
	
	public void printCommandMap(CommandMap commandMap){
		log.debug("======= [ Clients Command ] =======");
		commandMap.getMap().entrySet().stream().forEach(x->log.debug("[" + x.getKey() + "] : " + x.getValue().toString()));
		log.debug("===================================");
	}
	 
	public void printSelectQueryMap(Map<String,Object> commandMap){
		log.debug("===== [ Select Query Result ] =====");
		commandMap.entrySet().stream().forEach(x->log.debug("[" + x.getKey() + "] : " + x.getValue().toString()));
		log.debug("===================================");
	}
}