package com.mohit.calculator.parser.ast;

/**
 * IASTBase is the interface of the ASTNode as each of the
 * nodes of the AST implements it.
 * 
 * @author Mohit Kumar
 */
public interface IASTBase
{
    /**
     * Compute the value of this node.
     */
    public double compute();
}