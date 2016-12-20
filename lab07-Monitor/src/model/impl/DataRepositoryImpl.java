package model.impl;

import java.util.Arrays;

import model.DataRepository;

public class DataRepositoryImpl implements DataRepository {

	double[] data;
	int currentSize;
	
	//remembers up to maxValues values
	public DataRepositoryImpl(int maxValues) {
		data = new double[maxValues];
		currentSize = 0;
	}
	
	
	@Override
	public void addValue(double value) {
		data[currentSize++] = value;
	}

	@Override
	public double[] getValues() {
		return Arrays.copyOf(data, currentSize);
	}

}
