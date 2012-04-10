# java objects to plist serializer

java-plist-serializer is a Java library that can be used to convert Java Objects into their Property List representation.

```java

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

```

```java

Comment comment = new Comment("maciej walkowiak", "hello plist");

PlistSerializer serializer = new PlistSerializerImpl();

String xml = serializer.serialize(comment);

```

