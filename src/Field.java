import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Field {

	ArrayList<BrickPiece> brickList = new ArrayList<BrickPiece>();
	Color[] brickColors = new Color[]{Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
	int numberOfBricks = 10;
	BallPiece ball;
	BarPiece bar;
	Thread ballMoveThread;
	Thread ballCheckThread;

	BallPiece normalBall = new BallPiece(Game.width/2,(Game.height*3)/4,18,3,300); //TODO make array of ball objects

	int brickWidth = ((Game.width-((numberOfBricks+1)*10)) / numberOfBricks);
	int brickHeight = brickWidth/3;

	int barWidth = brickWidth*2;
	int barHeight = brickHeight;

	public void initializeGame()
	{
		initializeBricks();
		spawnBall(Game.width/2,(Game.height*3)/4,18,3,10); //TODO fix speed
		bar = new BarPiece(Game.width/2 - (barWidth/2),(Game.height*7)/8);
	}

	public void spawnBall(int x, int y, int radius, int direction, int speed) //TODO put in direction
	{		
		ball = new BallPiece(x,y, radius, direction, speed);
		moveBall();
	}

	public void resetBall(int ballType)
	{
		//ball = BallPiece.ballArray[ballType];
		ball.setX(BallPiece.ballArray[ballType].getX());
		ball.setY(BallPiece.ballArray[ballType].getY());
		ball.setRadius(BallPiece.ballArray[ballType].getRadius());
		ball.setDirection(BallPiece.ballArray[ballType].getDirection());
		ball.setSpeed(BallPiece.ballArray[ballType].getSpeed());
	}
	
	public void checkCollision()
	{		
		ball.bounce(wallCollision()[0],wallCollision()[1]); //regular bounce
//		else if(barCollision())
//			ball.bounce();
	}

	public Boolean[] wallCollision()
	{	
		Boolean [] collisions = new Boolean[] {(ball.getX()+ball.getRadius()*2 >= Game.width || ball.getX() <= 0),(ball.getY() <= 0)};
		
		return collisions; //if x or y is out of bounds
	}
	
	public boolean barCollision()
	{			
		if(ball.getX()+ball.getRadius()*2 >= bar.getX()-barWidth/2 && ball.getX() <= bar.getX()+barWidth/2
				&& ball.getY()+ball.getRadius()*2 >= bar.getY() && ball.getY() <= bar.getY()+barHeight)
			return true;
		else if (ball.getY()+ball.getRadius()*2 >= Game.height-Game.statusBarHeight)
		{
			//spawnBall(Game.width/2,(Game.height*3)/4,18,3,300);
			resetBall((int) (Math.random()*BallPiece.ballArray.length));
		}
		return false;
	}

	public void moveBall()
	{
		ballMoveThread = new Thread( new Runnable()
		{
			public void run(){
				while(true)
				{
					try {

						Thread.sleep(1000/60); //speed per second
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					ball.move();
					checkCollision(); //TODO RUN IN OWN THREAD
				}
			}
		});

		ballMoveThread.start();
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
