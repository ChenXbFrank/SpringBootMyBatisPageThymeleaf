package com.cxb.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cxb.model.Book;
/**
 * springbootJPA
 *       这种就不需要写service类，直接在controller里面调用该dao的方法
 * @author 81046
 *
 */
public interface BookDao extends JpaRepository<Book, Integer>{
	
	//And --- 等价于 SQL 中的 and 关键字
	public List<Book> findByNameAndStock(String name,int stock);
	
	// Or --- 等价于 SQL 中的 or 关键字
	public List<Book> findByNameOrStock(String name,int stock);
	
	//Between --- 等价于 SQL 中的 between 关键字
	public List<Book> findByStockBetween(int min,int max);
	
	//LessThan --- 等价于 SQL 中的 "<"
	public List<Book> findByStockLessThan(int max);
	
	//GreaterThan --- 等价于 SQL 中的">"  
	public List<Book> findByStockGreaterThan(int min);  
	
	//IsNull --- 等价于 SQL 中的 "is null"
	public List<Book> findByNameIsNull();
	
	//IsNotNull --- 等价于 SQL 中的 "is not null"
	public List<Book> findByNameIsNotNull();  
	
	//NotNull --- 与 IsNotNull 等价
	public List<Book> findByNameNotNull(); 
	
	//Like --- 等价于 SQL 中的 "like"
	public List<Book> findByNameLike(String name); 
	
	//NotLike --- 等价于 SQL 中的 "not like"
	public List<Book> findByNameNotLike(String name); 
	
	//OrderBy --- 等价于 SQL 中的 "order by"
	public List<Book> findByNameNotNullOrderByStockAsc();  
	
	//Not --- 等价于 SQL 中的 "！ ="
	public List<Book> findByNameNot(String name);
	
	//In --- 等价于 SQL 中的 "in"
	public List<Book> findByNameIn(String name);
	
	//NotIn --- 等价于 SQL 中的 "not in"
	public List<Book> findByNameNotIn(String name);
	
	
	//***************************************
	//利用原生的sql进行操作
	
	// @Query 注解中编写 JPQL 语句
	// 使用 @Modifying 进行修饰. 以通知 　　SpringData， 这是一个 UPDATE 或 DELETE 操作
	@Query(value="select * from book where name = ?1 ",nativeQuery=true)
	@Modifying
	public List<Book> findBookByName(String name);
	
	
	@Query(value="delete from book where id = ?1 ",nativeQuery=true)
	@Modifying
	@Transactional
	public void deleteBookById(int id);
	
	
	//利用原生的SQL进行修改操作  
   @Query(value = "update book set name=?1 where id=?2 ", nativeQuery = true)  
   @Modifying 
   @Transactional
   public void updateBookName(String name,int id);  
   
   //利用原生的SQL进行插入操作  
   @Query(value = "insert into book(name,author) value(?1,?2)", nativeQuery = true)  
   @Modifying  
   @Transactional
   public void insertBook(String name,String author);  
   
   //分页查询 查询计算元素总个数总页数，数据多的情况下，代价是昂贵的  
   Page<Book> findByNameAndAuthor(String name ,String author,Pageable pageable);  
   
   //分页查询，返回的是一个片段，它只知道下一片段或者上一片段是否可用。  
   Slice<Book> findByName(String name,Pageable pageable); 
   
   //分页查询所有
   //分页查询 查询计算元素总个数总页数，数据多的情况下，代价是昂贵的  
   Page<Book> findAll(Pageable pageable); 
   
}
