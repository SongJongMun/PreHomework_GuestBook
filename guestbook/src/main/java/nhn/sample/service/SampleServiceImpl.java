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
 * ServiceImpl : Service �������̽��� ���� ���ǵ� �޼��带 ������ �����ϴ� Ŭ����
 * @Servcie ������̼��� �̿��Ͽ� Service ��ü���� �����Ͽ���, �� ��ü�� �̸��� "sampleService"��� ������. 
 * 
 */
@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Service������ ������ ������ ���� DAO(Data Access Object) ��ü�� ����
	 */
	@Resource(name="sampleDAO")
    private SampleDAO sampleDAO;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		 return sampleDAO.selectBoardList(map);
	}
 
}