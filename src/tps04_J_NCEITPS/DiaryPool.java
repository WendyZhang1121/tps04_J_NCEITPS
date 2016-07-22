package tps04_J_NCEITPS;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class DiaryPool {
	
	final int numOfThreads = 3; // Maximum number of threads allowed in pool 
	final Executor exec;
	final Diary diary;
	
	DiaryPool() {
		exec = (Executor) Executors.newFixedThreadPool(numOfThreads); 
		diary = new Diary();
	}
	
	public void doSomething1() { 
		exec.execute(new Runnable() {
		@Override 
		public void run() { 
			diary.setDay(Day.FRIDAY); 
			diary.threadSpecificTask();
			} 
		});
	}
	
	public void doSomething2() { 
		exec.execute(new Runnable() {
		@Override 
		public void run() { 
			diary.threadSpecificTask();
			} 
		});
	}
	
	public static void main(String[] args) {
		DiaryPool dp = new DiaryPool();
		dp.doSomething1(); 
		
		DiaryPool dp2 = new DiaryPool();
		dp.doSomething2(); 
		
		DiaryPool dp3 = new DiaryPool();
		dp3.doSomething2(); 
		// Thread 1, requires current day as Friday dp.doSomething2(); 
		// Thread 2, requires current day as Monday dp.doSomething2(); 
		// Thread 3, requires current day as Monday
		} 
}