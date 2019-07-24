package evaluation.investec.gcd.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import evaluation.investec.gcd.CommonFactor;

public class CommonFactorTest {
	
	@Test
	public void testGCD() {
		CommonFactor cf = new CommonFactor();
		List<Integer> numlist = new ArrayList<Integer>();
		numlist.add(25);
		numlist.add(30);
		numlist.add(35);
		
		int gcd = cf.highestCommonFactor(numlist);
		assertTrue(gcd == 5);
		
		numlist = new ArrayList<>();
		numlist.add(24);
		numlist.add(54);
		
		gcd = cf.highestCommonFactor(numlist);
		assertTrue(gcd == 6);
		
		numlist = new ArrayList<>();
		numlist.add(2);
		numlist.add(3);
		numlist.add(5);
		
		gcd = cf.highestCommonFactor(numlist);
		assertTrue(gcd == 1);
	}

}
