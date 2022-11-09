public class MathHandler
{
	private static String firstNum = "";
	private static String finalNum = "";
	private static String result = "";

	private static void sum()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		result = String.valueOf(a + b);
	}
	private static void subtraction()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		result = String.valueOf(a - b);
	}
	private static void multiplication()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		result = String.valueOf(a*b);
	}
	private static void division()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		if(b == 0.0) result = "No solution";
		else result = String.valueOf(a/b);
	}
	private static void power()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		result = String.valueOf(Math.pow(a, b));
	}
	private static void root()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		if(a < 0.0 || b == 0.0) result = "No solution";
		else result = String.valueOf(Math.pow(a, 1.0/b));
	}
	private static void logarithm()
	{
		double a = Double.parseDouble(firstNum);
		double b = Double.parseDouble(finalNum);
		if(a <= 0.0 || b <= 0.0 || b == 1.0) result = "No solution";
		else result = String.valueOf(Math.log10(a)/Math.log10(b));
	}
	private static void radSine()
	{
		double a = Double.parseDouble(finalNum);
		result = String.valueOf(Math.sin(a));
	}
	private static void degSine()
	{
		double a = Double.parseDouble(finalNum);
		result = String.valueOf(Math.sin(Math.PI*a/180.0));
	}
	private static void radCosine()
	{
		double a = Double.parseDouble(finalNum);
		result = String.valueOf(Math.cos(a));
	}
	private static void degCosine()
	{
		double a = Double.parseDouble(finalNum);
		result = String.valueOf(Math.cos(Math.PI*a/180.0));
	}
	private static void radTangent()
	{
		double a = Double.parseDouble(finalNum);
		if(Math.cos(a) == 0.0) result = "No solution";
		else result = String.valueOf(Math.tan(a));
	}
	private static void degTangent()
	{
		double a = Double.parseDouble(finalNum);
		if(Math.cos(Math.PI*a/180.0) == 0.0) result = "No solution";
		else result = String.valueOf(Math.tan(Math.PI*a/180.0));
	}
	private static void factorial()
	{
		double af = Double.parseDouble(finalNum);
		if(af < 0.0 || af != Math.round(af)) result = "No solution";
		else
		{
			long a = Math.round(af);
			long tmp = 0;

			if(a == 0) a = 1;
			else if(a > 2) for(tmp = a - 1; tmp > 1; tmp--) a *= tmp;

			result = String.valueOf(a);
		}
	}
	private static void binomial()
	{
		double af = Double.parseDouble(firstNum);
		double bf = Double.parseDouble(finalNum);
		if(af < 0.0 || af != Math.round(af)) result = "No solution";
		else if(bf < 0.0 || bf != Math.round(bf)) result = "No solution";
		else if(Math.round(af) < Math.round(bf)) result = "No solution";
		else
		{
			long a = Math.round(af);
			long b = Math.round(bf);
			long c = a - b;
			long tmp = 0;

			if(a == 0) a = 1;
			else if(a > 2) for(tmp = a - 1; tmp > 1; tmp--) a *= tmp;

			if(b == 0) b = 1;
			else if(b > 2) for(tmp = b - 1; tmp > 1; tmp--) b *= tmp;

			if(c == 0) c = 1;
			else if(c > 2) for(tmp = c - 1; tmp > 1; tmp--) c *= tmp;

			result = String.valueOf(a/(b*c));
		}
	}

	public static void setFirstNum(String inputStr)
	{
		firstNum = inputStr;
	}
	public static void setFinalNum(String inputStr)
	{
		finalNum = inputStr;
	}
	public static String calculate(MathOperations mathop, boolean angunit)
	{
		switch(mathop)
		{
			case SUM:
				sum();
				break;

			case SUBTRACTION:
				subtraction();
				break;

			case MULTIPLICATION:
				multiplication();
				break;

			case DIVISION:
				division();
				break;

			case POWER:
				power();
				break;

			case ROOT:
				root();
				break;

			case LOGARITHM:
				logarithm();
				break;

			case SINE:
				if(angunit) degSine();
				else radSine();
				break;

			case COSINE:
				if(angunit) degCosine();
				else radCosine();
				break;

			case TANGENT:
				if(angunit) degTangent();
				else radTangent();
				break;

			case FACTORIAL:
				factorial();
				break;

			case BINOMIAL:
				binomial();
				break;
		}

		return result;
	}
}
