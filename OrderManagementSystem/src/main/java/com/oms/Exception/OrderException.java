package com.oms.Exception;

public class OrderException extends Exception {

	public OrderException() {
		super();
		System.out.println("I am at orderException default arg. constructor");
	}
	
	public OrderException(String msg) {
		super();
		System.out.println("I am at orderException single arg. constructor");
	}
}
