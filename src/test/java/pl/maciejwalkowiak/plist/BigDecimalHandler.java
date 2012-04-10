package pl.maciejwalkowiak.plist;

import pl.maciejwalkowiak.plist.handler.Handler;

import java.math.BigDecimal;

public class BigDecimalHandler implements Handler {
	public boolean supports(Object object) {
		return object instanceof BigDecimal;
	}

	public String handle(Object object) {
		BigDecimal bigDecimal = (BigDecimal) object;

		return XMLHelper.wrap(bigDecimal.intValue()).with("integer");
	}
}
