package com.mohit.calculator.exception;

import java.lang.Exception;

public class InvalidTokenException extends Exception
{
    private static final long serialVersionUID = 7256924252560094370L;

	public InvalidTokenException(String message)
    {
        super(message);
    }
}