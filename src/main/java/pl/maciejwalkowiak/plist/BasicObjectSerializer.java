package pl.maciejwalkowiak.plist;

import pl.maciejwalkowiak.plist.handler.Handler;
import pl.maciejwalkowiak.plist.handler.HandlerNotFoundException;
import pl.maciejwalkowiak.plist.handler.HandlerWrapper;
import pl.maciejwalkowiak.plist.handler.PlistSerializationException;

public class BasicObjectSerializer {
	private HandlerWrapper handlerWrapper;

	public BasicObjectSerializer(HandlerWrapper handlerWrapper) {
		this.handlerWrapper = handlerWrapper;
	}

	/**
	 * Serializes object with type: Integer, String, Boolean, Date, Map, Collection
	 *
	 * @param object - object to serialize
	 * @return - serialized object plist without key
	 */
	public StringBuilder serializeBasicObject(Object object) {
		StringBuilder result = new StringBuilder();

		try {
			Handler handler = handlerWrapper.getHandlerForObject(object);

			result.append(handler.handle(object));
		} catch (HandlerNotFoundException e) {
			throw new PlistSerializationException("BasicObjectSerializer was used to serialize complex object");
		}

		return result;
	}
}
