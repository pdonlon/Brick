import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Field {

	ArrayList<BrickPiece> brickList = new ArrayList<BrickPiece>();
	Color[] brickColors = new Color[]{Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
	int numberOfBricks = 10;
	BallPiece ball;
	BarPiece bar;
	
	int brickWidth = ((Game.width-((numberOfBricks+1)*10)) / numberOfBricks);
	int brickHeight = brickWidth/3;
	
	int barWidth = brickWidth*2;
	int barHeight = brickHeight;
		
	public void initializeGame()
	{
		initializeBricks();
		spawnBall(Game.width/2,(Game.height*3)/4);
		bar = new BarPiece(Game.width/2 - (barWidth/2),(Game.height*7)/8);
	}

	public void spawnBall(int x, int y) //TODO put in direction
	{
		ball = new BallPiece(x,y, 18,3,300);
	}
	
	public void initializeBricks()
	{
		int counter = 0;
		int offY = 10;
		for(int y=0; y<6; y++)
		{
			int offX = 0;
			for(int x=0; x<10; x++)
			{
				offX+=10;
				brickList.add(new BrickPiece(x*brickWidth+offX,y*brickHeight+offY,brickColors[y], counter));
				counter++;
			}
			offY+=10;
		}
	}
	
	public void moveBar(int xDes)
	{
		//System.out.println(xDes+" + "+barWidth);
		
		if(xDes - barWidth/2 < 0)
		{
			bar.setX(barWidth/2);
			return;
		}
		if(xDes + barWidth/2 > Game.width)
		{
			bar.setX(Game.width - barWidth/2);
			return;
		}
			bar.setX(xDes);
			
	}

	public void play()
	{
		Thread b = new Thread( new Runnable(){
			public void run(){

				ball.move();
				
					try {
						Thread.sleep(100000000);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

			}
		});

		b.start();
	}

	public void paintField(Graphics g) 
	{
		for(BrickPiece b: brickList)
		{
			g.setColor(b.getColor());
			g.fillRect(b.getX(), b.getY(), brickWidth, brickHeight);
		}
		
//		g.setColor(Color.red);
//		g.fillRect(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
		
		g.setColor(Color.white);
		g.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
		
		g.setColor(Color.black);
		g.fillRect(bar.getX()-barWidth/2, bar.getY(), barWidth, barHeight);
		
		//System.out.println(ball.getX()+", "+ball.getY());
	}
}
