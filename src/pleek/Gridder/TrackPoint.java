package pleek.Gridder;

import processing.core.PVector;

public class TrackPoint {

	/*
	 * THIS THE POINT THAT WILL BE TRACKED
	 * FOR THE GRID FUNCTIONS
	 * MEANING: IF WE WANT TO TRACK THE MOUSE,
	 * WE NEED TO CREATE/SET GridPoint(mouseX, mouseY);
	 */

	PVector point;

	public TrackPoint() {
		point = new PVector();
	}

	public void setTrackPoint(PVector _point) {
		point = _point;
	}

	public void setTrackPoint(float _x, float _y, float _z) {
		point.x = _x;
		point.y = _y;
		point.z = _z;
	}
	public void setTrackPoint(float _x, float _y) {
		point.x = _x;
		point.y = _y;
		point.z = pleek.Gridder.Gridder.class.;
	}
}
