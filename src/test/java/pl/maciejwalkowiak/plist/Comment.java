package pl.maciejwalkowiak.plist;

public class Comment {
	private String content;
	private String author;

	public Comment(String author, String content) {
		this.content = content;
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}
}
