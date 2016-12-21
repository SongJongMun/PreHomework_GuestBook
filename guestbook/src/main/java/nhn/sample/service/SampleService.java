package nhn.sample.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author skuug
 * 비즈니스 로직의 수행을 위한 메서드를 정의한다.
 */
public interface SampleService {
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;

}
