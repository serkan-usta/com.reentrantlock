
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;




class ReentrantLockClass {
    
  
    private int count = 0;
    private Lock lock = new ReentrantLock(); // being without Object class
    private final Condition condition = lock.newCondition();

    //core method
    public void increase(){
        for (int i = 0 ;  i < 10000 ; i++) {
            
            count++;
        }
        
        
    }
    public void thread1Function(){
        
        lock.lock();
        System.out.println("Thread 1 is working...");
        System.out.println("Thread 1 is waiting for waking up....");
        
        try {
            condition.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(ReentrantLock.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thread 1 woke up ve go on to progress....");
        increase();
       
        
        lock.unlock();

    }
    public void thread2Function(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ReentrantLock.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner scanner = new Scanner(System.in);
        
        
        lock.lock();
        System.out.println("Thread 2 works....");
        
        increase();
        System.out.println("Press a buttom...");
        scanner.nextLine();
        condition.signal();
        System.out.println("I woke up Thread1 and am going ...");
       
        lock.unlock();
        
        
        
        
        
    }
    public void threadOver(){
        System.out.println("Count result  : " + count);
        
        
    }
}
