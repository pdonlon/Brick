
public class BallPiece extends Piece{

	int radius;
	int direction;

	int[][] allDirections = new int[][]{{-1,-1},{1,-1},{1,1},{-1,1}};
	
	//1 2 quadrants for directions
	//3 4
	
	public BallPiece(int x, int y, int radius, int direction) {
		super(x, y);

		this.radius = radius;
		this.direction = direction;
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
	
	public void move()
	{
	x = x+allDirections[direction][0];
	y = y+allDirections[direction][1];
		
	if(wallCollision(x+allDirections[direction][0],y+allDirections[direction][1])) //will really check the next place
		bounce();
	}
	
	public boolean collision(int xDes, int yDes)
	{
		//TODO make into 2 methods - check boundary collision and piece collision
		boolean collision = false;

		collision = wallCollision(xDes, yDes);
		
		return collision;
	}
	
	public boolean wallCollision(int xDes, int yDes)
	{		
		if((xDes+radius*2 > Game.width || xDes < 0) || (yDes+radius*2 > Game.height || yDes < 0)) //if x or y is out of bounds
			return true;	
		else
			return false;
	}
	
	public void bounce()//takes direction returns opposite direction (also should check what it's bouncing off of - bar should have different behaviors)
	{
		if(direction<3)
			direction++;
		else
			direction = 0;
	}
	
	
}
