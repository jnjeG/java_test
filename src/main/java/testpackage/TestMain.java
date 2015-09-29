package testpackage;

/**
 * the url of the blog :
 * http://www.cnblogs.com/yejg1212/p/3270152.html
 */
public class TestMain {
    public static void main(String[] args) {
        // 当前类(class)所在的包目录
        System.out.println(TestMain.class.getResource(""));
        // class path根目录
        System.out.println(TestMain.class.getResource("/"));

        // testpackage.TestMain.class在<bin>/testpackage包中
        // 2.properties  在<bin>/testpackage包中
        System.out.println(TestMain.class.getResource("2.properties"));

        // testpackage.TestMain.class在<bin>/testpackage包中
        // 3.properties  在<bin>/testpackage.subpackage包中
        System.out.println(TestMain.class.getResource("subpackage/3.properties"));

        // testpackage.TestMain.class在<bin>/testpackage包中
        // 1.properties  在bin目录（class根目录）
        System.out.println(TestMain.class.getResource("/1.properties"));
    }
}