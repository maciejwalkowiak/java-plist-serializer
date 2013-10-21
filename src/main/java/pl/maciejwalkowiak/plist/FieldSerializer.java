/*
 * Copyright (c) 2012 Maciej Walkowiak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pl.maciejwalkowiak.plist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import pl.maciejwalkowiak.plist.annotation.PlistAlias;
import pl.maciejwalkowiak.plist.annotation.PlistIgnore;
import pl.maciejwalkowiak.plist.handler.Handler;
import pl.maciejwalkowiak.plist.handler.HandlerNotFoundException;
import pl.maciejwalkowiak.plist.handler.HandlerWrapper;
import pl.maciejwalkowiak.plist.handler.PlistSerializationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Maciej Walkowiak
 */
public class FieldSerializer {
	private static final Logger logger = LoggerFactory.getLogger(FieldSerializer.class);

	private HandlerWrapper handlerWrapper;

	private PlistSerializerImpl plistSerializer;

	private FieldComparator fieldComparator = new FieldComparator();

	public FieldSerializer(HandlerWrapper handlerWrapper, PlistSerializerImpl plistSerializer) {
		this.handlerWrapper = handlerWrapper;
		this.plistSerializer = plistSerializer;
	}

	public String serializeFields(Object objectToConvert) {
		StringBuilder result = new StringBuilder();
		List<Field> fields = Arrays.asList(objectToConvert.getClass().getDeclaredFields());

		Collections.sort(fields, fieldComparator);
		for (Field field : fields) {
			result.append(serializeField(objectToConvert, field));
		}

		return XMLHelper.wrap(result).with("dict");
	}

	private String serializeField(Object o, Field field) {
		String result = "";

		int modifiers = field.getModifiers();

		if (!Modifier.isTransient(modifiers) && !Modifier.isStatic(modifiers)) {
			ReflectionUtils.makeAccessible(field);

			if (!field.isAnnotationPresent(PlistIgnore.class)) {
				result = processField(field, o).toString();
			} else {
				logger.debug("field {} is ignored", field.getName());
			}
		}

		return result;
	}

	private StringBuilder processField(Field field, Object object) {
		StringBuilder result = new StringBuilder();

		Object fieldValue = getFieldValue(field, object);

		if (fieldValue != null) {
			result.append(createKey(field));

			try {
				Handler handler = handlerWrapper.getHandlerForObject(fieldValue);

				result.append(handler.handle(fieldValue));
			} catch (HandlerNotFoundException e) {
				result.append(plistSerializer.serialize(fieldValue));
			}
		}

		return result;
	}

	private Object getFieldValue(Field field, Object object) {
		try {
			return field.get(object);
		} catch (IllegalAccessException e) {
			throw new PlistSerializationException("Cannot access field " + field.getName());
		}
	}

	private String createKey(Field field) {
		String keyToWrap;

		if (field.isAnnotationPresent(PlistAlias.class)) {
			PlistAlias alias = field.getAnnotation(PlistAlias.class);

			if (alias.followStrategy()) {
				keyToWrap = plistSerializer.getNamingStrategy().fieldNameToKey(alias.value());
			} else {
				keyToWrap = alias.value();
			}
		} else {
			keyToWrap = plistSerializer.getNamingStrategy().fieldNameToKey(field.getName());
		}

		return XMLHelper.wrap(keyToWrap).with("key");
	}
}
