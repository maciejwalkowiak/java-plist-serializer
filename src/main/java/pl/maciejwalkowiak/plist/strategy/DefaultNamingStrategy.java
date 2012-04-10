package pl.maciejwalkowiak.plist.strategy;

import pl.maciejwalkowiak.plist.NamingStrategy;

/**
 * Does not do any conversion. Returns string to convert unchanged
 */
public class DefaultNamingStrategy implements NamingStrategy {
	public String fieldNameToKey(String toConvert) {
		return toConvert;
	}
}
