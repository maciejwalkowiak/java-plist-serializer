package pl.maciejwalkowiak.plist;

/**
 * Helper class used to wrap serialized objects with keys.
 * You can create instance of it by calling {@link #wrap(Object)} method
 *
 * @author Maciej Walkowiak
 */
public class XMLHelper {
	private XMLHelper() {}

	private Object objectToWrap;

	public static XMLHelper wrap(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("object to wrap cannot be null");
		}

		XMLHelper xmlHelper = new XMLHelper();
		xmlHelper.objectToWrap = object;

		return xmlHelper;
	}

	public String with(String wrappedWith) {
		if (wrappedWith == null || wrappedWith.isEmpty()) {
			throw new IllegalArgumentException("wrappedWith cannot be null or empty");
		}

		StringBuilder result = new StringBuilder();

		result.append("<").append(wrappedWith).append(">").append(this.objectToWrap).append("</").append(wrappedWith).append(">");

		return result.toString();
	}
}
