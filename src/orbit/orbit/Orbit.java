package orbit.orbit;

import orbit.PolarMath;
import orbit.model.Body;

/**
 * Represents the orbit of a two-body system.
 * 
 * @author Hanavan Kuhn
 *
 */
public abstract class Orbit {

	/**
	 * The parent body.
	 */
	private final Body parent;
	/**
	 * The child body.
	 */
	private final Body child;

	/**
	 * Creates a new {@code Orbit} object with the specified parent and child
	 * bodies.
	 * 
	 * @param parent
	 *            the parent body
	 * @param child
	 *            the child body
	 */
	public Orbit(Body parent, Body child) {
		this.parent = parent;
		this.child = child;
	}

	/**
	 * Gets the parent body of this orbit. This body lies at one of the foci of the
	 * ellipse.
	 * 
	 * @return the parent body
	 */
	public final Body getParent() {
		return parent;
	}

	/**
	 * Gets the child body of this orbit. This body lies on the orbit in reference
	 * to the parent.
	 * 
	 * @return the child body
	 */
	public final Body getChild() {
		return child;
	}

	/**
	 * Gets the eccentricity of the orbit. An eccentricity of 0 is a circular orbit.
	 * An eccentricity > 0 and < 1 is an elliptical orbit. An eccentricity = 1 is a
	 * parabolic orbit. An eccentricity > 1 is a hyperbolic orbit.
	 * 
	 * @return the eccentricity of the orbit
	 */
	public abstract double getEccentricity();

	/**
	 * Gets the length of the semi-major axis of the orbit. This is half the
	 * distance between the periapsis and apoapsis.
	 * 
	 * @return the length of the semi-major axis
	 */
	public abstract double getSemiMajorAxis();

	/**
	 * Gets the inclination of the orbit. This is the angle, in radians, of the
	 * orbit above the reference plane, based at the ascending node.
	 * 
	 * @return the inclination angle
	 */
	public abstract double getInclinationAngle();

	/**
	 * Gets the angle of the ascending node. This is the angle, in radians, of the
	 * ascending node in reference to the positive x direction.
	 * 
	 * @return the angle of the ascending node
	 */
	public abstract double getAscendingNodeAngle();

	/**
	 * Gets the angle of periapsis. This is the angle, in radians, of the position
	 * in the orbit where the child body is the farthest away, in reference to the
	 * ascending node.
	 * 
	 * @return the angle of periapsis
	 */
	public abstract double getPeriapsisAngle();

	/**
	 * Gets the true anomaly. This is the angle, in radians, of the starting point
	 * of the object, in reference to the argument of periapsis.
	 * 
	 * @return the true anomaly.
	 */
	public abstract double getTrueAnomaly();

	/**
	 * Gets the position of the child body in reference to the parent body for a
	 * given time.
	 * 
	 * @param time
	 *            the time in orbit
	 * @return the position of the body in 3D space (x, y, z)
	 */
	public abstract double[] getPositionRelativeToParent(double time);

	/**
	 * Gets the gravitational parameter between the parent and the child body.
	 * 
	 * @return the gravitational parameter
	 */
	public final double getGravitationalParameter() {
		return PolarMath.G * (child.getMass() + parent.getMass());
	}

	/**
	 * Gets the reduced mass, or weighted average, of the two bodies in relation to
	 * each other.
	 * 
	 * @return the reduced mass
	 */
	public final double getReducedMass() {
		return (child.getMass() * parent.getMass()) / (child.getMass() + parent.getMass());
	}

}
