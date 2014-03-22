package pleek.Gridder;

import java.awt.Polygon;
import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PVector;

public class RectangularGrid extends Grid {

	// Rectangle gridBounds;
	Polygon gridBounds;
	PVector spacing;
	PVector[] corners;

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

	private void init() {
		// gridBounds = new Polygon(xs, ys, 4);
		// gridBounds = new Rectangle((int) anchor.x, (int) anchor.y, (int)
		// size.x, (int) size.y);
		// spacing = new PVector(size.x / subDivsCount[0], size.y /
		// subDivsCount[1], size.z / subDivsCount[2]);

		// INIT DEFAULT CORNERS
		corners = new PVector[8];
		float initCornerSize = 100;
		corners[0] = new PVector(0, 0, 0);
		corners[1] = new PVector(initCornerSize, 0, 0);
		corners[2] = new PVector(0, initCornerSize, 0);
		corners[3] = new PVector(initCornerSize, initCornerSize, 0);
		corners[4] = new PVector(0, 0, initCornerSize);
		corners[5] = new PVector(initCornerSize, 0, initCornerSize);
		corners[6] = new PVector(0, initCornerSize, initCornerSize);
		corners[7] = new PVector(initCornerSize, initCornerSize, initCornerSize);
	}

	public void setFrontCorners(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
		corners[0].set(x1, y1, z1);
		corners[1].set(x2, y2, z2);
		corners[2].set(x3, y3, z3);
		corners[3].set(x4, y4, z4);

		for (int i = 0; i < 4; i++) {
			System.out.println("Front Corner " + i + " : " + corners[i].x + "," + corners[i].y);
		}
	}

	public void setBackCorners(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4) {
		corners[4].set(x1, y1, z1);
		corners[5].set(x2, y2, z2);
		corners[6].set(x3, y3, z3);
		corners[7].set(x4, y4, z4);

		for (int i = 4; i < corners.length; i++) {
			System.out.println("Back Corner " + i + " : " + corners[i].x + "," + corners[i].y);
		}
	}

	@SuppressWarnings("static-access")
	public void draw() {
		p5.noFill();
		p5.stroke(255);

		// p5.pushMatrix();
		// p5.translate(0, 0, 0);

		for (int i = 0; i < corners.length; i++) {
			p5.pushMatrix();
			p5.translate(corners[i].x, corners[i].y, corners[i].z);
			p5.stroke(255, 255, 0);
			p5.ellipse(0, 0, 10, 10);
			p5.popMatrix();
		}

		// DRAW GRID BOUNDS
		p5.noFill();
		// FRONT
		p5.stroke(255, 255, 0);
		p5.beginShape();
		p5.vertex(corners[0].x, corners[0].y, corners[0].z);
		p5.vertex(corners[1].x, corners[1].y, corners[1].z);
		p5.vertex(corners[3].x, corners[3].y, corners[3].z);
		p5.vertex(corners[2].x, corners[2].y, corners[2].z);
		p5.endShape(p5.CLOSE);
		// BACK
		p5.beginShape();
		p5.vertex(corners[4].x, corners[4].y, corners[4].z);
		p5.vertex(corners[5].x, corners[5].y, corners[5].z);
		p5.vertex(corners[7].x, corners[7].y, corners[7].z);
		p5.vertex(corners[6].x, corners[6].y, corners[6].z);
		p5.endShape(p5.CLOSE);

		p5.text("0", corners[0].x, corners[0].y, corners[0].z);
		p5.text("1", corners[1].x, corners[1].y, corners[1].z);
		p5.text("2", corners[2].x, corners[2].y, corners[2].z);
		p5.text("3", corners[3].x, corners[3].y, corners[3].z);
		p5.text("4", corners[4].x, corners[4].y, corners[4].z);
		p5.text("5", corners[5].x, corners[5].y, corners[5].z);
		p5.text("6", corners[7].x, corners[7].y, corners[7].z);
		p5.text("7", corners[6].x, corners[6].y, corners[6].z);

		// X LINES
		p5.stroke(255,0,0);
		for (int z = 0; z < subDivsCount[2]; z++) {

			float topZ_left = p5.lerp(corners[0].z, corners[4].z, subDivsZ[z]);
			float topZ_right = p5.lerp(corners[1].z, corners[5].z, subDivsZ[z]);
			float bottomZ_left = p5.lerp(corners[2].z, corners[6].z, subDivsZ[z]);
			float bottomZ_right = p5.lerp(corners[3].z, corners[7].z, subDivsZ[z]);

			float topY_left = p5.lerp(corners[0].y, corners[4].y, subDivsZ[z]);
			float topY_right = p5.lerp(corners[1].y, corners[5].y, subDivsZ[z]);
			float bottomY_left = p5.lerp(corners[2].y, corners[6].y, subDivsZ[z]);
			float bottomY_right = p5.lerp(corners[3].y, corners[7].y, subDivsZ[z]);

			for (int x = 0; x < subDivsCount[0]; x++) {

				float topZ = p5.lerp(topZ_left, topZ_right, subDivsX[x]);
				float bottomZ = p5.lerp(bottomZ_left, bottomZ_right, subDivsX[x]);

				float topY = p5.lerp(topY_left, topY_right, subDivsX[x]);
				float bottomY = p5.lerp(bottomY_left, bottomY_right, subDivsX[x]);

				float topX_front = p5.lerp(corners[0].x, corners[1].x, subDivsX[x]);
				float topX_back = p5.lerp(corners[4].x, corners[5].x, subDivsX[x]);
				float bottomX_front = p5.lerp(corners[2].x, corners[3].x, subDivsX[x]);
				float bottomX_back = p5.lerp(corners[6].x, corners[7].x, subDivsX[x]);

				float topX = p5.lerp(topX_front, topX_back, subDivsZ[z]);
				float bottomX = p5.lerp(bottomX_front, bottomX_back, subDivsZ[z]);

				p5.line(topX, topY, topZ, bottomX, bottomY, bottomZ);

			}

		}

		/*
		// Y LINES
		for (int z = 0; z < subDivsCount[2]; z++) {

			float topZ_left = p5.map(subDivsZ[z], 0, 1, corners[0].z, corners[4].z);
			float topZ_right = p5.map(subDivsZ[z], 0, 1, corners[1].z, corners[5].z);
			float bottomZ_left = p5.map(subDivsZ[z], 0, 1, corners[2].z, corners[6].z);
			float bottomZ_right = p5.map(subDivsZ[z], 0, 1, corners[3].z, corners[7].z);

			// float topY_left = p5.map(value, start1, stop1, start2, stop2)
			float topY_left = p5.lerp(corners[0].y, corners[4].y, subDivsZ[z]);
			float topY_right = p5.lerp(corners[1].y, corners[5].y, subDivsZ[z]);
			float bottomY_left = p5.lerp(corners[2].y, corners[6].y, subDivsZ[z]);
			float bottomY_right = p5.lerp(corners[3].y, corners[7].y, subDivsZ[z]);

			for (int y = 0; y < subDivsCount[0]; y++) {

				float topZ = p5.lerp(topZ_left, topZ_right, subDivsX[y]);
				float bottomZ = p5.lerp(bottomZ_left, bottomZ_right, subDivsX[y]);

				float topY = p5.lerp(topY_left, topY_right, subDivsX[y]);
				float bottomY = p5.lerp(bottomY_left, bottomY_right, subDivsX[y]);

				float topX_front = p5.lerp(corners[0].x, corners[1].x, subDivsX[y]);
				float topX_back = p5.lerp(corners[4].x, corners[5].x, subDivsX[y]);
				float bottomX_front = p5.lerp(corners[2].x, corners[3].x, subDivsX[y]);
				float bottomX_back = p5.lerp(corners[6].x, corners[7].x, subDivsX[y]);

				float topX = p5.lerp(topX_front, topX_back, subDivsZ[z]);
				float bottomX = p5.lerp(bottomX_front, bottomX_back, subDivsZ[z]);

				p5.line(topX, topY, topZ, bottomX, bottomY, bottomZ);

			}

		}
		*/
		

		/*
		 * // float[] planesZ = new float[subDivsCount[2]]; float[]
		 * depthProjectionForGridX = new float[4]; float[]
		 * depthProjectionForGridY = new float[4];
		 * 
		 * 
		 * for (int z = 0; z < subDivsCount[2]; z++) {
		 * 
		 * float upperZ_left = p5.map(subDivsZ[z], 0, 1, corners[0].z,
		 * corners[4].z); float upperZ_right = p5.map(subDivsZ[z], 0, 1,
		 * corners[1].z, corners[5].z); float bottomZ_left = p5.map(subDivsZ[z],
		 * 0, 1, corners[2].z, corners[6].z); float bottomZ_right =
		 * p5.map(subDivsZ[z], 0, 1, corners[3].z, corners[7].z);
		 * 
		 * for (int y = 0; y < subDivsCount[1]; y++) {
		 * 
		 * //float frontY_left = p5.map(subDivsY[y], 0, 1, corners[0].y,
		 * corners[2].y); //float frontY_right = p5.map(subDivsY[y], 0, 1,
		 * corners[1].y, corners[3].y); //float backY_left = p5.map(subDivsY[y],
		 * 0, 1, corners[4].y, corners[6].y); //float backY_right =
		 * p5.map(subDivsY[y], 0, 1, corners[5].y, corners[7].y);
		 * 
		 * 
		 * 
		 * for (int x = 0; x < subDivsCount[0]; x++) {
		 * 
		 * float frontX_top = p5.map(subDivsX[x], 0, 1, corners[0].x,
		 * corners[1].x); float frontX_bottom = p5.map(subDivsX[x], 0, 1,
		 * corners[0].x, corners[3].x); float backX_top = p5.map(subDivsX[x], 0,
		 * 1, corners[4].x, corners[5].x); float backX_bottom =
		 * p5.map(subDivsX[x], 0, 1, corners[6].x, corners[7].x);
		 * 
		 * float frontX = p5.lerp(frontX_top, frontX_bottom, subDivsY[y]); float
		 * backX = p5.lerp(backX_top, backX_bottom, subDivsY[y]);
		 * 
		 * // ------------------
		 * 
		 * float frontY_top = p5.lerp(corners[0].y, corners[1].y, subDivsX[x]);
		 * float frontY_bottom = p5.lerp(corners[2].y, corners[3].y,
		 * subDivsX[x]); float backY_top = p5.lerp(corners[4].y, corners[5].y,
		 * subDivsX[x]); float backY_bottom = p5.lerp(corners[6].y,
		 * corners[7].y, subDivsX[x]);
		 * 
		 * float topY = p5.lerp(frontY_top, backY_top, subDivsX[z]); float
		 * bottomY = p5.lerp(frontY_bottom, backY_bottom, subDivsZ[z]);
		 * 
		 * // ------------------
		 * 
		 * float frontZ_top = p5.lerp(corners[0].z, corners[1].z, subDivsX[x]);
		 * float frontZ_bottom = p5.lerp(corners[2].z, corners[3].z,
		 * subDivsX[x]); float backZ_top = p5.lerp(corners[4].z, corners[5].z,
		 * subDivsX[x]); float backZ_bottom = p5.lerp(corners[6].z,
		 * corners[7].z, subDivsX[x]);
		 * 
		 * float topZ = p5.lerp(frontZ_top, backZ_top, subDivsZ[x]); float
		 * bottomZ = p5.lerp(frontZ_bottom, backZ_bottom, subDivsZ[x]);
		 * 
		 * // ------------------
		 * 
		 * //float frontY = p5.lerp(frontY_left, frontY_right, subDivsX[z]);
		 * //float backY = p5.lerp(backY_left, backY_right, subDivsX[z]);
		 * 
		 * 
		 * 
		 * //p5.line(frontX_top, topY, z1, frontX_bottom, bottomY, z2)
		 * 
		 * 
		 * 
		 * //float finalY = p5.lerp(frontY_left, frontY_right, amt)
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * } for (int i = 0; i < subDivsCount[2]; i++) {
		 * 
		 * // Z FOR X LINES depthProjectionForGridX[0] = p5.map(subDivsZ[i], 0,
		 * 1, corners[0].z, corners[4].z); depthProjectionForGridX[1] =
		 * p5.map(subDivsZ[i], 0, 1, corners[1].z, corners[5].z);
		 * depthProjectionForGridX[2] = p5.map(subDivsZ[i], 0, 1, corners[2].z,
		 * corners[6].z); depthProjectionForGridX[3] = p5.map(subDivsZ[i], 0, 1,
		 * corners[3].z, corners[7].z);
		 * 
		 * depthProjectionForGridY[0] = p5.map(subDivsZ[i], 0, 1, corners[0].z,
		 * corners[4].z); depthProjectionForGridY[1] = p5.map(subDivsZ[i], 0, 1,
		 * corners[1].z, corners[5].z); depthProjectionForGridY[2] =
		 * p5.map(subDivsZ[i], 0, 1, corners[2].z, corners[6].z);
		 * depthProjectionForGridY[3] = p5.map(subDivsZ[i], 0, 1, corners[3].z,
		 * corners[7].z);
		 * 
		 * // INIT POINTS AT X/Y BOUNDS FOR SUBDIVISIONS float[] upperX = new
		 * float[subDivsCount[0]]; float[] upperY = new float[subDivsCount[0]];
		 * float[] lowerX = new float[subDivsCount[0]]; float[] lowerY = new
		 * float[subDivsCount[0]]; // float[] upperZ = new
		 * float[subDivsCount[0]]; // float[] lowerZ = new
		 * float[subDivsCount[0]];
		 * 
		 * float[] leftX = new float[subDivsCount[1]]; float[] leftY = new
		 * float[subDivsCount[1]]; float[] rightX = new float[subDivsCount[1]];
		 * float[] rightY = new float[subDivsCount[1]];
		 * 
		 * // CALCULATE POINTS AT BOUNDS for (int j = 0; j < upperX.length; j++)
		 * {
		 * 
		 * float upperZ = p5.lerp(depthProjectionForGridX[0],
		 * depthProjectionForGridX[1], 0.5f); float lowerZ =
		 * p5.lerp(depthProjectionForGridX[2], depthProjectionForGridX[3],
		 * 0.5f);
		 * 
		 * upperX[j] = p5.map(subDivsX[j], 0, 1, corners[0].x, corners[1].x);
		 * upperY[j] = p5.map(subDivsX[j], 0, 1, corners[0].y, corners[1].y);
		 * 
		 * lowerX[j] = p5.map(subDivsX[j], 0, 1, corners[2].x, corners[3].x);
		 * lowerY[j] = p5.map(subDivsX[j], 0, 1, corners[2].y, corners[3].y);
		 * 
		 * // DRAW p5.stroke((j / (float) upperX.length) * 255);
		 * p5.line(upperX[j], upperY[j], upperZ, lowerX[j], lowerY[j], lowerZ);
		 * 
		 * // System.out.println("\t" + upperX[j] + "\t" + upperY[j] + "\t" // +
		 * lowerX[j] + "\t" + lowerY[j]); }
		 * 
		 * for (int j = 0; j < leftX.length; j++) {
		 * 
		 * float leftZ = p5.lerp(depthProjectionForGridX[0],
		 * depthProjectionForGridX[2], 0.5f); float rightZ =
		 * p5.lerp(depthProjectionForGridX[1], depthProjectionForGridX[3],
		 * 0.5f);
		 * 
		 * leftX[j] = p5.map(subDivsY[j], 0, 1, corners[0].x, corners[2].x);
		 * leftY[j] = p5.map(subDivsY[j], 0, 1, corners[0].y, corners[2].y);
		 * 
		 * rightX[j] = p5.map(subDivsY[j], 0, 1, corners[1].x, corners[3].x);
		 * rightY[j] = p5.map(subDivsY[j], 0, 1, corners[1].y, corners[3].y);
		 * 
		 * // DRAW p5.stroke((j / (float) leftX.length) * 255);
		 * p5.line(leftX[j], leftY[j], leftZ, rightX[j], rightY[j], rightZ);
		 * 
		 * // System.out.println("\t" + upperX[j] + "\t" + upperY[j] + "\t" // +
		 * lowerX[j] + "\t" + lowerY[j]); }
		 * 
		 * }
		 * 
		 * p5.popMatrix();
		 */

		/*
		 * // DRAW LINES - VERTICAL p5.stroke(255,0,0); for (int i = 0; i <
		 * upperX.length; i++) { //p5.line(upperX[i], upperY[i], lowerX[i],
		 * lowerY[i]); p5.line(upperX[i], upperY[i], lowerX[i], lowerY[i]);
		 * 
		 * }
		 * 
		 * 
		 * // DRAW LINES HORIZONTAL for (int i = 0; i < leftX.length; i++) {
		 * //p5.line(upperX[i], upperY[i], lowerX[i], lowerY[i]);
		 * p5.line(leftX[i], leftY[i], rightX[i], rightY[i]);
		 * 
		 * }
		 */

		// LINE DRAWING FOR LINEAR INTERPOLATION (SIMPLE EASY WAY)
		/*
		 * // X for (int i = 0; i < subDivsCount[0]; i++) { //float spacingSum =
		 * anchor.x; float lineX = p5.map(subDivsX[i], 0, 1, anchor.x, anchor.x
		 * + gridBounds.width); p5.line(lineX, anchor.y, lineX, anchor.y +
		 * gridBounds.height); p5.text(i, lineX, anchor.y - 20); }
		 * 
		 * // Y for (int i = 0; i < subDivsCount[1] + 1; i++) {
		 * p5.line(anchor.x, anchor.y + (spacing.y * i), anchor.x + size.x,
		 * anchor.y + (spacing.y * i)); }
		 */

	}

	private void fillSubDivisions() {

	}

	/*
	 * public void setInterpolationType(int type){
	 * super.setInterpolationType(type); }
	 */

}