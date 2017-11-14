// The "BladeTheVampireHunter" class.
import java.lang.Boolean;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;


public class BladeTheVampireHunter extends Applet implements MouseListener, MouseMotionListener, Runnable, KeyListener // All the implementations that are needed
{
    // Place instance variables here

    // Initialization of variables
    int appletsize_x = 900;
    int appletsize_y = 600;

    int mx; //Mouse x coordinate
    int my; //Mouse y coordinate

    int x_pos = appletsize_x / 2;         // x - Position of ball
    int y_pos = appletsize_y / 2;        // y - Position of ball

    int yVamp = appletsize_y / 2;      // y - Position of ball

    int radius = 40;        // Radius of the player

    int[] vampires = { - 900, -2000, -6000, -3800, -30, -200, -7000, -8000, 0}; //List of Vampires
    int[] vampires1 = {800, 1500, 950, 2000, 5000, 2900, 4000, 9000, 6500};  //List of Vampires

    int x_speed = 0; //The initial x speed of The main character or you
    int y_speed = 0; //The initial y speed of The main character or you
    int heart = 3; //Heart
    int score = 0; //score
    int highScore = 0; //Highscore
    boolean start = false; //start
    boolean howTo = false; //tutorial
    boolean right = false; //right slash
    boolean left = false; //left slash
    boolean hit = false; //Blade getting hit
    boolean vampireRight = true; //Vampires that are coming from the right
    boolean vampireLeft = true; //Vampires that are coming from the left
    boolean death = false; //when you die
    boolean pause = false; //pause button in game
    boolean wallHit = false; //if the guy hits the wall



    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;
    // create meadia tracker and image variables for image
    Image img;
    MediaTracker tr;
    Image dizzy;
    Image menu;
    Image mainGame;
    Image pauseOff;
    Image pauseOn;
    Image tutorial;
    Image heart3;
    Image heart2;
    Image heart1;
    Image bladePicture;
    Image bladeBleed;
    Image vampireR;
    Image vampireL;
    Image bladeRight;
    Image bladeLeft;
    Image beating;
    Image gameOver;
    int picWidth, picHeight;

    public void init ()
    { //these are the code to make the methods work like mouse move and key pressed.
	addMouseListener (this);
	addMouseMotionListener (this);
	addKeyListener (this);
	MediaTracker tracker = new MediaTracker (this);
	requestFocus (); //starts the applet without touching the screen
	// Place the body of the initialization method here
	resize (900, 600);
	//Toolkiting the Images
	menu = getImage (getCodeBase (), "Menu.jpg");
	tutorial = getImage (getCodeBase (), "tutorial.gif");
	mainGame = getImage (getCodeBase (), "Game wall.jpg");
	pauseOff = getImage (getCodeBase (), "Pause.png");
	pauseOn = getImage (getCodeBase (), "PauseOn.png");
	dizzy = getImage (getCodeBase (), "Concussion.gif");
	heart3 = getImage (getCodeBase (), "3.png");
	heart2 = getImage (getCodeBase (), "2.png");
	heart1 = getImage (getCodeBase (), "1.png");
	bladePicture = getImage (getCodeBase (), "Blade.png");
	bladeBleed = getImage (getCodeBase (), "Bleed.gif");
	vampireR = getImage (getCodeBase (), "right.gif");
	vampireL = getImage (getCodeBase (), "left.gif");
	bladeRight = getImage (getCodeBase (), "Slash right.gif");
	bladeLeft = getImage (getCodeBase (), "Slash left.gif");
	beating = getImage (getCodeBase (), "GameOver.gif");
	gameOver = getImage (getCodeBase (), "Game Over.png");
	prepareImage (bladePicture, this);
	// Add the picture to the list of images to be tracked
	tracker.addImage (menu, 0);
	tracker.addImage (bladePicture, 0);
	tracker.addImage (bladeBleed, 0);
	tracker.addImage (vampireR, 0);
	tracker.addImage (vampireL, 0);
	tracker.addImage (bladeRight, 0);
	tracker.addImage (bladeLeft, 0);
	tracker.addImage (gameOver, 0);
	try
	{
	    tracker.waitForAll ();
	}


	catch (InterruptedException e)
	{
	}
	// Initialize the picture size
	picWidth = bladePicture.getWidth (null);
	picHeight = bladePicture.getHeight (null);

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
	{
	    if (start == true)
	    {
		if (howTo == true)
		{
		    if (pause == false)
		    {
			if (heart > 0)
			{
			    for (int i = 0 ; i < vampires.length ; i++)
			    {

				if (vampires [i] < x_pos)
				{

				    vampires [i]++;
				    if (score > 10)
				    {
					vampires [i] += 2;
				    }
				    else if (score > 40)
				    {
					vampires1 [i] += 3;
				    }


				}
				else if (vampires [i] > x_pos) //Vampire got killed
				{
				    vampires [i] = (int) (Math.random () * -10000 + - 1000);
				    score++;
				}
				else if (vampires [i] == x_pos)
				{
				    heart--;
				    vampires [i] = (int) (Math.random () * -10000 + - 1000);
				    hit = true;
				}


				if (vampires1 [i] > x_pos)
				{
				    vampires1 [i]--;
				    if (score > 10)
				    {
					vampires1 [i] -= 2;
				    }
				    else if (score > 40)
				    {
					vampires1 [i] -= 3;
				    }

				}
				else if (vampires1 [i] < x_pos) //Vampire got killed
				{
				    vampires1 [i] = (int) (Math.random () * 10000 + 1000);
				    score++;
				}
				else if (vampires1 [i] == x_pos)
				{
				    hit = true;
				    vampires1 [i] = (int) (Math.random () * 10000 + 1000);
				    heart--;
				}
			    }
			}
		    }

		}
	    }

	    // Ball is bounced if its x - position reaches the right border of the applet
	    if (x_pos > appletsize_x - radius)
	    {

		// Change direction of ball movement
		x_speed = -1;

	    }
	    // Ball is bounced if its x - position reaches the left border of the applet
	    else if (x_pos < radius)
	    {

		// Change direction of ball movement
		x_speed = 1;

	    }

	    x_pos += x_speed; //update the current location of the x on the ball
	    y_pos += y_speed; //update the current location of the y on the ball
	    if (score > highScore)
	    {
		highScore = score;
	    }

	    // repaint the applet
	    repaint ();

	    try
	    {
		// Stop thread for 9 milliseconds
		Thread.sleep (9);


	    }
	    catch (InterruptedException ex)
	    {
		// do nothing
	    }

	    // set ThreadPriority to maximum value
	    Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);

	}
    }



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
    public void mouseClicked (MouseEvent e)
    {
	mx = e.getX (); // gets the x - coordinate of the mouse's location
	my = e.getY ();
	//pause button
	if (mx > 377 && mx < 377 + 118 && my > 20 && my < 20 + 79 && pause == false)
	{
	    pause = true;
	}
	else if ((mx > 340 && mx < 340 + 118 && my > 20 && my < 20 + 79) && pause == true)
	{
	    pause = false;
	}
	//Next button in the tutorial screen
	if (mx > 752 && mx < 752 + 103 && my > 36 && my < 36 + 81)
	{
	    howTo = true;
	    repaint ();
	}

	//start button
	if (mx > 340 && mx < 340 + 242 && my > 401 && my < 401 + 103)
	{
	    start = true;
	    repaint ();
	}
	if (mx > 402 && mx < 402 + 100 && my > 360 && my < 360 + 78)
	{
	    death = true;
	    if (death == true) //if dead
	    {
		heart = 3;
		score = 0;
		x_pos = appletsize_x / 2;         // x - Position of ball
		y_pos = appletsize_y / 2;
		start = false; //start
		howTo = false; //tutorial
		right = false; //right slash
		left = false; //left slash
		hit = false; //Blade getting hit
		vampireRight = true; //Vampires that are coming from the right
		vampireLeft = true; //Vampires that are coming from the left
		death = false; //when you die
		pause = false;
		vampires [0] = -900;
		vampires [1] = -2000;
		vampires [2] = -6000;
		vampires [3] = -3800;
		vampires [4] = -30;
		vampires [5] = -200;
		vampires [6] = -7000;
		vampires [7] = -8000;
		vampires [8] = 0;

		vampires1 [0] = 800;
		vampires1 [1] = 1500;
		vampires1 [2] = 950;
		vampires1 [3] = 2000;
		vampires1 [4] = 5000;
		vampires1 [5] = 2900;
		vampires1 [6] = 4000;
		vampires1 [7] = 9000;
		vampires1 [8] = 6500;
	    }
	    repaint ();
	}



    }



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



    public void keyPressed (KeyEvent e)
    {
	int key = e.getKeyCode ();

	showStatus (key + "");

	if (start == true)
	{
	    if (howTo == true)
	    {
		if (pause == false)
		{
		    if (heart > 0)
		    {

			// user presses left cursor key
			if (key == 37)
			{
			    left = true;
			    // changing x - speed so that ball moves to the left side (x_speed negative)
			    x_speed = -14;
			    y_speed = 0;
			}
			// user presses right cursor key
			else if (key == 39)
			{
			    right = true;
			    // changing x - speed so that ball moves to the right side (x_speed positive)
			    x_speed = 14;
			    y_speed = 0;
			}


		    }
		}
	    }
	}


    }


    public void keyReleased (KeyEvent e)
    {
	int key = e.getKeyCode ();

	if (start == true)
	{
	    if (howTo == true)
	    {

		// user presses left cursor key
		if (key == 37)
		{
		    left = false;
		    // changing x - speed so that ball moves to the left side (x_speed negative)
		    x_speed = 0;
		    y_speed = 0;
		}


		// user presses right cursor key
		else if (key == 39)
		{
		    right = false;
		    // changing x - speed so that ball moves to the right side (x_speed positive)
		    x_speed = 0;
		    y_speed = 0;
		}

	    }
	}


    }


    public void keyTyped (KeyEvent e)
    {

    }



    public void paint (Graphics g)
    {
	if (start == false)
	{
	    //Menu Screen
	    g.drawImage (menu, 0, 0, null);
	}
	if (start == true)
	{
	    g.drawImage (tutorial, 0, 0, null);
	}
	if (start == true)
	{
	    if (howTo == true)
	    {
		//Game background
		g.drawImage (mainGame, 0, 0, null);
		//display counter of hearts
		if (heart == 1)
		{
		    g.drawImage (heart1, 50, 20, 3 * radius, 2 * radius, null);
		}
		else if (heart == 2)
		{
		    g.drawImage (heart3, 50, 20, 3 * radius, 2 * radius, null);

		}
		else if (heart == 3)
		{
		    g.drawImage (heart2, 50, 20, 3 * radius, 2 * radius, null);

		}
		//Font of the score
		Font font = new Font ("Terminal Regular", 0, 25);
		g.setColor (Color.WHITE);
		g.setFont (font);
		//display of the score
		g.drawString ("Score : " + String.valueOf (score), 700, 60);
		g.drawString ("High Score : " + String.valueOf (highScore), 700, 80);
		//vampire hunter
		if (right == false && left == false && hit == false)
		{
		    g.drawImage (bladePicture, x_pos - radius, y_pos - radius, 2 * radius, 2 * radius, null);
		}
		//blade getting hit animation
		if (hit == true && right == false && left == false)
		{
		    g.drawImage (bladeBleed, x_pos - radius, y_pos - radius, 2 * radius, 2 * radius, null);
		}
		//vampire slash right and left animation
		if (left == true)
		{
		    g.drawImage (bladeLeft, x_pos - radius, 220, 3 * radius, 3 * radius, null);
		}
		if (right == true)
		{
		    g.drawImage (bladeRight, x_pos - radius, 220, 3 * radius, 3 * radius, null);
		}
		//concussion
		if (x_pos > appletsize_x - radius || x_pos < radius)
		{
		    g.drawImage (dizzy, x_pos - radius, 250, 3 * radius, radius, null);

		}

		//Draws the array of vampires
		for (int i = 0 ; i < 8 ; i++)
		{

		    g.drawImage (vampireL, vampires [i] - radius, 220, 3 * radius, 3 * radius, null); //Left Vampire

		    g.drawImage (vampireR, vampires1 [i] - radius, 220, 3 * radius, 3 * radius, null); //Right Vampire
		}
		if (heart > 0)
		{ //Pause screen
		    if (pause == true)
		    {
			g.drawImage (pauseOn, 378, 20, 3 * radius, 2 * radius, null);

		    }
		    else if (pause == false)
		    {
			g.drawImage (pauseOff, 378, 20, 3 * radius, 2 * radius, null);

		    }
		}
	    }
	    //death screen
	    if (heart == 0)
	    {
		g.drawImage (beating, 0, 0, 900, 600, null);
		g.drawImage (gameOver, 0, 0, null);
		g.drawString ("High Score : " + String.valueOf (highScore), 376, 470);
	    }


	}
    } // paint method
} // BladeTheVampireHunter class


