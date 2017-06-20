/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sammediatask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author MHDSHA
 */
public class GameEngine {

    private static final Random random = new Random();

    private Integer minNumber;

    private Integer maxNumber;

    private Integer nLevel;

    private List<String> operators;

    public Integer getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(Integer minNumber) {
        this.minNumber = minNumber;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Integer getnLevel() {
        return nLevel;
    }

    public void setnLevel(Integer nLevel) {
        this.nLevel = nLevel;
    }

    public List<String> getOperators() {
        return operators;
    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }

    private Integer generateNumber() {
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

    private String generateOperation() {
        switch (random.nextInt(3)) {
            case 0:
                return operators.get(0);
            case 1:
                return operators.get(1);
            case 2:
                return operators.get(2);
            default:
                return operators.get(0);
        }

    }

    private String pickRandomElementFromArrayList(List<String> array) {
        int rnd = random.nextInt(array.size());
        String value = array.remove(rnd);
        return value;
    }

    private boolean isOperator(String token) {
        for (int i = 0; i < operators.size(); i++) {
            if (token.equals(operators.get(i))) {
                return true;
            }
        }
        return false;
    }

    private Integer priority(String token) {
        if (token.equals("*")) {
            return 2;
        }
        if (token.equals("+")) {
            return 1;
        }
        if (token.equals("-")) {
            return 1;
        }
        return 0;
    }

    private Integer rightPriority(String token) {
        if (token.equals("+")) {
            return 1;
        }
        if (token.equals("-")) {
            return 2;
        }
        if (token.equals("*")) {
            return 3;
        }
        return 0;
    }

    private String print(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }

        String left = print(((Node) obj).left);
        String right = print(((Node) obj).right);
        Node nLeft = null;
        Node nRight = null;

        if (!(((Node) obj).left instanceof String)) {
            nLeft = (Node) ((Node) obj).left;
            if (priority(nLeft.getOperation()) < priority(((Node) obj).getOperation())) {
                left = "(" + left + ")";
            }
        }

        if (!(((Node) obj).right instanceof String)) {
            nRight = (Node) ((Node) obj).right;
            if (!(((Node) obj).left instanceof String)) {
                nLeft = (Node) ((Node) obj).left;
            }
            if (rightPriority(nRight.getOperation()) < rightPriority(((Node) obj).getOperation())
                    || (nRight.getOperation().equals(((Node) obj).getOperation())
                    && (nLeft != null && nLeft.getOperation().equals(((Node) obj).getOperation()) && ((Node) obj).getOperation().equals("-")))) {
                right = "(" + right + ")";
            }
        }
        String s = left + ' ' + ((Node) obj).getOperation() + ' ' + right;
        return s;
    }

    public String generatePostfixEquation(int level) {
        List<String> terminals = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        String postfixExp = "";
        for (int i = 0; i < level; i++) {
            terminals.add(String.valueOf(generateNumber()));
        }
        for (int i = 0; i < level - 1; i++) {
            terminals.add(generateOperation());
        }

        while (terminals.size() > 0) {
            String value = "";
            if (stack.size() < 2) {
                value = terminals.remove(0);
                if (!isOperator(value)) {
                    stack.push(Integer.parseInt(value));
                }
            } else {
                value = pickRandomElementFromArrayList(terminals);
                if (isOperator(value)) {
                    stack.pop();
                    stack.pop();
                } else {
                    stack.push(Integer.parseInt(value));
                }

            }
            if (terminals.size() > 0) {
                postfixExp = postfixExp + value + " ";
            } else {
                postfixExp = postfixExp + value;
            }
        }
        return postfixExp;
    }

    public Integer evaluate(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] terminals = expression.split(" ");
        for (String ch : terminals) {
            if (!isOperator(ch)) {
                stack.push(Integer.parseInt(ch));
            } else {

                Integer right = stack.pop();
                Integer left = stack.pop();

                if (ch.equals("+")) {
                    stack.push(left + right);
                } else if (ch.equals("-")) {
                    stack.push(left - right);
                } else {
                    stack.push(left * right);
                }
            }
        }

        return stack.pop();
    }

    public String postfixToInfix(String expression) {
        Stack<Object> stack = new Stack<>();
        String[] terminals = expression.split(" ");
        for (String token : terminals) {
            if (isOperator(token)) {
                if (stack.size() < 2) {
                    return "Invalid expression.";
                }
                Node node = new Node();
                node.right = stack.pop();
                node.left = stack.pop();
                node.operation = token;
                stack.push(node);
            } else {
                stack.push(token);
            }
        }
        if (stack.size() != 1) {
            return "Invalid expression.";
        }
        return print(stack.pop());
    }

}
