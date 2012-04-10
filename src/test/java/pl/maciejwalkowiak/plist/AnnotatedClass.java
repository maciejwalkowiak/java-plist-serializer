package pl.maciejwalkowiak.plist;

import pl.maciejwalkowiak.plist.annotation.PlistAlias;

public class AnnotatedClass {
	@PlistAlias(value = "firstField", followStrategy = true)
	private String followingStrategy = "i am following";

	@PlistAlias(value = "secondField")
	private String notFollowingStrategy = "i am not following";
}
