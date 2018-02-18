package OneTread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;


public class DGListFromFile {

private String pathFile;
private ArrayList<GoalDownload> listFiles=new ArrayList<GoalDownload>();
private LinkedList<GoalDownload> linkedListFiles=new LinkedList<GoalDownload>();
private ConcurrentLinkedQueue<GoalDownload> listFilesS=new ConcurrentLinkedQueue<GoalDownload>();

DGListFromFile(String pathFile){
	this.pathFile=pathFile;
}
	


public ConcurrentLinkedQueue<GoalDownload> getListFiles(){

try(BufferedReader br=new BufferedReader(new FileReader(pathFile)))	{
String text;	
while((text=br.readLine())!=null){
String[] goalString=text.replace("\n", "").split(" ");
listFilesS.add(new GoalDownload(goalString[0], goalString[1]));

}
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return listFilesS;
}

	
	
}
	

