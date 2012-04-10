package pl.maciejwalkowiak.plist.handler;

import pl.maciejwalkowiak.plist.PlistSerializerImpl;
import pl.maciejwalkowiak.plist.XMLHelper;

import java.util.Collection;

public class CollectionHandler extends AbstractHandler {

	public CollectionHandler(PlistSerializerImpl plistSerializer) {
		super(plistSerializer);
	}

	public boolean supports(Object object) {
		return object instanceof Collection || isArray(object);
	}

	public String doHandle(Object object) {
		StringBuilder result = new StringBuilder();

		Collection col = (Collection) object;

		for (Object o: col) {
			result.append(plistSerializer.serialize(o));
		}

		return XMLHelper.wrap(result).with("array");
	}

	private boolean isArray(final Object obj) {
		return obj instanceof Object[] || obj instanceof boolean[] ||
				obj instanceof byte[] || obj instanceof short[] ||
				obj instanceof char[] || obj instanceof int[] ||
				obj instanceof long[] || obj instanceof float[] ||
				obj instanceof double[];
	}
}
