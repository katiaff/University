package lab3;

public class Bubble2 {
	
	private final static int MIN_SIZE = 10000;
	private final static int MAX_SIZE = 1280000;

	public static void main(String[] args) {
	
		timeSorted();
		timeInverse();
		timeRandom();
	}

	private static void timeRandom() {
		int[] vector = new int[MIN_SIZE];
		
		while (vector.length <= MAX_SIZE){
			Vector.random(vector, 100);
			
			long t1 = System.currentTimeMillis();
			Bubble1.bubble(vector);
			long t2 = System.currentTimeMillis();
			
			long time = t2-t1;
			
			System.out.println("RANDOM -- size: " + vector.length + " -- time: " + time + " ms");
			
			vector = new int[vector.length * 2];
		}
		
	}

	private static void timeInverse() {
		int[] vector = new int[MIN_SIZE];
		
		while (vector.length <= MAX_SIZE){
			Vector.inverselySorted(vector);
			
			long t1 = System.currentTimeMillis();
			Bubble1.bubble(vector);
			long t2 = System.currentTimeMillis();
			
			long time = t2-t1;
			
			System.out.println("INVERSE -- size: " + vector.length + " -- time: " + time + " ms");
			
			vector = new int[vector.length * 2];
		}
		
	}

	private static void timeSorted() {
		int[] vector = new int[MIN_SIZE];
		
		while (vector.length <= MAX_SIZE){
			Vector.sorted(vector);
			
			long t1 = System.currentTimeMillis();
			Bubble1.bubble(vector);
			long t2 = System.currentTimeMillis();
			
			long time = t2-t1;
			
			System.out.println("SORTED -- size: " + vector.length + " -- time: " + time + " ms");
			
			vector = new int[vector.length * 2];
		}
		
	}

}
