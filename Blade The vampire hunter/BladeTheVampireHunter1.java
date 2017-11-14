// The "BladeTheVampireHunter" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import hsa.Console;
import java.util.Random;
import java.io.*; //Importing File Class
import javax.imageio.*; //Importing Image Class


public class BladeTheVampireHunter1 extends Applet implements KeyListener, MouseListener, MouseMotionListener, Runnable // All the implementations that are needed
{

    // Place instance variables here
    boolean isClicked = false;
    // Initialization of variables
    int appletsize_x = 1000;
    int appletsize_y = 720;

    int x_pos = appletsize_x / 2;         // x - Position of ball
    int y_pos = appletsize_y / 2;        // y - Position of ball

    int[] VampireX = new int [8];

    int[] VampireY = new int [8];

    int finX;
    int finY;

    int radius = 50;        // Radius of ball

    double x_speed = 1;
    double y_speed = 1;

    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;
    // create meadia tracker and image variables for image
    Image img;
    MediaTracker tr;
    private final String PICTURE_PATH = "E:/Blade The vampire hunter/Blade.png";
    private final String PICTURE_PATH1 = ":/Blade The vampire hunter/Vampire.png";
    Image picture;
    Image vampire;
    int picWidth, picHeight;



    public void init ()
    {
	int[] VampireX = {200, 520, 420, 310, 201, 61, 50, 113, 470};
	int[] VampireY = {200, 520, 420, 310, 201, 61, 50, 113, 470};
	//Random Generator
	for (int i = 0 ; i < VampireX.length ; i++)
	{
	    int x = VampireX [i];
	}
	for (int c = 0 ; c < VampireY.length ; c++)
	{
	    int y = VampireY [c];
	}


	requestFocus ();
	// Place the body of the initialization method here
	resize (1000, 720);
	setBackground (Color.GRAY);
	//Toolkiting the Images
	picture = getToolkit ().getImage (PICTURE_PATH);
	prepareImage (picture, this);
	vampire = getToolkit ().getImage (PICTURE_PATH1);
	prepareImage (vampire, this);
	// Add the picture to the list of images to be tracked

	MediaTracker tracker = new MediaTracker (this);


	tracker.addImage (picture, 0);
	tracker.addImage (vampire, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
	// If there were any errors loading the image, then abort the
	// program with a message.
	if (tracker.isErrorAny ())
	{
	    showStatus ("Couldn't load " + PICTURE_PATH);
	    return;
	}
	// Initialize the picture size
	picWidth = picture.getWidth (null);
	picHeight = picture.getHeight (null);
	addMouseListener (this);
    } // init method


    public void start ()
    {

	// define a new thread
	Thread th = new Thread (this);
	// start this thread
	th.start ();

    }




    public void stop ()
    {
	//nothing right now
    }


    public void destroy ()
    {
	//nothing right now
    }


    public void run ()
    {

	// lower ThreadPriority
	Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);

	// run a long while (true) this means in our case "always"
	while (true)
	{ //Finding the angle

	    if (finX != x_pos || finY != y_pos)
	    { //Slope
		double deltaX1 = finX - x_pos; //x2-x1
		double deltaY1 = finY - y_pos; //y2-y1
		double distance = Math.sqrt (deltaX1 * deltaX1 + deltaY1 * deltaY1);
		double directionX = deltaX1 / distance;
		double directionY = deltaY1 / distance;

		// Calculate the actual walk amount
		double movementX = directionX * x_speed;
		double movementY = directionY * y_speed;

		// Move the player
		x_pos = (int) movementX;
		y_pos = (int) movementY;




		//Bug fix
		if ((deltaX1 < 0 && deltaY1 < 0) || (deltaX1 < 0 && deltaY1 > 0))
		{
		    x_speed *= -1;
		    y_speed *= -1;
		}
		repaint ();


		x_pos += x_speed; //update the current location of the x on the ball
		y_pos += y_speed; //update the current location of the y on the ball

		// repaint the applet
		repaint ();

		try
		{
		    // Stop thread for 20 milliseconds
		    Thread.sleep (20);
		}
		catch (InterruptedException ex)
		{
		    // do nothing
		}

		// set ThreadPriority to maximum value
		Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	    }
	}
    }




    /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {


	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}


	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }
     // method to handle key - down events
    public boolean keyDown (Event e, int key)
    {

	// user presses left cursor key
	if (key == Event.LEFT)
	{
	    // changing x - speed so that ball moves to the left side (x_speed negative)
	    x_speed = -1;
	    y_speed = 0;
	}
	// user presses right cursor key
	else if (key == Event.RIGHT)
	{
	    // changing x - speed so that ball moves to the right side (x_speed positive)
	    x_speed = 1;
	    y_speed = 0;
	}
	// user presses space bar (value = 32!)
	else if (key == 32)
	{
	    // Stop ball (x_speed = 0)
	    x_speed = 0;
	    y_speed = 0;
	}

	else if (key == Event.DOWN)
	{
	    // Stop ball (x_speed = 0)
	    y_speed = 1;
	    x_speed = 0;

	}
	else if (key == Event.UP)
	{
	    // Stop ball (x_speed = 0)
	    y_speed = -1;
	    x_speed = 0;

	}
	else
	{
	    /* Additionally the method prints out the ASCII - value if an other key is pressed. This is not necessary but a possibility for you to test which value a key has.*/
	    System.out.println ("Character: " + (char) key + " Integer Value: " + key);
	}

	// DON'T FORGET (although it has no meaning here)
	return true;

    } // method to handle key - down events
    public boolean keyDown (Event e, int key)
    {

	// user presses left cursor key
	if (key == Event.LEFT)
	{
	    // changing x - speed so that ball moves to the left side (x_speed negative)
	    x_speed = -1;
	    y_speed = 0;
	}
	// user presses right cursor key
	else if (key == Event.RIGHT)
	{
	    // changing x - speed so that ball moves to the right side (x_speed positive)
	    x_speed = 1;
	    y_speed = 0;
	}
	// user presses space bar (value = 32!)
	else if (key == 32)
	{
	    // Stop ball (x_speed = 0)
	    x_speed = 0;
	    y_speed = 0;
	}

	else if (key == Event.DOWN)
	{
	    // Stop ball (x_speed = 0)
	    y_speed = 1;
	    x_speed = 0;

	}
	else if (key == Event.UP)
	{
	    // Stop ball (x_speed = 0)
	    y_speed = -1;
	    x_speed = 0;

	}
	else
	{
	    /* Additionally the method prints out the ASCII - value if an other key is pressed. This is not necessary but a possibility for you to test which value a key has.*/
	    System.out.println ("Character: " + (char) key + " Integer Value: " + key);
	}

	// DON'T FORGET (although it has no meaning here)
	return true;

    }


    public void paint (Graphics g)
    {
	// Place the body of the drawing method here
	// Place the body of the drawing method here (100,200 is size of image)

	g.drawImage (picture, x_pos, y_pos, 50, 60, null);
	for (int i = 0 ; i < 8 ; i++)
	{
	    g.drawImage (vampire, VampireX [i], VampireY [i], 50, 60, null);
	}
    } // paint method


    public void mouseEntered (MouseEvent e)
    {
	//does nothing
    }


    public void mouseExited (MouseEvent e)
    {
	//does nothing
    }


    /*
	The mouseClicked method is activated whenever the mouse is clicked
    */
    // public void mouseClicked (MouseEvent e)
    // {
    //     int mx = e.getX (); // gets the x - coordinate of the mouse's location
    //     int my = e.getY ();
    // 
    //     finX = mx;
    //     finY = my;
    // 
    //     repaint ();
    //     e.consume ();
    // }



    /*
    The mousePressed method detects when the mouse button is pressed and coresponds them to different actions
	@param MouseEvent e - Recieves the location of the mouse in relation to the applet screen
    */

    public void mousePressed (MouseEvent e)
    {

    }


    /*
	The mouseReleased method is activated whenever the mouse is released after being pressed
    */
    public void mouseReleased (MouseEvent e)
    {

    }


    /*
       The mouseMoved method is activated whenever the mouse moves
    */
    public void mouseMoved (MouseEvent e)
    { // called during motion when no buttons are down
	int mx = e.getX (); // gets the x - coordinate of the mouse's location
	int my = e.getY (); // gets the y- coordinate of the mouse's location
	showStatus ("Mouse at (" + mx + "," + my + ")");
	repaint ();
	e.consume ();
    }


    /*
	The mouseDragged method is activated whenever the mouse is dragged
    */
    public void mouseDragged (MouseEvent e)
    { // called during motion with buttons down
	int mx = e.getX (); // gets the x - coordinate of the mouse's location
	int my = e.getY (); // gets the y- coordinate of the mouse's location
	showStatus ("Mouse at (" + mx + "," + my + ")");
	repaint ();
	e.consume ();
    }



} // BladeTheVampireHunter class


