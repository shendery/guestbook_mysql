package com.example.guestbook.entity;

import java.util.Date;

public class Greeting {

	private Long id;
	private String book;
	private String authorId;
	private String authorEmail;
	private String content;
	private Date created;

	public Greeting(Long id, String book, String authorId, String authorEmail, String content, Date created) {
		this.id = id;
		this.book = book;
		this.authorId = authorId;
		this.authorEmail = authorEmail;
		this.content = content;
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
