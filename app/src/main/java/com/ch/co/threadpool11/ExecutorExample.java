package com.ch.co.threadpool11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ExecutorExample {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 提交Runnable任务
        executor.execute(new RunnableTask());

        // 提交Callable任务并获取结果
        Future<Integer> futureResult = executor.submit(new CallableTask());

        try {
            // 等待并获取Callable任务的结果
            Integer result = futureResult.get();
            System.out.println("Callable task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }
}

// Runnable任务示例
class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable task is being executed by " + Thread.currentThread().getName());
    }
}

// Callable任务示例
class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("Callable task is being executed by " + Thread.currentThread().getName());
        return 42;  // 返回一个结果
    }
}