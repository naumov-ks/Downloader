package OneTread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DGListFromFile implements getGoalListFiles {
private String pathFile;
private ArrayList<GoalDownload> listFiles=new ArrayList<GoalDownload>();
	
DGListFromFile(String pathFile){
	this.pathFile=pathFile;
}
	
	
public ArrayList<GoalDownload> getListFiles(){

try(BufferedReader br=new BufferedReader(new FileReader(pathFile)))	{
String text;	
while((text=br.readLine())!=null){
String[] goalString=text.replace("\n", "").split(" ");
listFiles.add(new GoalDownload(goalString[0], goalString[1]));

}
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return listFiles;
}
	

	
	
}
	

