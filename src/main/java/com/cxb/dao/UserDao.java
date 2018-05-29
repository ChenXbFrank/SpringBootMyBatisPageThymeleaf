package com.cxb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxb.model.User;

@Mapper
public interface UserDao {
	
	@Select("select * from user where id = #{id}")
    User findUserById(@Param("id") int id);
	
	@Update("update user set name=#{name}, age=#{age} where id=#{id}")
    int updateById(@Param("name") String name,@Param("age") int age,@Param("id") int id);
	
	@Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int saveUser(@Param("name") String name,@Param("age") int age);
	
	//下面的两个方法都是xml中配置的
	User findUserByAge(int age);
	
	List<User> findListByName(String name);
	
	List<User> findAllUser();
	
	int saveUserByParm(Map<String,Object> map);
	
	int updateUserById(Map<String,Object> map);
	
	void deleteUserById(int id);
	
}
