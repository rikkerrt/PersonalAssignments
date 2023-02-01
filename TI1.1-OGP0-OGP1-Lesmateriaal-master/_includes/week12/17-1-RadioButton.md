### 17.1. RadioButtons

A [`RadioButton`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/RadioButton.html) is a button that can be selected or not selected. The `RadioButton` cannot be "deselected" once it's selected. To deselect it, select another radiobutton in the same radiobutton group

You create a `RadioButton` using its constructor. Here is a `RadioButton` instantiation example:

```java
RadioButton teacherRadioButton = new RadioButton("Teacher");
```

The `String` passed as parameter to the `RadioButton` constructor is displayed next to the `RadioButton`.

To make a `RadioButton` visible you must add it to the scene graph of your application. This means adding the `RadioButton` to a `Scene`, or as child of a layout which is attached to a `Scene` object.

Here is an example that attaches a `RadioButton` to the scene:

```java
public void start(Stage stage) {
    RadioButton teacherRadioButton = new RadioButton("Teacher");

    HBox hbox = new HBox(teacherRadioButton);

    Scene scene = new Scene(hbox, 200, 100);
    stage.setScene(scene);
    stage.show();
}
```

The application resulting from running this example looks like this:

![RadioButton](images/17_1_RadioButton1.png)

The `RadioButton` class has a method named `isSelected` which lets you determine if the `RadioButton` is selected or not. The `isSelected` method returns a boolean with the value true if the `RadioButton` is selected, and false if not. Here is an example:

```java
boolean isSelected = teacherRadioButton.isSelected();
```

#### 17.1.1. Combining RadioButtons in groups

Radio buttons are typically used in a group to present several mutually exclusive options. Groups are defined by using the object [`ToggleGroup`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ToggleGroup.html) from the JavaFX library. When `ToggleGroup` is used, only one radio button inside the group can be selected. 

In the example above we create two groups of RadioButtons in order to have two inputs. The following code is used

```java
public void start(Stage stage) {

    // Position section
    ToggleGroup positionToggleGroup = new ToggleGroup();
    Label positionLabel = new Label("Position:");

    RadioButton teacherRB = new RadioButton("Teacher");
    teacherRB.setToggleGroup(positionToggleGroup);
    teacherRB.setSelected(true);

    RadioButton assistantRB = new RadioButton("Assistant");
    assistantRB.setToggleGroup(positionToggleGroup);

    RadioButton studentRB = new RadioButton("Student");
    studentRB.setToggleGroup(positionToggleGroup);

    VBox positionVBox = new VBox();
    positionVBox.getChildren().addAll(positionLabel, teacherRB, assistantRB, studentRB);

    // Study section
    ToggleGroup studyToggleGroup = new ToggleGroup();
    Label studyLabel = new Label("Study:");

    RadioButton compEngRB = new RadioButton("Computer Engineering");
    compEngRB.setToggleGroup(studyToggleGroup);
    compEngRB.setSelected(true);

    RadioButton computerScienceRB = new RadioButton("Computer Science");
    computerScienceRB.setToggleGroup(studyToggleGroup);

    VBox studyVBox = new VBox();
    studyVBox.getChildren().addAll(studyLabel, compEngRB, computerScienceRB);

    // Add everything towards the UI
    BorderPane borderPane = new BorderPane();
    HBox hBox = new HBox();
    hBox.getChildren().addAll(positionVBox, studyVBox);
    borderPane.setCenter(hBox);

    Scene firstScene = new Scene(borderPane);
    stage.setScene(firstScene);
    stage.show();
}
```

The application resulting from running this example looks like this:

![RadioButton](images/17_1_RadioButton.png)

The selected radio button can be found by using the method `isSelected` on the object `RadioButton`, which returns a `Boolean` value, or the selected object `RadioButton` can be found using the method `getSelectedToggle` on the object `ToggleGroup`. If no RadioButton is selected the `getSelectedToggle` method returns null .

```java
RadioButton selectedGroup = (RadioButton)studyToggleGroup.getSelectedToggle();
System.out.println(selectedGroup.getText());
```

It's also possible to add `EventHandler` to see if the selection is changed like in the example below, which displays a pop-up when *Computer Engineering* is selected.

```java
compEngRB.setOnAction(e -> {
    if (compEngRB.isSelected()) {
        new Alert(Alert.AlertType.INFORMATION, "TI 4 all!", ButtonType.OK).show();
    }
});
```

But sometimes it is more convenient to create an `EventHandler` on the `ToggleGroup`. This allows you to only create one `EventHandler` for the `ToggleGroup`. You can implement it using the following code

```java
studyToggleGroup.selectedToggleProperty().addListener(
                (ov, old_toggle, new_toggle) -> {
                    if (studyToggleGroup.getSelectedToggle() != null) {
                        String text = ((RadioButton)studyToggleGroup.getSelectedToggle()).getText();
                    }
                });
```

{% include week12/exercise/FX_001.md %}
{: .exercises }