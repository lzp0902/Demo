package com.bm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dm.UserDm;
import com.vo.UserVO;

@Controller
public class UserController {
	
	@Resource
	UserDm  userdm;
	
	
	@RequestMapping("/login.action")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		String username = request.getParameter("UserName");
		String password = request.getParameter("PassWord");
		List<UserVO> list = userdm.queryUser(username);
		boolean flag = false;
		for(UserVO vo:list){
			if(password.equals(vo.getPassWord())){
				flag = true;
			}else{
				flag = false;
			}
		}
		if(flag){
			return "main";
		}else{
			return "login";
		}
	}
}
