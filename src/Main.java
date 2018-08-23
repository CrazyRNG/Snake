import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Main extends Frame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Image buffImage;
    Main()
    {
	setSize(400,400);
	myCanvas cv = new myCanvas();
	repaint();
	try {
	    URL url = (this.getClass().getResource("/jujkit.png"));
	     buffImage = ImageIO.read(url).getScaledInstance(370, 280,
	    	    Image.SCALE_FAST);
	    
	    System.out.println(url);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	add(new Panel().add(cv));
	this.setVisible(true);
	
    }
    
    
    
    public class myCanvas extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	myCanvas(){
	    
	}
	
	public void paint(Graphics g){
		g.drawImage(buffImage, 0, 0, this);
	    }
    }
    public static void main(
	    String[] args)
    {
	
	new Main();
	System.out.println(2/10);

    }

}
