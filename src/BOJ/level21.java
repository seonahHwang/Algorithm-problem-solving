package BOJ;

public class level21 {
	static int fibo(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		return fibo(n-1) + fibo(n-2);
	}
	public static void main(String args[]) {
		int ans = 0;
		int num = 5;
		ans = fibo(num) % 1234567;
		System.out.println(ans);
	}
}
