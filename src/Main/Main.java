package Main;

import java.awt.Frame;
import processing.core.*;
import processing.opengl.*;
import pleek.Gridder.*;
import peasy.*;

public class Main extends PApplet {

	Gridder gridder;
	RectangularGrid rectGrid;

	PeasyCam cam;

	public void setup() {
		size(500, 500, P3D);

		cam = new PeasyCam(this, 100);
		cam.setMinimumDistance(50);
		cam.setMaximumDistance(1000);
		cam.lookAt(200, 200, -200);
		cam.setDistance(1000);

		gridder = new Gridder(this);
		// rectGrid = gridder.createRectangularGrid();

		int[] subD = { 8, 8, 8 };
		rectGrid = gridder.createRectangularGrid(new PVector(100, 100), new PVector(300, 300), subD);
		rectGrid.setFrontCorners(100, 100, 0, 300, 50, 0, 50, 300, 0, 450, 400, 0);
		rectGrid.setBackCorners(100, 100, -400, 300, 50, -400, 50, 300, -400, 450, 400, -400);
		rectGrid.setMidpoint(0.5f, 0.5f, 0.5f);
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { Main.class.getName() });
		// PApplet.main(new String[] { "--present", Main.class.getName() }); //
		// PRESENT MODE
	}

	public void draw() {

		frame.setTitle(String.valueOf(frameRate));

		background(0);

		pushMatrix();
		// translate(-(width * 0.5f),0, -200);

		rectGrid.draw();

		stroke(0, 0, 255);
		ellipse(0, 0, 20, 20);
		text("Origin 0,0,0", 0, 0, 0);

		stroke(255, 255, 0);
		pushMatrix();
		translate(0, 0, cam.getLookAt()[2]);
		ellipse(cam.getLookAt()[0], cam.getLookAt()[1], 10, 10);
		popMatrix();

		popMatrix();
		// noLoop();

	}

	public void keyPressed() {

		if (key == '1') {
			rectGrid.setInterpolationType(Gridder.QUADRATIC);
		}

		if (key == '2') {
			rectGrid.setInterpolationType(Gridder.LINEAR);
		}

	}

}