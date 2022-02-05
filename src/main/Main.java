package main;

import java.io.File;

public class Main {

    public static void testCreateFolder(){
        File test = new File("D:\\javaProject\\test\\44");
        System.out.println(test.mkdir()); // in folder D:\javaProject\test create folder "44"
    }


    public static void main(String[] args) {

//        File someDir = new File("D:\\javaProject\\test\\dir3");
        File someDir = new File("D:\\javaProject\\test\\dir3\\dir1\\subdir1\\1.txt");
        // D:\javaProject\test\dir3\dir1\subdir1\1.txt
        File destDir = new File("D:\\javaProject\\test\\dir4");
        FileCopier copier = new FileCopier(new byte[1024]);
        copier.copy(someDir, destDir);
        // testCreateFolder();

    }



}
