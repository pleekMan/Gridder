package Main;

import pleek.Gridder.*;
import processing.core.*;

public class Main extends PApplet{
	
	Gridder gridder;
	RectangularGrid rectGrid;

	public void setup(){
		size(500,500);

		gridder = new Gridder(this);
		//rectGrid = gridder.createRectangularGrid();
		int[] subD = {30,10,3};
		rectGrid = gridder.createRectangularGrid(new PVector(100,100), new PVector(300,300), subD);
	}

	public static void main(String args[]){
		PApplet.main(new String[] { Main.class.getName() });
		//PApplet.main(new String[] { "--present", Main.class.getName() }); // PRESENT MODE
	}

	public void draw(){
		background(0);
		
		rectGrid.setMidpoint((float)mouseX/width, 0.5f, 0.5f);
		rectGrid.draw();
		
	}

	public void keyPressed(){

		if(key == '1'){
			rectGrid.setInterpolationType(Gridder.QUADRATIC);
		}
		
		if(key == '2'){
			rectGrid.setInterpolationType(Gridder.LINEAR);
		}
		

	}

}
