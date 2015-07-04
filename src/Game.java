import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JFrame implements ActionListener, KeyListener, MouseListener, MouseMotionListener{

	Field field;
	GamePanel jpan = new GamePanel();
	static int height = 750;
	static int width = 750;
	static int statusBarHeight;
	boolean gameOver = false;
	Thread paintThread;
	Thread ballThread;

	public static void main(String[] args) throws InterruptedException {

		Game game = new Game();	
	}

	public Game() throws InterruptedException
	{
		super("Brick");

		field = new Field();
		field.initializeGame();

		this.pack();
		this.add(jpan);
		this.setTitle("Brick");
		this.setSize(height,width);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		statusBarHeight = getInsets().top;
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		//setFocusable(true);
		requestFocus();

		this.setVisible(true);
		playGame();
	}
	
	public void moveBall()
	{
		System.out.println(field.ball.getSpeed());
		System.out.println(field.ball.getDirection());
		ballThread = new Thread( new Runnable()
		{
			public void run(){
				while(true)
				{
				field.ball.move();
					try {
						
						Thread.sleep(1000/field.ball.getSpeed()); //speed per second
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});

		ballThread.start();
	}
	
	public void playGame()
	{
		moveBall();
		paintGame(1000);
	}
	
	public void paintGame(final int framesPerSecond) //TODO later take variable to check if need to paint game or not
	{
		paintThread = new Thread( new Runnable(){
			public void run(){
				while(true)
				{
					try {
						Thread.sleep(1000/framesPerSecond);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					repaint();
				}
			}
		});

		paintThread.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public class GamePanel extends JPanel{
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			field.paintField(g);
			this.setBackground(Color.lightGray);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		field.moveBar(e.getX());
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		field.moveBar(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {

		field.moveBar(e.getX());
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		field.moveBar(e.getX());
	}

}
