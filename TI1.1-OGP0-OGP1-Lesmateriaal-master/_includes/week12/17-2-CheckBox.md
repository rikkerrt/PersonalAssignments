### 17.2. CheckBoxes

A [`CheckBox`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/CheckBox.html) is a button which can be in two different states: *Selected and not selected*. The `CheckBox` control is represented by the class `javafx.scene.control.CheckBox`.

You create a `CheckBox` control via the `CheckBox` constructor. Here is a `CheckBox` instantiation example:

```java
CheckBox greenCheckBox = new CheckBox("Green");
```

The `String` passed to the `CheckBox` constructor is displayed next to the `CheckBox` control.

To make a `CheckBox` control visible you must add it to the scene graph of your application. That means adding the `CheckBox` control to a `Scene` object, or to some layout component which is itself added to a `Scene` object.

Here is an example showing how to add a `CheckBox` to the scene graph:

```java
public void start(Stage stage) {

    // Checkboxes
    Label examLabel = new Label("Selected exams:");
    CheckBox ogp1CheckBox = new CheckBox("OGP-1");
    CheckBox hwiCheckbox = new CheckBox("Hardware interfacing");
    CheckBox wiskundeCheckBox = new CheckBox("Wiskunde");

    VBox selectedCourse = new VBox();
    selectedCourse.getChildren().addAll(examLabel, ogp1CheckBox, hwiCheckbox, wiskundeCheckBox);

    Scene firstScene = new Scene(selectedCourse);
    stage.setScene(firstScene);
    stage.show();
}
```

The application resulting from running this code looks like this:

![Checkboxes](images/17_2_CheckBoxes.png)

The method `isSelected` can be used to check if the Checkbox is selected. It's also possible to add `EventHandler` to see if the selection is changed like in the example below

```java
wiskundeCheckBox.setOnAction(e -> {
    if (wiskundeCheckBox.isSelected()) {
        new Alert(Alert.AlertType.INFORMATION, "Math 4 all!", ButtonType.OK).show();
    }
});
```