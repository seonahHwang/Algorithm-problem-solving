package aa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class R17140 {
	static int R, C, K, maxr, maxc;
	static int a[][], nmap[][];
	static int num[];
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	static void print() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void copy() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				a[i][j] = nmap[i][j];
			}
		}
	}

	static void sortarr(int sw) {
		nmap = new int[101][101];
		if (sw == 0) {
			for (int i = 0; i < maxr; i++) {
				num = new int[101];
				for (int j = 0; j < maxc; j++) {
					num[a[i][j]]++;
				}
				PriorityQueue<Item> pq = sort();
				maxc = Math.max(pq.size() * 2, maxc);
				for (int j = 0; j < 101; j += 2) {
					if (pq.isEmpty())
						break;
					Item item = pq.poll();
					nmap[i][j] = item.n;
					nmap[i][j + 1] = item.cnt;
				}
			}
		} else {
			for (int i = 0; i < maxc; i++) {
				num = new int[101];
				for (int j = 0; j < maxr; j++) {
					num[a[j][i]]++;
				}
				PriorityQueue<Item> pq = sort();
				maxr = Math.max(pq.size() * 2, maxr);
				for (int j = 0; j < 101; j += 2) {
					if (pq.isEmpty())
						break;
					Item item = pq.poll();
					nmap[j][i] = item.n;
					nmap[j + 1][i] = item.cnt;
				}

			}
		}
		copy();
	}

	static PriorityQueue<Item> sort() {
		PriorityQueue<Item> q = new PriorityQueue<Item>(new ComparatorSort());
		for (int i = 1; i <= 100; i++) {
			if (num[i] > 0) {
				q.add(new Item(i, num[i]));
			}
		}
		return q;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		a = new int[101][101];
		num = new int[101];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		maxr = 3;
		maxc = 3;
		int time = 0;
		while (true) {
			if (time > 100) {
				time = -1;
				break;
			}
			if (a[R - 1][C - 1] == K)
				break;
			if (maxr >= maxc)
				sortarr(0); // 행정렬
			else
				sortarr(1); // 열정렬
			print();
			time++;
		}
		System.out.println(time);
	}

	static class ComparatorSort implements Comparator<Item> {
		public int compare(Item i1, Item i2) {
			if (i1.cnt < i2.cnt) {
				return -1;
			} else if (i1.cnt == i2.cnt) {
				if (i1.n < i2.n) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
	}

	public static class Item {
		int n, cnt;

		public Item(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}
}
