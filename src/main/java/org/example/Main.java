package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        SplayTree tree = new SplayTree();
        int[] arr = new int[10000];
        double time3=0;
        double time4=0;
        double time5 = 0;
        int count3=0, count4=0, count5 = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }


        File file3 = new File("Task3.txt");
        file3.createNewFile();
        try {
            OutputStream output = new FileOutputStream(file3, false);
            PrintStream stream = new PrintStream(output);
            for (int i = 0; i < arr.length; i++) {
                long startTime3 = System.nanoTime();
                tree.insert(arr[i]);
                long endTime3 = System.nanoTime();
                int count = tree.getCountOp();
                count3 +=count;
                double t = (double) (endTime3 - startTime3) / 1000;
                time3 += t;
                stream.print(i);
                stream.print(") Время работы: ");
                stream.print(t);
                stream.print(" микросекунда  ");
                stream.print("\n");
//                stream.print("   ")
                stream.print("Количество операций: ");
                stream.print(count);
                stream.print("\n");
                stream.print("\n");

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file4 = new File("Task4.txt");
        file4.createNewFile();
        try {
            OutputStream output = new FileOutputStream(file4, false);
            PrintStream stream = new PrintStream(output);
            for (int i = 0; i < 100; i++) {
                int num = (int) (Math.random() * 10000);
                long startTime4 = System.nanoTime();
                tree.search(arr[num]);
                long endTime4 = System.nanoTime();
                int count = tree.getCountOp();
                count4 +=count;
                double t = (double) (endTime4 - startTime4) / 1000;
                time4 +=t;
                stream.print("Элемент ");
                stream.print(num);
                stream.print(") Время работы: ");
                stream.print(t);
                stream.print(" микросекунда  ");
                stream.print("\n");
//                stream.print("   ")
                stream.print("Количество операций: ");
                stream.print(count);
                stream.print("\n");
                stream.print("\n");

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file5 = new File("Task5.txt");
        file5.createNewFile();
        try {
            OutputStream output = new FileOutputStream(file5, false);
            PrintStream stream = new PrintStream(output);
            for (int i = 0; i < 1000; i++) {
                int num = (int) (Math.random() * 10000);
                long startTime5 = System.nanoTime();
                tree.delete(arr[num]);
                long endTime5 = System.nanoTime();
                int count = tree.getCountOp();
                count5 +=count;
                double t = (double) (endTime5 - startTime5) / 1000;
                time5 +=t;
                stream.print("Элемент ");
                stream.print(num);
                stream.print(") Время работы: ");
                stream.print(t);
                stream.print(" микросекунда  ");
                stream.print("\n");
//                stream.print("   ")
                stream.print("Количество операций: ");
                stream.print(count);
                stream.print("\n");
                stream.print("\n");

            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("вставка " + time3 + "микросекунда всего " + "||| на количество операций " + count3);
        System.out.println("поиск " + time4 + "микросекунда всего " + "||| на количество операций " + count4);
        System.out.println("удаление " + time5 + "микросекунда всего " + "||| на количество операций " + count5);

    }
}