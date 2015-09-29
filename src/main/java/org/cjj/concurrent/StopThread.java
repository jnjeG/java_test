package org.cjj.concurrent;


/**
 * Wrong code.Don't use it.
 *
 * @author chenjunjie
 */
public class StopThread {
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        t.stop();
        ThreadDeath d = new ThreadDeath();
    }
}