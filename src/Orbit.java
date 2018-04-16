
public class Orbit {

	private Body parent;
	private Body child;
	
	//public boolean isMovingToward() {
		//double angleTo = child.angleTo(parent);
		//return Math.abs(angleTo - child.getAngle()) < Math.PI / 2;
	//}
	
	public Body getParent() {
		return parent;
	}
	
	public Body getChild() {
		return child;
	}
	
	public double getDistanceFromParent(double angle) {
		return getSemiLatusRectum() / (1 + (getEccentricity() * Math.cos(angle)));
	}
	
	public Orbit(Body parent, Body child) {
		this.parent = parent;
		this.child = child;
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

	public double getGravitationalParameter() {
		return Body.G * (child.getMass() + parent.getMass());
	}

	public double getSpecificOrbitalEnergy() {
		return child.getDirectionSquared() / 2 - (getGravitationalParameter() / distance());
	}

	public double getSpecificAngularMomentum() {
		double[] dir = child.getDirectionVector();
		double distx = parent.getX() - child.getX();
		double disty = parent.getY() - child.getY();
		return (dir[0] * disty) - (dir[1] * distx);
	}

	public double getReducedMass() {
		return (child.getMass() * parent.getMass()) / (child.getMass() + parent.getMass());
	}
	
	public double distance() {
		return child.distance(parent);
	}
	
	
	
}
