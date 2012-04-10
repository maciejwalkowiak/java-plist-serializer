package pl.maciejwalkowiak.plist.handler;

public class StringHandler extends SimpleHandler {
	public boolean supports(Object object) {
		return object instanceof String;
	}

	@Override
	protected String getWrap() {
		return "string";
	}
}
