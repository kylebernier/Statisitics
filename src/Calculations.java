import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class Calculations.
 */
public class Calculations {

	/**
	 * Gets the mean.
	 *
	 * @param list
	 *            the list
	 * @return the mean
	 */
	public static double getMean(float[] list) {
		double mean = 0;
		for (int i = 0; i < list.length; i++)
			mean += list[i];
		return mean / list.length;
	}

	/**
	 * Gets the sum.
	 *
	 * @param list
	 *            the list
	 * @return the sum
	 */
	public static double getSum(float[] list) {
		int sum = 0;
		for (int i = 0; i < list.length; i++)
			sum += list[i];
		return sum;
	}

	/**
	 * Gets the sum2.
	 *
	 * @param list12
	 *            the list12
	 * @return the sum2
	 */
	public static double getSum2(float[] list12) {
		int sum = 0;
		for (int i = 0; i < list12.length; i++)
			sum += list12[i] * list12[i];
		return sum;
	}

	/**
	 * Gets the ssd.
	 *
	 * @param list
	 *            the list
	 * @return the ssd
	 */
	public static double getSSD(float[] list) {
		double ssd = 0;
		double m = getMean(list);
		for (int i = 0; i < list.length; i++) {
			ssd += (m - list[i]) * (m - list[i]);
		}
		return Math.sqrt(ssd / (list.length - 1));
	}

	/**
	 * Gets the sd.
	 *
	 * @param list
	 *            the list
	 * @return the sd
	 */
	public static double getSD(float[] list) {
		double ssd = 0;
		double m = getMean(list);
		for (int i = 0; i < list.length; i++) {
			ssd += (m - list[i]) * (m - list[i]);
		}
		return Math.sqrt(ssd / (list.length));
	}

	/**
	 * Gets the min.
	 *
	 * @param list
	 *            the list
	 * @return the min
	 */
	public static double getMin(float[] list) {
		float small = Float.MAX_VALUE;
		for (int i = 0; i < list.length; i++)
			if (small > list[i])
				small = list[i];
		return small;
	}

	/**
	 * Gets the max.
	 *
	 * @param list
	 *            the list
	 * @return the max
	 */
	public static double getMax(float[] list) {
		float big = Float.MIN_VALUE;
		for (int i = 0; i < list.length; i++)
			if (big < list[i])
				big = list[i];
		return big;
	}

	/**
	 * Gets the med.
	 *
	 * @param list
	 *            the list
	 * @return the med
	 */
	public static double getMed(float[] list) {
		Arrays.sort(list);
		int middle = list.length / 2;
		if (list.length % 2 == 1) {
			return list[middle];
		} else {
			return (list[middle - 1] + list[middle]) / 2.0;
		}
	}

	/**
	 * Gets the q1.
	 *
	 * @param list
	 *            the list
	 * @return the q1
	 */
	public static double getQ1(float[] list) {
		Arrays.sort(list);
		float[] l2 = new float[list.length / 2];
		for (int i = 0; i < list.length / 2; i++)
			l2[i] = list[i];
		return getMed(l2);
	}

	/**
	 * Gets the q3.
	 *
	 * @param list
	 *            the list
	 * @return the q3
	 */
	public static double getQ3(float[] list) {
		Arrays.sort(list);
		float[] l2 = new float[list.length / 2];
		for (int i = 0; i < list.length / 2; i++)
			l2[i] = list[list.length - i - 1];
		return getMed(l2);
	}

	/**
	 * Gets the r.
	 *
	 * @param lists
	 *            the lists
	 * @return the r
	 */
	public static double getR(float[][] lists) {
		float[] list1 = lists[0];
		float[] list2 = lists[1];
		int n = list1.length;
		double sumX = 0.0;
		double sumY = 0.0;
		double sumX2 = 0.0;
		double sumY2 = 0.0;
		double sumXY = 0.0;
		for (int i = 0; i < n; i++) {
			double x = list1[i];
			double y = list2[i];
			sumX = sumX + x;
			sumY = sumY + y;
			sumXY = sumXY + (x * y);
			sumX2 = sumX2 + (x * x);
			sumY2 = sumY2 + (y * y);
		}
		return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY), 0.5);
	}

	/**
	 * Gets the r2.
	 *
	 * @param lists
	 *            the lists
	 * @return the r2
	 */
	public static double getR2(float[][] lists) {
		return Math.pow(getR(lists), 2);
	}

	/**
	 * Gets the b.
	 *
	 * @param lists
	 *            the lists
	 * @return the b
	 */
	public static double getB(float[][] lists) {
		float[] list1 = lists[0];
		float[] list2 = lists[1];
		return getR(lists) * getSSD(list2) / getSSD(list1);
	}

	/**
	 * Gets the a.
	 *
	 * @param lists
	 *            the lists
	 * @return the a
	 */
	public static double getA(float[][] lists) {
		float[] list1 = lists[0];
		float[] list2 = lists[1];
		return getMean(list2) - getB(lists) * getMean(list1);
	}

	/**
	 * Gets the resid.
	 *
	 * @param lists
	 *            the lists
	 * @return the resid
	 */
	public static float[][] getResid(float[][] lists) {
		float[] list1 = lists[0];
		float[] list2 = lists[1];

		int length = list1.length;

		double a = getA(lists);
		double b = getB(lists);

		float[] resid = new float[length];
		float[] predicted = new float[length];
		for (int i = 0; i < length; i++) {
			predicted[i] = (float) ((list1[i] * b + a));
			resid[i] = (float) (list2[i] - (list1[i] * b + a));
		}

		float[][] values = { resid, predicted };
		return values;
	}
}