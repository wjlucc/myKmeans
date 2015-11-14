package k_means;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Kmeans {
	public static void main(String[] args) {
		// 1、用一个数组存点，定义一个点的类，成员变量包括 x，y，簇号码。
		Point[] data = { new Point(2, 10), new Point(2, 5), new Point(8, 4),
				new Point(5, 8), new Point(7, 5), new Point(6, 4),
				new Point(1, 2), new Point(4, 9), new Point(7, 3),
				new Point(1, 3), new Point(3, 9) };

		// 2、初始化，数组中随机选k个点，定义一个方法。定义k个点的数组，存放k个中心，再定义一个boolean类型的 标志检测数据是否变化。
		Point[] cluster = setCluster(data);
		boolean flag = true;// flag = true 表示还需要迭代计算。

		// while循环执行以下操作：
		// 4、定义一个方法，用来更新簇中的每个对象，计算该对象到k个中心的距离，选出最近的一个做更新。
		// 5、更新簇均值。更新boolean标志。	
		
		while (flag) {
			setClusterNum(data, cluster);
			flag = updataCluster(data, cluster);
		}

		//
		Arrays.sort(data);

		// 打印结果
		printCluster(data);
		
		System.out.println("簇心的均值如下：");
		printCluster(cluster);

	}

	/**
	 * 打印出簇的结果。
	 * 
	 * @param data
	 */
	private static void printCluster(Point[] data) {

		for (int i = 0; i < data.length; i++) {
//			System.out.println(data[i]);
			System.out.println(data[i].num+"\t"+data[i].x +"\t"+data[i].y );
		}

	}

	/**
	 * 错误 ！！！！！ 这个方法用来更新簇心的值，若簇心为发生变化，说明迭代完毕，返回false。
	 * 根据每个对象的num确定其簇所在的位置，更新该位置的值。 簇心如何计算？簇中对象的均值
	 * 
	 * @param data
	 * @param cluster
	 * @return
	 */
	private static boolean updataCluster(Point[] data, Point[] cluster) {

		boolean flag = true;
		for (int i = 0; i < data.length; i++) {
			Point averValue = calAverValue(data[i], cluster[data[i].num]);
			// 自定义对象的比较，这里还要再做一下。
			if (!isEqual(cluster[data[i].num], averValue)) {
				cluster[data[i].num] = copy(averValue);
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * 用来比较两个对象是否相等。
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private static boolean isEqual(Point p1, Point p2) {

		boolean flag = true;

		if (p1.x != p2.x) {
			flag = false;
		} else if (p1.y != p2.y) {
			flag = false;
		} else if (p1.num != p2.num) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * 计算两个点的平均值
	 * 
	 * @param point
	 * @param point2
	 * @return
	 */
	private static Point calAverValue(Point point, Point point2) {

		Point result = new Point();
		result.x = (point.x + point2.x) / 2;
		result.y = (point.y + point2.y) / 2;
		result.num = point.num;
		return result;
	}

	/**
	 * 错误 ！！！！！,还没有更改，应该在每次讲该值加入对应的簇后就更新簇心的值 这个方法用来。跟新每个数据对象的num值，设置为其簇号。
	 * 双重for循环使得，每个对象所在的簇为距离其最近的。
	 * 
	 * @param data
	 * @param cluster
	 */
	private static void setClusterNum(Point[] data, Point[] cluster) {
		for (int i = 0; i < data.length; i++) {

			// 先将每个的簇号设置为第一个，之后循环计算，选出最小的值，
			double minDisc = calculDisc(data[i], cluster[0]);
			data[i].num = 0;

			for (int j = 1; j < cluster.length; j++) {
				double tempDisc = calculDisc(data[i], cluster[j]);
				if (tempDisc < minDisc) {
					minDisc = tempDisc;
					data[i].num = j;
				}
			}
		}
	}

	/**
	 * 这个方法目的就是返回两个Point 对象的距离。
	 * 
	 * @param point
	 * @param point2
	 * @return
	 */
	private static double calculDisc(Point point, Point point2) {

		double disc = 0.0;
		disc = Math.sqrt(Math.pow((point.x - point2.x), 2)
				+ Math.pow((point.y - point2.y), 2));
		return disc;
	}

	
	/**
	 * 对传入的数据随机选择k个作为簇心，将簇心的数组返回回去。
	 * 
	 * @param data
	 * @return
	 */
	private static Point[] setCluster(Point[] data) {
		System.out.print("请输入簇的数目：");
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		
		// 建立的这个数组用来存放簇心
		Point[] cluster = new Point[k];
		
		// 随机选取k个点作为初始的簇心
		for(int i = 0;i<k;i++){
			//产生一个小于0-k 的随机数，随机地选取，判断所选的对象的num是不是0，是的话就增加，不是的话就i-1
			Random r = new Random();
			int n = r.nextInt(k);
			
			if(data[n].num != 0){
				i--;
			}else{
				data[n].num = i;
				cluster[i] = copy(data[n]);
			}
		}


//选用以下这三个典型数据作为初始的簇心，测试下结果	
//		Point[] cluster = new Point[3];
//		data[6].num = 0;
//		cluster[0] = copy(data[6]);
//		data[7].num = 1;
//		cluster[1] = copy(data[7]);
//		data[8].num = 2;
//		cluster[2] = copy(data[8]);		
		
		return cluster;

	}

	/**
	 * 对对象进行赋值，否则一个发生变化，另一个也会发生变化，将两者独立起来。
	 * 
	 * @param point
	 */
	private static Point copy(Point point) {

		Point p = new Point();
		p.x = point.x;
		p.y = point.y;
		p.num = point.num;
		return p;
	}

}
