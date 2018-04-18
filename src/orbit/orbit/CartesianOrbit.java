package orbit.orbit;

import orbit.model.Body;

public class CartesianOrbit extends Orbit {

	public double[] getPositionRelativeToParent(double angle) {
		double r = getSemiLatusRectum() / (1 + (getEccentricity() * Math.cos(angle)));
		return new double[] {Math.cos(angle) * r, Math.sin(angle) * r, 0};
	}

	public CartesianOrbit(Body parent, Body child) {
		super(parent, child);
	}

	public double getSemiLatusRectum() {
		double h = getSpecificAngularMomentum();
		double u = getGravitationalParameter();
		return h * h / u;
	}

	public double getTrueAnomaly() {
		double p = getSemiLatusRectum();
		double e = getEccentricity();
		return Math.acos(((p / distance()) - 1) / e);
	}

	public double getPeriapsis() {
		return getSemiMajorAxis() * (1 - getEccentricity());
	}

	public double getEccentricity() {
		double soe = getSpecificOrbitalEnergy();
		double h = getSpecificAngularMomentum();
		double u = getGravitationalParameter();
		return Math.sqrt(1 + (2 * soe * h * h) / (u * u));
	}

	public double getSemiMajorAxis() {
		return -getGravitationalParameter() / (2 * getSpecificOrbitalEnergy());
	}

	public double getSpecificOrbitalEnergy() {
		return getChild().getDirectionSquared() / 2 - (getGravitationalParameter() / distance());
	}

	public double getSpecificAngularMomentum() {
		double[] dir = getChild().getDirectionVector();
		double distx = getParent().getX() - getChild().getX();
		double disty = getParent().getY() - getChild().getY();
		return (dir[0] * disty) - (dir[1] * distx);
	}

	public double distance() {
		return getChild().distance(getParent());
	}

	@Override
	public double getInclinationAngle() {
		return 0;
	}

	@Override
	public double getAscendingNodeAngle() {
		return 0;
	}

	@Override
	public double getPeriapsisAngle() {
		return 0;
	}

}
