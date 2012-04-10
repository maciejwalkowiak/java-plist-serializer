package pl.maciejwalkowiak.plist;

import pl.maciejwalkowiak.plist.annotation.PlistAlias;
import pl.maciejwalkowiak.plist.annotation.PlistIgnore;

public class ClassWithAnnotations {
	@PlistIgnore
	private String ignoredField = "i am ignored";

	@PlistAlias("trick")
	private String fieldWithAlias = "i am renamed";
}
