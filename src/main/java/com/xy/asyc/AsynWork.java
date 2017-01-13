package com.xy.asyc;

import java.io.Serializable;

/**
 * 异步工作接口类
 */
public interface AsynWork extends Serializable {

    /**
     * @desc 得到线程名字，查看分析使用
     * 
     * @return String
     */
    public String getThreadName();

    /**
     * @desc 调用指定对象的方法，并返回结果。如果需要结果回调返回AsynCallback，不需要返回null
     * 
     * @return AsynCallback
     */
    public AsynCallback call() throws Exception;

    /**
     * @desc 得到任务执行优先级
     * 
     * @return int
     */
    public int getPriority();

}
