package nhn.common.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author skuug
 * DAO : Data Access Object : Data Access Object�� Factory ����ȭ ��Ű�� ������, ����Ͻ� ������ ���ȭ �ϴ� ���.
 * Context-mapper.xml���� SqlSessionTemplate�� �����Ͽ���, �̴� SqlSession�� ��ü��
 * AbstractDAO���� SqlSession�� �����ϰ� Autowired Annotation�� ���� xml�� ������ �������踦 �ڵ����� ����.
 * AbstractDAO���� Insert, Delete, Update, Select Method�� ������
 * ���� ���߿����� �� ����Ͻ� ������ ����� DAO�� �����Ͽ� AbstractDAO�� ��ӹ޵��� �� ��ȹ�̴�. 
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