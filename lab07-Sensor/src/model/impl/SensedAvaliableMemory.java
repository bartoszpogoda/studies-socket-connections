package model.impl;

import model.Sensed;

public class SensedAvaliableMemory implements Sensed{

	@Override
	public String getValue() {
		return Long.toString(Runtime.getRuntime().availableProcessors());
	}

}
