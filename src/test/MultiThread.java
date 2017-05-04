//package test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//public class MultiThread {
//	public void runThread(int intval){
//		System.out.println("hello : "+intval);
//	}
//	public void createThreads(int intThreadCounts) throws InterruptedException{
////		Thread[] threads = new Thread[intThreadCounts];
////		for(Thread thread : threads){
////			thread = new Thread(new Runnable() {
////				public void run() {
////					// some code to run in parallel
////					thread.start();
////				}
////			});
////			}
////		}
//		ExecutorService threadPool = Executors.newFixedThreadPool(10);
//		 // submit jobs to be executing by the pool
//		 for (int i = 0; i < intThreadCounts; i++) {
//		    threadPool.submit(new Runnable() {
//		         public void run() {
//		             // some code to run in parallel
//		         }
//		         runThread(i);
//		     });
//		 }
//		 // once you've submitted your last job to the service it should be shut down
//		 threadPool.shutdown();
//		 // wait for the threads to finish if necessary
//		 threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
//	}
//public static void main(String args[]) throws InterruptedException {
//	new MultiThread().createThreads(3);
//}   
//}