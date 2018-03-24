package com.mohit.calculator.parser.ast;

/**
 * It represent a node in AST which split into two nodes like
 * ASTPlusOperator, ASTMinusOperator, ASTMultiplyOperator, etc.
 * 
 * @author Mohit Kumar
 */
public class ASTNode
{
    public ASTNode(IASTBase first, IASTBase second)
    {
        this.first = first;
        this.second = second;
    }

    /**
     * The first AST node.
     */
    public IASTBase getFirst() {
        return first;
    }

    /**
     * The second AST node.
     */
    public IASTBase getSecond() {
        return second;
    }

    private IASTBase first, second;
}