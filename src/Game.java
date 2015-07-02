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
	boolean gameOver = false;
	
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

		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		//setFocusable(true);
		requestFocus();
		
		this.setVisible(true);
		while(!gameOver)
		{
			moveBall();
		}
	}
	
	public void moveBall() throws InterruptedException
	{
		Thread.sleep(2);
		field.ball.move();
		repaint();
		//System.out.println("moved");
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		field.moveBar(e.getX());

	}
	
}
