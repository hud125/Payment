package com.aurfy.haze.utils.rule;

public interface Rule {

	boolean isEmpty();
	
	boolean satisfy(Object object);

}
