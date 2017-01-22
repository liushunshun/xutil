package com.xy.pattern.callback;

/**
 * Created by XiuYang on 2016/10/20.
 */
public class Student implements ICallback{
    private Teacher teacher;

    public Student(Teacher teacher){
        this.teacher = teacher;
    }
    @Override
    public void sove(String result) {
        System.out.println("老师给的答案是："+result);
    }

    public void run(){
        new Thread(()->{
            teacher.ask(this,1);
        }).start();
        System.out.println("学生去玩了");
    }

    public static void main(String[] args) {
        new Student(new Teacher()).run();
    }
}
