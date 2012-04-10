## Java Objects to Property List Serializer

java-plist-serializer is a Java library that can be used to convert Java Objects into their Property List representation.

Usage is very simple:


    Comment comment = new Comment("maciej walkowiak", "hello plist");
    PlistSerializer serializer = new PlistSerializerImpl();

    String xml = serializer.serialize(comment); // creates xml just for serialized object
    String validXml = serializer.toXML(comment); // creates valid xml with header, dtd include

### Supported data types

Out of the box serializer supports fields with types:

* Boolean, Short, Integer, Long, Float, Double and their primitive equivalents
* String
* java.util.Date
* java.util.Collection
* java.util.Map

When object passed to serialization is not of one fields included in that list - serializer iterates through its fields using reflection, serializes each field and wraps result into <dict />.

#### Adding custom serializer

If there is a need to add serializer for another data type it can be done by implementing <code>pl.maciejwalkowiak.plist.handler.Handler</code> interface and then added to PlistSerializer by calling
<code>pl.maciejwalkowiak.plist.PlistSerializerImpl#setAdditionalHandlers</code>.

An example how to implement and add custom data type serializer can be found in test method: <code>PlistSerializerImplTest#testAdditionalHandler</code>

### Customization

There are few ways to customize XML output:

* object fields can be ignored by annotating them with <code>@PlistIgnore</code>
* object field by default is serialized to <code><key /></code> with field's name content. To provide custom key name use <code>@PlistAlias</code> annotation
* you can choose or define your own key naming strategy. Currently keys can be created with exactly the same name as field by using <code>DefaultNamingStrategy</code>
or fields can be changed to uppercase using <code>UppercaseNamingStrategy</code>. You can implement your own naming policy by implementing <code>pl.maciejwalkowiak.plist.NamingStrategy</code> and registering it by <code>PlistSerializerImpl</code> constructor argument

### Known issues

If you have cycle dependency in serialized object tree you will get StackOverFlowError unless you ignore reference by annotating with <code>@PlistIgnore</code>

