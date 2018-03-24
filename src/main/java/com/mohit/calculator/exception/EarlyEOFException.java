package com.mohit.calculator.exception;

public class EarlyEOFException extends Exception
{
	private static final long serialVersionUID = 6452833707568457081L;
    
    public EarlyEOFException(String message) {
        super(message);
    }
}