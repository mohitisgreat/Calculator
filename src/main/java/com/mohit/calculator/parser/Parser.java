package com.mohit.calculator.parser;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.mohit.calculator.exception.InvalidTokenException;
import com.mohit.calculator.parser.ast.*;

/**
 * Parser is used to parse the mathmatical expression.
 * @author Mohit Kumar Agrawal
 */
public class Parser {
    /**
     * Creates a new parser.
     * 
     * @param Source The expression that should be parsed.
     */
    public Parser(String source) {
        lexer = new Lexer(source);
        functionStack = new FunctionStack();
        constantStack = new HashMap<>();
        variableStack = new HashMap<>();

        constantStack.put("PI", new ASTConstant(Math.PI));
        constantStack.put("E", new ASTConstant(Math.E));
    }

    /**
     * Returns the source that is begin parsed.
     */
    public String getSource() {
        return lexer.getSource();
    }

    /**
     * Returns the current token.
     */
    public Token getCurrentToken() {
        return currentToken;
    }

    /**
     * Adds the function to the stack.
     * 
     * @param functionName The name of the function.
     * @param argCount The number of the arguments that shoulkd be passed to the function.
     * @param func The behaviour of the function.
     */
    public void addFunctionToStack(String functionName, int argCount, FunctionStack.ICallFunction func) {
        functionStack.addFunction(functionName, argCount, func);
    }

    /**
     * Add the constant value to the stack.
     * 
     * @param constant The name of the constant.
     * @param value The value of the constant.
     */
    public void addConstantToStack(String constant, Double value) {
        constantStack.put(constant, new ASTConstant(value));
    }

    /**
     * Put the variable to the stack.
     * 
     * @param variableName The name of the variable.
     * @param value The value of the variable.
     * 
     * Remember that variable name is case-sensitive while constants are case-insensitive.
     */
    public void addVariableToStack(String variableName, Double value) {
        variableStack.put(variableName, new ASTConstant(value));
    }

    /**
     * Parse the expression.
     * 
     * <pre>Example:<code>
     * import com.mohit.calculator.parser.Parser;
     *
     * public class ParserApp
     * {
     *    public static void main(String[] args) {
     *        String expression = "2*2";
     *        Parser parser = new Parser(expression);
     *        double result = parser.parse().compute();
     *        System.out.println(expression + " = " + result)
     *    }
     * }
     * </code></pre>
     * 
     * @return Return the AST after parsing.
     */
    public IASTBase parse() throws Exception {
        IASTBase a = term();
        IASTBase b;
        while (true) {
            int curPos = lexer.getCursorPosition();
            currentToken = lexer.getNextToken();

            switch (currentToken.getTokenType()) {
            case PLUS:
                b = term();
                a = new ASTPlusOperator(a, b);
                break;
            case MINUS:
                b = term();
                a = new ASTMinusOperator(a, b);
                break;
            default:
                lexer.setCursorPosition(curPos);
                return a;
            }
        }
    }

    public void initSource(String expression) {
        lexer = new Lexer(expression);
    }

    private void eat(TokenType tokenType) throws Exception {
        if (!lexer.getNextToken().compareTokenType(tokenType)) {
            throw new InvalidTokenException("Expected a " + tokenType.toString() + " token.");
        }
    }
    private IASTBase factor() throws Exception {
        currentToken = lexer.getNextToken();
        if (currentToken.compareTokenType(TokenType.REAL)) {
            double fac = Double.parseDouble(currentToken.getTokenValue());
            return new ASTConstant(fac);
        } else if (currentToken.compareTokenType(TokenType.LPAREN)) {
            IASTBase ret = parse();
            eat(TokenType.RPAREN);
            return ret;
        } else if(currentToken.compareTokenType(TokenType.IDENTIFIER)) {
            String identifier = currentToken.getTokenValue();
            IFunction function = functionStack.getFunctionByName(identifier);

            if (function == null) {
                if (constantStack.containsKey(identifier.toUpperCase())) {
                    return constantStack.get(identifier.toUpperCase());
                } else if (variableStack.containsKey(identifier)){
                    return variableStack.get(identifier);
                } else {
                    return new ASTConstant(0.0);
                }
            }
            List<IASTBase> args = new ArrayList<>();
            eat(TokenType.LPAREN);
            int argCount = function.getArgumentCount();
            for (int x = 0; x < argCount; ++x) {
                args.add(parse());
                if (x != (argCount-1)) {
                    eat(TokenType.COMMA);
                }
            }
            eat(TokenType.RPAREN);
            return new ASTCallable(function, args);
        }
        else if(currentToken.compareTokenType(TokenType.MINUS)) {
            currentToken = lexer.getNextToken();
            if (currentToken.compareTokenType(TokenType.REAL)) {
                return new ASTConstant(-Double.parseDouble(currentToken.getTokenValue()));
            } else {
                throw new InvalidTokenException("Expected a number here.");
            }
        }
        else {
            throw new InvalidTokenException(
                "Expected a factor at: " + Integer.toString(lexer.getCursorPosition())
            );
        }
    }
    private IASTBase term() throws Exception {

        IASTBase a = factor();
        IASTBase b;
        while (true) {
            int curPos = lexer.getCursorPosition();
            currentToken = lexer.getNextToken();

            switch (currentToken.getTokenType()) {
            case MUL:
                b = factor();
                a = new ASTMultiplyOperator(a, b);
                break;
            case DIV:
                b = factor();
                a = new ASTDivideOperator(a, b);
                break;
            default:
                lexer.setCursorPosition(curPos);
                return a;
            }
        }
    }

    private Token currentToken;
    private Lexer lexer;
    private FunctionStack functionStack;
    private Map<String, ASTConstant> constantStack;
    private Map<String, ASTConstant> variableStack;
}