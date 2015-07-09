import java.awt.Color;
import java.util.Observable;
import java.util.Observer;


public class BallPiece extends Piece{

	int radius;
	int direction;
	int speedX;
	int speedY;

	static BallPiece normalBall = new BallPiece(Game.width/2,(Game.height*3)/4,18,3,10);
	static BallPiece bigBall = new BallPiece(Game.width/2,(Game.height*3)/4,36,3,7);
	static BallPiece smallBall = new BallPiece(Game.width/2,(Game.height*3)/4,9,3,13);

	static BallPiece[] ballArray = new BallPiece[]{normalBall, bigBall, smallBall};
	
	int[][] allDirections = new int[][]{{-1,-1},{1,-1},{1,1},{-1,1}};

	//1 2 quadrants for directions
	//3 4

	public BallPiece(int x, int y, int radius, int direction, int speed) {
		super(x, y);

		this.radius = radius;
		this.direction = direction;
		speedX = speed;
		speedY = speed;
	}

	public int getRadius()
	{
		return radius;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(int direction)
	{
		this.direction = direction;
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
		x = x+(allDirections[direction][0])*speedX;
		y = y+(allDirections[direction][1])*speedY;
	}

	//yDes+radius*2 > Game.height-Game.statusBarHeight

	public void bounce(boolean xOut, boolean yOut)//takes direction returns opposite direction (also should check what it's bouncing off of - bar should have different behaviors)
	{
		if(xOut)
			x += (-1)*speedX;
		if(yOut)
			y += (-1)*speedY;
		
		if(direction<3)
			direction++;
		else
			direction = 0;
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
