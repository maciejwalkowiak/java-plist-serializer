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
				new DataHandler(),	
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
