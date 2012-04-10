package pl.maciejwalkowiak.plist.handler;

import pl.maciejwalkowiak.plist.XMLHelper;

/**
 * Handler that just wraps object around key
 */
public abstract class SimpleHandler implements Handler {

	public String handle(Object object) {
		if (supports(object)) {
			return XMLHelper.wrap(object).with(getWrap());
		} else {
			throw new ObjectNotSupportedException("Handler does not support: " + object);
		}
	}

	protected abstract String getWrap();
}
