package pl.maciejwalkowiak.plist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.maciejwalkowiak.plist.handler.Handler;
import pl.maciejwalkowiak.plist.handler.HandlerWrapper;
import pl.maciejwalkowiak.plist.strategy.DefaultNamingStrategy;

import java.util.List;

/**
 * Implementation of {@link PlistSerializer} capable of serializing objects containing elements:
 * <ul>
 *     <li>basic types: {@link Integer}, {@link String}, {@link Boolean}, {@link Double}, {@link Short},
 *     {@link Long}, {@link Byte} as well as theirs primitive representation</li>
 *     <li>{@link java.util.Collection}</li>
 *     <li>{@link java.util.Map}</li>
 * </ul>
 *
 * Goes through all object fields and serializes them into plist entries. Fields can be ignored by annotating them with
 * {@link pl.maciejwalkowiak.plist.annotation.PlistIgnore} annotation. Its key name can be changed by annotation them with
 * {@link pl.maciejwalkowiak.plist.annotation.PlistAlias} annotation.
 *
 * Its possible to add handlers for additional objects by calling {@link #setAdditionalHandlers(java.util.List)}.
 *
 * For naming plist &lt;key&gt; {@link #namingStrategy} strategy can be set. By default {@link DefaultNamingStrategy} is used.
 *
 * @author Maciej Walkowiak
 */
public class PlistSerializerImpl implements PlistSerializer {
	private static final Logger logger = LoggerFactory.getLogger(PlistSerializerImpl.class);

	private HandlerWrapper handlerWrapper;
	private BasicObjectSerializer basicObjectSerializer;
	private FieldSerializer fieldSerializer;
	private NamingStrategy namingStrategy = new DefaultNamingStrategy();

	public PlistSerializerImpl(NamingStrategy namingStrategy) {
		this();
		this.namingStrategy = namingStrategy;
	}

	public PlistSerializerImpl() {
		this.handlerWrapper = new HandlerWrapper(this);
		this.fieldSerializer = new FieldSerializer(handlerWrapper, this);
		this.basicObjectSerializer = new BasicObjectSerializer(handlerWrapper);
	}

	public String toXmlPlist(Object objectToConvert) {
		StringBuilder result = new StringBuilder(HEADER);

		result.append(serialize(objectToConvert));

		result.append(FOOTER);

		return result.toString();
	}

	public String serialize(Object objectToConvert) {
		StringBuilder result = new StringBuilder();

		logger.debug("converting object = {}", objectToConvert);

		if (objectToConvert != null) {
			if (handlerWrapper.isSupported(objectToConvert)) {
				result.append(basicObjectSerializer.serializeBasicObject(objectToConvert));
			} else {
				result.append(fieldSerializer.serializeFields(objectToConvert));
			}
		}

		return result.toString();
	}

	public void setAdditionalHandlers(List<Handler> additionalHandlers) {
		handlerWrapper.addAdditionalHandlers(additionalHandlers);
	}

	public NamingStrategy getNamingStrategy() {
		return namingStrategy;
	}

	HandlerWrapper getHandlerWrapper() {
		return handlerWrapper;
	}
}
