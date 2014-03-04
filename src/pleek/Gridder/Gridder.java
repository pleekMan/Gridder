package pleek.Gridder;
import processing.core.*;
import java.util.ArrayList;

public class Gridder {

	PApplet p5;
	ArrayList<Grid> grids;
	
	final static public int LINEAR = 0;
	final static public int QUADRATIC = 1;

	
	public Gridder(PApplet _p5){
		p5 = _p5;
		grids = new ArrayList<Grid>();
		
		
	}
	
	public RectangularGrid createRectangularGrid(){
		RectangularGrid grid = new RectangularGrid(p5);
		grids.add(grid);
		return grid;
	}
	
	public RectangularGrid createRectangularGrid(PVector origin, PVector size, int[] subDivisions){
		RectangularGrid grid = new RectangularGrid(p5, origin, size, subDivisions);
		grids.add(grid);
		return grid;
	}
}
