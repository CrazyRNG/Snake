import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;


	
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SnakeGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    static ArrayList<Tail> tails;

    
    public static ArrayList<Tail> getTails()
    {
	return tails;
    }

    public static void increaseTails()
    {
	if (tails.size() < 10) {
	    SnakeGUI.tails.add(new Tail(Worm.xAxisStarting, Worm.yAxisStarting));
	}

    }

    public JPanel contentPane;
    static JLabel currentScore;
    static JLabel bestScore;
    /**
     * Launch the application.
     */
    public static void main(
	    String[] args)
    {
	EventQueue.invokeLater(new Runnable() {
	    public void run()
	    {
		try {
		    SnakeGUI frame = new SnakeGUI();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     * @throws IOException 
     */
    public SnakeGUI() throws IOException {
	
	readFiles();
	tails = new ArrayList<Tail>();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(443, 209, 480, 390);
	//this.setResizable(false);
	
	//this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	JLabel cs = new JLabel(" Current Score:  ");
	JLabel bs = new JLabel(" Best Score:  ");
	currentScore = new JLabel();
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0,0));
	JPanel jp = new JPanel();
	jp.setLayout(new FlowLayout());
	//contentPane.add(new MyCanvis());
	setContentPane(contentPane);
	jp.add(cs);
	jp.add(currentScore);
	jp.add(bs);
	jp.add(bestScore);

	contentPane.add(new MyCanvis());
	currentScore.setPreferredSize(new Dimension(50,25));
	//currentScore.setEditable(false);
	currentScore.isOpaque();
	currentScore.setFocusable(false);
	bestScore.setPreferredSize(new Dimension(50,25));
	//bestScore.setEditable(false);
	bestScore.isOpaque();
	bestScore.setFocusable(false);
	contentPane.add(jp,BorderLayout.PAGE_START);
	Thread th = new Thread(new Runnable(){

	    @Override
	    public void run()
	    {
		// TODO Auto-generated method stub
		addKeyListener(new CustomKeyListener());
	    }
	    
	});
	
	th.start();
    }
    
    void readFiles(){
	bestScore= new JLabel();
	bestScore.setText("0");
	FileReader fr = null;
	URL url = (this.getClass().getResource("/jujkit.png"));
	
	try {
	    fr = new FileReader("D:\\Java\\01.08.2017\\WorkSpace\\resourceFolder\\text.txt");
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	BufferedReader bufferedReader = new BufferedReader(fr);
	String line = "";
	try {
	    while((line = bufferedReader.readLine()) != null){
	        bestScore.setText(line);
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}

