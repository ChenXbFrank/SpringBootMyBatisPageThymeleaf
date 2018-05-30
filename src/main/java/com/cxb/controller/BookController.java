package com.cxb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxb.dao.BookDao;
import com.cxb.model.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookDao bookDao;
	
	//http://localhost:8080/book/list
	@RequestMapping(value="/list", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Book> list() {
		return bookDao.findAll();
	}
	
	@RequestMapping(value="/saveBook", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Book saveBook(HttpServletRequest request) {
		String author = request.getParameter("author");
		String name = request.getParameter("name");
		String stock = request.getParameter("stock");
		Book book = new Book();
		book.setAuthor(author);
		book.setName(name);
		book.setStock(Integer.parseInt(stock));
		return bookDao.save(book);
	}
	
	@RequestMapping(value="/updateBook", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Book updateBook(HttpServletRequest request) {
		String author = request.getParameter("author");
		String name = request.getParameter("name");
		String stock = request.getParameter("stock");
		Book book = new Book();
		book.setAuthor(author);
		//设置id之后，就会执行update
		book.setId(3);
		book.setName(name);
		book.setStock(Integer.parseInt(stock));
		return bookDao.save(book);
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public void delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		bookDao.deleteById(Integer.parseInt(id));
	}
	
	//http://localhost:8080/book/findByNameAndStock?name=庐州月&stock=20
	@RequestMapping(value="/findByNameAndStock", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Book> findByNameAndStock(HttpServletRequest request) {
		String name = request.getParameter("name");
		String stock = request.getParameter("stock");
		return bookDao.findByNameAndStock(name,Integer.parseInt(stock));
	}
	
	//http://localhost:8080/book/findByNameOrStock?name=庐州月&stock=22
	@RequestMapping(value="/findByNameOrStock", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Book> findByNameOrStock(HttpServletRequest request){
		String name = request.getParameter("name");
		String stock = request.getParameter("stock");
		//满足条件1和条件2的集合
		return bookDao.findByNameOrStock(name,Integer.parseInt(stock));
	}
	
	@RequestMapping(value="/findByStockBetween", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Book> findByStockBetween(){
		//满足条件1和条件2的集合
		return bookDao.findByStockBetween(10,20);
	}
	
	//http://localhost:8080/book/findBookByName?name=千百度
	@RequestMapping(value="/findBookByName", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Book> findBookByName(HttpServletRequest request){
		String name = request.getParameter("name");
		return bookDao.findBookByName(name);
	}
	
	//http://localhost:8080/book/updateBookName?name=叹服&id=1
	@RequestMapping(value="/updateBookName", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public void updateBookName(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		bookDao.updateBookName(name, Integer.parseInt(id));
	}
	
	//http://localhost:8080/book/insertBook?name=千百度1&author=许嵩
	@RequestMapping(value="/insertBook", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public void insertBook(HttpServletRequest request) {
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		bookDao.insertBook(name, author);
	}
	
	//http://localhost:8080/book/deleteBookById?id=6
	@RequestMapping(value="/deleteBookById", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public void deleteBookById(HttpServletRequest request){
		String id = request.getParameter("id");
		bookDao.deleteById(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/findByNameAndAuthor", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Page<Book> findByNameAndAuthor(HttpServletRequest request){
		@SuppressWarnings("deprecation")
		//根据id降序
		Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		return bookDao.findByNameAndAuthor(name, author, pageable);
	}
	
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Page<Book> findAll(){
		@SuppressWarnings("deprecation")
		//根据id降序
		Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
		return bookDao.findAll(pageable);
	}
	
	@RequestMapping(value="/findByName", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	Slice<Book> findByName(HttpServletRequest request){
		@SuppressWarnings("deprecation")
		//根据id降序
		Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
		String name = request.getParameter("name");
		return bookDao.findByName(name, pageable);
	}
}
