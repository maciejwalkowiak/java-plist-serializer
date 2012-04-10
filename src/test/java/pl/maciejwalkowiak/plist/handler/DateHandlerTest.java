package pl.maciejwalkowiak.plist.handler;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.fest.assertions.Assertions.assertThat;

public class DateHandlerTest {
	private DateHandler dateHandler = new DateHandler();

	@Test
	public void testFormattingDate() throws ParseException {
		//given
		Date date = fromString("2012-01-10 10:22:23");

		//when
		String formattedDate = dateHandler.handle(date);

		//then
		assertThat(formattedDate).isEqualTo("<date>2012-01-10T10:22:23Z</date>");
	}

	private Date fromString(String dateToParse) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return simpleDateFormat.parse(dateToParse);
	}
}
