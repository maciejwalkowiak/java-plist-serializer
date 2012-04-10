package pl.maciejwalkowiak.plist;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

public class XMLHelperTest {
	@Test
	public void shouldThrowExceptionWhenNullWrapped() throws Exception {
		//given
		Object objectToWrap = null;

		//when
		try {
			XMLHelper.wrap(objectToWrap);
			fail();

		} catch (Exception e) {
			//then
			assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
		}

	}

	@Test
	public void shouldThrowExceptionWhenWrappedWithIsNull() throws Exception {
		//given
		Object objectToWrap = "hello";
		String wrappedWith = null;

		//when
		XMLHelper xmlHelper = XMLHelper.wrap(objectToWrap);

		try {
			xmlHelper.with(wrappedWith);
			fail();

		} catch (Exception e) {
			//then
			assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
		}
	}

	@Test
	public void shouldThrowExceptionWhenWrappedWithIsEmpty() throws Exception {
		//given
		Object objectToWrap = "hello";
		String wrappedWith = "";

		//when
		XMLHelper xmlHelper = XMLHelper.wrap(objectToWrap);

		try {
			xmlHelper.with(wrappedWith);
			fail();

		} catch (Exception e) {
			//then
			assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
		}
	}
}
