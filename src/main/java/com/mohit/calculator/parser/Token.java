package com.mohit.calculator.parser;

/**
 * Represent a single token.
 * 
 * @author Mohit Kumar
 */
public class Token {
    /**
     * Construct a token
     * 
     * @param tokenType The type of the token.
     * @param tokenValue The string value of the token.
     */
    public Token(TokenType tokenType, String tokenValue) {
        this.tokentype = tokenType;
        this.tokenval = tokenValue;
    }

    /**
     * Compares the token to the type.
     * 
     * @param token The type of the token to be compared with.
     * 
     * @return Returns true on equal, otherwise false.
     */
    public boolean compareTokenType(TokenType token) {
        return tokentype == token;
    }

    /**
     * Return the token type.
     */
    public TokenType getTokenType() {
        return tokentype;
    }

    /**
     * Return the value of the token.
     */
    public String getTokenValue() {
        return tokenval;
    }

    private String tokenval;
    private TokenType tokentype;
}