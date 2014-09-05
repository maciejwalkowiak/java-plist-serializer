/*
 * Copyright (c) 2012 Maciej Walkowiak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pl.maciejwalkowiak.plist;

import pl.maciejwalkowiak.plist.handler.Handler;
import pl.maciejwalkowiak.plist.handler.HandlerNotFoundException;
import pl.maciejwalkowiak.plist.handler.HandlerWrapper;
import pl.maciejwalkowiak.plist.handler.PlistSerializationException;

/**
 * @author Maciej Walkowiak
 */
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
