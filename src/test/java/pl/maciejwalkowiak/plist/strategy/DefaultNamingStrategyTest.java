package pl.maciejwalkowiak.plist.strategy;

import org.junit.Test;
import pl.maciejwalkowiak.plist.NamingStrategy;

import static org.fest.assertions.Assertions.assertThat;

public class DefaultNamingStrategyTest {
	@Test
	public void shouldKeepStringAsItIs() {
		//given
		NamingStrategy strategy = new DefaultNamingStrategy();
		String toConvert = "helloWorld";

		//when
		String converted = strategy.fieldNameToKey(toConvert);

		//then
		assertThat(converted).isEqualTo(toConvert);
	}
}
