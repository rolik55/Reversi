package com.Reversi;

import java.util.Scanner;

public abstract class InputVerifier {
    private static Scanner input;
    private static String inputX;
    private static int inputY;

    public static boolean isValidCoordinate(int coordinate) {
        if (coordinate < 1 || coordinate > 8) {
            return false;
        }
        return true;
    }

    public static String findCoordinateX(String inputX) {
        String X;
        switch (inputX) {
            case "a":
                X = "1";
                break;
            case "b":
                X = "2";
                break;
            case "c":
                X = "3";
                break;
            case "d":
                X = "4";
                break;
            case "e":
                X = "5";
                break;
            case "f":
                X = "6";
                break;
            case "g":
                X = "7";
                break;
            case "h":
                X = "8";
                break;
            default:
                X = "-1";
        }
        return X;
    }

    public static int getInputX() {
        input = new Scanner(System.in);
        System.out.println("Select X coordinates (a-h)");
        inputX = input.next();
//        if(inputX.charAt(0) == 'z') {
//            GameHandler.restoreBoard();
//            nextPlayer.makeTurn(board, player);
//            //getInputX();
//        }
//        else {
            inputX = findCoordinateX(inputX);
            while(!isValidCoordinate(Integer.valueOf(inputX))) {
                System.out.println("Invalid input. Please input another value (a-h).");
                inputX = input.next();
                inputX = findCoordinateX(inputX);
            }
        //}
        return Integer.valueOf(inputX);
    }

    public static int getInputY() {
        input = new Scanner(System.in);
        System.out.println("Select Y coordinates (1-8)");
        inputY = input.nextInt();
        while(!isValidCoordinate(inputY)){
            System.out.println("Invalid input. Please input another value (1-8).");
            inputY = input.nextInt();
        }
        return inputY;
    }
}
