package Controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Dao.LoginDaoImpl;
import Entity.LoginReturn;

@Controller
public class LoginController {

	@RequestMapping(value="login")
	@ResponseBody
	public LoginReturn login(String username,String pwd) {
		LoginDaoImpl login=new LoginDaoImpl();
		LoginReturn temp=login.userLogin(username, pwd);
		return temp;
	}
	


}
