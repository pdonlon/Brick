import java.awt.Color;


public class BrickPiece extends Piece{

	Color c;
	int brickNumber; //helps identify which brick to remove when hit
	
	public BrickPiece(int x, int y, Color c, int brickNumber) {
		super(x, y);

		this.c = c;
		this.brickNumber = brickNumber;
	}

	public Color getColor()
	{
		return c;
	}
	
	public int getBrickNumber()
	{
		return brickNumber;
	}
}
