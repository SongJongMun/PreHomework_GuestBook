package nhn.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import nhn.common.dao.AbstractDAO;

/**
 * 
 * @author skuug
 * @Repository라는 어노테이션을 통해서 이 클래스가 DAO 임을 선언하고 이름을 "sampleDAO"로 작성
 * SampleServiceImpl에서 @Resource(name="sampleDAO")로 bean을 수동으로 등록하였고, 거기서 사용된 빈이 SampleDAO 클래스
 */
@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
		//selectList 메서드의 인자는 두가지, 첫번째는 쿼리 이름, 두번째는 쿼리가 실행되는데 필요한 변수들, 결과값은 list 형식으로 반환할 수 있도록 형변환
        return (List<Map<String, Object>>)selectList("sample.selectBoardList", map);
    }
}
