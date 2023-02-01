## 19. TableView

The [`TableView`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html) control enables users to create a Table of multiple rows and column. The `TableView` control is represented by the class `javafx.scene.control.TableView`. The vertical items are called columns and are represented by the JavaFX control [`TableColumn`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html), the horizontal lines are called rows and are represented by the JavaFX control [`TableRow`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableRow.html). The `TableView` component can be usefull to display multiple object with multiple values, such as an address book.

### 19.1. Defining a TableView

In this chapter we are creating an address book using the `TableView`

![Address Book](images/19_TableView_AddressBook.png)

The screen capture is created using the following code:

```java
private TableView table = new TableView();

    @Override
    public void start(Stage stage)  {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View");
        stage.setWidth(400);
        table.setPrefWidth(350);
        stage.setHeight(500);

        Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setPrefWidth(100);
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setPrefWidth(100);
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
```

The table control is created by instantiating the class `TableView`. In the example it is added to the VBox layout container, however, you can add it directly to the application scene.
The example defines three columns to store the following information in an address book: a contact's *first name* and *last name*, and an *email address*. The columns are created by using the class `TableColumn`.
The `getColumns` method of the class `TableView`  adds the previously created columns to the table. In your applications, you can use this method to dynamically add and remove columns.

You can manage visibility of the columns by calling the method `setVisible`. For example, if the logic of your application requires hiding user email addresses, you can implement this task as follows: 

```java
emailCol.setVisible(false)
```

When the structure of your data requires a more complicated representation, you can create nested columns.

For example, suppose that the contacts in the address book have two email accounts. Then you need two columns to show the primary and the secondary email addresses. Create two subcolumns, and call the method `getColumns` on the object `emailCol` as shown in the example.

```java
TableColumn firstEmailCol = new TableColumn("Primary");
TableColumn secondEmailCol = new TableColumn("Secondary");

emailCol.getColumns().addAll(firstEmailCol, secondEmailCol);
```

After you have added these lines to code, and compiled and run the application code, the table appears as shown in the figure down here.

![TableView combined columns](images/19_TableView_AddressBook2.png)

Although the table is added to the application, the standard caption *No content in table* appears, because no data is defined. Instead of showing this caption, you can use the method `setPlaceholder` to specify a `Node` object to appear in an empty table.

### 19.2. Data Model

When you create a table in a JavaFX application, it is a best practice to implement a class that defines the data model and provides methods and fields to further work with the table. The following code creates the Person class to define data in an address book.

```java
public class Person {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
    }

    public String getFirstName() {
        this.return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return this.lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
```

The *firstName*, *lastName*, and *email* `String` properties are created to enable the referencing of a particular data element. Additionally, the `get` and `set` methods are provided for each data element. Thus, for example, the `getFirstName` method returns the value of the `firstName` attribute, and the `setFirstName` method specifies a value for this attribute.

When the data model is outlined in the class `Person`, you can create an `ObservableList` and define as many data rows as you would like to show in your table. The code fragment implements this task.

```java
ObservableList<Person> data = FXCollections.observableArrayList(
    new Person("Jacob", "Smith", "jacob.smith@example.com"),
    new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
    new Person("Ethan", "Williams", "ethan.williams@example.com"),
    new Person("Emma", "Jones", "emma.jones@example.com"),
    new Person("Michael", "Brown", "michael.brown@example.com")
);
```

The next step is to associate the data with the table columns. You can do this through the properties defined for each data element, as shown here.

```java
firstNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
lastNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
emailCol.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));
```

The method `setCellValueFactory` specifies a cell factory for each column. The cell factories are implemented by using the class `PropertyValueFactory`, which uses the *firstName*, *lastName*, and *email* properties of the table columns as references to the corresponding methods of the class `Person`.

When the data model is defined, and the data is added and associated with the columns, you can add the data to the table by using the setItems method of the class `TableView`: `table.setItems(data);`

Because the object `ObservableList` enables the tracking of any changes to its elements, the `TableView` content automatically updates whenever the data changes.

```java
private TableView table = new TableView();
private ObservableList<Person> data =
        FXCollections.observableArrayList(
                new Person("Jacob", "Smith", "jacob.smith@example.com"),
                new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                new Person("Ethan", "Williams", "ethan.williams@example.com"),
                new Person("Emma", "Jones", "emma.jones@example.com"),
                new Person("Michael", "Brown", "michael.brown@example.com")
        );

@Override
public void start(Stage stage)  {
    Scene scene = new Scene(new Group());
    stage.setTitle("Table View");
    stage.setWidth(410);
    table.setPrefWidth(370);
    stage.setHeight(500);

    Label label = new Label("Address Book");
    label.setFont(new Font("Arial", 20));

    table.setEditable(true);

    TableColumn firstNameCol = new TableColumn("First Name");
    firstNameCol.setPrefWidth(100);
    firstNameCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("firstName"));
    TableColumn lastNameCol = new TableColumn("Last Name");
    lastNameCol.setPrefWidth(100);
    lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("lastName"));
    TableColumn emailCol = new TableColumn("Email");
    emailCol.setMinWidth(200);
    emailCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("email"));

    table.setItems(data);
    table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

    VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(label, table);

    ((Group) scene.getRoot()).getChildren().addAll(vbox);

    stage.setScene(scene);
    stage.show();
}
```

When you compile and run this application code, the following screen will appear:

![Filled Addressbook](images/19_2_AddressBook-filled.png)

### 19.3. Modify data

Till now we only can display static data in a TableView. The next step is to edit the data in the `TableView`.

For add new data to the `TableView` you can use text fields to enter new values into the First Name, Last Name, and Email columns. The Text Field control enables your application to receive text input from a user. The example below creates three text fields, defines the prompt text for each field, and creates the *Add* button.

```java
TextField addFirstName = new TextField();
addFirstName.setPromptText("First Name");
addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
TextField addLastName = new TextField();
addLastName.setMaxWidth(lastNameCol.getPrefWidth());
addLastName.setPromptText("Last Name");
TextField addEmail = new TextField();
addEmail.setMaxWidth(emailCol.getPrefWidth());
addEmail.setPromptText("Email");

Button addButton = new Button("Add");
addButton.setOnAction(new EventHandler<ActionEvent>() {
    (e) -> {
        data.add(new Person(
            addFirstName.getText(),
            addLastName.getText(),
            addEmail.getText()
        ));
        addFirstName.clear();
        addLastName.clear();
        addEmail.clear();
    }
});
```

When a user clicks the *Add* button, the values entered in the text fields are included in a `Person` constructor and added to the data `ObservableList`. Thus, the new entry with contact information appears in the table.

![Addressbook Add](images/19_3_AddressBook-add.png)

To delete a row from the dataset, you can implement it by create a `Button` which has the following `EventHandler` attached:

```java
deleteButton.setOnAction(e -> {
    Person selectedItem = table.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        table.getItems().remove(selectedItem);
    }
});
```

The code above here deletes the selected row from the `ObservableList`. You can do many more actions on the `TableView` such as editing, sorting and filtering, but they are outside of the scope of this course.

{% include week12/exercise/FX_004.md %}
{: .exercises }