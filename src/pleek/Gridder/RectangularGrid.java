package pleek.Gridder;

import java.awt.Polygon;
import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PVector;

public class RectangularGrid extends Grid {

	Rectangle gridBounds;
	//Polygon gridBounds;
	PVector spacing;

	/**
	 * @param _p5
	 * @param _origin
	 * @param _size
	 * @param _subdivisions
	 */
	public RectangularGrid(PApplet _p5, PVector _origin, PVector _size, int[] _subdivisions) {
		super(_p5, _origin, _size, _subdivisions);
		init();

	}

	/**
	 * @param _p5
	 */
	public RectangularGrid(PApplet _p5) {
		super(_p5);
		init();
	}

	private void init(){//float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		//int[] xs = {(int)x1,(int)x2,(int)x3,(int)x4};
		//int[] ys = {(int)y1,(int)y2,(int)y3.(int)y4};
		//gridBounds = new Polygon(xs, ys, 4);
		gridBounds = new Rectangle((int) anchor.x, (int) anchor.y, (int) size.x, (int) size.y);
		spacing = new PVector(size.x / subDivsCount[0], size.y / subDivsCount[1], size.z / subDivsCount[2]);
	}

	public void draw() {
		p5.noFill();
		p5.stroke(255);

		// X
		for (int i = 0; i < subDivsCount[0]; i++) {
			//float spacingSum = anchor.x;
			float lineX = p5.map(subDivsX[i], 0, 1, anchor.x, anchor.x + gridBounds.width);
			p5.line(lineX, anchor.y, lineX, anchor.y + gridBounds.height);
			p5.text(i, lineX, anchor.y - 20);
		}

		// Y
		for (int i = 0; i < subDivsCount[1] + 1; i++) {
			p5.line(anchor.x, anchor.y + (spacing.y * i), anchor.x + size.x, anchor.y + (spacing.y * i));
		}

	}
	
	/*
	public void setInterpolationType(int type){
		super.setInterpolationType(type);
	}
	*/

}
