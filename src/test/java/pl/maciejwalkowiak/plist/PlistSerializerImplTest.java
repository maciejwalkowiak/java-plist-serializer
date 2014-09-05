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

import org.junit.Test;
import pl.maciejwalkowiak.plist.handler.Handler;
import pl.maciejwalkowiak.plist.strategy.UppercaseNamingStrategy;

import java.math.BigDecimal;
import java.util.*;

import static org.fest.assertions.Assertions.assertThat;

public class PlistSerializerImplTest {
	private PlistSerializerImpl plistSerializer = new PlistSerializerImpl();

	@Test
	public void testStringArraySerialization() {
		//given
		List<String> strings = Arrays.asList("string1", "string2", "string3");

		//when
		String xml = plistSerializer.serialize(strings);

		//then
		assertThat(xml).isEqualTo("<array><string>string1</string><string>string2</string><string>string3</string></array>");
	}

	@Test
	public void testIntegerListSerialization() {
		//given
		List<Integer> strings = Arrays.asList(4,6,8);

		//when
		String xml = plistSerializer.serialize(strings);

		//then
		assertThat(xml).isEqualTo("<array><integer>4</integer><integer>6</integer><integer>8</integer></array>");
	}

	@Test
	public void testObjectSerialization() {
		//given
		Post post = new Post(new Author("jason bourne"), "java-plist-serializer introduction", 9);
		post.addComment(new Comment("maciejwalkowiak", "first comment"));
		post.addComment(new Comment("john doe", "second comment"));

		//when
		String xml = plistSerializer.serialize(post);

		//then
		assertThat(xml).isEqualTo("<dict><key>author</key><dict><key>name</key><string>jason bourne</string></dict>" +
				"<key>comments</key><array>" +
				"<dict><key>author</key><string>maciejwalkowiak</string><key>content</key><string>first comment</string></dict>" +
				"<dict><key>author</key><string>john doe</string><key>content</key><string>second comment</string></dict></array>" +
				"<key>title</key><string>" + post.getTitle() + "</string>" +
				"<key>views</key><integer>" + post.getViews() + "</integer>" +
				"</dict>");
	}

	@Test
	public void testNullSerialization() {
		assertThat(plistSerializer.serialize(null)).isEqualTo("");
	}

	@Test
	public void testStaticFieldSerialization() {
		//given
		ClassWithStaticFields classWithStaticFields = new ClassWithStaticFields();

		//when
		String result = plistSerializer.serialize(classWithStaticFields);

		//then
		assertThat(result).isEqualTo("<dict><key>serializableField</key><integer>1</integer></dict>");
	}

	@Test
	public void testAnnotations() {
		//given
		ClassWithAnnotations classWithAnnotations = new ClassWithAnnotations();

		//when
		String xml = plistSerializer.serialize(classWithAnnotations);

		//then
		assertThat(xml).isEqualTo("<dict><key>trick</key><string>i am renamed</string></dict>");
	}

	@Test
	public void testPlistRenameWithFollowingStrategy() {
		//given
		PlistSerializerImpl plistSerializer = new PlistSerializerImpl(new UppercaseNamingStrategy());
		AnnotatedClass annotatedClass = new AnnotatedClass();

		//when
		String xml = plistSerializer.serialize(annotatedClass);

		//then
		assertThat(xml).isEqualTo("<dict><key>FIRST_FIELD</key><string>i am following</string><key>secondField</key><string>i am not following</string></dict>");
	}

	@Test
	public void testFieldSerializationWhenNull() {
		//given
		Comment comment = new Comment(null, "content");

		//when
		String xml = plistSerializer.serialize(comment);

		//then
		assertThat(xml).isEqualTo("<dict><key>content</key><string>content</string></dict>");
	}

	@Test
	public void testToXML() {
		//given
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\">";
		String footer = "</plist>";

		String testObject= "testObject";

		//when
		String xml = plistSerializer.toXmlPlist(testObject);

		//then
		assertThat(xml).startsWith(header).endsWith(footer);
	}

	@Test
	public void testAdditionalHandler() {
		//given
		Handler bigDecimalHandler = new BigDecimalHandler();

		//when
		plistSerializer.setAdditionalHandlers(Arrays.asList(bigDecimalHandler));
		String xml = plistSerializer.serialize(new BigDecimal(3));

		//then
		assertThat(xml).isEqualTo("<integer>3</integer>");
	}

	@Test
	public void testDoubleSerializationHandler() {
		//given
		Double object = 4.55d;

		//when
		String xml = plistSerializer.serialize(object);

		//then
		assertThat(xml).isEqualTo("<real>4.55</real>");
	}
	
	@Test
	public void testInheritedFieldsSerialization()
	{
		//given
		FooChild object = new FooChild("test1", "test2");
		
		//when
		String xml = plistSerializer.serialize(object);
		
		//then
		assertThat(xml).isEqualTo("<dict><key>bar</key><string>test2</string><key>foo</key><string>test1</string></dict>");
	}

	@Test
	public void testSupportedDataTypes() throws IllegalAccessException, InstantiationException {
		//given
		List supportedDataTypes = Arrays.asList(
				Integer.valueOf(1),
				Double.valueOf(1d),
				Float.valueOf(1f),
				Short.valueOf("1"),
				Long.valueOf(1l),
				Double.valueOf(1d),
				new Boolean(true),
				new Date(),
				new HashSet<String>(),
				new HashMap<String, String>());

		for (Object o: supportedDataTypes) {
			//when
			boolean isSupported = plistSerializer.getHandlerWrapper().isSupported(o);

			//then
			assertThat(isSupported).isTrue();
		}
	}
}
