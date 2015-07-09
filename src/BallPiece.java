import java.awt.Color;
import java.util.Observable;
import java.util.Observer;


public class BallPiece extends Piece{

	int radius;
	int speedX;
	int speedY;

	static BallPiece normalBall = new BallPiece(Game.width/2,(Game.height*3)/4,18,10);
	static BallPiece bigBall = new BallPiece(Game.width/2,(Game.height*3)/4,36,7);
	static BallPiece smallBall = new BallPiece(Game.width/2,(Game.height*3)/4,9,13);

	static BallPiece[] ballArray = new BallPiece[]{normalBall, bigBall, smallBall};
	
	int[][] allDirections = new int[][]{{-1,-1},{1,-1},{1,1},{-1,1}};

	//1 2 quadrants for directions
	//3 4

	public BallPiece(int x, int y, int radius, int speed) {
		super(x, y);

		this.radius = radius;
		speedX = speed;
		speedY = speed;
	}

	public int getRadius()
	{
		return radius;
	}

	public int getSpeedX()
	{
		return speedX;
	}

	public void setSpeedX(int speed)
	{
		speedX = speed;
	}
	
	public int getSpeedY()
	{
		return speedY;
	}
	
	public void setSpeedY(int speed)
	{
		speedY = speed;
	}

	public void move()
	{
		x = x+(-1)*speedX;
		y = y+(-1)*speedY;
	}

	//yDes+radius*2 > Game.height-Game.statusBarHeight

	public void bounce(boolean xOut, boolean yOut)//takes direction returns opposite direction (also should check what it's bouncing off of - bar should have different behaviors)
	{	
		if(xOut)
		{
			speedX = -speedX;
			x += speedX;
		}
		if(yOut)
		{
			speedY = -speedY;
			y += speedY;
		}
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
