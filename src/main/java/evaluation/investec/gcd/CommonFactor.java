package evaluation.investec.gcd;

import java.util.ArrayList;
import java.util.List;

public class CommonFactor {
	
	/*
	 * No number is divisible by a value greater than number/2, therefore we only need to check
	 * division by a list of prime number between 2 and half the number's value.
	 */
	public int highestCommonFactor(List<Integer> numbers) {
		final GCDResult result = new GCDResult(numbers.get(0));
		
		List<Integer> remainingNumbers = numbers.subList(1, numbers.size());
		remainingNumbers.stream().forEach(num -> result.setGcdResult(findGCD(result.getGcdResult(), num.intValue())));
		
		return result.getGcdResult();
	}
	
	private int findGCD(int number1, int number2) {
        //base case
        if(number2 == 0){
            return number1;
        }
        return findGCD(number2, number1%number2);
    }
	
	private class GCDResult {
		private int gcdResult;
		
		public GCDResult(int number) {
			this.gcdResult = number;
		}
		
		public int getGcdResult() {
			return this.gcdResult;
		}
		public void setGcdResult(int number) {
			this.gcdResult = number;
		}
	}
}
