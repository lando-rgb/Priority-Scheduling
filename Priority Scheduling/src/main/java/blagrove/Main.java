package blagrove;

import java.util.Scanner;

public
class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter y to exit program or n to continue");
        String input = scanner.nextLine();

        if (input.equals("n")){

        Scanner s = new Scanner(System.in);
        Priority obj = new Priority();
        obj.getValues(s);
        obj.NonPreemptive();
    }else {
            System.exit(0);
        }
}
}
