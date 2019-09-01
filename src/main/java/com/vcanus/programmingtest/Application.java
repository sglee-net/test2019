package com.vcanus.programmingtest;

import java.io.IOException;

public class Application {
    public static void main(String args[]) throws IOException {
        System.out.println("Programming Test");
        int n = Integer.parseInt(args[0]);
        switch(n) {
            case 1:
            case 2:
            case 3:
                System.out.println("not implemented yet");
                break;
            case 4:
                Test04 test = new Test04();
                test.readFile(
                        args[1],
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]),
                        args[4]);
                test.run();
                break;
            default:
                break;
        }
    }
}
