package pl.maciejwalkowiak.plist.handler;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BooleanHandlerTest {
	private BooleanHandler handler = new BooleanHandler();

	@Test
	public void shouldReturnTrue() {
		//given
		Boolean toHandle = true;

		//when
		String result = handler.handle(toHandle);

		//then
		assertThat(result).isEqualTo("<true/>");
	}

	@Test
	public void shouldReturnFalse() {
		//given
		Boolean toHandle = false;

		//when
		String result = handler.handle(toHandle);

		//then
		assertThat(result).isEqualTo("<false/>");
	}
}
