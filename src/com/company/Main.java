package com.company;

import java.util.Scanner;
import java.lang.*;
import java.util.TreeMap;

public class Main {
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
       map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }
        static String letters[]  =  { "I","II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: в одну строку через пробел ");
            System.out.println(calc(sc.nextLine()));

        }
    public static String calc(String input){

       String [] op = input.split(" ", 3);
       int index1;
       int index2;
       String operation = op[1];
       int result;

        if (testRom(op[0])&&testRom(op[2])){
            index1 = convertToAr(op[0]);
            index2 = convertToAr(op[2]);
            if ((index1<0||index1>10)||(index2<0||index2>10)) throw new IllegalStateException();
            result= operation(index1, operation, index2);
            return convertToRome(result);
        } else if (testAr(op[0])&&testAr(op[2])){
            index1 = Integer.parseInt(op[0]);
            index2 = Integer.parseInt((op[2]));
            result= operation(index1, operation, index2);
            return String.valueOf(result);
        }else {
            throw new IllegalStateException("Не верный ввод");
        }
   }
    private static int operation(int index1, String operation, int index2){
        int result = switch (operation){
            case("+") -> index1+index2;
            case ("-")-> index1-index2;
            case ("*")-> index1*index2;
            case ("/")->index1/index2;
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
        return result;
    }

    private static int convertToAr(String letter){



                int result = switch (letter){
                    case("I") -> 1;
                    case("II") -> 2;
                    case("III") -> 3;
                    case ("IV")-> 4;
                    case ("V")-> 5;
                    case("VI") -> 6;
                    case("VII") -> 7;
                    case("VIII") -> 8;
                    case ("IX")->9;
                    case ("X")->10;
                    default -> -1;
                };

        return result;
    }
    private static String convertToRome(int number){
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + convertToRome(number-l);
    }

    private static boolean testRom(String s){
           for (int i=0;i<letters.length;i++){
               if ( s.equals(letters[i])) return true;
            }
        return false;


    }
    private static boolean testAr(String s){
            char simbol = s.charAt(0);
            if (Character.isDigit(simbol)) return true;
            return false;
    }
}
