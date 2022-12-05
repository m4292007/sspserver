package Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatch_Test {

	private final static int THREADS = 2;

	private static CountDownLatch latch = new CountDownLatch(THREADS);

	public static class RandomSleepRunnable implements Runnable {

		private int id = 0;
		private static Random random = new Random(System.currentTimeMillis());

		public RandomSleepRunnable(int id) {
			this.id = id;
		}

		@Override
		public void run() {

			System.out.println("Thread(" + id + ") : Start.");
			// 1000ms 에서 2000ms 사이의 딜레이 값을 랜덤하게 생성.
			int delay = random.nextInt(1001) + 1000;

			try {
				System.out.println("Thread(" + id + ") : Sleep " + delay + "ms");
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread(" + id + ") : End.");
			// lacth 의 카운터에서 -1.
			latch.countDown();
		}

	}

	public static void main(String... args) {
		for (int i = 0; i < THREADS; ++i) {
			new Thread(new RandomSleepRunnable(i)).start();

		}

		try {
			System.out.println("1111");

			latch.await();
			System.out.println("xxx");
			
			// lacth.await(2000, TimeUnit.MILLISECONDS);

		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		System.out.println("All threads terminated.");

	}
}