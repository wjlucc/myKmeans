package k_means;

/**
 * ����������� �ڵ����Ҳ���Ŵصľ�ֵ��num ��ʾ��غ��롣
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
	 * ��Ȼ������д�÷�����
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