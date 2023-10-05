package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String method = signatureString.substring(0, signatureString.indexOf("("));
        String arguments = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")"));

        List<MethodSignature.Argument> argumentList = new ArrayList<>();
        if (!arguments.isEmpty()) {
            for (String s : arguments.split(", ")) {
                MethodSignature.Argument argument = new MethodSignature.Argument(s.split(" ")[0], s.split(" ")[1]);
                argumentList.add(argument);
            }
        }
        String[] methodBody = method.split(" ");
        MethodSignature methodSignature;
        if (methodBody.length > 2) {
            methodSignature = new MethodSignature(methodBody[2], argumentList);
            methodSignature.setAccessModifier(methodBody[0]);
            methodSignature.setReturnType(methodBody[1]);
        } else {
            methodSignature = new MethodSignature(methodBody[1], argumentList);
            methodSignature.setReturnType(methodBody[0]);
        }
        return methodSignature;
    }
}
