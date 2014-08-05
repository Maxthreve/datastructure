package Assignment;
import java.util.concurrent.Callable;


public class DistanceWorker implements Callable<MyPoint> 
{
	private int m_start;
	private int m_end;
	private MyPoint m_find;
	private MyPoint[] m_points;
	
	public DistanceWorker(int start, int end, MyPoint find, MyPoint[] points)
	{
		if (start == 0 || start == 1)
			start = 3;
		
		m_start = start;
		m_end = end;
		m_points = points;
		m_find = find; 
	}
	
	@Override
	public MyPoint call() throws Exception 
	{
		MyPoint best = m_points[0];
		for (int i = m_start; i < m_end; i++)
		{
			if (m_points[i].getDistance(m_find) < best.getDistance(m_find)) 
			{
				best = m_points[i];
			}
		}
		return best;
	}

}
