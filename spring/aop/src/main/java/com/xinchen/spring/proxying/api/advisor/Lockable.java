package com.xinchen.spring.proxying.api.advisor;

/**
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 11:11
 */
public interface Lockable {
    void lock();

    void unlock();

    boolean locked();
}
