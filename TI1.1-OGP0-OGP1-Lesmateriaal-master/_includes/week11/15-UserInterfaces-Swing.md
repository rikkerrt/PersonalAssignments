## 15. User Interfaces

**Attention! A part of the user interface tests opens a user interface and uses your mouse to click on the user interface components. When you are executing user interface tests, do not use your mouse!**

So far, our programs have only been composed of application logic and text user interface which made use of application logic. In a couple of exercises we have also got a graphical user interface, but they had usually been created for us. Next, we see how we can create graphical user interfaces in Java.

User interfaces are windows which contain different types of buttons, text boxes, and menus. When we program user interfaces we use Java's [Swing](http://docs.oracle.com/javase/tutorial/uiswing/components/index.html) component library, which provides us with classes to create and handle user interfaces.

The basic element of a user interface is the class [JFrame](http://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html), and we create the user interface components in its component section. Orthodox user interfaces implement the interface `Runnable`, and they are started in the main program. In this course, we use the following user interface body:

```java
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private JFrame frame;

    public UserInterface() {
    }

    @Override
    public void run() {
        frame = new JFrame("Title");
        frame.setPreferredSize(new Dimension(200, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
    }

    public JFrame getFrame() {
        return frame;
    }
}
```

Let's have a closer look the the user interface code above.

```java
public class UserInterface implements Runnable {
```

The class `UserInterface` implements Java's [Runnable](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) interface, which allows us to execute a threaded program. Executing a threaded program means that we execute different parts of the program at the same time. We do not dig deeper into threads, but a further information on threads is provided by the course *Operating Systems*.

```java
    private JFrame frame;
```

The user interface contains a `JFrame` object as variable, which is the basic element of a visible user interface. All user interface components are added to the `JFrame` object component container. Note that **object variables cannot be initiated outside the methods**. For instance, an initialisation of the object variable `JFrame` with the class definition "`private JFrame frame = new JFrame()`" would evade user interface thread execution order, and it can lead to a breakdown.

```java
    @Override
    public void run() {
        frame = new JFrame("Title");
        frame.setPreferredSize(new Dimension(200, 100));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
```

The interface `Runnable` defines the method `public void run()`, which has to be implemented by all classes which implement the interface. With the method `public void run()`, we first create a new `JFrame` whose title is *"Title"*. After this, we define the frame size whose width is 200 pixels and height is 100 pixels. The statement `frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);` tells to the `JFrame` object that the user interface has to close when the user presses the cross icon.

Afterwards, we call the method `createComponents` which is defined lower down in the class. The method is given JFrame's [Container](http://docs.oracle.com/javase/8/docs/api/java/awt/Container.html) object as parameter, where we can add user interface components.

Finally, we call the method `frame.pack()` which packs the `JFrame` object as defined before and sorts the user interface components of the Container object contained by `JFrame`. At the end, we call the method `frame.setVisible(true)`, to show the user interface to the user.

```java
    private void createComponents(Container container) {
    }
```

In the method `createComponents` we add user interface components to the `JFrame's` container. In our example there is no UI component in addition to our `JFrame` window. The class `UserInterface` has also the method `getFrame` which we can use to retrieve the `JFrame` object which is encapsulated in the class.

Swing user interfaces are started through the method `invokeLater`, which is provided by the class [SwingUtilities](http://docs.oracle.com/javase/6/docs/api/javax/swing/SwingUtilities.html). `invokeLater` receives as parameter an object which implements the interface `Runnable`. The method adds the `Runnable` object to the execution queue and calls it as soon as possible. With the class `SwingUtilities`, we can start new threads when we need them.

```java
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);
    }
}
```

When we execute the main method above, the user interface we have defined in the class `UserInterface` appears in our screen.

![First GUI](images/11_17-first-ui.png)

### 15.1 UI Components

User Interfaces are composed of a background window (JFrame) and a component Container, as well as the UI components which are set into the container. UI components are different kinds of buttons, texts, and other items. Every component has its own class. It's useful to get accustomed to Oracle visual sequence of components at the address [http://docs.oracle.com/javase/tutorial/uiswing/components/index.html](http://docs.oracle.com/javase/tutorial/uiswing/components/index.html).

#### 15.1.1 Text

Text can be displayed with the help of the class [JLabel](http://docs.oracle.com/javase/8/docs/api/javax/swing/JLabel.html). `JLabel` provides a UI component which can be assigned text and whose text can be modified. The text is assigned either in the constructor, or separately, with the `setText` method.

Let's modify our UI container to display text. We create a new `JLabel` text component within the method `createComponents`. Then we retrieve the Container object from our `JFrame` object, and we add `JLabel` to Container using its `add` method.

```java
    private void createComponents(Container container) {
        JLabel text = new JLabel("Text field!");
        container.add(text);
    }
```

As you see from the code above, `JLabel` shall display the text *"Text field!"*. When we execute the user interface, we see the following window.

![Text field](images/15_1_1_text-field.png)

{% include week11/exercise/036.md %}
{: .exercises }

### 15.1.2 Buttons

You can add buttons to your user interface using the class `JButton`. Adding a `JButton` object to your user interface is similar to adding a `JLabel` object.

```java
    private void createComponents(Container container) {
        JButton button = new JButton("Click!");
        container.add(button);
    }
```

![Click](images/15_1_2_click.png)

Next, we try to add both text and a button to our user interface.

```java
    private void createComponents(Container container) {
        JButton button = new JButton("Click!");
        container.add(button);
        JLabel text = new JLabel("Text.");
        container.add(text);
    }
```

When we execute the program we see the following user interface.

![Text](images/15_1_2_text.png)

### 15.2 Setting up UI Components

All UI components have got their own location in the user interface. The component location is defined by the UI *Layout Manager*. Before, when we tried to add many different components to our `Container` object, only one component became visible. Every `Container` object has a default UI layout manager: `BorderLayout.`

`BorderLayout` places the UI components to five areas: the user interface centre and the four compass points. When we use the Container's `add` method, we can give it another parameter, clarifying where we would like to place the component. the BorderLayout class has five class variables available for use: `BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.SOUTH, BorderLayout.WEST, ja BorderLayout.CENTER`.

The UI layout manager we want to use is assigned to the `Container` object in the parameter of the method `setLauout`. In addition to the UI component, the method `add` can also be assigned the location wehere the component should be placed. In the example below, we assign a component to every `BorderLayout` location.

```java
    private void createComponents(Container container) {
        // the following line is not essential in this case, because BorderLayout is default in JFrame
        container.setLayout(new BorderLayout());

        container.add(new JButton("North"), BorderLayout.NORTH);
        container.add(new JButton("East"), BorderLayout.EAST);
        container.add(new JButton("South"), BorderLayout.SOUTH);
        container.add(new JButton("West"), BorderLayout.WEST);
        container.add(new JButton("Center"), BorderLayout.CENTER);

        container.add(new JButton("Default (Center)"));
    }
```

Notice that the button *"Center"* is not visible in our user interface because the button *"Default (Center)"* is assigned to its place by default. A container with the code above will look like the following after increasing its size manually.

![LayoutOverride](images/15_2_layout-override.png)

As for UI components, there are also many UI layout managers. Oracle has a visual guide to learn more about UI layout managers at [http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html](http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html). Below, we introduce the layout manager [BoxLayout](http://docs.oracle.com/javase/8/docs/api/javax/swing/BoxLayout.html).

#### 15.2.1 BoxLayout

When we use `BoxLayout`, UI components are added into the user interface either horizontally or vertically. The BoxLayout constructor is given a Container object as parameter -- where we have been adding the UI components -- and the layout direction of the UI components. The layout direction can be either `BoxLayout.X_AXIS`, i.e. components are set up horizontally, or `BoxLayout.Y_AXIS`, i.e. the componets are set up vertically. Differently than `BorderLayout`, BoxLayout does not have a limited number of places. In other words, you can add to your Container as many components as you want.

Arranging the user interface with `BoxLayout` works as using `BorderLayout`. We first create the layout manager and we assign it to the `Container` object using its method `setLayout`. After this, we can add components to the `Container` object using the `add` method. We don't need a further parameter specifying the location. Below, you find an example of components placed in horizontal order.

```java
    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("First!"));
        container.add(new JLabel("Second!"));
        container.add(new JLabel("Third!"));
    }
```

![YAxis](images/15_2_1_layout-xaxis.png)

Setting up the components vertically does not require major changes. We modify the direction parameter of the `BoxLayout` constructor: `BoxLayout.Y_AXIS`.

```java
    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("First!"));
        container.add(new JLabel("Second!"));
        container.add(new JLabel("Third!"));
    }
```

![YAxis](images/15_2_1_layout-yaxis.png)

Using the different layout managers, we can create user interfaces where the components are set up appropriately. Below, there is an example of user interface where the components are placed vertically. First there is some text, and then an optional selection. You can create a multiple-exclusion scope for a set of buttons -- meaning that turning "on" one of those buttons turns off all the others in the group -- using [ButtonGroup](http://docs.oracle.com/javase/8/docs/api/javax/swing/ButtonGroup.html) and [JRadioButton](http://docs.oracle.com/javase/8/docs/api/javax/swing/JRadioButton.html).

```java
    private void createComponents(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        container.add(new JLabel("Choose meat or fish:"));

        JRadioButton meat = new JRadioButton("Meat");
        JRadioButton fish = new JRadioButton("Fish");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(meat);
        buttonGroup.add(fish);

        container.add(meat);
        container.add(fish);
    }
```

Once the UI is launched, and Meat is selected, the UI looks as follows.

![ButtonGroup](images/15_2_1_buttongroup.png)

{% include week11/exercise/037.md %}
{: .exercises }

### 15.3 Managing Action Events

So far, even though our user interfaces are beautiful, they are quite boring: they do not react in any way according to the actions done on the interfaces. Such unresponsiveness does not depend on our user interface components, but on the fact we haven't provided them with any way to listen to action events.

Action event listeners *listen* the UI components they are assigned to. Always when we perform an action on our UI components -- pressing a button, for instance -- the UI component calls a particular method of all the action event listeners assigned to it. Action event listeners are classes which implement a particular interface, and whose instances can be assigned to UI components. When an action event happens, the UI component goes through all its action event listeners, and calls the method defined by the interface.

The most used action event listener interface with Swing user interfaces is [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html). The interface `ActionListener` defines the method `void actionPerformed(ActionEvent e)`, which receives an [ActionEvent](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionEvent.html) object as parameter.

Let's implement our first own action event listener, which has to print a message only when we press the relative button. The class `MessageListener` implements `ActionListener` and prints the message *"Message received!"* when the method `actionPerformed` is called.

```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Message received!");
    }
}
```

Next, we create the a `JButton` for our user interface, and we add a instance of `MessageListener` to it. The class `JButton` can be added an action event listener by using the method defined in its parent class `AbstractButton`: `public void addActionListener(ActionListener actionListener)`.

```java
    private void createComponents(Container container) {
        JButton button = new JButton("Send!");
        button.addActionListener(new MessageListener());

        container.add(button);
    }
```

![Send](images/15_3_send.png)

When we press the button in our user interface we see the following message.

```output
Message received!
```

#### 15.3.1 Handling Objects in the Action Event Listeners

Often, we want that an action event listener modified the state of an object. In order to have access to the object in the action event listener, the action event listener constructor has to be assigned a reference to the obejct concerned. Action eventlisteners are exactly similar to other Java's class, and we can program their whole functionality.

Let's take the following user interface, which has two [JTextArea](http://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html)s -- where the user can input text, and a `JButton`. The user interface makes use of [GridLayout](http://docs.oracle.com/javase/8/docs/api/java/awt/GridLayout.html), which makes the user interface look like a coordinate system. In the `GridLayout` constructor, we defined one line and three columns.

```java
    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(1, 3);
        container.setLayout(layout);

        JTextArea textAreaLeft = new JTextArea("The Copier");
        JTextArea textAreaRight = new JTextArea();
        JButton copyButton = new JButton("Copy!");

        container.add(textAreaLeft);
        container.add(copyButton);
        container.add(textAreaRight);
    }
```

After a manual resize, the UI looks like the following.

![Copier](images/15_3_1_copier.png)

We want to implement our user interface so that the text in the left area would be copied into the right area when we press the `JButton`. This is possible by implementing an action event listener. Let's create the class `AreaCopier` which implements `ActionListener` and copies the text from one to the other `JTextArea`.

```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

public class AreaCopier implements ActionListener {

    private JTextArea origin;
    private JTextArea destination;

    public AreaCopier(JTextArea origin, JTextArea destination) {
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.destination.setText(this.origin.getText());
    }
}
```

Adding the new action event listener to the `JButton` object is possible using the method `addActionListener`.

```java
    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(1, 3);
        container.setLayout(layout);

        JTextArea textAreaLeft = new JTextArea("The Copier");
        JTextArea textAreaRight = new JTextArea();
        JButton copyButton = new JButton("Copy!");

        AreaCopier copier = new AreaCopier(textAreaLeft, textAreaRight);
        copyButton.addActionListener(copier);

        container.add(textAreaLeft);
        container.add(copyButton);
        container.add(textAreaRight);
    }
```

When we press the button, the text in the left area is copied into the right one.

![NestedObjects](images/15_3_1_copier2.png)

{% include week11/exercise/038.md %}
{: .exercises }

### 15.4 Separating Application and UI Logic

Mixing the application logic (the functionality to print or compute, for instance) and the user interface together in the same classes is usually a bad thing. It makes much more difficult to test and modify a program, and it makes the code much more difficult to read. As the single responsibility principle states: each class should have only one clear responsibility. Separating the application logic from the UI logic works smoothly planning your interfaces appropriately. Let's suppose, that we have got a the class `PersonRecord`, and we want to implement a user interface to record people.

```java
public interface PersonRecord {
    void record(Person person);
    Person get(String id);

    void delete(Person person);
    void delete(String id);
    void deleteAll();

    Collection<Person> getAll();
}
```

#### 15.4.1 UI Implementation

When we implement our user interface, a good start is adding the components to it. If we want to record people, we need fields for their name and their ID number, as well as a button to add the person. We use Java's [JTextField](http://docs.oracle.com/javase/8/docs/api/javax/swing/JTextField.html) to input text, and the class JButton to implement our button. In addition, we also create `JLabel` textual descriptions which tell the user what to do.

For our UI layout, we use `GridLayout`. There are three lines and two columns in our user interface. We add the action event listener later. The UI method `createComponents` looks like the following.

```java
    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(3, 2);
        container.setLayout(layout);

        JLabel textName = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel textID = new JLabel("ID: ");
        JTextField idField = new JTextField();

        JButton addButton = new JButton("Add!");
        // event listener

        container.add(textName);
        container.add(nameField);
        container.add(textID);
        container.add(idField);
        container.add(new JLabel(""));
        container.add(addButton);
    }
```

After adding the information, our user interface looks like the following.

![Copier](images/15_4_persondetails.png)

The action event listener has to know about the recording functionality (`PersonRecord` interface), as well as the fields it uses. Let's create the class `PersonRecordListener` which implements ActionListener. As constructor parameter, the class is assigned an object which implements the interface `PersonRecord`, as well as two `JTextField` objects which stand for the name and ID fields. In the method `actionPerformed` we create a new `Person` object and we record it using the record method of our `PersonRecord` object.

```java
public class PersonRecordListener implements ActionListener {

    private PersonRecord personRecord;
    private JTextField nameField;
    private JTextField idField;

    public PersonRecordListener(PersonRecord personRecord, JTextField nameField, JTextField idField) {
        this.personRecord = personRecord;
        this.nameField = nameField;
        this.idField = idField;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Person person = new Person(nameField.getText(), idField.getText());
        this.personRecord.record(person);
    }
}
```

In order to retrieve a `PersonRecord` reference to `PersonRecordListener`, the user interface must have access to it. Let's add to our user interface the object variable `private PersonRecord personRecord` which is set up in the constructor. We also modify the constructor of the class `UserInterface`, which is assigned a class which implements the interface `PersonRecord`.

```java
public class UserInteface implements Runnable {

    private JFrame frame;
    private PersonRecord personRecord;

    public UserInteface(PersonRecord personRecord) {
        this.personRecord = personRecord;
    }
    // ...
```

Now we can create the action event listener `PersonRecordListener`, which is given both a `PersonRecord` reference and the fields.

```java
    private void createComponents(Container container) {
        GridLayout layout = new GridLayout(3, 2);
        container.setLayout(layout);

        JLabel nameText = new JLabel("Name: ");
        JTextField nameField = new JTextField();
        JLabel idText = new JLabel("ID: ");
        JTextField idField = new JTextField();

        JButton addButton = new JButton("Add!");
        PersonRecordListener listener = new PersonRecordListener(personRecord, nameField, idField);
        addButton.addActionListener(listener);

        container.add(nameText);
        container.add(nameField);
        container.add(idText);
        container.add(idField);
        container.add(new JLabel(""));
        container.add(addButton);
    }
```

{% include week11/exercise/039.md %}
{: .exercises }

### 15.5 Nested Container Objects

Sometimes we end up in a situation, where the `Container` object provided by `JFrame` is not suitable enough for our UI layout. We may want our user interface to look different or to group up UI components according to their use. For instance, building a user interface like the one below would not be so easy, using only the `Container` object provided by the class `JFrame`.

![NestedObjects](images/15_5_nestedobjects.png)

We can place Container objects inside each other. The class [JPanel](http://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html) (see also [How to Use Panels](http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html)) allows for nested `Container` objects. It is possible to add UI components to a `JPanel` instance in the same way we add components to the Container instance of `JFrame` class. Moreover, it is possible to add an instance of `JPanel` to a `Container` object. This makes possible to use many `Container` objects to develop one user interface.

Creating a user interface like the one above is easier with `JPanel`. Let's create a user interface with three buttons -- Execute, Test, and Send -- plus a text field. The buttons are a group on its own, and we assign them to a `JPanel` object which is placed in the lower part of the `Container` object which we have got from `JFrame` class.

```java
    private void createComponents(Container container) {
        container.add(new JTextArea());
        container.add(createPanel(), BorderLayout.SOUTH);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JButton("Execute"));
        panel.add(new JButton("Test"));
        panel.add(new JButton("Send"));
        return panel;
    }
```

The `JPanel` class is given as constructor parameter the layout style to use. If in its constructor the layout style requires a reference to the `Container` object used, the `JPanel` class also has the method `setLayout`.

If our user interface has clear, separate, groups of components we can also inherit the `JPanel` class. For instance, the panel above could be implemented in the following way, too.

```java
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        super(new GridLayout(1, 3));
        createComponents();
    }

    private void createComponents() {
        add(new JButton("Execute"));
        add(new JButton("Test"));
        add(new JButton("Send"));
    }
}
```

Now we can create a `MenuPanel` instance in our user interface class.

```java
    private void createComponents(Container container) {
        container.add(new JTextArea());
        container.add(new MenuPanel(), BorderLayout.SOUTH);
    }
```

Note that in case you need an action event listener, the class `MenuPanel` must be given all the objects its need as parameter.

{% include week11/exercise/040.md %}
{: .exercises }