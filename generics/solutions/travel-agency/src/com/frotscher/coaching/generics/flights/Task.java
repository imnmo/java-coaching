package com.frotscher.coaching.generics.flights;

public interface Task<PARAMS, RESULT> {

	PARAMS getParameters();
	
	RESULT execute();
}