import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class AnalyseLine 
{
	String estival = "21ju-22se";
	String nonEstival = "23se-20ju";
	public static String analyseLine(String[] line)
	{	
		if(line[0].charAt(1)!='V'){
		String outputString=line[0].substring(1,line[0].length()-1)+analyseTxt(line[3])+line[7]+line[8].substring(1,line[8].length()-1);
		return outputString;
		}
		else return "";
	}
	public static String analyseTxt(String line){
		String returnLine = "";
		String hours = " (";
		String[] words;
		Boolean estival = false;
		words = line.split(" ");
		for(int i=0;i<words.length;i++){
			if(words[i].length()!=0){
				if(words[i].charAt(words[i].length()-1)==';')
					words[i]=words[i].substring(0,words[i].length()-1);
				if(words[i].charAt(words[i].length()-1)=='.')
					words[i]=words[i].substring(0,words[i].length()-1);
				if(words[i].charAt(words[i].length()-1)=='S')
					words[i]=words[i].substring(0,words[i].length()-1);
				else if(words[i].charAt(words[i].length()-1)=='s')
					words[i]=words[i].substring(0,words[i].length()-1);
			}
			switch(words[i]){
			case "LUNDI" : returnLine+="L";break;
			case "MARDI" : returnLine+="M";break;
			case "MERCREDI" : returnLine+="m";break;
			case "MERDREDI" : returnLine+="m";break;/// oui il y a des faute d<orthographe --'
			case "JEUDI" : returnLine+="J";break;
			case "VENDREDI" : returnLine+="V";break;
			case "SAMEDI" : returnLine+="S";break;
			case "DIMANCHE" :  returnLine+="D";break;
			case "lundi" : returnLine+="L";break;
			case "mardi" : returnLine+="M";break;
			case "mercredi" : returnLine+="m";break;
			case "merdredi" : returnLine+="m";break;
			case "jeudi" : returnLine+="J";break;
			case "vendredi" : returnLine+="V";break;
			case "samedi" : returnLine+="S";break;
			case "dimanche" :  returnLine+="D";break;
			}
			if(tryParse(words[i])!=null)
				hours += " "+words[i];
								
			
		}
		hours+=") ";
		returnLine="\""+returnLine+hours+line.substring(1,line.length());
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
			System.out.println("Unable to download file :\n'" + urlString +"'\non '"+destination.toString()+"'\n");
            //e.printStackTrace();
        }
    }
	public static Integer tryParse(String text) {
		  try {
		    return Integer.parseInt(text);
		  } catch (NumberFormatException e) {
		    return null;
		  }
		}
}
