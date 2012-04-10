package pl.maciejwalkowiak.plist.handler;

import pl.maciejwalkowiak.plist.PlistSerializerImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Wraps all defined handlers and provides simple interface to get handlers for objects.
 * By default contains handlers for all basic types and {@link java.util.Map} and {@link java.util.Collection}
 *
 * @author Maciej Walkowiak
 */
public class HandlerWrapper {
	private List<Handler> handlers = new ArrayList<Handler>();

	public HandlerWrapper(PlistSerializerImpl plistSerializer) {
		this.handlers.addAll(Arrays.asList(
				new MapHandler(plistSerializer),
				new BooleanHandler(),
				new StringHandler(),
				new IntegerHandler(),
				new DoubleHandler(),
				new DateHandler(),
				new CollectionHandler(plistSerializer)));
	}

	/**
	 * Adds handlers for object types not supported by default
	 *
	 * @param additionalHandlers collection of additional handlers
	 */
	public void addAdditionalHandlers(List<Handler> additionalHandlers) {
		this.handlers.addAll(additionalHandlers);
	}

	public Handler getHandlerForObject(Object object) throws HandlerNotFoundException {
		Handler result = null;

		for (Handler handler : handlers) {
			if (handler.supports(object)) {
				result = handler;

				break;
			}
		}

		if (result == null) {
			throw new HandlerNotFoundException("handler for object = " + object + " not found");
		}

		return result;
	}

	public boolean isSupported(Object object) {
		boolean result = false;

		for (Handler handler : handlers) {
			if (handler.supports(object)) {
				result = true;

				break;
			}
		}

		return result;
	}
}
