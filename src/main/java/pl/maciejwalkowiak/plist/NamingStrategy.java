package pl.maciejwalkowiak.plist;

/**
 * NamingStrategy for object fields and map keys conversion into plist &lt;key&gt;.
 * Idea is exactly the same as for Hibernate namings strategy.
 *
 */
public interface NamingStrategy {
	String fieldNameToKey(String toConvert);
}
