package OneTread;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Thrd extends Thread{
ConcurrentLinkedQueue<GoalDownload> x;
private String name;

Thrd(ConcurrentLinkedQueue<GoalDownload> x, String name){
this.x=x;
this.name=name;
}
	
	
public void run() {
	try{
		System.out.println("Старт "+name);
		boolean z=true;
		while(z==true) {
		z=saysFiles(x);
		sleep(1000);
		}
		System.out.println("Завершился "+name);
	}catch (Exception e) {
		// TODO: handle exception
	}
}
	
public boolean saysFiles(ConcurrentLinkedQueue<GoalDownload> x) {
	
	GoalDownload y=x.poll();
	if(y==null) {
		return false;
	}
	System.out.println(name+y.getFileName()+" "+y.getUrlFile());
	return true;
}


}
