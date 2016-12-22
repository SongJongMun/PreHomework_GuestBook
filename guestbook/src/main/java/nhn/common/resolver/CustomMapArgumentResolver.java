package nhn.common.resolver;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
 
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
 
import nhn.common.common.CommandMap;
 
/**
 * 
 * @author skuug
 * HandlerMethodArgumentResolver 인터페이스를 상속, supportsParameter 메서드와 resolveArgument 메서드를 구현함
 * HandlerMethodArgumentResolver 는 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정할 수 있도록 해줌
 * 
 */
public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver{

	/**
	 * Resolver가 적용 가능한지 검사하는 역할을 수행
	 * 컨트롤러의 파라미터가 CommandMap 클래스인지 검사
	 */
	@Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CommandMap.class.isAssignableFrom(parameter.getParameterType());
    }
 
	/**
	 * 파라미터와 기타 정보를 받아서 실제 객체를 반환
	 * request에 담겨있는 모든 키(key)와 값(value)을 commandMap에 저장하여 반환
	 */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
    	CommandMap commandMap = new CommandMap();
        
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Enumeration<?> enumeration = request.getParameterNames();
         
        String key = null;
        String[] values = null;
        
        while(enumeration.hasMoreElements()){
            key = (String) enumeration.nextElement();
            values = request.getParameterValues(key);
            if(values != null){
                commandMap.put(key, (values.length > 1) ? values:values[0] );
            }
        }
        
        return commandMap;
    }
}