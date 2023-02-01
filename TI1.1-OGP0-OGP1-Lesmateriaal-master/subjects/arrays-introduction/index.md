---
layout: default
title: Introduction to arrays
meta: Put extra info here, like if there any subjects required for this subject
todo: what should be improved in this chapter
---
{% include licence.md %}

> We have prepared several video clips to introduce araays. Watch those videos and do the exercises in the videos. Then come back and read the explanation and summary below.

### Arrays

Sometimes we don't know beforehand how many input values our algorithm will handle because that is determined at run time. For example, it might depend on what the user enters.
Storing all these values in separate variables would be problematic, because how many variables would you need to use?

Therefore we store such values in a *single* variable of type *'array'* of a given data type (e.g. an array of integers). Let's call our array variable `a`. The individual values in this array can be accessed using their *index*, i.e. their position in the array, and this index is specified between '[ ]' brackets. For example, `a[3]` would be the value at index 3 in the array. We can access each index in the array in any order we want (random order access) because each entry in the array is of the same data type and therefore of the same size, so positions in the array can easily be calculated by the compiler.

Arrays in the education software tool LARP start with index 1, whereas arrays in most programming languages (including Java) start with index 0, so this is something to take care of when moving from a flowchart in LARP to the equivalent Java code in IntelliJ.

We would like our arrays (e.g. in our flowcharts) to automatically grow in size if we add more values to them, and fortunately they can. In a tool like LARP this happens automatically when you add a value at a specific index. In Java code we can achieve the same effect by using an `ArrayList` data type.

Note that the traditional array data type in Java has a *fixed* length which is determined when it is created. For example, if such an array is created with a size of 5, you can store no more than 5 items in it. We will therefore mostly use ArrayList in Java.

{% include_relative exercises/001.md %}
{% include_relative exercises/002.md %}
{% include_relative exercises/003.md %}
{% include_relative exercises/004.md %}
{: .exercises }
