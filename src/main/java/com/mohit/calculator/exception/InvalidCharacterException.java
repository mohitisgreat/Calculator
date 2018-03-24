package com.mohit.calculator.exception;

public class InvalidCharacterException extends Exception
{
    private static final long serialVersionUID = -3517157380924492084L;

	public InvalidCharacterException(int cursorPosition, char ch) {
        super("Invalid token " + Character.toString(ch) + " at the cursor position " + 
            Integer.toString(cursorPosition));
    }
}