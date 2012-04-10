package pl.maciejwalkowiak.plist.handler;

public class DoubleHandler extends SimpleHandler {
	@Override
	protected String getWrap() {
		return "real";
	}

	public boolean supports(Object object) {
		return object instanceof Float || object instanceof Double;
	}
}
