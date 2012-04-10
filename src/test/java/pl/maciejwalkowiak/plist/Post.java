package pl.maciejwalkowiak.plist;

import java.util.ArrayList;
import java.util.List;

public class Post {
	private String title;
	private Integer views = 0;
	private List<Comment> comments = new ArrayList<Comment>();
	private Author author;

	public Post(Author author, String title, Integer views) {
		this.title = title;
		this.views = views;
		this.author = author;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public String getTitle() {
		return title;
	}

	public Integer getViews() {
		return views;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
