## 18. Menus

### 18.1 MenuBar

The [`MenuBar`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html) provides applications with a visual drop down menu similar to that most desktop applications have at the top of their application window. The `MenuBar` is represented by the class `javafx.scene.control.MenuBar`. Here is an example screenshot of what a  `MenuBar` can look like:

![Menu Example](images/12_Menu_Example.png)

Before you can use the `MenuBar` you must create a `MenuBar` instance. Here is an example of creating a `MenuBar` instance:

```java
MenuBar menuBar = new MenuBar();
```

Before a `MenuBar` becomes visible you will have to add it to the scene. Here is an example of adding a `MenuBar` to the scene graph:

```java
public void start(Stage stage) {
    MenuBar menuBar = new MenuBar();

    VBox vBox = new VBox(menuBar);
    Scene scene = new Scene(vBox, 960, 600);

    stage.setScene(scene);
    stage.show();
}
```

Notice how the `MenuBar` is added to the root layout (`VBox`) of the scene. This places the `MenuBar` at the top of the application window.

Note that the above example does not add any menus or menu items to the `MenuBar`, so if you run the example you will not actually see the `MenuBar`. We will see how to add menus and menu items in the following sections.

Once the `MenuBar` instance is created, you can add [`Menu`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Menu.html) instances to it. A `Menu` instance represents a single vertical menu with nested menu items. Thus, you can add multiple `Menu` instances to a `MenuBar` to add multiple vertical drop down menus.

```java
Menu menu1 = new Menu("Menu 1");
MenuBar menuBar = new MenuBar();
menuBar.getMenus().add(menu1);
```

Here is a screenshot showing the `MenuBar` as configured by the example code above:

As you can see, there is only a single menu in the `MenuBar` titled *Menu 1*. This menu has no menu items nested under it. Once you have created a `Menu` instance you must add one or more `MenuItem` instances to it. Each `MenuItem` corresponds to a menu item in the menu it is added to. Here is an example of adding 2 `MenuItem` instances to a `Menu`, which is then added to a `MenuBar`:

```java
Menu menu = new Menu("Menu 1");
MenuItem menuItem1 = new MenuItem("Item 1");
MenuItem menuItem2 = new MenuItem("Item 2");

menu.getItems().add(menuItem1);
menu.getItems().add(menuItem2);

MenuBar menuBar = new MenuBar();
menuBar.getMenus().add(menu);
```

The `MenuBar` configurations created in the previous examples do not react if you select any of the menu items. In order to respond to the selection of a `MenuItem` you must set an event listener on the `MenuItem`. Here is an example of adding an event listener to a `MenuItem`:

```java
MenuItem menuItem1 = new MenuItem("Item 1");

menuItem1.setOnAction(e -> {
    System.out.println("Menu Item 1 Selected");
});
```

### 18.2. Sub menus

The `MenuBar` supports multiple layers of menus. A menu nested inside another menu is called a submenu. The `Menu` class extends the `MenuItem` class and can therefore be used as a menu item inside another `Menu` instance. Here is an example that creates a single menu with a submenu inside:

```java
Menu menu = new Menu("Menu 1");

Menu subMenu = new Menu("Menu 1.1");
MenuItem menuItem11 = new MenuItem("Item 1.1.1");
subMenu.getItems().add(menuItem11);
menu.getItems().add(subMenu);

MenuItem menuItem1 = new MenuItem("Item 1");
menu.getItems().add(menuItem1);

MenuItem menuItem2 = new MenuItem("Item 2");
menu.getItems().add(menuItem2);

MenuBar menuBar = new MenuBar();
menuBar.getMenus().add(menu);
```

The `MenuBar` resulting from the above example will look similar to this:

![Menu Submenu](images/12_Menu_SubMenu.png)
