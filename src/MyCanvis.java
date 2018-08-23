
//learn more about Jpanel/Jcomponent and adding one into another and interactions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class MyCanvis extends JComponent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    int wormX, wormY;
    int x, y;
    ArrayList<CooardinateAxes> coordinates;
    Image buffImage;
    Graphics2D gd2;
    Graphics g;
    AffineTransform affAT;
    Image buffImageTwo;
    Worm worm;
    final int speedChange = 50;
    long wormSpeed;
    long paintSpeed;
    int xYellowWallsStart;
    int xYellowWallsFinish;
    int xYellowWallsRange;
    int yYellowWallsStart;
    int yYellowWallsFinish;
    int yYellowWallsRange;
    int startingSeconds;
    int startingMinutes;
    static int dotX;
    static int dotY;
    Random rnd;
    Random rnd1;
    Thread potatoThread;
    ArrayList<Integer> potatoesX;
    int numberOfPotatoes;
    static ArrayList<Integer> potatoArrayX = new ArrayList<>();
    static ArrayList<Integer> potatoArrayY = new ArrayList<>();
    int walkScore;
    int score;

    MyCanvis() {
	
	
	score = -60;
	worm = new Worm();
	wormX = Worm.xAxisStarting;
	wormY = Worm.yAxisStarting;
	calendar = Calendar.getInstance();
	startingSeconds = calendar.get(Calendar.SECOND);
	startingMinutes = calendar.get(Calendar.MINUTE);
	System.out.println(startingSeconds);
	System.out.println(startingMinutes);
	potatoesX = new ArrayList<>();
	potatoesY = new ArrayList<>();
	potatoesX.add(100);
	potatoesY.add(100);
	wormSpeed = 1000;
	paintSpeed = 1000;
	xYellowWallsStart = 26;
	xYellowWallsFinish = 400;
	yYellowWallsStart = 0;
	yYellowWallsFinish = 300;
	xYellowWallsRange = xYellowWallsFinish - xYellowWallsStart;
	yYellowWallsRange = yYellowWallsFinish - yYellowWallsStart;
	createImage();

	move(worm);
	doArt(worm);

    }

    public ArrayList<Integer> getPotatoesX()
    {
	return potatoesX;
    }

    public void setPotatoesX(
	    ArrayList<Integer> potatoesX)
    {
	this.potatoesX = potatoesX;
    }

    ArrayList<Integer> potatoesY;

    public ArrayList<Integer> getPotatoesY()
    {
	return potatoesY;
    }

    public void setPotatoesY(
	    ArrayList<Integer> potatoesY)
    {
	this.potatoesY = potatoesY;
    }

    Calendar calendar;
    static boolean pass = true;

    public long getWormSpeed()
    {
	return wormSpeed;
    }

    public void setWormSpeed(
	    long wormSpeed)
    {
	this.wormSpeed = wormSpeed;
    }

    public long getPaintSpeed()
    {
	return paintSpeed;
    }

    public void setPaintSpeed(
	    long paintSpeed)
    {
	this.paintSpeed = paintSpeed;
    }

    void pass()
    {

	{

	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
		@Override
		public void run()
		{
		    pass = true;
		}
	    }, 0, 1999, TimeUnit.MILLISECONDS);
	}
    }

    void doArt(
	    Worm worm)
    {

	{

	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
		@Override
		public void run()
		{
		    walkScore();
		   // 
		    SnakeGUI.currentScore.setText(String.valueOf(getScore()));
		    if (Integer.parseInt(SnakeGUI.bestScore.getText()) < Integer
			    .parseInt(SnakeGUI.currentScore.getText())) {
			SnakeGUI.bestScore.setText(SnakeGUI.currentScore.getText());
		    }
		    int numOfLoops = Worm.xAxisPastTenMoves.size() / 10;
		    numOfLoops++;
		    for (int i = 1, x = 0, y = 10; i <= numOfLoops; i++, x += 10, y += 10) {
			if (isBetween(Worm.xAxisPastTenMoves.size(), x, y)) {
			    numberOfPotatoes = i;
			}
		    }

		    repaint();
		}
	    }, 0, 100, TimeUnit.MILLISECONDS);
	}
    }

    public boolean isBetween(
	    int numToTest,
	    int lower,
	    int upper)
    {

	return numToTest >= lower && numToTest <= upper;
    }

    void move(
	    Worm worm)
    {

	ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	executorService.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run()
	    {
		worm.autoMoveY();
		try {
		    die(isDead());
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}, 0, 100, TimeUnit.MILLISECONDS);
    }

    boolean isDead()
    {
	if (Worm.xAxisStarting < 40 || Worm.xAxisStarting > 400 || Worm.yAxisStarting < 10
		|| Worm.yAxisStarting > 280) {
	    return true;
	}
	if (Worm.colDead == true) {
	    return true;
	}
	return false;
    }

    void die(
	    boolean died) throws IOException
    {

	if (died) {

	    JOptionPane.showMessageDialog(this, "GG, YOU HAVE DIED " + Worm.diedxTimes + " TIMES!", "Inane warning",
		    JOptionPane.WARNING_MESSAGE);

	    
	    Worm.colDead = false;
	    worm = new Worm(1);
	    saveToFile();
	    score = 0;
	}
    }

    void randomPotato()
    {
	rnd = new Random();
	rnd1 = new Random();
	int xInput = 10 * (5 + rnd.nextInt(37));
	int yInput = 10 * (1 + rnd1.nextInt(28));
	potatoesX = new ArrayList<Integer>();
	potatoesY = new ArrayList<Integer>();
	potatoesX.add(xInput);
	potatoesY.add(yInput);
	System.out.println(potatoesX.get(0));
	System.out.println(potatoesY.get(0));
    }

    void spawnDot()
    {

	rnd = new Random();
	rnd1 = new Random();
	int xInput = 10 * (4 + rnd.nextInt(37));
	int yInput = 10 * (1 + rnd1.nextInt(28));
	dotX = xInput;
	dotY = yInput;
	potatoArrayX.add(dotX);
	potatoArrayY.add(dotY);
	// System.out.println(dotX);
	// System.out.println(dotY);
	pass = false;
	System.out.println("There are " + numberOfPotatoes + " Potatoes");

    }

    void isOnDot()
    {

    }

    void potato(
	    Graphics g)
    {

	if (pass) {
	    // for (int i = 0; i < numberOfPotatoes; i++) {
	    spawnDot();

	    score += Worm.xAxisPastTenMoves.size() * 10 * 2;
	    // }

	} else {
	    // for (int i = 0; i < numberOfPotatoes; i++) {
	    // g.fillRect(potatoArrayX.get(i), potatoArrayY.get(i), 10, 10);
	    // }
	    g.fillRect(dotX, dotY, 10, 10);

	}

    }

    void walkScore()
    {
	walkScore++;
	if (walkScore % 50 == 0) {
	    // System.out.println(walkScore+"sssss");
	    if (score == 5 == false) {
		score += 5;
	    }

	}

    }

    int getScore()
    {
	return score;
    }

    public void paintComponent(
	    Graphics g)
    {

	g.setColor(Color.BLACK);
	g.drawRect(25, 0, 401, 301);
	g.setColor(Color.ORANGE);
	g.fillRect(xYellowWallsStart, yYellowWallsStart, xYellowWallsFinish, yYellowWallsFinish);
	g.drawImage(buffImage, 40, 10, this);
	potato(g);

	// worm
	g.setColor(Color.BLACK);
	for (int i = 0; i < Worm.xAxisPastTenMoves.size(); i++) {
	    g.fillRect(Worm.xAxisPastTenMoves.get(i), Worm.yAxisPastTenMoves.get(i), 10, 10);
	}

	g.fillRect(Worm.xAxisStarting, Worm.yAxisStarting, 10, 10);

    }
    void saveToFile() throws IOException{
	
	
	 
	     File file = new File("D:\\Java\\01.08.2017\\WorkSpace\\resourceFolder\\text.txt");
		    System.out.println(file.getAbsolutePath());
//		    String str = " swagPOPIN ";
//		    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//		    writer.append(' ');
//		    writer.append(str);
//		    writer.close();
		    PrintWriter out;
		    try {
			out = new PrintWriter(new FileWriter(file));
			out.print(SnakeGUI.bestScore.getText());
			out.close();
			System.out.print(file);
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } 
	 
	    
	    
    }

    Image createImage()
    {
	try {

	    URL url = (this.getClass().getResource("/jujkit.png"));
	    System.out.println("this" + url);
	    // FileOutputStream fos = new
	    // FileOutputStream("resourceFolder/HighScore");
	    buffImage = ImageIO.read(url).getScaledInstance(370, 280, Image.SCALE_FAST);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
}
