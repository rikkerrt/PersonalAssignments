### 17.5. ListView

The [`ListView`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html) control enables users to choose one or more options from a predefined list of choices. The `ListView` control is represented by the class `javafx.scene.control.ListView`.

You create a `ListView` simply by creating a new instance of the `ListView` class. Here is a `ListView` instantiation example:

```java
ListView listView = new ListView();
```

You can add items (options) to a `ListView` by obtaining its item collection and add items to it. Here is an example that adds items to a `ListView`:

```java
listView.getItems().add("Item 1");
listView.getItems().add("Item 2");
listView.getItems().add("Item 3");
```

To make a `ListView` visible you must add it to the `scene`. This means that you must add the `ListView` to a `Scene` object or to some layout component which is then attached to the `Scene` object.

Here is an example showing how to add a `ListView` to the scene:

```java
public void start(Stage stage)  {
    ListView listView = new ListView();

    listView.getItems().add("Item 1");
    listView.getItems().add("Item 2");
    listView.getItems().add("Item 3");

    HBox hbox = new HBox(listView);

    Scene scene = new Scene(hbox, 300, 120);
    stage.setScene(scene);
    stage.show();
}
```

The application resulting from running this example would look similar to this screenshot:

![ListView](images/17_5_ListView.png)

Like we noticed inside ComboBoxes, it's recommened to use an `OberservableList` when items can be changed.

Notice how the `ListView` shows multiple options by default. You can set a height and width for a `ListView`, but you cannot set explicitly how many items should be visible. The height determines that based on the height of each item displayed.

If there are more items in the `ListView` than can fit into its visiible area, the `ListView` will add scroll bars so the user can scroll up and down over the items.

You can read the selected indexes of a `ListView` via its `SelectionModel`. Here is an example showing how to read the selected indexes of a `ListView`:

```java
ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
```

The `OberservableList` will contain `Integer` objects representing the indexes of the selected items in the `ListView`.

Here is a full example with a button added which reads the selected items of the `ListView` when clicked:

```java
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ListViewExperiments extends Application  {


    @Override
    public void start(Stage stage)  {
        ListView listView = new ListView();
        listView.getItems().add("Item 1");
        listView.getItems().add("Item 2");
        listView.getItems().add("Item 3");

        Button button = new Button("Read Selected Value");

        button.setOnAction(event -> {
            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                System.out.println("o = " + o + " (" + o.getClass() + ")");
            }
        });

        VBox vBox = new VBox(listView, button);

        Scene scene = new Scene(vBox, 300, 120);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
```

To allow multiple items in the `ListView` to be selected you need to set the corresponding selection mode on the `ListView` selection model. Here is an example of setting the selection mode on the `ListView`:

```java
listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
```

Once you have set the `SelectionMode.MULTIPLE` on the `ListView` selection model, the user can select multiple items in the `ListView` by holding down *SHIFT* or *CTRL* keys when selecting additional items after the first selected item.

Here is a full example that shows how to set a `ListView` into multiple selection mode, including a button which when clicked will write out the indices of the selected items in the `ListView`:

```java
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ListViewExperiments extends Application  {

    @Override
    public void start(Stage stage)  {
        ListView listView = new ListView();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getItems().add("Item 1");
        listView.getItems().add("Item 2");
        listView.getItems().add("Item 3");

        Button button = new Button("Read Selected Value");

        button.setOnAction(event -> {
            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                System.out.println("o = " + o + " (" + o.getClass() + ")");
            }
        });

        VBox vBox = new VBox(listView, button);

        Scene scene = new Scene(vBox, 300, 120);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
```

#### 17.5.1. Working with ObservableList

JavaFX components do not store their data in normal ArrayLists internally, but in `ObservableList`. An ObservableList is similar to an `ArrayList` to store data, but it adds a feature where it is possible to get notified in code when the data changes. In JavaFX this is used to update the user interface components.

Fortunately, it is very easy to map an `ObservableList` to an `ArrayList`, using the `FXCollections` class.

```java
    ArrayList<String> myData = new ArrayList<>();
    ...
    studentList.setItems(FXCollections.observableList(myData));
```

The advantage of using this, is that you can use your own data classes (ArrayList of data), which is really easy to refresh into the user interface. An example of use would be

```java
public class ListDemo extends Application {

    private ArrayList<String> data;
    private int i;

    public ListDemo()
    {
        this.data = new ArrayList<String>();
        this.data.add("Item 1");
        this.data.add("Item 2");
        this.data.add("Item 3");
        this.i = 4;
    }


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane main = new BorderPane();

        ListView<String> list = new ListView<String>();
        main.setCenter(list);

        HBox bottomBar = new HBox();
        main.setBottom(bottomBar);

        list.setItems(FXCollections.observableList(data));

        Button add = new Button("Add");
        bottomBar.getChildren().add(add);
        add.setOnAction(e -> {
            this.data.add("Item " + i);
            list.refresh();
            this.i++;
        });

        Button print = new Button("Print Console");
        bottomBar.getChildren().add(print);
        print.setOnAction(e -> {
            System.out.println(this.data);
        });

        stage.setScene(new Scene(main));
        stage.show();
    }

    public static void main(String[] args) {
        launch(ListDemo.class);
    }
}

```

In this example, when we click the 'add' button, a new item gets added to the `ArrayList`. To update the list, just call the `list.refresh()` method to update the user interface to represent the new data. The beauty is that the user interface and your own data classes are automatically in sync, so you do not have to add items to the `ListView` and your own data class seperately, and you do not have to read out the values of the list to get data. This is a good example of seperating data and logic as discussed last week. This technique also works for `ComboBox`

{% include week12/exercise/FX_003.md %}
{: .exercises }