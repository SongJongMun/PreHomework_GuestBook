package nhn.spring.guestbook;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * RequestMapping : jsp에서 들어온 요청에 해당하는 비즈니스 로직을 찾아주는 역활, 
	 * return : jsp 페이지 이름, Servlet 설정에서 자동으로 prefix(/WEB-INF/views)와  suffix(.jsp) 수행
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//비즈니스 로직에서 수행한 결과를 화면으로 보내주기 위한 부분, ServerTime이라는 이름으로 formattedDate를 전송함
		model.addAttribute("serverTime", formattedDate );
		
		
		return "home";
	}
	
}
