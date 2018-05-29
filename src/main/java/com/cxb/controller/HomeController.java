package com.cxb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxb.model.User;
import com.cxb.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//页面跳转不能使用 @RestController  而是使用@Controller
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	//加了@ResponseBody 就是返回字符串了
	public String index(ModelMap map) {
		//返回值给页面
		map.addAttribute("name", "小石潭记");
		return "index";
	}
	
	@RequestMapping(value="/userById", method=RequestMethod.GET)
	//加了@ResponseBody 就是返回字符串了
	public String userById(HttpServletRequest request,ModelMap map) {
		String id = request.getParameter("id");
		//根据id查询user
		User user = userService.findUserById(Integer.parseInt(id));
		//返回集合对象给页面
		map.addAttribute("user", user);
		return "user";
	}
	
	@RequestMapping(value="/userByAge", method=RequestMethod.GET)
	//加了@ResponseBody 就是返回字符串了
	public String userByAge(HttpServletRequest request,ModelMap map) {
		String age = request.getParameter("age");
		//根据年纪查询user
		User user = userService.findUserByAge(Integer.parseInt(age));
		//返回集合对象给页面
		map.addAttribute("user", user);
		return "user";
	}
	
	//http://localhost:8080/home/listByName?name=赵云
	@RequestMapping(value="/listByName", method=RequestMethod.GET)
	//加了@ResponseBody 就是返回字符串了
	public String listByName(HttpServletRequest request,ModelMap map) {
		String name = request.getParameter("name");
		List<User> list = userService.findListByName(name);
		//返回集合对象给页面
		map.addAttribute("list", list);
		return "list";
	}
	
	//http://localhost:8080/home/list
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(ModelMap map) {
		//模拟集合
		List<User> list = userService.findAllUser();
		//返回集合对象给页面
		map.addAttribute("list", list);
		PageInfo<User> pageInfo=new PageInfo<>(list);
		System.out.println(pageInfo);
		return "list";
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/listPage", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public PageInfo listPage() {
		/**
		 * int pageNum, 当前页             
		 * int pageSize, 每页的条数
		 */
		PageHelper.startPage(2,5);
		//模拟集合
		List<User> list = userService.findAllUser();
		//返回集合对象给页面
		PageInfo<User> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	@ResponseBody
	public String saveUser(HttpServletRequest request) {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		//注解式的保存对象
		userService.saveUser(name, Integer.parseInt(age));
		Map<String,Object> map =new HashMap<>();
		map.put("name", name);
		map.put("age", age);
		int saveUser = userService.saveUserByParm(map);
		return "save success "+saveUser+" record ";
	}
	
	@RequestMapping(value="/updateUserById", method=RequestMethod.GET)
	@ResponseBody
	public String updateUserById(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		//注解式的修改
//		int update = userService.updateById(name, Integer.parseInt(age), Integer.parseInt(id));
		Map<String,Object> map=new HashMap<>();
		map.put("name", name);
		map.put("age", Integer.parseInt(age));
		map.put("id", Integer.parseInt(id));
		int update = userService.updateUserById(map);
		return "update success "+update+" record ";
	}
	
	@RequestMapping(value="/deleteUserById", method=RequestMethod.GET)
	@ResponseBody
	public String deleteUserById(HttpServletRequest request) {
		String id = request.getParameter("id");
		userService.deleteUserById(Integer.parseInt(id));
		return "delete success record";
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="/error", method=RequestMethod.GET)
	@ResponseBody
	public String error() {
		return "sorry error";
	}
}
