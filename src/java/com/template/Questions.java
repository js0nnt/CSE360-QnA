package com.template;

public class Questions {
	
	private final String id;
	private final String author;
	private final String text;
	private final long timestamp;
	private final boolean resolved;
	
	public Questions(String id, String Author, String text, long timestamp, boolean resolved) {
		this.id = id;
		this.author = Author;
		this.text = text;
		this.timestamp = timestamp;
		this.resolved = resolved;
	}
	
	
	public String getId() { return id; }
	public String getAuthor() {return author; }
	public String getText() {return text; }
	public long getTimeStamp() {return timestamp; }
	public boolean isResolved() { return resolved; }
}
