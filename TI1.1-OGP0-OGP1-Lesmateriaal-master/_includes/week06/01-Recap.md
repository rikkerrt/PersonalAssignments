

### 6.1.1 Character strings are immutable
The String objects of Java, as with the Money class objects, are unchangeable, *immutable*. If for example a new object is concatenated to the end of a character string with the + operator, the original character string doesn't become longer, but a new character string object is born:

```java
String characterString = "test";
characterString + "tail";

System.out.println( characterString );  // test
```

We see that the character string cannot be changed, but we can add the value of the new character string - that was born from concatenation - to the old variable:

```java
String characterString = "test";
characterString = characterString + "tail";   // or characterString += "tail";

System.out.println( characterString );  // testtail
```

Now the variable `characterString` refers to a new character string object, which was created by combining the previous character string value the variable referred to ("test") with the "tail" character string. Nothing refers to the "test" character string object anymore.

