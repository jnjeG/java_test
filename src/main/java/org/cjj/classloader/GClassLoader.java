package cjj.classloader;

import com.sun.nio.zipfs.ZipFileStore;
import com.sun.nio.zipfs.ZipFileSystem;

/**
 * Created by Administrator on 2015/9/29.
 */
public class GClassLoader {

    public static void main(String args[]) {
        ClassLoader bootstrapClassLoader = String.class.getClassLoader();
        System.out.println(bootstrapClassLoader);
        System.out.println();


        /**同一个ClassLoader对象**/
        ClassLoader extentionClassLoader = ZipFileStore.class.getClassLoader();
        System.out.println(extentionClassLoader);
        ClassLoader extentionClassLoader2 = ZipFileSystem.class.getClassLoader();
        System.out.println(extentionClassLoader2);
        System.out.println();


        /**同一个ClassLoader对象**/
        ClassLoader systemClassLoader = GClassLoader.class.getClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader systemClassLoader2 = Thread.currentThread().getContextClassLoader();
        System.out.println(systemClassLoader2);
        ClassLoader systemClassLoader3 = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader3);
        System.out.println();


        GClassLoaderBean gcb = new GClassLoaderBean();
        ClassLoader userDefinedClassLoader = gcb.getClass().getClassLoader();
        System.out.println(userDefinedClassLoader);


    }
}
