package com.cxb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxb.dao.UserDao;
import com.cxb.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}
	
	/**
	 * 通过年纪查找单个对象
	 * @param age
	 * @return
	 */
	public User findUserByAge(int age) {
		return userDao.findUserByAge(age);
	}
	
	/**
	 * 通过名字查找集合对象
	 * @param name
	 * @return
	 */
	public List<User> findListByName(String name){
		return userDao.findListByName(name);
	}
	
	/**
	 * 查询所有的user
	 * @return
	 */
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
	
	/**
	 * 新增用户
	 * @param name
	 * @param age
	 * @return
	 */
	public int saveUserByParm(Map<String,Object> map) {
		return userDao.saveUserByParm(map);
	}
	
	/**
	 * 根据id修改该用户信息
	 * @param name
	 * @param age
	 * @return
	 */
	public int updateUserById(Map<String,Object> map) {
		return userDao.updateUserById(map);
	}
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}
	
	public int updateById(String name, int age, int id) {
		return userDao.updateById(name, age, id);
	}
	
	public int saveUser(String name, int age) {
		return userDao.saveUser(name, age);
	}
}
