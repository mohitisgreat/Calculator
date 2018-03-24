package com.mohit.calculator.runtime;

import com.mohit.calculator.parser.Parser;
import com.mohit.calculator.parser.ast.IASTBase;

import java.util.Map;
import java.util.HashMap;

/**
 * Context
 */
public class Context {
    private Map<String, Double> variableStack;

    /**
     * Construct the runtime Context object.
     */
    public Context() {
        variableStack = new HashMap<>();
    }

    /**
     * Process the mathematical expression.
     */
    public String processExpression(String expression) throws Exception {
        double val = 0.0;

        if (expression.contains("=")) {
            String[] s = expression.split("=");
            if (s.length == 2) {
                String varName = s[0];
                String expr = s[1];

                Double value = evaluate(expr);
                variableStack.put(varName.trim(), value);

                val = value;
            } else if (s.length > 2) {
                // We have a problem.
                return "Error: Multiple equal sign in a single expression is not allowed.";
            } else {
                // Impossible condition
                return "Error: Unreachable code reached.";
            }
        } else {
            val = evaluate(expression);
        }

        return Double.toString(val);
    }

    /**
     * Clear the variable stack.
     */
    public void clearStack() {
        variableStack.clear();
    }

    /**
     *  Evaluate the the expression.
     */
    private Double evaluate(String expression) throws Exception {
        Parser parser = new Parser(expression);

        variableStack.forEach((str, val)->{
            parser.addVariableToStack(str, val);
        });

        IASTBase ast = parser.parse();
        return ast.compute();
    }
}