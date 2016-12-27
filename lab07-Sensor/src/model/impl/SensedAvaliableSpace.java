package model.impl;

import java.io.File;

import model.Sensed;

public class SensedAvaliableSpace implements Sensed {

	@Override
	public String getValue() {
		File file = new File("D:\\");
		
		return Long.toString(file.getFreeSpace());
	}

}
