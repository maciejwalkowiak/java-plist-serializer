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

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

public class HandlerWrapperTest {
	private HandlerWrapper handlerWrapper;

	@Before
	public void setup() {
		handlerWrapper = new HandlerWrapper(null);
	}

	@Test
	public void testGetHandlerForObjectWhenHandlerFound() throws Exception {
		//given
		//handler supporting boolean and integer

		//when
		Handler handler = handlerWrapper.getHandlerForObject(Boolean.TRUE);

		//then
		assertThat(handler).isInstanceOf(BooleanHandler.class);
	}

	@Test
	public void testGetHandlerForObjectWhenHandlerNotFound() throws Exception {
		//given
		//handler supporting boolean and integer
		BigDecimal bigDecimal = new BigDecimal(1);

		//when
		try {
			handlerWrapper.getHandlerForObject(bigDecimal);

			//then
			fail();
		} catch (HandlerNotFoundException e) {

		}
	}

	@Test
	public void testIsSupported() throws Exception {
		assertThat(handlerWrapper.isSupported(new BigDecimal(1))).isFalse();
		assertThat(handlerWrapper.isSupported(Boolean.FALSE)).isTrue();
	}
}
