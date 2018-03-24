package com.mohit.calculator.parser;

/**
 * The type of the tokens.
 * @author Mohit Kumar
 */

public enum TokenType {
    /**
     * Represent a real number.
     */
    REAL,

    /**
     * Represent the end of expression.
     */
    EOF,

    /**
     * Represent the plus(+) operator
     */
    PLUS,

    /**
     * Represent the minus(-) operator
     */
    MINUS,

    /**
     * Represent the multiply(*) operator
     */
    MUL,

    /**
     * Represent the division operator(/) operator
     */
    DIV,

    /**
     * Represent the left parenthesis '('
     */
    LPAREN,

    /**
     * Represent the right parenthesis ')'
     */
    RPAREN,

    /**
     * Represent a identifier.
     */
    IDENTIFIER,

    /**
     * Represent a comma ','.
     */
    COMMA,
}