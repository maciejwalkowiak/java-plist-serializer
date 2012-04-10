package pl.maciejwalkowiak.plist;

public class ClassWithReferenceToItself {
	private String someString;
	private ClassWithReferenceToItself self;

	public ClassWithReferenceToItself(String someString) {
		this.someString = someString;
		self = this;
	}
}
