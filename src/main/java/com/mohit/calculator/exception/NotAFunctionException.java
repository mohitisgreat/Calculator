package com.mohit.calculator.exception;

/**
 * NotAFunctionException
 */
public class NotAFunctionException extends Exception{
    private static final long serialVersionUID = -6386861583073941935L;

	public NotAFunctionException(String invFuncName) {
        super("'"+invFuncName+"' is not a function.");
    }
}