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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.maciejwalkowiak.plist.PlistSerializerImpl;
import pl.maciejwalkowiak.plist.strategy.DefaultNamingStrategy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class MapHandlerTest {
	@InjectMocks
	private MapHandler mapHandler;

	@Mock
	private PlistSerializerImpl plistSerializer;

	@Test
	public void testMapSerialization() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("key1", 1);
		map.put("key2", 2);

		given(plistSerializer.serialize(anyInt())).willReturn("<integer>1</integer>");
		given(plistSerializer.getNamingStrategy()).willReturn(new DefaultNamingStrategy());

		String xml = mapHandler.doHandle(map);

		assertThat(xml).isEqualTo("<dict><key>key1</key><integer>1</integer><key>key2</key><integer>1</integer></dict>");
	}

	@Test
	public void testWhenPlistSerializerNotSet() {
		//given
		MapHandler mapHandler = new MapHandler(null);

		//when
		try {
			mapHandler.handle(new HashMap<String, String>());

			//then
			fail();
		} catch (IllegalStateException e) {

		}
	}
}
