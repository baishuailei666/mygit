package learn;

/**
 * @Auther :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2018/10/9 0009
 * @Desc :银行有一个金库，有两个账户分别存300斤黄金，每次存100斤，存3次。
 * 目的：
 *      该程序是否有安全问题？如果有，如何解决？
 * 如何找问题：
 *      1、明确哪些代码是多线程执行代码
 *      2、明确哪些是共享数据
 *      3、明确多线程运行代码中哪些语句是操作共享数据的
 **/
public class ThreadBankDemo {
    public static void main(String[] args) {
        Cus cus = new Cus();
        Thread t1 = new Thread(cus);
        Thread t2 = new Thread(cus);

        t1.start();
        t2.start();
    }
}

class Bank {
    private int sum;
    // 同步函数可以解决这种问题
    public synchronized void add(int n) {
        sum += n;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sum=" + sum);
    }
}

class Cus implements Runnable{
    private Bank bank = new Bank();

    @Override
    public void run() {

        for (int i=0; i<3; i++) {
            bank.add(100);
        }
    }
}


