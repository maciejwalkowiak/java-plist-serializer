package pl.maciejwalkowiak.plist.handler;

import pl.maciejwalkowiak.plist.PlistSerializerImpl;
import pl.maciejwalkowiak.plist.XMLHelper;

import java.util.Map;

public class MapHandler extends AbstractHandler {
	public MapHandler(PlistSerializerImpl plistSerializer) {
		super(plistSerializer);
	}

	@Override
	String doHandle(Object object) {
		Map<String, Object> map = (Map<String, Object>) object;

		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			result.append(XMLHelper.wrap(plistSerializer.getNamingStrategy().fieldNameToKey(entry.getKey())).with("key"));
			result.append(plistSerializer.serialize(entry.getValue()));
		}

		return XMLHelper.wrap(result).with("dict");
	}

	public boolean supports(Object object) {
		return object instanceof Map;
	}
}
