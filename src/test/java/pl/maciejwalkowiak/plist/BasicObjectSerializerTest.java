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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.maciejwalkowiak.plist.handler.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class BasicObjectSerializerTest {
	@InjectMocks
	private BasicObjectSerializer basicObjectSerializer;

	@Mock
	private HandlerWrapper handlerWrapper;

	@Test
	public void testSerializationWhenHandlerFound() throws HandlerNotFoundException {
		//given
		String object = "hello";
		Handler handler = new StringHandler();

		given(handlerWrapper.getHandlerForObject(eq(object))).willReturn(handler);

		//when
		StringBuilder output = basicObjectSerializer.serializeBasicObject(object);

		//then
		assertThat(output.toString()).isEqualTo(handler.handle(object));
	}

	@Test
	public void testThrowingExceptionWhenHandlerNotFound() throws HandlerNotFoundException {
		//given
		String object = "hello";
		given(handlerWrapper.getHandlerForObject(eq(object))).willThrow(new HandlerNotFoundException("handler not found"));

		//when
		try {
			basicObjectSerializer.serializeBasicObject(object);

			//then
			fail();
		} catch(PlistSerializationException e) {

		}
	}


}

