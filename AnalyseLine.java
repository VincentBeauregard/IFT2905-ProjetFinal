import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class AnalyseLine 
{
	public static String analyseLine(String[] line)
	{	
		String outputString=line[0]+",";
		outputString+=analyseTxt(line[3])+"\""+line[7]+"\","+line[8];
		return outputString;
	}
	
	public static String analyseTxt(String line){
		String returnLine = "";
		String[] words;
		words = line.split(" ");
		for(int i=0;i<words.length;i++){
			if(words[i].length()!=0){
				if(words[i].charAt(words[i].length()-1)=='S')
					words[i]=words[i].substring(0,words[i].length()-1);
				else if(words[i].charAt(words[i].length()-1)=='.')
					words[i]=words[i].substring(0,words[i].length()-1);
			}
			switch(words[i]){
			case "LUNDI" : returnLine+="L";break;
			case "MARDI" : returnLine+="M";break;
			case "MERCREDI" : returnLine+="m";break;
			case "JEUDI" : returnLine+="J";break;
			case "VENDREDI" : returnLine+="V";break;
			case "SAMEDI" : returnLine+="S";break;
			case "DIMANCHE" :  returnLine+="D";break;
			}
		}
		returnLine="\""+returnLine+"\",";
		return returnLine;
	}
	
	public static void downloadFileFromURL(String urlString, File destination) {    
        try {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
