package com.mohit.calculator.parser;

import java.util.ArrayList;
import java.util.List;

import com.mohit.calculator.exception.BadArgumentException;
import com.mohit.calculator.exception.NotAFunctionException;
import com.mohit.calculator.parser.ast.IASTBase;

/**
 * FunctionStack Object stores the list of the 
 * registered functions.
 * 
 * @author Mohit Kumar
 */
public class FunctionStack {
    /**
     * Delegate for the function call.
     */
    public interface ICallFunction {
        /**
         * Return the result from the function.
         * 
         * @param args The arguments that should be passed to the function.
         */
        public double call(List<IASTBase> args);
    }

    /**
     * Intialize the FunctionStack.
     */
    public FunctionStack() {
        functions = new ArrayList<>();

        /**
         * Add the basic functions to the stack.
         */
        initStack();
    }

    /**
     * Return the list of the functions.
     */
    public List<IFunction> getFunctions() {
        return functions;
    }

    /**
     * Search the function in the stack and return
     * the function with the specified name.
     * 
     * @param funcName The function name.
     * @return Returns the function.
     */
    public IFunction getFunctionByName(String funcName) {
        for (IFunction func : getFunctions()) {
            if (func.getName().toUpperCase().equals(funcName.toUpperCase())) {
                return func;
            }
        }
        return null;
    }

    /**
     * Add the function to the stack.
     * 
     * @param name The name of the function.
     * @param argCount The number of the arguments should be passed.
     * @param func The behaviour of the call function of the function object.
     */
    public void addFunction(String name, int argCount, ICallFunction func) {
        functions.add(new IFunction() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public int getArgumentCount() {
                return argCount;
            }

            @Override
            public double call(List<IASTBase> arguments) {
                return func.call(arguments);
            }
        });
    }

    /**
     * Call the function with the given arguments.
     * 
     * @param function The refrence to the function to be called.
     * @param arguments An array of the arguments that should be passed to the function.
     * 
     * @return Returns the result if the function succeed.
     */
    public static double callFunction(IFunction function, List<IASTBase> arguments) throws Exception {
        if (function != null) {
            if (arguments.size() == function.getArgumentCount()) {
                return function.call(arguments);
            } else {
                throw new BadArgumentException(
                    "Invalid number of arguments passed to the function."
                );
            }
        } else {
            throw new NotAFunctionException("functionName");
        }
    }

    private void initStack() {
        addFunction("sin", 1, args -> {
            return Math.sin(args.get(0).compute());
        });
        addFunction("cos", 1, args -> {
            return Math.cos(args.get(0).compute());
        });
        addFunction("tan", 1, args -> {
            return Math.tan(args.get(0).compute());
        });
        addFunction("cot", 1, args -> {
            return 1.0 / Math.tan(args.get(0).compute());
        });
        addFunction("sec", 1, args -> {
            return 1.0 / Math.cos(args.get(0).compute());
        });
        addFunction("cosec", 1, args -> {
            return 1.0 / Math.sin(args.get(0).compute());
        });
        addFunction("sqrt", 1, args -> {
            return Math.sqrt(args.get(0).compute());
        });
        addFunction("abs", 1, args -> {
            return Math.abs(args.get(0).compute());
        });
        addFunction("sqr", 1, args -> {
            double value = args.get(0).compute();
            return value * value;
        });
        addFunction("cube", 1, args -> {
            double value = args.get(0).compute();
            return value * value * value;
        });
        addFunction("cbrt", 1, args -> {
            double value = args.get(0).compute();
            return Math.cbrt(value);
        });
        addFunction("pow2", 1, args -> {
            long power =  (long) Math.floor(args.get(0).compute());
            long ret = (1 << power);
            return (double)ret;
        });
        addFunction("pow", 2, args -> {
            double a = args.get(0).compute();
            double b = args.get(1).compute();
            return Math.pow(a, b);
        });
        addFunction("fac", 1, args -> {
            double arg1 = args.get(0).compute();
            return fact((int)Math.floor(arg1));
        });
        addFunction("toradians", 1, args -> {
            return (args.get(0).compute() * Math.PI)/180.0;
        });
        addFunction("todegrees", 1, args-> {
            return (args.get(0).compute() * 180.0)/Math.PI;
        });
    }

    private double fact(int arg) {
        if (arg == 0) return 1.0;
        return fact(arg-1) * arg;
    }

    private List<IFunction> functions;
}
