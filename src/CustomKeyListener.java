import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener{

    static int xListener = 0;
    static int yListener = 0;
    @Override
    public void keyPressed(
	    KeyEvent e)
    {
	
	Object obj = e.getKeyChar();
	if(obj.equals('s') || e.getKeyCode()==KeyEvent.VK_DOWN){
	    if(yListener != -10){
		 yListener = +10;
		 Worm.setMove(+10,0);
	    }
	    xListener = 0;
	   
	}else if(obj.equals('w') || e.getKeyCode()==KeyEvent.VK_UP){
	    if(yListener != +10){
		yListener = -10;
		Worm.setMove(-10,0);
	    }
	    xListener = 0;
	}else if(obj.equals('d') || e.getKeyCode()==KeyEvent.VK_RIGHT){
	    if(xListener != -10){
		xListener = +10;
		Worm.setMove(0,+10);
	    }
	    yListener = 0;
	}else if(obj.equals('a') || e.getKeyCode()==KeyEvent.VK_LEFT){
	    if(xListener != +10){
		xListener = -10;
		Worm.setMove(0,-10);
	    }
	    yListener = 0;
	    
	}
    }

    @Override
    public void keyReleased(
	    KeyEvent e)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void keyTyped(
	    KeyEvent e)
    {
	// TODO Auto-generated method stub
	
    }

}
