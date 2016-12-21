package nhn.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import nhn.common.dao.AbstractDAO;

/**
 * 
 * @author skuug
 * @Repository��� ������̼��� ���ؼ� �� Ŭ������ DAO ���� �����ϰ� �̸��� "sampleDAO"�� �ۼ�
 * SampleServiceImpl���� @Resource(name="sampleDAO")�� bean�� �������� ����Ͽ���, �ű⼭ ���� ���� SampleDAO Ŭ����
 */
@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
		//selectList �޼����� ���ڴ� �ΰ���, ù��°�� ���� �̸�, �ι�°�� ������ ����Ǵµ� �ʿ��� ������, ������� list �������� ��ȯ�� �� �ֵ��� ����ȯ
        return (List<Map<String, Object>>)selectList("sample.selectBoardList", map);
    }
}
