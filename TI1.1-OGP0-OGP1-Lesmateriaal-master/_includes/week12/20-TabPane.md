## 20. TabPane

Tabs are very useful components to split a single screen into multiple pages. This functionallity is provided using the class [`Tab`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Tab.html) which represent a tabpage and the tab control class [`TabPane`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TabPane.html)

![Tab Example](images/20_Tab-example.png)

The following code is used to create the example above:

```java
@Override
    public void start(Stage stage)  {
        stage.setTitle("Creating Tab");

        TabPane tabpane = new TabPane();

        // create multiple tabs
        Tab universityTab = new Tab("University");
        Label universityLabel = new Label("University tab page");
        universityTab.setContent(universityLabel);

        Tab workTab = new Tab("Work");
        Label workLabel = new Label("Work tab page");
        workTab.setContent(workLabel);

        Tab privateTab = new Tab("Private");
        Label privateLabel = new Label("Private tab page");
        privateTab.setContent(privateLabel);

        tabpane.getTabs().addAll(universityTab, workTab, privateTab);

        Scene scene = new Scene(tabpane, 600, 500);
        stage.setScene(scene);
        stage.show();
    }
```

In the example above we first create the Tab control using the class `TabPane`, after that we create three tabpages using the class `Tab` and add all the tabs to the tab control, using the code `tabpane.getTabs().addAll(universityTab, workTab, privateTab);`.

The context of each tab page is set using the method `setContent`. The content can be a single UI control or a layout manager with multiple controls.

