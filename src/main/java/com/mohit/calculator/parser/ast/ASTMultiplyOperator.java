package com.mohit.calculator.parser.ast;

/**
 * Represent a multiplication in the expression as a*b
 * @author Mohit Kumar
 */
public class ASTMultiplyOperator extends ASTNode implements IASTBase
{
    public ASTMultiplyOperator(IASTBase first, IASTBase second)
    {
        super(first, second);
    }

    public double compute()
    {
        return getFirst().compute() * getSecond().compute();
    }
}