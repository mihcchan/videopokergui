import java.util.Calendar;

public class Random {
	private long p = 2147483648L;
	private long m = 843314861;
	private long a = 453816693;

	private Calendar hora = Calendar.getInstance();
	private long xi = hora.getTimeInMillis();

	public Random() {

	}

	public Random(int k) {
		xi = k;
	}

	public void setSemente(int semente) {
		xi = semente;
	}

	public double getRand() {

		xi = (int) ((a + m * xi) % p);
		double d = xi;
		return d / p;
	}

	public int getIntRand(int max) {
		double valorRand = getRand();

		return (int) (Math.abs((max * valorRand)));
	}


	@Override
	public String toString() {
		return xi + "";
	}

}
