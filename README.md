## Java Objects to Property List Serializer

java-plist-serializer is a Java library that can be used to convert Java Objects into their Property List representation.

Usage is very simple:


    Comment comment = new Comment("maciej walkowiak", "hello plist");
    PlistSerializer serializer = new PlistSerializerImpl();

    String xml = serializer.serialize(comment);

Supported data types:

* Boolean, Short, Integer, Long, Float, Double and their primitive equivalents
* String
* java.util.Date
* java.util.Collection
* java.util.Map

When object passed to serialization is not of one fields included in that list - serializer iterates through its fields using reflection, serializes each field and wraps result into '''xml dict'''

