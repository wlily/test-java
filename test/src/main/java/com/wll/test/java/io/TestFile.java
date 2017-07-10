package com.wll.test.java.io;

import java.io.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestFile {
    public static void main(String[] args) throws IOException {
        TestFile testFile = new TestFile();

        File f = testFile.createFile("/home/wll/1-code/study/test-java", "test.txt");
        testFile.write(f, "test1111");
        System.out.println(testFile.read(f));

        testFile.write(f, "test2222");
        System.out.println(testFile.read(f));

    }

    public File createFile(String path, String name) throws IOException {
        File f = new File(path + File.separator + name);
        if(!f.getParentFile().exists()){
            f.mkdirs();
        }
        if(!f.exists()){
            f.createNewFile();
        }
        return f;
    }

    public void deleteFile(String fileName){
        File f = new File(fileName);
        if(f.exists()){
            f.delete();
        }
        else{
            System.out.println("file not exist");
        }
    }

    public void write(File f, String s) throws IOException {
        OutputStream out = new FileOutputStream(f);
        byte[] b = s.getBytes();
        out.write(b);
        out.close();
    }

    public String read(File f) throws IOException {
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];
        int len = in.read(b);
        in.close();
        return new String(b, 0, len);
    }

    public String readToEnd(File f) throws IOException {
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];
        int count =0;
        int temp = 0;
        while((temp=in.read())!=(-1)){
            b[count++]=(byte)temp;
        }
        in.close();
        return new String(b);
    }

    public void copy(File src, File dst) throws IOException {
        if(!src.exists()){
            System.out.println("被复制的文件不存在");
            System.exit(1);
        }

        InputStream input = new FileInputStream(src);
        OutputStream output = new FileOutputStream(dst);
        if((input != null) && (output != null)){
            int temp = 0;
            while((temp = input.read()) != -1){
                output.write(temp);
            }
        }
        input.close();
        output.close();
    }

    public String memoryCopy(String s) throws IOException {
        ByteArrayInputStream input=new ByteArrayInputStream(s.getBytes());
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        int temp = 0;
        while((temp=input.read())!=-1){
            char ch=(char)temp;
            output.write(Character.toLowerCase(ch));
        }
        String outStr=output.toString();
        input.close();
        output.close();
        return outStr;
    }
}
