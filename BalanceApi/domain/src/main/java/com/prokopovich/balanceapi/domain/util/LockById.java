package com.prokopovich.balanceapi.domain.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;

public class LockById {

    private static class LockWrapper {

        private final Lock lock = new ReentrantLock();
        private final AtomicInteger threadsInQueue = new AtomicInteger(1);

        private LockWrapper addThreadInQueue() {

            threadsInQueue.incrementAndGet();

            return this;
        }

        private int removeThreadFromQueue() {

            return threadsInQueue.decrementAndGet();
        }

    }

    private static ConcurrentHashMap<Long, LockWrapper> locks = new ConcurrentHashMap<>();

    public void lock(Long key) {

        var lockWrapper = locks.compute(key, (k, v) -> v == null ? new LockWrapper() : v.addThreadInQueue());
        lockWrapper.lock.lock();
    }

    public void unlock(Long key) {

        var lockWrapper = locks.get(key);
        lockWrapper.lock.unlock();

        if (lockWrapper.removeThreadFromQueue() == 0) {

            locks.remove(key, lockWrapper);
        }
    }
}
