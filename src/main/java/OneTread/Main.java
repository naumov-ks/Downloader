package OneTread;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.google.common.util.concurrent.RateLimiter;
/*

public class Main {

public static void main(String[] args) throws Exception {
		
		InputStream in = null;
		FileOutputStream out=null;
		String ads=new String("xyi");
		System.out.println("Введите URL:");
		Scanner sc=new Scanner(System.in);
        ads=sc.nextLine();
		URL url= new URL(ads);
        URLConnection yc = url.openConnection();
        int size=yc.getContentLength();
        System.out.println(size);               
        final RateLimiter rateLimiter = RateLimiter.create(10000.0);
        
        try{
        	long startTime=System.currentTimeMillis();
        	in=yc.getInputStream();
        	int buffersize=in.available();
        	System.out.println("Размер буффера "+buffersize);
        	byte[] buffer=new byte[buffersize];
        	out=new FileOutputStream("copy2.jpg");
			int a;
			while((a=in.read(buffer))!=-1){
				rateLimiter.acquire(buffer.length);
				System.out.println(a);
				out.write(buffer, 0, a);
			}
			long estimatedTime=System.currentTimeMillis()-startTime;
		       System.out.println("Время скачивания:"+estimatedTime/1000+" сек");
		}finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
       
        
    }

}
*/