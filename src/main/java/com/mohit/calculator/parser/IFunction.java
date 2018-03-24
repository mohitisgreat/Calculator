package com.mohit.calculator.parser;

import java.util.List;

import com.mohit.calculator.parser.ast.IASTBase;

/**
 * Represent a function object in the expression.
 * 
 * @author Mohit Kumar
 */
public interface IFunction {
    public String getName();
    public int getArgumentCount();
    public double call(List<IASTBase> arguments);
}