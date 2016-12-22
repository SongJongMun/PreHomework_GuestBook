package nhn.common.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
 
/**
 * 
 * @author skuug
 * HandlerMethodArgumentResolver는 컨트롤러의 파라미터가 Map 형식이면 동작하지 않음
 * HandlerMethodArgumentResolver를 이용하여 그러한 기능을 만들더라도, 컨트롤러의 파라미터가 Map 형식이면 직접 설정한 클래스가 아닌, 스프링에서 기본적으로 설정된 ArgumentResolver를 거치게 된다. 
 * 그래서 Map을 대신할 CommandMap을 작성한다.
 */
public class CommandMap {
    Map<String,Object> map = new HashMap<String,Object>();
     
    public Object get(String key){
        return map.get(key);
    }
     
    public void put(String key, Object value){
        map.put(key, value);
    }
     
    public Object remove(String key){
        return map.remove(key);
    }
     
    public boolean containsKey(String key){
        return map.containsKey(key);
    }
     
    public boolean containsValue(Object value){
        return map.containsValue(value);
    }
     
    public void clear(){
        map.clear();
    }
     
    public Set<Entry<String, Object>> entrySet(){
        return map.entrySet();
    }
     
    public Set<String> keySet(){
        return map.keySet();
    }
     
    public boolean isEmpty(){
        return map.isEmpty();
    }
     
    public void putAll(Map<? extends String, ?extends Object> m){
        map.putAll(m);
    }
     
    public Map<String,Object> getMap(){
        return map;
    }
}