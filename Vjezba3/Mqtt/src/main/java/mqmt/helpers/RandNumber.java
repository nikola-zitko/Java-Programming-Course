package mqmt.helpers;

public class RandNumber {
	public static int GenerateNumberInRange(double bottom, double upper){
		int random = (int)Math.round(Math.random() * upper + bottom);
		return random;
	}
}
