package pl.maciejwalkowiak.plist.handler;

public class IntegerHandler extends SimpleHandler {
	public boolean supports(Object object) {
		return object instanceof Integer
				|| object instanceof Long
				|| object instanceof Short;
	}

	@Override
	protected String getWrap() {
		return "integer";
	}
}
