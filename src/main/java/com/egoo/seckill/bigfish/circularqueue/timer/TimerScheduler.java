package com.egoo.seckill.bigfish.circularqueue.timer;


import com.egoo.seckill.bigfish.circularqueue.timer.TaskHolder;
import com.egoo.seckill.bigfish.circularqueue.timer.TimerContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description:
 * @Author liyu
 * @Datetime 2018/12/29 12:30:00
 */
public class TimerScheduler extends TimerTask {

    private TimerContext timerContext;

    public TimerScheduler() {}

    public TimerScheduler(TimerContext timerContext) {
        this.timerContext = timerContext;
    }

    /**
     * 定时检测，如果定时器触发时间到了就从集合中删除并执行任务，否则圈数减一。
     */
    @Override
    public void run() {
        if (timerContext == null)
            return;

        Queue<TaskHolder> tasks = timerContext.getCurrentTasks();
        synchronized (tasks) {
            Iterator<TaskHolder> itor = tasks.iterator();
            while (itor.hasNext()) {
                TaskHolder timer = itor.next();
                if (timer.isTimeOut()) {
                    itor.remove();
                    new Thread(timer.getTask()).start();
                } else {
                    timer.cutDown();
                }
            }

        }

        timerContext.tick();
    }

    public void addTask(Runnable task, int delays) {
        timerContext.addTask(task, delays);
    }

    public TimerContext getTimerContext() {
        return timerContext;
    }

    public void setTimerContext(TimerContext timerContext) {
        this.timerContext = timerContext;
    }

    public static void main(String[] args) throws IOException {
        TimerContext context = new TimerContext(5, 1);
        TimerScheduler sheduler = new TimerScheduler(context);
        sheduler.addTask(new Runnable() {
            public void run() {
                System.out.println(DateUtils.now()+"---21");


            }
        }, 10);
        System.out.println(DateUtils.now()+"---11");


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(sheduler, 0, context.getTickDuration() * 1000L);

        System.in.read();
    }




}
