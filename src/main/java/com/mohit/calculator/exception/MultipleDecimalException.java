package com.mohit.calculator.exception;

public class MultipleDecimalException extends Exception
{
    private static final long serialVersionUID = 2152538393512378710L;

	public MultipleDecimalException(int cursorPosition) {
        super("Multiple cursor found at cursor position: " + Integer.toString(cursorPosition));
    }
}