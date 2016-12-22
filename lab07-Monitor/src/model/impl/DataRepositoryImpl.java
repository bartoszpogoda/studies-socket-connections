package model.impl;

import java.util.Arrays;

import model.DataRepository;

public class DataRepositoryImpl implements DataRepository {

	private String[] data;
	private int currentSize;
	private int maxValues;
	
	//remembers up to maxValues values
	public DataRepositoryImpl(int maxValues) {
		data = new String[maxValues];
		currentSize = 0;
		this.maxValues = maxValues;
	}
	
	
	@Override
	public void addData(String value) {
		if(currentSize < maxValues){
			data[currentSize++] = value;
		}
		else{
			for(int i=0 ; i < currentSize - 1 ; i++){
				data[i] = data[i+1];
			}
			data[currentSize-1] = value;
		}
	}

	@Override
	public String[] getValues() {
		return Arrays.copyOf(data, currentSize);
	}

}
