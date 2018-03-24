package com.mohit.calculator.parser.ast;

/**
 * Represent a minus operation in the expression as a-b
 * @author Mohit Kumar
 */
public class ASTMinusOperator extends ASTNode implements IASTBase
{
    public ASTMinusOperator(IASTBase first, IASTBase second)
    {
        super(first, second);
    }

    public double compute() {
        return getFirst().compute() - getSecond().compute();
    }
}