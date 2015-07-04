
public class Piece {

	//contrusctor includes location x and y, what kind of piece it is
	
	int x;
	int y;

	public Piece(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	//paint by going through array list of bricks that are still left and then print the location of bar and ball

}
