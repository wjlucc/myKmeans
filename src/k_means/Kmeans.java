package k_means;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Kmeans {
	public static void main(String[] args) {
		// 1����һ�������㣬����һ������࣬��Ա�������� x��y���غ��롣
		Point[] data = { new Point(2, 10), new Point(2, 5), new Point(8, 4),
				new Point(5, 8), new Point(7, 5), new Point(6, 4),
				new Point(1, 2), new Point(4, 9), new Point(7, 3),
				new Point(1, 3), new Point(3, 9) };

		// 2����ʼ�������������ѡk���㣬����һ������������k��������飬���k�����ģ��ٶ���һ��boolean���͵� ��־��������Ƿ�仯��
		Point[] cluster = setCluster(data);
		boolean flag = true;// flag = true ��ʾ����Ҫ�������㡣

		// whileѭ��ִ�����²�����
		// 4������һ���������������´��е�ÿ�����󣬼���ö���k�����ĵľ��룬ѡ�������һ�������¡�
		// 5�����´ؾ�ֵ������boolean��־��	
		
		while (flag) {
			setClusterNum(data, cluster);
			flag = updataCluster(data, cluster);
		}

		//
		Arrays.sort(data);

		// ��ӡ���
		printCluster(data);
		
		System.out.println("���ĵľ�ֵ���£�");
		printCluster(cluster);

	}

	/**
	 * ��ӡ���صĽ����
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
	 * ���� ���������� ��������������´��ĵ�ֵ��������Ϊ�����仯��˵��������ϣ�����false��
	 * ����ÿ�������numȷ��������ڵ�λ�ã����¸�λ�õ�ֵ�� ������μ��㣿���ж���ľ�ֵ
	 * 
	 * @param data
	 * @param cluster
	 * @return
	 */
	private static boolean updataCluster(Point[] data, Point[] cluster) {

		boolean flag = true;
		for (int i = 0; i < data.length; i++) {
			Point averValue = calAverValue(data[i], cluster[data[i].num]);
			// �Զ������ıȽϣ����ﻹҪ����һ�¡�
			if (!isEqual(cluster[data[i].num], averValue)) {
				cluster[data[i].num] = copy(averValue);
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * �����Ƚ����������Ƿ���ȡ�
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
	 * �����������ƽ��ֵ
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
	 * ���� ����������,��û�и��ģ�Ӧ����ÿ�ν���ֵ�����Ӧ�Ĵغ�͸��´��ĵ�ֵ �����������������ÿ�����ݶ����numֵ������Ϊ��غš�
	 * ˫��forѭ��ʹ�ã�ÿ���������ڵĴ�Ϊ����������ġ�
	 * 
	 * @param data
	 * @param cluster
	 */
	private static void setClusterNum(Point[] data, Point[] cluster) {
		for (int i = 0; i < data.length; i++) {

			// �Ƚ�ÿ���Ĵغ�����Ϊ��һ����֮��ѭ�����㣬ѡ����С��ֵ��
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
	 * �������Ŀ�ľ��Ƿ�������Point ����ľ��롣
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
	 * �Դ�����������ѡ��k����Ϊ���ģ������ĵ����鷵�ػ�ȥ��
	 * 
	 * @param data
	 * @return
	 */
	private static Point[] setCluster(Point[] data) {
		System.out.print("������ص���Ŀ��");
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		
		// �������������������Ŵ���
		Point[] cluster = new Point[k];
		
		// ���ѡȡk������Ϊ��ʼ�Ĵ���
		for(int i = 0;i<k;i++){
			//����һ��С��0-k ��������������ѡȡ���ж���ѡ�Ķ����num�ǲ���0���ǵĻ������ӣ����ǵĻ���i-1
			Random r = new Random();
			int n = r.nextInt(k);
			
			if(data[n].num != 0){
				i--;
			}else{
				data[n].num = i;
				cluster[i] = copy(data[n]);
			}
		}


//ѡ����������������������Ϊ��ʼ�Ĵ��ģ������½��	
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
	 * �Զ�����и�ֵ������һ�������仯����һ��Ҳ�ᷢ���仯�������߶���������
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