package nhn.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
 * Annotation Controller : ������ �����ӿ�ũ�� ���� Ŭ������ ��Ʈ�ѷ� ��� ���� �����
 * ��Ʈ�ѷ�(Controller)�� �� Ŭ���̾�Ʈ���� ���� ��û�� �ش� �����Ͻ� ������ ȣ���ϰ�, �������� �Բ� ������ ���ִ� Dispatcher ������ �Ѵ�. 
 * Ŭ������ ����ο� @Controller ������̼�(Annotation)�� �̿��Ͽ�, Controller ��ü���� �����Ѵ�. 
 */

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * Service ������ ������ ���� ����
	 * SampeServiceImpl�� �����ϰ� �� Service�� "sampleService"��� �̸����� ������ �Ͽ��µ�, �̸� ����ϱ� ���� ����.
	 * @Resource������̼��� ���ؼ� �ʿ��� ��(bean)�� �������� ����ϴ� ��. 
	 * �������� ����� ���� �̸��� "sampleService"�̰�, �̴� @Service("sampleService")��� �������� ���� �� �̸��ΰ��� Ȯ��
	 */
	@Resource(name = "sampleService")
	private SampleService sampleService;

	/**
	 * 
	 * @param commandMap
	 * @return mv(ModelAndView Class Instance)
	 * @throws Exception
	 * 
	 * Annotation Requestmapping : DisptacherServlet��  �ش� ������̼��� �������� � ��Ʈ�ѷ��� �޼ҵ尡 ȣ��Ǿ�� ���� ����
	 * value="/sample/openSampleList.do"�� ������Ʈ�� ����� �ּҸ� �ǹ��Ѵ�.
	 */
	@RequestMapping(value="/sample/openSampleList.do")
    public ModelAndView openSampleList(Map<String,Object> commandMap) throws Exception{
        //������ ���� : �ش� ��Ʈ�ѷ��� ����ǰ� ���� ������ View�� ����
		ModelAndView mv = new ModelAndView("");
        log.debug("���ͼ��� �׽�Ʈ");
         
        return mv;
    }
	
	
	/**
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * 
	 * @RequestMapping�� ��û URL�� �ǹ�, /sample/openSampleBoardList.do ��� �ּҸ� ȣ���ϰ� �Ǹ�, �� �ּҴ� @RequestMapping ������̼ǰ� ���εǾ�, �ش� �޼��尡 ����ȴ�.
	 * 
	 */
	@RequestMapping(value = "/sample/openSampleBoardList.do")
	public ModelAndView openSampleBoardList(Map<String, Object> commandMap) throws Exception {
		//ȭ�鿡 ������ jsp������ �ǹ�
		ModelAndView mv = new ModelAndView("/sample/boardList");

		/**
		 * �۹�ȣ, ������, �ۼ��� ���� ������ Map�� ����, Map�� �ٽ� Ű(key)�� ��(value)�� ���еǾ����µ�, ������ �÷��� �۹�ȣ, ������, �ۼ��� ���� Ű�� ���� ���� ����ȴ�.
		 * Service���� ����Ͻ� ������ ȣ���Ͽ� ó���� �� �ֵ��� ��.
		 * ���񽺷����� ����� ModelAndView ��ü�� ��Ƽ� Ŭ���̾�Ʈ, �� jsp���� �� ����� ����� �� �ֵ��� �Ѵ�.
		 */
		List<Map<String, Object>> list = sampleService.selectBoardList(commandMap);
		mv.addObject("list", list);

		return mv;
	}
	
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
}