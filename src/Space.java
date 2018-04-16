import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Space implements Iterable<Body> {

	private List<Body> bodies = new ArrayList<Body>();

	public void addBody(Body body) {
		bodies.add(body);
	}

	public Body getBody(String name) {
		for (Body b : bodies) {
			if (b.getName().equals(name)) {
				return b;
			}
		}
		return null;
	}

	public void tick(double time) {
		List<Body> bodies = new ArrayList<Body>();
		for (Body body : this.bodies) {
			Body newBody = new Body(body);
			for (Body body2 : this.bodies) {
				if (!body.equals(body2))
					newBody.applyGravity(body2, time);
			}
			newBody.tick(time);
			bodies.add(newBody);
		}
		this.bodies = bodies;
	}

	public Iterator<Body> iterator() {
		return bodies.iterator();
	}

}
