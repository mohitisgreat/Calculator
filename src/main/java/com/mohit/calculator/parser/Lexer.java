package com.mohit.calculator.parser;

import com.mohit.calculator.exception.*;

/**
 * The Lexer class provides the facility to perform
 * lexically analysis on the expression.
 * 
 * @author Mohit Kumar
 */
public class Lexer {
    /**
     * Create a new lexer.
     */
    public Lexer(String source, int cursorPosition) {
        this.source = source;
        this.cursorPos = cursorPosition;
    }

    /**
     * Create a new lexer.
     */
    public Lexer(String source) {
        this.source = source;
        this.cursorPos = 0;
    }

    /**
     * Return the expression.
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the current cursor position.
     */
    public int getCursorPosition() {
        return cursorPos;
    }

    /**
     * Set the cursor position.
     * 
     * @param newCursorPos The new cursor position to be set.
     */
    public void setCursorPosition(int newCursorPos) {
        cursorPos = newCursorPos;
    }

    /**
     * Returns the next token.
     */
    public Token getNextToken() throws InvalidCharacterException {
        while (cursorPos < source.length() && Character.isSpaceChar(source.charAt(cursorPos))) {
            cursorPos++;
        }

        if (cursorPos == source.length()) {
            return new Token(TokenType.EOF, "EOF");
        } else if (isNumericDigit(source.charAt(cursorPos))) {
            try {
                return new Token(TokenType.REAL, nextNumber());
            } catch (MultipleDecimalException e) {
                throw new InvalidCharacterException(cursorPos, source.charAt(cursorPos));
            }
        } else if (source.charAt(cursorPos) == '+') {
            cursorPos++;
            return new Token(TokenType.PLUS, "+");
        } else if (source.charAt(cursorPos) == '-') {
            cursorPos++;
            return new Token(TokenType.MINUS, "-");
        } else if (source.charAt(cursorPos) == '*') {
            cursorPos++;
            return new Token(TokenType.MUL, "*");
        } else if (source.charAt(cursorPos) == '/') {
            cursorPos++;
            return new Token(TokenType.DIV, "/");
        } else if (source.charAt(cursorPos) == '(') {
            cursorPos++;
            return new Token(TokenType.LPAREN, "(");
        } else if (source.charAt(cursorPos) == ')') {
            cursorPos++;
            return new Token(TokenType.RPAREN, ")");
        } else if (source.charAt(cursorPos) == ',') {
            cursorPos++;
            return new Token(TokenType.COMMA, ",");
        } else if (Character.isLetter(source.charAt(cursorPos))) {
            return new Token(TokenType.IDENTIFIER, identifier());
        } else {
            // Invalid token exception.
            throw new InvalidCharacterException(cursorPos, source.charAt(cursorPos));
        }
    }

    private boolean isNumericDigit(char ch) {
        return Character.isDigit(ch) || ch == '.';
    }

    private String nextNumber() throws MultipleDecimalException {
        String numStr = ""; // String representation of the number.
        boolean hasDecimal = false; // Flag which represent if the number has any decimal.

        while (cursorPos < source.length()) {
            if (isNumericDigit(source.charAt(cursorPos))) {
                if (source.charAt(cursorPos) == '.') {
                    if (hasDecimal) {
                        // Multiple Decimals found in the number.
                        throw new MultipleDecimalException(cursorPos);
                    } else {
                        hasDecimal = true;
                    }
                }
                numStr += source.charAt(cursorPos);
                cursorPos++;
            } else {
                break;
            }
        }
        return numStr;
    }

    private String identifier() {
        String ideString = "";

        if (Character.isLetter(source.charAt(cursorPos))) {
            while (Character.isLetter(source.charAt(cursorPos)) || Character.isDigit(source.charAt(cursorPos))
                    || source.charAt(cursorPos) == '_') {
                ideString += source.charAt(cursorPos);
                cursorPos++;

                if (cursorPos == source.length())
                    break;
            }
        }

        return ideString;
    }

    private String source;
    private int cursorPos;
}