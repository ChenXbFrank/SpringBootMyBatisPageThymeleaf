package com.cxb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//自动生成表
@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100)
	private String name;
	
	@Column(length=50)
	private String author;
	
	@Column(length=50)
	private Integer stock;   //库存
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
