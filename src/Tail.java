
public class Tail {
	
	int tailX;
	public int getTailX()
	{
	    return tailX;
	}

	public void setTailX(
		int tailX)
	{
	    this.tailX = tailX;
	}

	int tailY;
	
	public int getTailY()
	{
	    return tailY;
	}

	public void setTailY(
		int tailY)
	{
	    this.tailY = tailY;
	}
	
	

	public Tail() {

	}

	public Tail(int xAxisStarting, int yAxisStarting) {

	    tailX = xAxisStarting;
	    tailY = yAxisStarting;
	    
	}
    }
