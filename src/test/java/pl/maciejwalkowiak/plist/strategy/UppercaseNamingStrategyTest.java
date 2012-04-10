package pl.maciejwalkowiak.plist.strategy;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UppercaseNamingStrategyTest {
	@Test
	public void shouldAddUnderScores() {
		//given
		UppercaseNamingStrategy strategy = new UppercaseNamingStrategy();
		String toConvert = "helloWorld";

		//when
		String converted = strategy.fieldNameToKey(toConvert);

		//then
		assertThat(converted).isEqualTo("HELLO_WORLD");
	}
}
