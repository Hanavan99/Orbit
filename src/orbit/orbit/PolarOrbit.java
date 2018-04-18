package orbit.orbit;

import orbit.PolarMath;
import orbit.model.Body;

public class PolarOrbit extends Orbit {

	/**
	 * The eccentricity of the orbit.
	 */
	private double e;
	/**
	 * The semi-major axis of the orbit.
	 */
	private double a;
	/**
	 * The inclination of the orbit, measured at the ascending node.
	 */
	private double i;
	/**
	 * The longitude of the ascending node.
	 */
	private double bigomega;
	/**
	 * The angle where the distance from the parent body and the child body is
	 * greatest.
	 */
	private double smallomega;
	/**
	 * The position of the object at time = 0.
	 */
	private double v;

	public PolarOrbit(Body parent, Body child, double e, double a, double i, double bigomega, double smallomega,
			double v) {
		super(parent, child);
		this.e = e;
		this.a = a;
		this.i = i;
		this.bigomega = bigomega;
		this.smallomega = smallomega;
		this.v = v;
	}

	@Override
	public double[] getPositionRelativeToParent(double time) {
		double n = Math.sqrt(getGravitationalParameter() / (a * a * a));
		double M = n * time;
		return null;//PolarMath.toQuaternion(pitch, roll, yaw);
	}

	@Override
	public double getEccentricity() {
		return e;
	}

	@Override
	public double getSemiMajorAxis() {
		return a;
	}

	@Override
	public double getInclinationAngle() {
		return i;
	}

	@Override
	public double getAscendingNodeAngle() {
		return bigomega;
	}

	@Override
	public double getPeriapsisAngle() {
		return smallomega;
	}

	@Override
	public double getTrueAnomaly() {
		return v;
	}

}
