package com.android.homework.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther 19062
 * @date 2023/9/16
 * @time 19:59.
 */


public class ThreadUtils {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(CPU_COUNT + 1, 4);
    private static final int MAXIMUM_POOL_SIZE = Math.max(CPU_COUNT * 2 + 1, CORE_POOL_SIZE);
    private static final int BLOCK_QUEUE_SIZE = 100;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r,"launcher-pool-thread-" + threadNumber.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    };

    private static final ExecutorService executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(BLOCK_QUEUE_SIZE), THREAD_FACTORY);

    private static Handler mHandler = null;

    public static boolean isUIThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static Handler getUIHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public static Future<?> submit(Runnable task) {
        return executorService.submit(task);
    }

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
