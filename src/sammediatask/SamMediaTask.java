/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sammediatask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MHDSHA
 */
public class SamMediaTask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Select Level
        System.out.println("Steps:");
        System.out.println("-------------------------");
        System.out.print("1 - To play enter the args --level (level between 1 - 4) ");
        System.out.println(" --Ex: --level 3");
        System.out.println("2 - For each level has 5 rounds ");
        System.out.print("3 - If you want to finish game enter exit ");
        System.out.println(" --Ex: exit");

//            String splitInput1 = sc.next();
//            if (splitInput1.equals("exit")) {
//                System.err.println("Finished Game...");
//                break;
//            }
//            String splitInput2 = sc.next();
//            Integer level = sc.nextInt();
        Scanner sc = new Scanner(System.in);
        Integer level = 0;
        if (args.length == 2) {
            if (args[0].equals("--level")) {
                level = Integer.parseInt(args[1]);
                if (level >= 1 && level <= 4) {
                    while (true) {
                        /*
                            Initialize Game:  
                         */

                        GameEngine game = new GameEngine();
                        List<String> operators = new ArrayList<>();
                        operators.add("+");
                        operators.add("-");
                        operators.add("*");
                        game.setOperators(operators);
                        game.setMinNumber(1);
                        game.setMaxNumber(100);
                        game.setnLevel(level);

                        /*
                                generate postfix expression
                         */
                        String postfixExpression = game.generatePostfixEquation(level);
                        String infixExpression = game.postfixToInfix(postfixExpression);
                        Integer correctResult = game.evaluate(postfixExpression);

                        System.out.println("--level " + level);
                        System.out.print(infixExpression + " = ");
                        Integer playerResult = sc.nextInt();
                        if (playerResult.equals(correctResult)) {
                            System.out.println("Correct Answer");
                        } else {
                            System.err.println("Wrong Answer: The correct answer is: " + correctResult);
                        }
                    }
                } else {
                    System.out.println("The level just between 1 and 4 ");
                }

            } else if (args[0].equals("exit")) {
                System.out.println("Finish Game..");
            } else {
                System.out.println("Invalid args");
            }
        }
    }
}
