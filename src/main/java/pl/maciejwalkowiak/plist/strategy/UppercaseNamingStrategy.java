package pl.maciejwalkowiak.plist.strategy;

import pl.maciejwalkowiak.plist.NamingStrategy;

/**
 * Converts string into uppercase and replaces camel case into underscores
 */
public class UppercaseNamingStrategy implements NamingStrategy {
	public String fieldNameToKey(String toConvert) {
		return addUnderscores(toConvert).toUpperCase();
	}

	protected static String addUnderscores(String name) {
		StringBuffer buf = new StringBuffer(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if (
					Character.isLowerCase(buf.charAt(i - 1)) &&
							Character.isUpperCase(buf.charAt(i)) &&
							Character.isLowerCase(buf.charAt(i + 1))
					) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase();
	}
}
