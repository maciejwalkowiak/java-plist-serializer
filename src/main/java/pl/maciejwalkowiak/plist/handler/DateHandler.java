package pl.maciejwalkowiak.plist.handler;

import pl.maciejwalkowiak.plist.XMLHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHandler implements Handler {
	private SimpleDateFormat dateFormat;

	public DateHandler() {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public boolean supports(Object object) {
		return object instanceof Date;
	}

	public String handle(Object object) {
		return XMLHelper.wrap(dateFormat.format(object)).with("date");
	}
}
