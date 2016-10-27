package com.xy.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by XiuYang on 2016/10/27.
 */
public class MyConcurrent {
    private static final int N = 4000;
    private static final int theadNum = Runtime.getRuntime().availableProcessors();
    private static final int workNumPerThread = N / theadNum;
    private static final double fraction = 1.0/N;
    private final ExecutorService executors = Executors.newFixedThreadPool(theadNum);
    private final Map<Integer,Double> result = new ConcurrentHashMap<>();

    public static  volatile AtomicInteger a = new AtomicInteger();
    public void compute(){
        List<Future> list= new ArrayList<>();
        for(int i=0;i<theadNum;i++){
            Future future = executors.submit(()->{
                    for(int j=0;j<workNumPerThread;j++){
                        int random = twoDiceThrows(ThreadLocalRandom.current());
                        Double current=  result.get(random);
                        result.put(random,current==null?fraction:(current+=fraction));
                        a.incrementAndGet();
                    }
            });
            list.add(future);
        }
        for(Future future:list){
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(a);
        result.forEach((x,y)-> System.out.println(x+"-"+y));
    }

    private static int twoDiceThrows(ThreadLocalRandom random) {
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    public static void main(String[] args) {
        new MyConcurrent().compute();
    }
}
