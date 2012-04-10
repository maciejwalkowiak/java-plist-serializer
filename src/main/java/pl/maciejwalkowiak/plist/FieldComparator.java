package pl.maciejwalkowiak.plist;

import java.lang.reflect.Field;
import java.util.Comparator;

public class FieldComparator implements Comparator<Field> {
	public int compare(Field field, Field field1) {
		return field.getName().compareTo(field1.getName());
	}
}
