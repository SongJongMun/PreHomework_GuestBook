package nhn.sample.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author skuug
 * ����Ͻ� ������ ������ ���� �޼��带 �����Ѵ�.
 */
public interface SampleService {
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

}
