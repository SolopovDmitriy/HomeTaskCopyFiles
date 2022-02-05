package main;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopier {
    byte[] buffer;

    public FileCopier(byte[] buffer) {
        this.buffer = buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    //    srcFileOrDir – файл или папка, которую нужно скопировать
//    destDir – папка, в которую нужно скопировать srcFileOrDir
    public void copy(File srcFileOrDir, File destDir) { //srcFileOrDir = "D:\\javaProject\\test\\dir3";  destDir = "D:\\javaProject\\test\\dir4"
        // если первый параметр srcFileOrDir -- это файл,
        // а второй параметр destDir -- это папка
        if(srcFileOrDir.isFile() && destDir.isDirectory() ) {
            String fileName = srcFileOrDir.getName(); // "1.txt"
            File target = new File(destDir, fileName);// D:\javaProject\test\dir4\1.txt
            copyFile(srcFileOrDir, target);
        }
        // если первый параметр srcFileOrDir -- это папка,
        // а второй параметр destDir -- это папка
        else if(srcFileOrDir.isDirectory() && destDir.isDirectory()){
            copyDir(srcFileOrDir, destDir); //srcFileOrDir = "D:\\javaProject\\test\\dir3";  destDir = "D:\\javaProject\\test\\dir4"
        }else{
            System.out.println("Error. Second parameter is not a folder");
        }
    }


    private void copyDir(File dirSrc, File dirDest){ // "D:\\javaProject\\test\\dir3", "D:\\javaProject\\test\\dir4"
        File[] files = dirSrc.listFiles();
        for(File f : files){
            String fileName = f.getName();
            File target = new File(dirDest,fileName); //  target = "D:\\javaProject\\test\\dir4\\dir1
            if(f.isFile()){ // f = D:\javaProject\test\dir3\dir1
                copyFile(f,target);
            }else{
                target.mkdir(); // создаем папку target = "D:\\javaProject\\test\\dir4\\dir1
                copyDir(f,target);	// Рекурсивно вызываем метод копирования
            }
        }
    }


    private void copyFile(File srcFile ,File destFile){
        FileOutputStream writer = null;
        FileInputStream reader = null;
        try{
            reader = new FileInputStream(srcFile);// объект, который будет производить чтение файла
            writer = new FileOutputStream(destFile); // объект, который будет производить записи файла
            int len = 0;
            while((len = reader.read(buffer)) != -1){
                writer.write(buffer,0, len);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(writer!=null){
                try{
                    writer.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }




}
