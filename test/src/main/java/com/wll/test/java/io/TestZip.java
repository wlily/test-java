package com.wll.test.java.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by wll on 17-7-10.
 */
public class TestZip {
    public static void main(String[] args) {

    }

    public void zip(String srcName, String zipName) throws IOException {
        File srcFile = new File(srcName);
        File zipFile = new File(zipName);

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.setComment("hello");
        zipOut.putNextEntry(new ZipEntry(srcFile.getName()));

        InputStream input = new FileInputStream(new File(srcName));

        int temp = 0;
        while((temp = input.read()) != -1){
            zipOut.write(temp);
        }
        input.close();
        zipOut.close();
    }

    public void zip(File srcDirectory, String zipName) throws IOException {
        File zipFile = new File(zipName);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.setComment("hello");

        InputStream input;

        if(srcDirectory.isDirectory()){
            File[] files = zipFile.listFiles();
            for(int i=0; i<files.length; i++){
                input = new FileInputStream(files[i]);
                zipOut.putNextEntry(new ZipEntry(srcDirectory.getName() + File.separator + files[i].getName()));
                int temp = 0;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
                input.close();
            }
        }
        zipOut.close();
    }

    public void unZip(File srcFile, File outFile) throws IOException {
//        File outFile = new File("d:" + File.separator + "unZipFile.txt");
        ZipFile zipFile = new ZipFile(srcFile);
        ZipEntry entry = zipFile.getEntry("hello.txt");
        InputStream input = zipFile.getInputStream(entry);
        OutputStream output = new FileOutputStream(outFile);
        int temp = 0;
        while((temp = input.read()) != -1){
            output.write(temp);
        }
        input.close();
        output.close();
    }

    public void unZipAll(String fileName, String outPath) throws IOException {
        File file = new File(fileName);
        File outFile = null;
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry = null;
        InputStream input = null;
        OutputStream output = null;
        while((entry = zipInput.getNextEntry()) != null){
            System.out.println("解压缩" + entry.getName() + "文件");
            outFile = new File(outPath + File.separator + entry.getName());
            if(!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdir();
            }
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            input = zipFile.getInputStream(entry);
            output = new FileOutputStream(outFile);
            int temp = 0;
            while((temp = input.read()) != -1){
                output.write(temp);
            }
            input.close();
            output.close();
        }
    }
}

