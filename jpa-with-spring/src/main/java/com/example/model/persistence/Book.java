package com.example.model.persistence;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String isbn;
	private String title;
	private String author;
	private double price;
	
}