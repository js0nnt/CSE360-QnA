package com.template;

public class Answers {
	
	private final String id;
	private final String qId;
	private final String author;
	private final String text;
	private final long timestamp;
	
	public Answers(String id, String qId, String author, String text, long timestamp){
		
		this.id = id;
		this.qId = qId;
		this.author = author;
		this.text = text;
		this.timestamp = timestamp;
		
	}
	
	public String getId() {return id;}
	public String getQId() {return qId;}
	public String getAuthor() {return author;}
	public String getText() {return text;}
	public long getTimeStamp() {return timestamp; 
	}
}
