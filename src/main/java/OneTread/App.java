package OneTread;



import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class App {


	public static void main(String[] args) throws Exception {
		int numberOfFlows = 0;
        int speedLimit = 0;
        String pathToFile;
        String nameOfDir;
		
        /*
        numberOfFlows = Integer.valueOf(args[0].replaceAll("[^0-9]", ""));
        String suffix = args[1].replaceAll("[^a-zA-Z]", "").replaceAll("-", "").toLowerCase(); 
        speedLimit = Integer.valueOf(args[1].replaceAll("[^0-9]", ""));
        switch (suffix) {
            case "k":
                speedLimit = speedLimit * 1024;
                break;
            case "m":
                speedLimit = speedLimit * 1024 * 1024;
                break;
        }
        pathToFile = String.valueOf(args[2].replaceAll("-", ""));
        nameOfDir = String.valueOf(args[3].replaceAll("-", ""));
		*/
        Scanner scn=new Scanner(System.in);
        System.out.println("Ввведите параметры");
        System.out.println("Количество потоков:");
        numberOfFlows=scn.nextInt();
        System.out.println("Ограничение скорости");
        speedLimit=scn.nextInt();
        scn.nextLine();
        System.out.println("Путь txt с сылками:");
        pathToFile=scn.nextLine();
        System.out.println("Путь папки куда сохранять файлы:");
        nameOfDir=scn.nextLine();
        scn.close();
        
        File catalog = new File(nameOfDir);
        if (!catalog.exists() || !catalog.isDirectory()) {
            catalog.mkdir();
        }
                		
		DGListFromFile text=new DGListFromFile(pathToFile);
		ConcurrentLinkedQueue<GoalDownload> queueListFiles=text.getListFiles();
		
		ExecutorService es=Executors.newFixedThreadPool(numberOfFlows);
		for(int i=0;i<numberOfFlows;i++) {
			es.submit(new ThreadDownloadFile(queueListFiles, catalog, speedLimit));
		}
		System.out.println("Главный поток завершился");

	}
}
