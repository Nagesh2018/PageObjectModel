package utils;

import java.util.Random;

import wdMethods.ProjectMethods;

public class CommonFunctions extends ProjectMethods{

	public long randomNum() {
		Random gen = new Random();
		long randomLong = gen.nextLong();
		if(randomLong<0) {
			randomLong = randomLong*-1;
		}
		else {
		}
		return randomLong;
	}
}
