package com.demo.javademo.io.excel;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.*;

public class readInApacheIOTest {

    public static void main(String[] args) {
        try {
            readInApacheIOWithThreadPool("temp/test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void readInApacheIOWithThreadPool(String filePath) throws IOException, ExecutionException, InterruptedException {
        // 创建一个 最大线程数为 10，队列最大数为 100 的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60l, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        // 使用 Apache 的方式逐行读取数据
        LineIterator fileContents = FileUtils.lineIterator(new File(filePath), StandardCharsets.UTF_8.name());
        List<String> lines = Lists.newArrayList();
        while (fileContents.hasNext()) {
            String nextLine = fileContents.nextLine();
            lines.add(nextLine);
            // 读取到十万的时候
            if (lines.size() == 100000) {
                // 拆分成两个 50000 ，交给异步线程处理
                List<List<String>> partition = Lists.partition(lines, 50000);
                List<Future> futureList = Lists.newArrayList();
                for (List<String> strings : partition) {
                    Future<?> future = threadPoolExecutor.submit(() -> {
                        processTask(strings);
                    });
                    futureList.add(future);
                }
                // 等待两个线程将任务执行结束之后，再次读取数据。这样的目的防止，任务过多，加载的数据过多，导致 OOM
                for (Future future : futureList) {
                    // 等待执行结束
                    future.get();
                }
                // 清除内容
                lines.clear();
            }
        }
        // lines 若还有剩余，继续执行结束
        if (!lines.isEmpty()) {
            // 继续执行
            processTask(lines);
        }
        threadPoolExecutor.shutdown();
    }

    private static void processTask(List<String> strings) {
        for (String line : strings) {
            // 模拟业务执行
            try {
                TimeUnit.MILLISECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
