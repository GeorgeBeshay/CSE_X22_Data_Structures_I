package Practicing.Hashing;

public class Main {

	public static void main(String[] args) {
		int[] arr = new int[13];
		int[] arr2 = new int[13];
		int[] arr3 = new int[13];
		int[] toBeHashed = {19,41,22,44,59,31,73};
		int[] toBeHashed2 = {19,41,22,44,59,32,31,73};
		int[] toBeHashed3 = {23, 13, 632, 24, 74, 41, 73, 101};
		
		
		for(int i = 0 ; i < toBeHashed.length ; i++) {
			doubleHashing(toBeHashed[i], arr);
		}
		for(int j = 0 ; j <toBeHashed2.length ; j++) {
			linearProbing(toBeHashed2[j], arr2);
		}
		for(int u = 0 ; u <toBeHashed3.length ; u++) {
			linearProbing(toBeHashed3[u], arr3);
		}
		
		printArray(arr);
		printArray(arr2);
		printArray(arr3);
	
	}
	
	public static void doubleHashing(int K, int[] Container) {
		// h0(K) = K % N;
		// hPro(K) = 7 - (K%7);
		// hi(K) = { h0(K) + i * hPro(K) } % N
		
		int N = Container.length;
		int x = K % N;
		if (Container[x] == 0) {
			Container[x] = K;
		} else {
			while(Container[x] != 0) {
				x += ( 7 - (K%7) );
				x %= Container.length;
			}
			Container[x] = K;
		}
	}
	
	public static void linearProbing(int K, int[] Container) {
		// h0(K) = K % N
		// hi(K) = { h0(K) + i } % N
		int N = Container.length;
		int x = K % N;
		
		if (Container[x] == 0) {
			Container[x] = K;
		} else {
			while(Container[x] != 0) {
				x += 1;
				x %= Container.length;
			}
			Container[x] = K;
		}
	}
	
	public static void quadraticProbing(int K, int[] Container) {
		// h0(K) = K % N
		// hi(K) = { h0(K) + i^2 } % N
		int N = Container.length;
		int x = K % N;
		
		if (Container[x] == 0) {
			Container[x] = K;
		} else {
			int i = 1;
			while(Container[x] != 0) {
				x += i*i;
				i++;
				x %= Container.length;
			}
			Container[x] = K;
		}
	}
	
	public static void printArray(int[] arr) {
		System.out.print("[");
		for(int i = 0 ; i < arr.length-1 ; i++) {
			if(arr[i] > 9) {
			System.out.print(arr[i] + ", ");
			} else {
				System.out.print("0" + arr[i] + ", ");
			}
		}
		if(arr[arr.length-1] > 9) {
		System.out.print(arr[arr.length-1] + "]\n");
		} else {
			System.out.print("0" + arr[arr.length-1] + "]\n");
		}
		
		System.out.print("[");
		for(int i = 0 ; i < arr.length-1 ; i++) {
			if(i > 9) {
			System.out.print(i + ", ");
			} else {
				System.out.print("0" + i + ", ");
			}
		}
		System.out.print(arr.length-1 + "]\n");
		
		System.out.println("-------------------------------------------------------------------------");
	}
}
