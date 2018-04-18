package orbit;

public class PolarMath {

	/**
	 * Constant of universal gravitation.
	 */
	public static final double G = 1;

	public static double[] toQuaternion(double pitch, double roll, double yaw) {
		double[] result = new double[4];

		// Abbreviations for the various angular functions
		double cy = Math.cos(yaw * 0.5);
		double sy = Math.sin(yaw * 0.5);
		double cr = Math.cos(roll * 0.5);
		double sr = Math.sin(roll * 0.5);
		double cp = Math.cos(pitch * 0.5);
		double sp = Math.sin(pitch * 0.5);

		result[0] = cy * cr * cp + sy * sr * sp;
		result[1] = cy * sr * cp - sy * cr * sp;
		result[2] = cy * cr * sp + sy * sr * cp;
		result[3] = sy * cr * cp - cy * sr * sp;

		return result;
	}

	public static double[] toEulerAngle(double x, double y, double z, double w) {
		double[] result = new double[3];

		// roll (x-axis rotation)
		double sinr = +2.0 * (w * x + y * z);
		double cosr = +1.0 - 2.0 * (x * x + y * y);
		result[2] = Math.atan2(sinr, cosr);

		// pitch (y-axis rotation)
		double sinp = +2.0 * (w * y - z * x);
		if (Math.abs(sinp) >= 1)
			result[0] = Math.min(Math.PI / 2, sinp); // use 90 degrees if out of range
		else
			result[0] = Math.asin(sinp);

		// yaw (z-axis rotation)
		double siny = +2.0 * (w * z + x * y);
		double cosy = +1.0 - 2.0 * (y * y + z * z);
		result[1] = Math.atan2(siny, cosy);

		return result;
	}

}
