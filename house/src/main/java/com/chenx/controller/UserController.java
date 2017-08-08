package com.chenx.controller;

import com.chenx.model.User;
import com.chenx.service.UserService;
import com.chenx.util.MD5Utils;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ChenX on 2017/8/4.
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@RequestMapping("/toRegister")
	public ModelAndView skip(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}
	@GetMapping(value = "/validate")
	@ResponseBody
	public String validateUsername(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		boolean flag = userService.getUsername(username);
		/*if (flag){
			return "{'flag':false}";
		}
		return "{'flag':true}";*/
		if (flag){
			return "{\"flag\":false}";
		}
		return "{\"flag\":true}";
	}
	@PostMapping(value="/registe",produces = "application/json;charset=utf-8")
	public ModelAndView registeUser(User user,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String md5pwd = MD5Utils.md5(user.getPassword());
		user.setPassword(md5pwd);
		userService.registUser(user);
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value="/vcaptch")
	@ResponseBody
	public String validateCaptch(HttpServletRequest request,HttpServletResponse response){
		String id = request.getSession().getId();
		String code = request.getParameter("code");
		Boolean flag = imageCaptchaService.validateResponseForID(id, code);
		if (flag) {
			return "{\"flag\":true}";
		}
		return "{\"flag\":false}";
	}
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("save");
		if (password.length()<32){
			password = MD5Utils.md5(password);
		}
		User user = userService.login(username, password);
		if (null!=user&&"on".equals(autoLogin)){
			Cookie ucookie = new Cookie("username", username);
			Cookie pcookie = new Cookie("password", password);
			Cookie acookie = new Cookie("autoLogin", autoLogin);
			ucookie.setMaxAge(15*24*3600);
			pcookie.setMaxAge(15*24*3600);
			acookie.setMaxAge(15*24*3600);
			response.addCookie(ucookie);
			response.addCookie(pcookie);
			response.addCookie(acookie);
			request.getSession().setAttribute("user",user);
			mav.setViewName("index");
		}else {
			mav.addObject("error","登录出错，重新输入！");
			mav.setViewName("login");
		}
		return mav;
	}
	@RequestMapping("/autoLogin")
	public ModelAndView autoLogin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String username = "";
		String password = "";
		String autoLogin = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();
			}else if (cookie.getName().equals("password")) {
				password = cookie.getValue();
			}else if (cookie.getName().equals("autoLogin")) {
				autoLogin = cookie.getValue();
			}
		}
		if (username!=""&&password!=""&&autoLogin!="") {
			User user = userService.getUserByUsername(username);
			request.getSession().setAttribute("user",user);
			mav.setViewName("index");
		}else {
			mav.setViewName("login");
		}
		return mav;
	}
	//注销
	@RequestMapping("/cancel")
	public ModelAndView cancel(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		mav.setViewName("index");
		return mav;
	}

}
