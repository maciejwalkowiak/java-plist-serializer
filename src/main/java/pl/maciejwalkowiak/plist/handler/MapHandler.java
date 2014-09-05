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
import pl.maciejwalkowiak.plist.XMLHelper;

import java.util.Map;

/**
 * @author Maciej Walkowiak
 */
public class MapHandler extends AbstractHandler {
	public MapHandler(PlistSerializerImpl plistSerializer) {
		super(plistSerializer);
	}

	@Override
	String doHandle(Object object) {
		Map<String, Object> map = (Map<String, Object>) object;

		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			result.append(XMLHelper.wrap(plistSerializer.getNamingStrategy().fieldNameToKey(entry.getKey())).with("key"));
			result.append(plistSerializer.serialize(entry.getValue()));
		}

		return XMLHelper.wrap(result).with("dict");
	}

	public boolean supports(Object object) {
		return object instanceof Map;
	}
}
