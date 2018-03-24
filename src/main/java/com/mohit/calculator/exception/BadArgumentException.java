package com.mohit.calculator.exception;

public class BadArgumentException extends Exception
{
    private static final long serialVersionUID = -5592490718404443747L;

	public BadArgumentException(String message) {
        super(message);
    }
}