package com.xy.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by XiuYang on 2016/10/27.
 */
public class LambdaTest {

    private static int twoDiceThrows(ThreadLocalRandom random) {
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    static Map<Integer,Double> result = new HashMap<>();

    public static void main(String[] args) {
        int N = 2000000000;
        double fraction = 1.0/N;

        long start = System.currentTimeMillis();
        IntStream.range(0,N).forEach(
                (x)->{
                    int i = twoDiceThrows(ThreadLocalRandom.current());
                    Double current=  result.get(i);
                    result.put(i,current==null?fraction:(current+=fraction));
                }
        );
        result.forEach((x,y)->{
            System.out.println(x+"-"+y);
        });

        System.out.println(System.currentTimeMillis()-start);
    }


}
