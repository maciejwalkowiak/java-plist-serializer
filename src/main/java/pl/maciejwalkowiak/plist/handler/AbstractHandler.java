package pl.maciejwalkowiak.plist.handler;

import pl.maciejwalkowiak.plist.PlistSerializerImpl;

/**
 * Used for handlers that need to use {@link pl.maciejwalkowiak.plist.PlistSerializerImpl} internally.
 * {@link CollectionHandler}, {@link MapHandler}
 */
public abstract class AbstractHandler implements Handler {
	protected PlistSerializerImpl plistSerializer;

	protected AbstractHandler(PlistSerializerImpl plistSerializer) {
		this.plistSerializer = plistSerializer;
	}

	public String handle(Object object) {
		if (plistSerializer == null) {
			throw new IllegalStateException("plist is not initialized");
		}

		return doHandle(object);
	}

	abstract String doHandle(Object object);
}
