package pl.maciejwalkowiak.plist.handler;

public class BooleanHandler implements Handler {

	public String handle(Object object) {
		Boolean o = (Boolean) object;

		return "<" + String.valueOf(o) + "/>";
	}

	public boolean supports(Object object) {
		return object instanceof Boolean;
	}
}
