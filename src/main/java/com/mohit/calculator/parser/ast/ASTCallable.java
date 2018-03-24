package com.mohit.calculator.parser.ast;

import java.util.List;

import com.mohit.calculator.parser.FunctionStack;
import com.mohit.calculator.parser.IFunction;

/**
 * ASTCallable represents the function call in the expression.
 * 
 * @author Mohit Kumar
 */
public class ASTCallable implements IASTBase {
    public ASTCallable(IFunction function, List<IASTBase> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public double compute() {
        try {
            return FunctionStack.callFunction(function, arguments);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    IFunction function;
    List<IASTBase> arguments;
}
