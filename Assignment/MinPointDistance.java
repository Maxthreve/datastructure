package Assignment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MinPointDistance 
{
	public static void main(String args[]) throws Exception
	{
		// generate large array...
		Random rand = new Random();
		MyPoint points[] = new MyPoint[500000];
		for (int i = 0; i < 500000; i++)
		{
			double x = rand.nextDouble() * 1000;
			double y = rand.nextDouble() * 1000;
			points[i] = new MyPoint(x, y);
		}
		double x = rand.nextDouble() * 1000;
		double y = rand.nextDouble() * 1000;
		MyPoint find = new MyPoint(x, y);
		
		// start the clock...
		Date start = new Date();
		
		// launch the threads...
		List<Future<MyPoint>> results = new ArrayList<Future<MyPoint>>();
		ExecutorService pool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 5000; i++)
		{
			Future<MyPoint> ret = pool.submit(new DistanceWorker(i * 100, (i*100) + 99, find, points));
			results.add(ret);
		}
	
		// calculate the total primes found,
		// this will loop each time a thread completes...
		System.out.println("getting return values...");
		List<MyPoint> found = new ArrayList<MyPoint>();
		for (Future<MyPoint> f : results)
		{
			found.add(f.get());
		}

		// ok, now use one last thread to search for the results for the most bestest match...
		Future<MyPoint> ret = pool.submit(new DistanceWorker(0, found.size(), find, found.toArray(new MyPoint[0])));
		MyPoint bestMatch = ret.get();
		
		// shutdown pool...
		System.out.println("shutting down...");
		pool.shutdown();
		while (!pool.isTerminated()) 
		{
			Thread.yield();
		}
		
		// stop the clock...
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		
		System.out.println("Searching for: " + find);
		System.out.println("Best match: " + bestMatch);
		System.out.println("Time: " + (time/ 1000.0) + " seconds");
	}

}
