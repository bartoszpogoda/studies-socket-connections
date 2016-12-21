package model.impl;

import java.util.Random;

import model.Sensed;

public class SensedRandom implements Sensed {

	Random random;
	
	public SensedRandom() {
		random = new Random();
	}
	
	@Override
	public String getValue() {
		return Integer.toString(random.nextInt(100));
	}

}
