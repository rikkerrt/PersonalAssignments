### 17.4. ComboBox

The [`ComboBox`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ComboBox.html) control enables users to choose an option from a predefined list of choices, or type in another value if none of the predefined choices matches what the user wants to select. The `ComboBox` control is represented by the class `javafx.scene.control.ComboBox`. 

You create a `ComboBox` simply by creating a new instance of the `ComboBox` class. Here is a `ComboBox` instantiation example:

```java
ComboBox comboBox = new ComboBox();
```

You can add choices to a `ComboBox` by obtaining its item collection and add items to it. Here is an example that adds choices to a  `ComboBox`:

```java
comboBox.getItems().add("Choice 1");
comboBox.getItems().add("Choice 2");
comboBox.getItems().add("Choice 3");
```

To make a `ComboBox` visible you must add it to the scene. This means that you must add the `ComboBox` to a `Scene` object or to some layout component which is then attached to the `Scene` object.

Here is an example showing how to add a `ComboBox` to the scene:

```java
public void start(Stage stage) {
    ComboBox comboBox = new ComboBox();

    comboBox.getItems().add("Choice 1");
    comboBox.getItems().add("Choice 2");
    comboBox.getItems().add("Choice 3");

    HBox hbox = new HBox(comboBox);

    Scene scene = new Scene(hbox, 200, 120);
    stage.setScene(scene);
    stage.show();
}
```

The application resulting from running this example would look similar to this:

![ComboBox](images/17_4_ComboBox.png)

You can read the selected value of a `ComboBox` via its `getValue` method. If no choice is selected, the `getValue` method returns null. Here is an example of calling `getValue`:

```java
String value = (String) comboBox.getValue();
```

A `ComboBox` is not editable by default. That means, that by default the user cannot enter anything themselves, but only choose from the predefined list of options. To make a `ComboBox` editable you must call the `setEditable` method of the `ComboBox`. Here is an example making a `ComboBox` editable:

```java
comboBox.setEditable(true);
```

Once the `ComboBox` is editable the user can type in values into the `ComboBox`. The entered value is also read via the `getValue` method as explained earlier. The following screenthot shows a `ComboBox` which is editable, and with a custom value entered:

![ComboBox Editable](images/17_4_ComboBoxEdit.png)