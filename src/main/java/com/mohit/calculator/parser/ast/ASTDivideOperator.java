package com.mohit.calculator.parser.ast;

/**
 * Represent the division operation in the expression.
 * 
 * @author Mohit Kumar
 */
public class ASTDivideOperator extends ASTNode implements IASTBase
{
    public ASTDivideOperator(IASTBase first, IASTBase second)
    {
        super(first, second);
    }

    public double compute() 
    {
        return getFirst().compute() / getSecond().compute();
    }
}