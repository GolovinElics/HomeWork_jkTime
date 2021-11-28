/**
 * 本周作业：（必做）思考有多少种方式，在 main() 函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到 github。
 */

/**
 * @author liangfeng
 * @version 1.0
 */
class RunnableImp implements Runnable {

    private static final int x = 20;
    private static final int INITIAL_VALUE = 66;
    private static final int INITIAL_VALUE_TIME = 10;

    @Override
    public void run() {
        for (int i = 0; i < INITIAL_VALUE_TIME; i++) {
            System.out.println("播放音乐"+ i + "<" + sum() + ">");
        }
    }

    private static int sum() {
        return calculate(x);
    }

    private static int calculate(int i) {
        if (i < INITIAL_VALUE) {
            return 0;
        } else {
            return calculate(i - 2) + calculate(i + 1);
        }
    }
}

class RunnableImplStarter {
    public static void main(String[] args) {
        RunnableImp music = new RunnableImp();
        Thread t = new Thread(music);
        t.start();
    }
}