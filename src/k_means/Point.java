package k_means;

/**
 * 该类用来存放 节点对象，也会存放簇的均值。num 表示其簇号码。
 * 
 * @author WJLUCK
 * 
 */
public class Point implements Comparable<Point> {
	public double x;
	public double y;
	public int num;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		num = 0;
	}

	public Point(double x, double y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}

	public Point() {
		x = 0;
		y = 0;
		num = 0;
	}

	/**
	 * 自然排序重写该方法。
	 */
	@Override
	public int compareTo(Point o) {
		return num - o.num;
	}

	@Override
	public String toString() {

		return num + "-----"+"(" + x + "," + y + ")";
	}
}
