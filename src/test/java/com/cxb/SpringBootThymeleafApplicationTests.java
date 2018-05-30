package com.cxb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cxb.dao.BookDao;
import com.cxb.model.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootThymeleafApplicationTests {
	
	@Autowired
	private BookDao bookDao;

	@Test
	public void contextLoads() {
	}

	//测试dao里面的方法
	@Test
	public List<Book> test1() {
		List<Book> list = bookDao.findAll();
		for (Book book : list) {
			System.out.println(book.getName());
		}
		return list;
	}
}
