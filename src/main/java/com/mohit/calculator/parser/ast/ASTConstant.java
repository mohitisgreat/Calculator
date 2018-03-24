package com.mohit.calculator.parser.ast;

/**
 * A constant value in the expression.
 * 
 * @author Mohit Kumar
 */
public class ASTConstant implements IASTBase
{
    public ASTConstant(double value) {
        this.value = value;
    }

    @Override
    public double compute() {
        return value;
    }

    double value;
}