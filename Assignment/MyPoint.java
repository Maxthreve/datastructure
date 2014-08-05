package Assignment;

public class MyPoint 
{
	private double m_x;
	private double m_y;
	
	public MyPoint(double x, double y)
	{
		m_x = x;
		m_y = y;
	}
	
	public double getDistance(MyPoint pt2)
	{
		double factor1 = Math.pow(pt2.getX() - getX(), 2);
		double factor2 = Math.pow(pt2.getY() - getY(), 2);
		return Math.sqrt(factor1 + factor2);
	}
	
	public double getX()
	{ return m_x; }

	public double getY()
	{ return m_y; }
	
	@Override
	public String toString()
	{
		return String.format("(%f, %f)", m_x, m_y);
	}

}
