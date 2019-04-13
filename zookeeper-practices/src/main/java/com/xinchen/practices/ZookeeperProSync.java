package com.xinchen.practices;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/13 23:01
 */
public class ZookeeperProSync implements Watcher {

    /**
     * 计数器
     */
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        // zookeeper配置数据存放地址
        String path = "/username";
        zk = new ZooKeeper("192.168.201.130:2181", 5000, new ZookeeperProSync());

        // 让线程等待zk连接成功
        latch.await();

        // 获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zk.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                // 计算器减一，表示连接成功
                System.out.println("Connection Success.");
                latch.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                // zk节点数据变更事件通知
                // 可通过 set /username value 查看变化
                try {
                    System.out.println("配置已经修改,新值为: " + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
