package Synchronization;

import java.util.concurrent.*;

public class Synchronization {
    private static Account account = new Account();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // Create and launch 100 threads
        for(int i=0;i<100;i++){
            executor.execute(new AddAPennyTask());
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated() ) {
        }

        System.out.println("What is balance? " + account.getBalance());
    }
    private static class AddAPennyTask implements Runnable{
        public void run() {
            // -- synchronize the account Object based on entering a block of statements
            //synchronized(account) {
                account.deposit(1);
            //}
        }
    }

    private static class Account{
        private int balance = 0;
        public int getBalance() {
            return balance;
        }
        // -- synchronize the this Object based on entering a method
        public /*synchronized*/ void deposit(int amount) {
            int newBalance = balance + amount;
            try {
                Thread.sleep(5);
            }
            catch (InterruptedException ex) {
            }
            balance = newBalance;
        }
    }
}