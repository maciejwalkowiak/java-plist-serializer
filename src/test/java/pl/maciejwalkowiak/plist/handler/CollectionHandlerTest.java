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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.maciejwalkowiak.plist.PlistSerializerImpl;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class CollectionHandlerTest
{
    @InjectMocks
    private CollectionHandler collectionHandler;

    @Mock
    private PlistSerializerImpl plistSerializer;

    @Test
    public void testSupportsArray() throws Exception
    {
        //given
        int[] array = new int[] { 1, 2, 3 };

        //when
        boolean supports = collectionHandler.supports(array);

        //then
        assertThat(supports).isTrue();
    }

    @Test
    public void testSupportsList() throws Exception
    {
        //given
        List<Integer> list = Arrays.asList(1, 2, 3);

        //when
        boolean supports = collectionHandler.supports(list);

        //then
        assertThat(supports).isTrue();
    }

    @Test
    public void testIntConversion()
    {
        //given
        List<Integer> list = Arrays.asList(1, 2, 3);

        given(plistSerializer.serialize(anyInt())).willReturn("<integer>1</integer>");

        //when
        String result = collectionHandler.handle(list);

        //then

        assertThat(result).isEqualTo("<array><integer>1</integer><integer>1</integer><integer>1</integer></array>");

        for (Integer i : list)
        {
            Mockito.verify(plistSerializer).serialize(eq(i));
        }
    }

    @Test
    public void testNativeIntConversion()
    {
        //given
        int[] list = new int[] { 1, 2, 3 };

        given(plistSerializer.serialize(anyInt())).willReturn("<integer>1</integer>");

        //when
        String result = collectionHandler.handle(list);

        //then

        assertThat(result).isEqualTo("<array><integer>1</integer><integer>1</integer><integer>1</integer></array>");

        for (Integer i : list)
        {
            Mockito.verify(plistSerializer).serialize(eq(i));
        }
    }
}
