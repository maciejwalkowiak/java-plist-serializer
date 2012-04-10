package pl.maciejwalkowiak.plist.handler;

public interface Handler {
	boolean supports(Object object);
	String handle(Object object);
}
