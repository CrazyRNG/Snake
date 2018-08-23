import java.util.ArrayList;

public class Worm {

    public static int xAxisStarting;
    public static int yAxisStarting;
    public static int xMovement;
    public static int yMovement;
    public static int moveY;
    public static int moveX;
    public static ArrayList<Integer> xAxisPastTenMoves;
    public static ArrayList<Integer> yAxisPastTenMoves;
    public static int diedxTimes = 1;
    boolean grow = false;
    static boolean colDead = false;

    Worm() {

	xAxisPastTenMoves = new ArrayList<Integer>();
	yAxisPastTenMoves = new ArrayList<Integer>();
	xAxisStarting = 200;
	xAxisPastTenMoves.add(xAxisStarting);
	xAxisPastTenMoves.add(xAxisStarting);
	xAxisPastTenMoves.add(xAxisStarting);
	yAxisStarting = 150;
	yAxisPastTenMoves.add(yAxisStarting - 30);
	yAxisPastTenMoves.add(yAxisStarting - 20);
	yAxisPastTenMoves.add(yAxisStarting - 10);

	moveY = 0;
	moveX = 0;
	MyCanvis.potatoArrayX = new ArrayList<>();
	MyCanvis.potatoArrayY = new ArrayList<>();

    }

    Worm(int d) {
	diedxTimes += d;
	xAxisPastTenMoves = new ArrayList<Integer>();
	yAxisPastTenMoves = new ArrayList<Integer>();
	xAxisStarting = 200;
	xAxisPastTenMoves.add(xAxisStarting);
	xAxisPastTenMoves.add(xAxisStarting);
	xAxisPastTenMoves.add(xAxisStarting);
	yAxisStarting = 150;
	yAxisPastTenMoves.add(yAxisStarting - 30);
	yAxisPastTenMoves.add(yAxisStarting - 20);
	yAxisPastTenMoves.add(yAxisStarting - 10);

	moveY = 0;
	moveX = 0;
	MyCanvis.potatoArrayX = new ArrayList<>();
	MyCanvis.potatoArrayY = new ArrayList<>();
    }

    void collision(
	    int x,
	    int y)
    {
	if (xAxisPastTenMoves.size() > 3) {
	    for (int i = xAxisPastTenMoves.size()-3; i >= 0 ; i--) {
		if (x == xAxisPastTenMoves.get(i) && y == yAxisPastTenMoves.get(i)) {
		    colDead = true;
		}
	    }
	}

    }

    public void autoMoveY()
    {

	if (xAxisPastTenMoves.size() == 20) {
	    xAxisPastTenMoves.remove(0);
	    yAxisPastTenMoves.remove(0);
	}

	for (int i = 0; i < xAxisPastTenMoves.size(); i++) {
	    if (xAxisPastTenMoves.get(i) == MyCanvis.dotX && yAxisPastTenMoves.get(i) == MyCanvis.dotY) {
		MyCanvis.pass = true;
		grow = true;

		System.out.println(xAxisPastTenMoves.size());
	    }
	}

	 if (xAxisPastTenMoves.size() <= 100 && grow == false) {
	    xAxisPastTenMoves.remove(0);
	    yAxisPastTenMoves.remove(0);
	    xAxisPastTenMoves.add(xAxisStarting);
	    yAxisPastTenMoves.add(yAxisStarting);
	    xAxisStarting += moveX;
	    yAxisStarting += moveY;

	} else if (xAxisPastTenMoves.size() <= 100 && grow == true) {

	    xAxisPastTenMoves.add(xAxisStarting);
	    yAxisPastTenMoves.add(yAxisStarting);
	    xAxisStarting += moveX;
	    yAxisStarting += moveY;
	    grow = false;
	}
	collision(xAxisStarting, yAxisStarting);

    }

 

    public static int getMove()
    {
	return moveY;
    }

    public static void setMove(
	    int moveY,
	    int moveX)
    {
	Worm.moveY = moveY;
	Worm.moveX = moveX;
    }
}
