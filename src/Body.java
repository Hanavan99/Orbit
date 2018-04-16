import java.awt.Color;

public class Body {

	public static final double G = 1;
	public static final double FIELD_RESISTANCE = 0;

	private double mass;
	private double x;
	private double y;
	private double angle;
	private double velocity;
	private String name;
	private Color color;
	private boolean _static;

	public Body() {

	}

	public Body(double mass, double x, double y, double angle, double velocity, String name, Color color) {
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.velocity = velocity;
		this.name = name;
		this.color = color;
	}

	public Body(double mass, double x, double y, double angle, double velocity, String name, Color color,
			boolean _static) {
		this(mass, x, y, angle, velocity, name, color);
		this._static = _static;
	}

	public Body(Body old) {
		mass = old.mass;
		x = old.x;
		y = old.y;
		angle = old.angle;
		velocity = old.velocity;
		name = old.name;
		color = old.color;
		_static = old._static;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isStatic() {
		return _static;
	}

	public void tick(double time) {
		if (!_static) {
			x += velocity * time * Math.cos(angle);
			y += velocity * time * Math.sin(angle);
			velocity *= (1 - FIELD_RESISTANCE);
		}
	}

	public double distance(Body other) {
		return Math.sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y));
	}

	public void applyGravity(Body other, double time) {
		if (!_static) {
			double r = distance(other);
			if (r < 10)
				return;
			double f = (G * /* mass **/ other.mass * time) / (r * r);
			double ang = Math.atan2(other.y - y, other.x - x);
			double dx = velocity * Math.cos(angle) + f * Math.cos(ang);
			double dy = velocity * Math.sin(angle) + f * Math.sin(ang);
			velocity = Math.sqrt(dx * dx + dy * dy);
			angle = Math.atan2(dy, dx);
		}
	}

	public Orbit computeOrbit(Body parent) {
		return new Orbit(parent, this);
	}

	public double getDirectionSquared() {
		double[] dir = getDirectionVector();
		return dir[0] * dir[0] + dir[1] * dir[1];
	}

	public double[] getDirectionVector() {
		return new double[] { velocity * Math.cos(angle), velocity * Math.sin(angle) };
	}


	public double angleFromOrigin() {
		return Math.atan2(y, x);
	}

	public boolean equals(Body other) {
		return mass == other.mass && x == other.x && y == other.y && angle == other.angle && velocity == other.velocity;
	}

}
