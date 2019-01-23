package com.egoo.seckill.bigfish.circularqueue.ji.domian;

import com.egoo.seckill.bigfish.circularqueue.ji.structure.AbstractTask;

/**
 * Title:任务体
 */
public abstract class Task extends AbstractTask {

    public Task(int id, int after) {
        super(id, after);
    }
}
