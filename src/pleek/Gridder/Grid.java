package pleek.Gridder;

import processing.core.*;

abstract public class Grid {

	PApplet p5;
	PVector anchor;
	PVector size;
	protected int[] subDivsCount;
	protected float[] subDivsX, subDivsY, subDivsZ;
	protected float[] midPoints;

	private int interpolationType;

	Grid(PApplet _p5, PVector _origin, PVector _size, int[] _subdivisions) {

		p5 = _p5;
		anchor = _origin;
		size = _size;
		subDivsCount = _subdivisions;

		subDivsX = new float[subDivsCount[0]];
		subDivsY = new float[subDivsCount[1]];
		subDivsZ = new float[subDivsCount[2]];
		
		midPoints = new float[3];

		setInterpolationType(1);
		setMidpoint(0.9f, 0.5f, 0.5f);
		fillSubDivisions();

	}

	Grid(PApplet _p5) {

		p5 = _p5;
		anchor = new PVector(0, 0, 0);
		size = new PVector(100, 100, 100);
		subDivsCount = new int[3];
		subDivsCount[0] = 10;
		subDivsCount[1] = 10;
		subDivsCount[2] = 10;


	}

	abstract public void draw();

	public void setInterpolationType(int type) {

		if (type <= Gridder.QUADRATIC) {
			interpolationType = type;
		} else {
			interpolationType = Gridder.LINEAR;
		}
		/*
		switch (type) {
		case 0:
			interpolationType = Gridder.LINEAR;
			break;
		case 1:
			interpolationType = Gridder.QUADRATIC;
			break;
		default:
			interpolationType = Gridder.LINEAR;
			break;
		}
		*/

	}

	public void setMidpoint(float midPointX, float midPointY, float midPointZ) {
		if (interpolationType != Gridder.LINEAR) {
			midPoints[0] = midPointX;
			midPoints[1] = midPointY;
			midPoints[2] = midPointZ;
		} else {
			midPoints[0] = 0.5f;
			midPoints[1] = 0.5f;
			midPoints[2] = 0.5f;
			System.out.println("+ Gridder Warning :: Linear interpolations do not have a variable midPoint");
		}
		
		buildInterpolations();
	}

	private void buildInterpolations() {
		
		//CREATING THE QUADRATIC GRADIENT FROM 0 TO 1 (NOT FROM POINT TO POINT IN SPACE)

		for (int t = 0; t < subDivsCount[0]; t++) {

			float normalizedResolution = (float) t / subDivsCount[0];

			subDivsX[t] = 2*(1 - (normalizedResolution)) * (normalizedResolution) * midPoints[0] + p5.pow((normalizedResolution),2) * 1;
			//ENTERA: subDivsX[t] = p5.pow((1 - normalizeResolution),2) * 0 + 2*(1 - (normalizeResolution)) * (normalizeResolution) * midPoints[0] + p5.pow((normalizeResolution),2) * 1;

			//System.out.println("Line " + t + " X at:" + xSubT);

		}
	}

}
