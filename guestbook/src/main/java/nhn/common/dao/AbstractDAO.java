package nhn.common.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author skuug
 * DAO : Data Access Object : Data Access Object를 Factory 패턴화 시키는 것으로, 비즈니스 로직을 모듈화 하는 방법.
 * Context-mapper.xml에서 SqlSessionTemplate을 설정하였고, 이는 SqlSession을 대체함
 * AbstractDAO에서 SqlSession을 선언하고 Autowired Annotation을 통해 xml에 선언한 의존관계를 자동으로 주입.
 * AbstractDAO에서 Insert, Delete, Update, Select Method를 재정의
 * 실제 개발에서는 각 비즈니스 로직을 담당할 DAO를 생성하여 AbstractDAO를 상속받도록 할 계획이다. 
 */
public class AbstractDAO {
    protected Log log = LogFactory.getLog(AbstractDAO.class);
     
    @Autowired
    private SqlSessionTemplate sqlSession;
     
    protected void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
     
    public Object insert(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }
     
    public Object update(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.update(queryId, params);
    }
     
    public Object delete(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.delete(queryId, params);
    }
     
    public Object selectOne(String queryId){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId);
    }
     
    public Object selectOne(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
}