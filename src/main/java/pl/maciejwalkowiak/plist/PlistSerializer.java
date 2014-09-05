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
 * Main java-plist-serializer interface, representing a component that serializes Java objects to Apple Property Lists
 * {@see http://developer.apple.com/library/mac/#documentation/Cocoa/Conceptual/PropertyLists/Introduction/Introduction.html}
 *
 *
 * @author Maciej Walkowiak
 */
public interface PlistSerializer {
	static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\">";
	static final String FOOTER = "</plist>";

	/**
	 * Serializes object to plist and creates valid XML by wrapping it around XML header,
	 * plist DOCTYPE and &lt;plist&gt; XML tag.
	 *
	 * @param objectToConvert object to serialize
	 * @return a String containing valid XML
	 */
	String toXmlPlist(Object objectToConvert);

	/**
	 * Serializes object into plist XML
	 *
	 * @param objectToConvert object to serialize
	 * @return a String containing XML representation without XML header, DOCTYPE
	 */
	String serialize(Object objectToConvert);
}
