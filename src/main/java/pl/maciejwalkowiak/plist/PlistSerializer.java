package pl.maciejwalkowiak.plist;

/**
 * Main java-plist-serializer interface, representing a component that serializes Java objects to Apple Property Lists
 * {@see http://developer.apple.com/library/mac/#documentation/Cocoa/Conceptual/PropertyLists/Introduction/Introduction.html}
 *
 *
 * @author Maciej Walkowiak
 */
public interface PlistSerializer {
	static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\">";
	static final String FOOTER = "</plist>";

	/**
	 * Serializes object to plist and creates valid XML by wrapping it around XML header,
	 * plist DOCTYPE and &lt;plist&gt; XML tag.
	 *
	 * @param objectToConvert object to serialize
	 * @return a String containing valid XML
	 */
	String toXmlPlist(Object objectToConvert);

	/**
	 * Serializes object into plist XML
	 *
	 * @param objectToConvert object to serialize
	 * @return a String containing XML representation without XML header, DOCTYPE
	 */
	String serialize(Object objectToConvert);
}
