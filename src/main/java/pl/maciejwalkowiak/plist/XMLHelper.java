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

/**
 * Helper class used to wrap serialized objects with keys.
 * You can create instance of it by calling {@link #wrap(Object)} method
 *
 * @author Maciej Walkowiak
 */
public class XMLHelper {
	private XMLHelper() {}

	private Object objectToWrap;

	public static XMLHelper wrap(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("object to wrap cannot be null");
		}

		XMLHelper xmlHelper = new XMLHelper();
		xmlHelper.objectToWrap = object;

		return xmlHelper;
	}

	public String with(String wrappedWith) {
		if (wrappedWith == null || wrappedWith.isEmpty()) {
			throw new IllegalArgumentException("wrappedWith cannot be null or empty");
		}

		StringBuilder result = new StringBuilder();

		result.append("<").append(wrappedWith).append(">").append(this.objectToWrap).append("</").append(wrappedWith).append(">");

		return result.toString();
	}
}
