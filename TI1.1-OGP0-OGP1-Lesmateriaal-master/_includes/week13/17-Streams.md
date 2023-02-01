## 17. Streams

Till now we used a for/while or foreach loop to print all the items in a collection such as `Array` and `ArrayList` on the screen.

```java
    	int[] numbers = {5,2,4,1,45};
        for (int number : numbers) {
            System.out.println(number);
        }
```

### 17.1 Streams

The Java class [Arrays](https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html) provides a method for processing a collection as a stream.

```java
        int[] numbers = {5,2,4,1,45};
        Arrays.stream(numbers)
            .forEach(number -> System.out.println(number));
```

```output
5
2
4
1
45
```

With streams it's also possible to filter data. Therefore an if statement inside a loop in not necessary. 

```java
        int[] numbers = {5,2,4,1,45};
        Arrays.stream(numbers)
            .filter(number -> number < 10)
            .forEach(number -> System.out.println(number));
```

```output
5
2
4
1
```

The same can also be done in a two- and multi-dimensional Arrays.  In this case, the first stream call generates an input stream containing one-dimensional Array. Each one-dimensional Array can also be converted into a value input stream.

```java
        int rows = 2;
        int columns = 3;
        int[][] table = new int[rows][columns];

        Arrays.stream(table)
                .forEach(row -> {
                    Arrays.stream(row).forEach(column -> System.out.print(column + " "));
                    System.out.println();
                });
```

With collections such as `ArrayList` we also can use streams, such as in the following example

```java
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,4,2,4,23));
        numbers.forEach(number -> System.out.println(number));
```



{% include week11/exercise/043.md %}
{: .exercises }