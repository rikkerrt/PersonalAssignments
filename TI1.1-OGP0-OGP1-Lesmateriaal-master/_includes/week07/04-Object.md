## 4. Object

In our course, we have been using frequently the method `public String toString()` when we wanted to print an object in the shape of a string. Calling the method without setting it up properly does usually show weird text. We can have a look at the class Book, which does not contain the method `public String toString()` yet, and see what happens when the program uses the method `System.out.println()` and tries to print an object of Book class.

```java
public class Book {
    private String name;
    private int publishingYear;

    public Book(String name, int publishingYear) {
        this.name = name;
        this.publishingYear = publishingYear;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishingYear() {
        return this.publishingYear;
    }
}
            
Book objectBook = new Book("Object book", 2000);
System.out.println(objectBook);
```

if we take an object of `Book` class and use it as the parameter of the method `System.out.println()`, our program does not print an error message. Our program does not crash, and instead of reading an error message, we notice an interesting print output. The print output contains the name of the class, `Book`, plus an indefinite String which follows a @ character. Notice that when we call `System.out.println(objectBook)` Java calls `System.out.println(objectBook.toString())`, in fact, but this does not cause an error.

The explanation is related to the way Java classes are built. Each Java class automatically *inherits* the `Object` class, which contains a set of methods that are useful to each Java class. Heritage means that our class has access to the features and functions defined in the inherited class. Among the others, the class `Object` contains the method `toString`, which is inherited by the classes we create.

The `toString` method inherited from the object class is not usually the one we'd want. That's why we will want to replace it with one we make personally. Let us add the method `public String toString()` to our `Book` class. This method will replace the `toString` method inherited from the `Object` class.

```java
public class Book {
    private String name;
    private int publishingYear;

    public Book(String name, int publishingYear) {
        this.name = name;
        this.publishingYear = publishingYear;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishingYear() {
        return this.publishingYear;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.publishingYear + ")";
    }
}
```

If now we create an object instance, and we set it into the method `print`, we notice that the `toString` method of the class `Book` produces a string.

```java
Book objectBook = new Book("Object book", 2000);
System.out.println(objectBook);
```

```output
Object book (2000)
```

Above the `toString` method of class `Book` we see the `@Override` *annotation*. We use annotations to give guidelines to both the compiler and the reader about how to relate to the methods. The `@Override` annotation tells that the following method replaces the one defined inside the inherited class. If we don't add an annotation to the method we replace, the compiler gives us a *warning*, however avoiding writing annotations is not a mistake.

There are also other useful methods we inherit from the Object class. Let us now get acquainted with the methods equals and hashCode.

### 4.1 Equals Method

The equals method is used to compare two objects. The method is particularly used when we compare two String objects.

```java
Scanner reader = new Scanner(System.in);

System.out.print("Write password: ");
String password = reader.nextLine();

if (password.equals("password")) {
    System.out.println("Right!");
} else {
    System.out.println("Wrong!");
}
```

```output         
Write password: mightycarrot
Wrong!
```
            
The `equals` method is defined in the `Object` class, and it makes sure that both the parameter object and the compared object have the same reference. In other words, by default the method makes sure that we are dealing with one unique object. If the reference is the same, the method returns `true`, otherwise `false`. The following example should clarify the concept. The class `Book` doesn't implement its own `equals` method, and therefore it uses the one created by the `Object` class.

```java
Book objectBook = new Book("Objectbook", 2000);
Book anotherObjectBook = objectBook;

if (objectBook.equals(objectBook)) {
    System.out.println("The books were the same");
} else {
    System.out.println("The books were not the same");
}

// Now we create an object with the same contents, which is however a different, independent object
anotherObjectBook = new Book("Objectbook", 2000);

if (objectBook.equals(anotherObjectBook)) {
    System.out.println("The books were the same");
} else {
    System.out.println("The books were not the same");
}
```
   
Print output:

```output
The books were the same
The books were not the same
```

Even if the internal structure of both `Book` objects (i.e. the object variable values) is exactly the same, only the first comparison prints "The books were the same". This depends on the fact that only in the first case also the references were the same, i.e. we were comparing an object with itself. In the second example, we had two different objects even though they both had the same values.

When we use the equals method to compare strings, it works as we want it to: it identifies two strings as equal if the have the same contents even though they are two different objects. In fact, the default equals method is replaced with a new implementation in the class `String`.

We want that book comparison happened against name and year. We replace the equals method in the Object class with an implementation in the class `Book`. The equals method has to make sure whether the object is the same as the one received as parameter. First, we define a method according to which all the objects are the same.

```java
public boolean equals(Object object) {
    return true;
}
```

Our method is a little too optimistic, so let us change its functionality slightly. Let us define that the objects are not the same if the parameter object is null or if the the two object types are different. We can find out the type of an object with the method `getClass()` (which is denifed in the class `oject`). Otherwise, we expect that the objects are the same.

```java
public boolean equals(Object object) {
    if (object == null) {
        return false;
    }

    if (this.getClass() != object.getClass()) {
        return false;
    }

    return true;
}
```

The `equals` method finds out the class difference of two objects, but it is not able to distinguish two similar objects from each other. In order to compare our object with the object we received as parameter, and whose reference is Object type, we have to change the type of the Object reference. The reference type can be changed if and only if the object type is really such as we are converting it into. Type casting happens by specifying the desired class within brackets on the right side of the assignment sentence:

```java
WantedType variable = (WantedType) oldVariable;
```

Type casting is possible because we know two objects are the same type. If they are different type, the above `getClass` method returns false. Let us change the `Object` parameter received with the equals method into `Book` type, and let us identify two different books against their publishing year. The books are otherwise the same.

```java
public boolean equals(Object object) {
    if (object == null) {
        return false;
    }

    if (getClass() != object.getClass()) {
        return false;
    }

    Book compared = (Book) object;

    if(this.publishingYear != compared.getPublishingYear()) {
        return false;
    }

    return true;
}
```

Now, our comparison method is able to distinguish books against their publishing year. Wa want to check still that our book names are the same, and our own book name is not `null`.

```java
public boolean equals(Object object) {
    if (object == null) {
        return false;
    }

    if (getClass() != object.getClass()) {
        return false;
    }

    Book compared = (Book) object;

    if (this.publishingYear != compared.getPublishingYear()) {
        return false;
    }

    if (this.name == null || !this.name.equals(compared.getName())) {
        return false;
    }

    return true;
}
```

Excellent, we have got a method for comparison which works, finally! Below is our `Book` class as it looks like at the moment.

```java
public class Book {
    private String name;
    private int publishingYear;

    public Book(String name, int publishingYear) {
        this.name = name;
        this.publishingYear = publishingYear;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishingYear() {
        return this.publishingYear;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.publishingYear + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        Book compared = (Book) object;

        if (this.publishingYear != compared.getPublishingYear()) {
            return false;
        }

        if (this.name == null || !this.name.equals(compared.getName())) {
            return false;
        }

        return true;
    }
}
```

Now, our book comparison returns `true`, if the book contents are the same.

```java
Book objectBook = new Book("Objectbook", 2000);
Book anotherObjectBook = new Book("Objectbook", 2000);

if (objectBook.equals(anotherObjectBook)) {
    System.out.println("The books are the same");
} else {
    System.out.println("The books are not the same");
}
```

```output
The books are the same
```

#### 4.1.1 Equals and ArrayList

Various different Java made-up methods make use of the method `equals` to implement their search functionality. For instance, the contains mehod of class `ArrayList` compares objects through the `equals` method. Let us continue to use the `Book` class we defied for our examples. If our objects do not implement the method `equals`, we can't use the `contains` method, for instance. Try out the code below in two different book classes. The first class implements the equals method, the other does not.

```java
ArrayList<Book> books = new ArrayList<Book>();
Book objectBook = new Book("Objectbook", 2000);
books.add(objectBook);

if (books.contains(objectBook)) {
    System.out.println("The object book was found.");
}

objectBook = new Book("Objectbook", 2000);

if (!books.contains(objectBook)) {
    System.out.println("The object book was not found.");
}
```

### 4.2 HashCode Method

The `hashCode` method takes an object and returns a numeric value, i.e. a hash value. We need numeric values for instance when we use and object as `HashMap` keys. So far, we have been using only String and Integer objects as HashMap keys, and their `hashCode` method is implemented by default. Let us make an example where it is not so: let us continue with our book examples and let us start to take note of our books on loan. We want to implement our bookkeeping through Hashmap. The key is the book, and the book's value is a string, which tells the loaner's name:

```java
HashMap<Book, String> loaners = new HashMap<Book, String>();

Book objectbook = new Book("Objectbook", 2000);
loaners.put( objectbook, "Pekka" );
loaners.put( new Book("Test Driven Development",1999), "Arto" );

System.out.println( loaners.get( objectbook ) );
System.out.println( loaners.get( new Book("Objectbook", 2000) );
System.out.println( loaners.get( new Book("Test Driven Development", 1999) );
```

Print output:

```output
Pekka
null
null
```

We can find the loaner by searching against the same object which was given as `HashMap` key with the `put` method. However, if our search item is the same book but a different object, we are not able to find its loaner and we are retured with a `null` reference. This is again due to the default implementation of the `hashCode` method of `Object` class. The default implementation creates an index based on the reference; this means that different objects with the same content receive different method `hashCode` outputs, and therefore it is not possible to find the right place of the object in the HashMap.

To be sure the `HashMap` worked in the way we want - i.e. it returned the loaner when the key is an object with the right *content* (not necessarily the same object as the original value) - the class which works as key must overwrite both the `equals` method and the `hashCode` method. The method must be overwritten in such a way, so that it would assign the same numeric value to all objects which have the same content. Some objects with different content may eventually be assigned the same hashCode; however, different content objects should be assigned the same hashCode as rarely as possible, if we want our `HashMap` to be efficient.

Previously, we have successfully used `String` objects as `HashMap` keys, and we can therefore say that the `String` class has a `hashCode` implementation which works as expected. Let us delegate the calculation to the String object.

```java
public int hashCode() {
    return this.name.hashCode();
}
```

The solution above is quite good; but if name is `null`, we are thrown a `NullPointerException`. We can fix this by setting the condition: if the value of the name variable is is `null`, return value 7. Seven is a value chosen casually, thirteen could have done as well.

```java
public int hashCode() {
    if (this.name == null) {
        return 7;
    }

    return this.name.hashCode();
}
```

We can still improve the method `hashCode` by taking into consideration the book publishing year, in our calculations:

```java
public int hashCode() {
    if (this.name == null) {
        return 7;
    }

    return this.publishingYear + this.name.hashCode();
}
```

An additional remark: the output of the method `hashCode` of `HashMap` key objects tells us their value slot in the hash construction, i.e. their index in the HashMap. You may now be wondering: "doesn't this lead to a situation where more than one object ends up with the same index in the HashMap?". The answer is yes and no. Even if the `hashCode` method gave the same value to two different objects, HashMaps are built in such way that various different objects may have the same index. In order to distinguish objects with the same index, the key objects of the HashMap must have implemented the method `equals`.

The final `Book` class now.

```java
public class Book {

    private String name;
    private int publishingYear;

    public Book(String name, int publishingYear) {
        this.name = name;
        this.publishingYear = publishingYear;
    }

    public String getName() {
        return this.name;
    }

    public int getPublishingYear() {
        return this.publishingYear;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.publishingYear + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        Book compared = (Book) object;

        if (this.publishingYear != compared.getPublishingYear()) {
            return false;
        }

        if (this.name == null || !this.name.equals(compared.getName())) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        if (this.name == null) {
            return 7;
        }

        return this.publishingYear + this.name.hashCode();
    }
}
```

Let us sum up everything again: in order to use a class as `HashMap` key, we have to define

- The `equals` method in a way that objects with the same content will return true when compared, whereas different-content objects shall return false
- The `hashCode` method in a way that it assigns the same value to all the objects whose content is regarded as similar

The methods `equals` and `hashCode` of our class `Book` fulfill these two conditions. Now, the problem we faced before is solved, and we can find out the book loaners:

```java
HashMap<Book, String> loaners = new HashMap<Book, String>();

Book objectbook = new Book("Objectbook", 2000);
loaners.put( objectbook, "Pekka" );
loaners.put( new Book("Test Driven Development",1999), "Arto" );

System.out.println( loaners.get( objectbook ) );
System.out.println( loaners.get( new Book("Objectbook", 2000) );
System.out.println( loaners.get( new Book("Test Driven Development", 1999) );
```

Print output:

```output
Pekka
Pekka
Arto
```

