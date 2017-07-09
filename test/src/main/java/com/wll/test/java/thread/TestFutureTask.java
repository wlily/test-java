package com.wll.test.java.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by wll on 2/17/17.
 */
public class TestFutureTask {

    public static void main(String[] args) {
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Sleep start.");
                try{
                    Thread.sleep(1000*10);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Sleep end");
                return "time=" + System.currentTimeMillis();
            }
        };

        //直接使用Thread的方式执行
        FutureTask<String> ft = new FutureTask<String>(task);
        Thread t = new Thread(ft);
        t.start();

        try {
            System.out.println("waiting execute result");
            System.out.println("result = " + ft.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("======");
        //使用Executors执行
        FutureTask<String> ft2 = new FutureTask<String>(task);
        Executors.newSingleThreadExecutor().submit(ft2);
        try {
            System.out.println("waiting execute result");
            System.out.println("result = " +  ft2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    int count(String path, String keyword) throws ExecutionException, InterruptedException {
        int c = 0;
        File[] files = new File(path).listFiles();
        ArrayList<Future<Integer>> rs = new ArrayList<Future<Integer>>();
        for(File file: files){  //每个文件启动一个task去查找
            MatchCount count = new MatchCount();
            count.file = file;
            count.keyword = keyword;
            FutureTask<Integer> task = new FutureTask(count);
            rs.add(task); //将任务返回的结果添加到集合中
            Thread thread = new Thread(task);
            thread.start();
        }

        for(Future<Integer> f: rs){
            c += f.get(); //迭代返回结果并累加
        }
        System.out.println("包含关键字的总文件数为：" + c);
        return c;
    }

    static class  MatchCount implements Callable<Integer>{
        public File file;
        public String keyword;
        private  Integer count = 0;

        public Integer call() throws Exception {   //call封装线程所需做的任务
            if(search(file))
                count ++;
            return count;
        }

        public boolean search(File file){
            boolean founded = false;
            try(Scanner scanner = new Scanner(new FileInputStream(file))){
                while(!founded && scanner.hasNextLine()){
                    if (scanner.nextLine().contains(keyword))
                        founded = true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return  founded;
        }
    }
}


