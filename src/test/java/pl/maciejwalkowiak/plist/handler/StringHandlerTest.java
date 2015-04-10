package pl.maciejwalkowiak.plist.handler;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.text.ParseException;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by nkhan on 4/9/15.
 */
public class StringHandlerTest {
    private StringHandler stringHandler = new StringHandler();

    @Test
    public void testFormattingSimpleString() throws ParseException {
        //given
        String simpleString = "data1";

        //when
        String formattedString = stringHandler.handle(simpleString);

        //then
        assertThat(formattedString).isEqualTo("<string>data1</string>");
    }

    @Test
       public void testFormattingWithSpecialCharsString1() throws ParseException {
        //given a string with chars that need to be escaped
        String stringWithSpecialChars = "sugar&spice";

        //when
        String formattedString = stringHandler.handle(stringWithSpecialChars);

        //then
        assertThat(formattedString).isEqualTo("<string>sugar&amp;spice</string>");
    }

    @Test
    public void testFormattingWithSpecialCharsString2() throws ParseException {
        //given a string with chars that need to be escaped
        String stringWithSpecialChars = "<escapeme>";

        //when
        String formattedString = stringHandler.handle(stringWithSpecialChars);

        //then
        assertThat(formattedString).isEqualTo("<string>&lt;escapeme&gt;</string>");
    }

    @Test
    public void testFormattingWithSpecialCharsString3() throws ParseException {
        //given a string with chars that need to be escaped
        String stringWithSpecialChars = "'quoteme'";

        //when
        String formattedString = stringHandler.handle(stringWithSpecialChars);

        //then
        assertThat(formattedString).isEqualTo("<string>&apos;quoteme&apos;</string>");
    }

}
