package com.egoo.seckill.bigfish.circularqueue.ji.domian;


import com.egoo.seckill.bigfish.circularqueue.ji.structure.AbstractRingQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Title:环形队列
 */
public class RingQueue extends AbstractRingQueue {

    private ScheduledExecutorService stepPool = Executors.newScheduledThreadPool(1);


    public RingQueue() {
        super();

    }

    @Override
    public StepSlot nextStep(int slotIndex) {
        return slot[slotIndex];
    }

    @Override
    public int add(Task task) {
        return slot[task.getIndex()].addTask(task);
    }

    @Override
    public void remove(int slotIndex, int taskId) {
        slot[slotIndex].reomve(taskId);
    }

    @Override
    public void replaceSlot(int slotIndex, Task task) {
        remove(slotIndex, task.getId());
        add(task);
    }

    @Override
    public void initData() {
        //TODO 初始化数据(根据业务)
    }

    public void start() {
        new Thread(new Steper(this)).start();
    }

}
