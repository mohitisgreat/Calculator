package com.mohit.calculator.parser.ast;

/**
 * Represent addition in the expression as  a+b 
 * @author Mohit Kumar
 */
public class ASTPlusOperator extends ASTNode implements IASTBase
{
    public ASTPlusOperator(IASTBase first, IASTBase second)
    {
        super(first, second);
    }

    public double compute() {
        return getFirst().compute() + getSecond().compute();
    }
}