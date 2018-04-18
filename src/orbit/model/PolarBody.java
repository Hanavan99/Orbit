package orbit.model;

import orbit.orbit.Orbit;

public class PolarBody extends Body {

	private Orbit orbit;
	private double time;
	
	public PolarBody(Orbit orbit) {
		this.orbit = orbit;
	}
	
	
	@Override
	public void applyGravity(Body other, double time) {
		super.applyGravity(other, time);
		this.time += time;
	}
	@Override
	public double getX() {
		double[] pos = orbit.getPositionRelativeToParent(time);
		return pos[0];
	}

	@Override
	public double getY() {
		double[] pos = orbit.getPositionRelativeToParent(time);
		return pos[0];
	}

	@Override
	public Orbit computeOrbit(Body parent) {
		return orbit;
	}

	
	
}
