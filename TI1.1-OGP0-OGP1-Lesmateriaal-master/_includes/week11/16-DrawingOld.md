## 16. Drawing

Its `Container` functionality is not the only reason why we use the class `JPanel`: it is also used as drawing board, and the user inherits the `JPanel` class and overrides the method `protected void paintComponent(Graphics graphics)`. The user interface calls the method `paintComponent` whenever we want to draw again the UI component contents. The parameter of the method `paintComponent` receives from the user interface an object which implements the abstract class [Graphics](http://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html). Let's create the class `DrawingBoard JPanel` which inherits from `JPanel` and which overrides the `paintComponent` method.

```java
public class DrawingBoard extends JPanel {

    public DrawingBoard() {
        super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }
}
```

The drawing board above does not contain concrete drawing functionality. In the constructor, we can define the colour of our drawing board to be white by calling its superclass method `setBackground`. The method `setBackGround` receives an instance of the class `Color` as parameter. The class [Color](http://docs.oracle.com/javase/8/docs/api/java/awt/Color.html) contains the most common colours as class variables; for instance, you get the white colour using the class variable `Color.WHITE`.

The overridden `paintComponent` method calls the superclass `paintComponent` method, and it does not do anything else. Let's add the drawing board to the `createComponents` method of class `UserInterface`. We use the user interface which was defined at the beginning of the section 15. User Interfaces.

```java
    private void createComponents(Container container) {
        container.add(new DrawingBoard());
    }
```

When we start our user interface we see an empty screen, whose background colour is white. The size of the user interface below is set to 300x300 through the method `setPreferredSize`, and its title is *"Drawing Board"*.

![Drawing Board](images/16_drawingboard.png)

Drawing on the board is possible using the methods provides by the [Graphics](http://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html) object. Let's modify the method `paintComponent` of `DrawingBoard` and let's draw two rectangles using the method `fillRect` of the `Graphics` object.

```java
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.fillRect(50, 80, 100, 50);
        graphics.fillRect(200, 20, 50, 200);
    }
```

The method `fillRect` receives as parameter the x and y coordinates of a rectangle, plus the rectangle width and height. In fact, above we first draw a rectangle which starts with pixel whose coordinates are *(50, 80)*, which is 100 pixels long, and 50 pixels high. Afterwards, we draw a 50-pixel long, 100-pixel high rectangle which begins at *(200, 20)*.

As you notice from the picture, the coordinate system does not work as we are accustomed to.

![drawing board blocks](images/15_drawingboard-blocks.png)

Java's `Graphics` object (and most of other programming language libraries) expects the value of the y axis to grow downwards. The coordinate system origin, i.e. the point *(0,0)* is in the upper left corner: the `Graphics` object always knows the UI component where we draw, and it is able to define the location of the point to draw based on it. The location of the UI origin can become clear with the help of the following program. First we draw a green 10-pixel long, 200-pixel high rectangle which starts from the point *(0,0)*. Then we draw a black 200-pixel long, 10-pixel high rectangle which starts from the point *(0,0)*. The drawing colour is defined by the method setColor of our `Graphics` object.

```java
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(0, 0, 10, 200);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 200, 10);
    }
```

![Drawing board coordinates](images/16_drawingboard-coordinates.png)

Such coordinate system reversibility depends on the way user interface size is modified. When we modify the size of a user interface, this is reduced or increased by "dragging the bottom right corner"; in this way, the drawing on the screan would move while we change the UI size. Because the grid starts from the upper left corner, the drawing position is always the same, but the visible part changes.

{% include week11/exercise/041.md %}
{: .exercises }

Let's extend our previous example and draw an independent avatar-object in our user interface. Let's create the class Avatar; it has the coordinates of the point where it appears, and it is a circle with a 10-pixel diameter. The location of the avatar can be changed by calling its `move` method.

```java
import java.awt.Graphics;

public class Avatar {

    private int x;
    private int y;

    public Avatar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int movingX, int movingY) {
        this.x += movingX;
        this.y += movingY;
    }

    public void draw(Graphics graphics) {
        graphics.fillOval(x, y, 10, 10);
    }
}
```

Let's modify our drawing board, giving it an instance of our `Avatar` as constructor parameter. The method `paintComponent` of `DrawingBoard` does not draw the character itself, but it delegates the responsibility to the instance of the class `Avatar`.

```java
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawingBoard extends JPanel {

    private Avatar avatar;

    public DrawingBoard(Avatar avatar) {
        super.setBackground(Color.WHITE);
        this.avatar = avatar;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        avatar.draw(graphics);
    }
}
```

Let's also give our avatar as parameter to our user interface. `Avatar` is an independent object in the user interface, and we only want to draw it in the user interface. It is essential to change our UI constructor so that it received a `Avatar` object. Moreover, in the method `createComponents` we give an instance of the class `Avatar` as parameter to our `DrawingBoard` object.

```java
public class UserInterface implements Runnable {

    private JFrame frame;
    private Avatar avatar;

    public UserInterface(Avatar avatar) {
        this.avatar = avatar;
    }

// ...

    private void createComponents(Container container) {
        DrawingBoard drawingBoard = new DrawingBoard(avatar);
        container.add(drawingBoard);
    }
// ...
```

Now, our user interface can be started giving it an `Avatar` object as constructor parameter.

```java
        UserInterface ui = new UserInterface(new Avatar(30, 30));
        SwingUtilities.invokeLater(ui);
```

![GUI](images/16_drawingboard-avatar.png)

In the user interface above, we see a ball-like Avatar.

Let's now add the functionality to move the avatar. We want to move it using our keyboard. When the user presses the left arrow, the avatar should move left. Pressing the right arrow should move the avatar right. We need an action event listener, which would listen to our keyboard. The interface [KeyListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyListener.html) defines the functionality needed to listener to a keyboard.

The interface `KeyListener` calls for implementing the methods `keyPressed`, `keyReleased`, and `keyTyped`. We are only interested to the case in which the keyboard is pressed, so we can leave empty the methods `keyReleased` and `keyTyped`. Let's create the class `KeyboardListener`, which implements the interface `KeyListener`. The class receives as parameter a `Avatar` object, and the action event manager has to shift it.

```java
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Avatar avatar;

    public KeyboardListener(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            avatar.move(-5, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            avatar.move(5, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
```

The method `keyPressed` receives as parameter an instance of `KeyEvent` from the user interface. The `KeyEvent` object knows the number related to the pressed key thanks to its method `getKeycode()`. Different keys have got different class variables in the `KeyEvent` class; for instance, the left arrow is `KeyEvent.VK_LEFT`.

We want to listen to the keystrokes directed to our user interface (we don't want to write to the text field, for instance), and therefore we assign our keyboard listener to the `JFrame` instance. Let's modify our user interface and add the keyboard listener to the `JFrame` object.

```java
    private void createComponents(Container container) {
        DrawingBoard drawingBoard = new DrawingBoard(avatar);
        container.add(drawingBoard);

        frame.addKeyListener(new KeyboardListener(avatar));
    }
```

Our application now listens to keystrokes, and it leads them to the instance of the class `KeyboardListener`.

However, when we try out our user interface it does not work: the avatar does not move on the screen. What is the problem, in fact? We can check the keystrokes which are received by our `KeyboardListener` by adding a text printout to the beginning of our `keyPressed` method.

```java
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Keystroke " + e.getKeyCode() +  " pressed.");

        // ...
```

If we start our program and press some keys we will notice the following output.

```output
Keystroke 39 pressed.
Keystroke 37 pressed.
Keystroke 40 pressed.
Keystroke 38 pressed.
```

In fact, our keyboard listener works, but our drawing board does not update.

### 16.1 Drawing Board Repainting

User interface components usually have the functionality to repaint the component outer face, when needed. For instance, when we press the button, the instance of the class `JButton` is able to paint the button as if it was pressed, and to paint it normal again afterwards. The drawing board we have implemented does not have a pre-made update functionality; instead, we have to ask our drawing board to paint itself again when needed.

Each subclass of `Component` has the method `public void repaint()`, which repaints the component after it is called. We want that our `DrawingBoard` object would get repainted while the avatar moves. The avatar moves in the class `KeyboardListener`, and it is logic the repainting would happen there, too.

In order to be repainted, our keyboard listener needs a drawing board reference. Let's modify our class `KeyboardListener`, so that it would receive as parameter both an `Avatar` object and the `Component` object to repaint. We call the `repaint` method of the Component object after each `keyPressed` action event.

```java
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Component component;
    private Avatar avatar;

    public KeyboardListener(Avatar avatar, Component component) {
        this.avatar = avatar;
        this.component = component;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            avatar.move(-5, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            avatar.move(5, 0);
        }

        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
```

Let's also modify the `createComponents` method of `UserInterface` and give an instance of `DrawingBoard` as parameter to our keyboard listener.

```java
    private void createComponents(Container container) {
        DrawingBoard drawingBoard = new DrawingBoard(hahmo);
        container.add(drawingBoard);

        frame.addKeyListener(new KeyboardListener(avatar, drawingBoard));
    }
```

Now, our avatar's moves are visible also in the user interface. Whenever the user presses the keyboard, the user interface keyboard listener handles the call. At the end of each call, the `repaint` method of our drawing board is called, and the drawing board gets repainted.

![Moving avatar](images/16_1_drawingboard-avatar-movement.png)

{% include week11/exercise/042.md %}
{: .exercises }

### 16.2 Pre-made Application Frameworks

An application framework is a program which provides a baseline and a set of features to implement a particular application. One way to create an application framework is to create a class which provides pre-made features, so that classes can inherit it and build a particular application. Application frameworks are usually wide, and they are thought for a special purpose, for instance to program games or develop web-applications. Let's quickly get acquainted with a pre-made application library, by greating the application logic of a Game of Life.

{% include week11/exercise/043.md %}
{: .exercises }