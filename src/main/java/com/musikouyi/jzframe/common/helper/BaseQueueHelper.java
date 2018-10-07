package com.musikouyi.jzframe.common.helper;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.musikouyi.jzframe.common.queue.vo.ValueWrapper;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:04
 **/

/**
 * lmax.disruptor 高效队列处理模板.
 * 支持初始队列，即在init()前进行发布。
 * <p>
 * 调用init()时才真正启动线程开始处理
 * 系统退出自动清理资源.
 *
 * @param <D> 消息类
 * @param <E> 消息包装类
 * @param <H> 消息处理类
 * @author JIM
 */
public abstract class BaseQueueHelper<D, E extends ValueWrapper<D>, H extends WorkHandler<E>> {

    private Disruptor<E> disruptor;

    private RingBuffer<E> ringBuffer;

    private List<D> initQueue = new ArrayList<>();

    /**
     * 需要多少线程来消耗队列.
     *
     * @return
     */
    protected abstract int getThreadNum();

    /**
     * @return 队列长度，必须是2的幂
     */
    protected abstract int getQueueSize();

    /**
     * @return
     */
    private Class<E> eventClass;

    private Class<H> eventHandlerClass;

    //记录所有的队列，系统退出时统一清理资源
    private static List<BaseQueueHelper> queueHelperList = new ArrayList<>();


    public BaseQueueHelper() {
        Class<?> typeCls = getClass();
        Type genType = typeCls.getGenericSuperclass();
        while (true) {
            if (!(genType instanceof ParameterizedType)) {
                typeCls = typeCls.getSuperclass();
                genType = typeCls.getGenericSuperclass();
            } else {
                break;
            }
        }
        this.eventClass = (Class<E>) ((ParameterizedType) genType).getActualTypeArguments()[1];
        this.eventHandlerClass = (Class<H>) ((ParameterizedType) genType).getActualTypeArguments()[2];
    }

    @SuppressWarnings("unchecked")
    public void init() {
        disruptor = new Disruptor<E>(() -> {
            try {
                return (E) eventClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, getQueueSize(), DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, getStrategy());

        H[] eventHandlers = (H[]) Array.newInstance(eventHandlerClass, getThreadNum());
        for (int i = 0; i < getThreadNum(); i++) {
            try {
                eventHandlers[i] = (H) eventHandlerClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        disruptor.handleEventsWithWorkerPool(eventHandlers);
        ringBuffer = disruptor.start();

        for (D data : initQueue) {
            ringBuffer.publishEvent((event, sequence, data1) -> event.setValue(data1), data);
        }

        //加入资源清理钩子
        synchronized (queueHelperList) {
            if (queueHelperList.isEmpty()) {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    for (BaseQueueHelper baseQueueHelper : queueHelperList) {
                        baseQueueHelper.shutdown();
                    }
                }));
            }
            queueHelperList.add(this);
        }
    }

    /**
     * 如果要改变线程执行优先级，override此策略.
     * YieldingWaitStrategy会提高响应并在闲时占用70%以上CPU，慎用
     * SleepingWaitStrategy会降低响应更减少CPU占用，用于日志等场景.
     *
     * @return
     */
    protected WaitStrategy getStrategy() {
        return new BlockingWaitStrategy();
    }

    /**
     * 插入队列消息，支持在对象init前插入队列，则在队列建立时立即发布到队列处理.
     *
     * @param data
     */
    public synchronized void publishEvent(D data) {
        if (ringBuffer == null) {
            initQueue.add(data);
            return;
        }
        ringBuffer.publishEvent((event, sequence, data1) -> event.setValue(data1), data);
    }

    public void shutdown() {
        disruptor.shutdown();
    }
}
