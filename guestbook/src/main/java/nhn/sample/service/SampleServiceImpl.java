package nhn.sample.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import nhn.sample.dao.SampleDAO;

/**
 * 
 * @author skuug
 * ServiceImpl : Service 인터페이스를 통해 정의된 메서드를 실제로 구현하는 클래스
 * @Servcie 어노테이션을 이용하여 Service 객체임을 선언하였고, 이 객체의 이름은 "sampleService"라고 선언함. 
 * 
 */
@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Service에서는 데이터 접근을 위한 DAO(Data Access Object) 객체를 선언
	 */
	@Resource(name="sampleDAO")
    private SampleDAO sampleDAO;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		 return sampleDAO.selectBoardList(map);
	}
 
}