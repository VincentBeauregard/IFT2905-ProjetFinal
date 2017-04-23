package projet.trashyv15;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class AnalyseLine {

	static String estivale = " 21JU 22SE";
	static String nonestivale = "23SE 20JU";

	public static String analyseLine(String[] line) {
		if (line[0].charAt(1) != 'V') {
			String outputString = line[0].substring(1, line[0].length() - 1) + analyseTxt(line[3], "" + line[0].charAt(1));
			System.out.println(outputString);
			return outputString;
		} else return "";
	}

	public static String analyseTxt(String line,String type){

		boolean vert=false;
		if(type.equals("R")) vert=true;
		boolean estivaleBool = false;
		String returnLine = "\"(";
		String hours = "";
		String date = "";
		String lieu = "";
		String[] words;
		words = line.split(" ");

		for(int i=0;i<words.length;i++){
			if(words[i].length()!=0){
				if(words[i].charAt(words[i].length()-1)==';')
					words[i]=words[i].substring(0,words[i].length()-1);}
			if(words[i].equals("1er")) words[i]="1";}

		for(int i=0;i<words.length;i++){
			hours="";
			date="";
			lieu="";

			if(words[i].length()!=0){
				if(words[i].charAt(words[i].length()-1)==';')
					words[i]=words[i].substring(0,words[i].length()-1);
				if(words[i].charAt(words[i].length()-1)=='.'){
					if(estivaleBool){date+=nonestivale;estivaleBool=false;}
					words[i]=words[i].substring(0,words[i].length()-1);}
				if(words[i].charAt(words[i].length()-1)==')')
					words[i]=words[i].substring(0,words[i].length()-1);
				if(words[i].charAt(0)=='(')
					words[i]=words[i].substring(1,words[i].length());
				if(words[i].charAt(words[i].length()-1)=='S')
					words[i]=words[i].substring(0,words[i].length()-1);
				else if(words[i].charAt(words[i].length()-1)=='s')
					words[i]=words[i].substring(0,words[i].length()-1);
			}
			switch(words[i]){
				case "LUNDI" : returnLine+=" L ";break;
				case "MARDI" : returnLine+=" M ";break;
				case "MERCREDI" : returnLine+=" m ";break;
				case "MERDREDI" : returnLine+=" m ";break;/// oui il y a des faute d<orthographe --'
				case "JEUDI" : returnLine+=" J ";break;
				case "VENDREDI" : returnLine+=" V ";break;
				case "SAMEDI" : returnLine+=" S ";break;
				case "DIMANCHE" :  returnLine+=" D ";break;
				case "lundi" : returnLine+=" L ";break;
				case "mardi" : returnLine+=" M ";break;
				case "mercredi" : returnLine+=" m ";break;
				case "merdredi" : returnLine+=" m ";break;
				case "jeudi" : returnLine+=" J ";break;
				case "vendredi" : returnLine+=" V ";break;
				case "samedi" : returnLine+=" S ";break;
				case "dimanche" :  returnLine+=" D ";break;
			}
			if(words[i].equals("31octobre"))date+="-31OC";//cas particulier
			if(words[i].equals("estivale")){ date+=estivale;estivaleBool=true;}
			if(words[i].equals("trottoir")) lieu+=" trottoir";
			if(words[i].equals("rue")){ lieu+=" rue";
				if(words[i+2].equals("ruelle)"))lieu+="/ruelle";
			}
			if(words[i].equals("ruelle")&&!words[i-2].equals("rue"))lieu+=" ruelle";
			if(words[i].equals("fin"))
			{
				if(isMonth(words[i+2]))
					date+=" "+30 + parseMonth(words[i+2]);
				else if(isMonth(words[i+2].substring(0,words[i+2].length()-1)))
					date+=" "+30 + parseMonth(words[i+2]);
			}
			if(words[i].equals("début"))
			{
				if(isMonth(words[i+2]))
					date+=" "+1 + parseMonth(words[i+2]);
				else if(isMonth(words[i+2].substring(0,words[i+2].length()-1)))
					date+=" "+1 + parseMonth(words[i+2]);
			}
			if(tryParse(words[i])!=null)
				if(words[i-1].equals("et") && isMonth(words[i+1]))
					date+=""+words[i] + parseMonth(words[i+1])+"-";
				else if(words[i-1].equals("au") && isMonth(words[i+1]))
					date+="_"+words[i] + parseMonth(words[i+1])+"-";
				else if(words[i+1].equals("et") && isMonth(words[i+3]))
					date+=""+words[i] + parseMonth(words[i+3])+"-";
				else if(isMonth(words[i+1]))
					date+=""+words[i] + parseMonth(words[i+1])+"-";
				else
				if(Integer.parseInt(words[i])<40)
					if(vert&&words[i+1]!=null){
						if(tryParse(words[i+1])!=null)
							if(isMonth(words[i-1]))
								date+=""+words[i]+"-";
							else date+=words[i]+"-";
						else if(words[i+1].length()>0&&tryParse(words[i+1].substring(0,words[i+1].length()-1))!=null)
							date+=words[i]+"-";
						else if(tryParse(words[i-1])!=null)date+=words[i]+"-";}
					else if((words[i-1].equals("h")&&words[i].equals("30")))
						hours+=".5";
					else if(words[i-1].equals("et"))
						hours += "-"+words[i];
					else if(words[i+1].equals("h")
							||words[i+1].equals("h.")
							||words[i-1].equals("avant")
							||words[i+1].equals("h;"))hours += " "+words[i];

			if(date.equals("1JA-"))date="";
			returnLine=returnLine+date + hours+lieu;
		}
		if(date.length()<5)date="";
		lieu+=") ";
		returnLine=returnLine+") ";
		return returnLine;
	}

	private static boolean isMonth(String word) {

		if (word.length() != 0) {
			if (word.charAt(word.length() - 1) == '\"')
				word = word.substring(0, word.length() - 1);
			if (word.charAt(word.length() - 1) == ';')
				word = word.substring(0, word.length() - 1);
			if (word.charAt(word.length() - 1) == '.')
				word = word.substring(0, word.length() - 1);
			if (word.charAt(word.length() - 1) == 'S')
				word = word.substring(0, word.length() - 1);
			else if (word.charAt(word.length() - 1) == 's')
				word = word.substring(0, word.length() - 1);
		}

		word = word.toUpperCase();
		return word.equals("JANVIER") ||
				word.equals("FEVRIER") ||
				word.equals("MARS") ||
				word.equals("AVRIL") ||
				word.equals("MAI") ||
				word.equals("JUIN") ||
				word.equals("JUILLET") ||
				word.equals("AOUT") ||
				word.equals("SEPTEMBRE") ||
				word.equals("OCTOBRE") ||
				word.equals("NOVEMBRE") ||
				word.equals("DECEMBRE");
	}

	private static String parseMonth(String word) {

		if (word.length() != 0) {
			if (word.charAt(word.length() - 1) == '\"')
				word = word.substring(0, word.length() - 1);
			if (word.charAt(word.length() - 1) == ';')
				word = word.substring(0, word.length() - 1);
			if (word.charAt(word.length() - 1) == '.')
				word = word.substring(0, word.length() - 1);
			if (word.charAt(word.length() - 1) == 'S')
				word = word.substring(0, word.length() - 1);
			else if (word.charAt(word.length() - 1) == 's')
				word = word.substring(0, word.length() - 1);
		}

		word = word.toUpperCase();
		if (word.length() < 2)
			return "??";

		switch (word) {
			case "JUILLET": return "JL";
			default:        return word.substring(0, 2);
		}
	}

	@SuppressWarnings("unused")
	public static void downloadFileFromURL(Activity context, String _url, File _name) {

		try {
			URL u = new URL(_url);
			InputStream is = u.openStream();

			DataInputStream dis = new DataInputStream(is);

			byte[] buffer = new byte[1024];
			int length;

			FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + _name));
			while ((length = dis.read(buffer)) > 0) {
				System.out.println();
				fos.write(buffer, 0, length);
			}

		} catch (MalformedURLException mue) {
			Log.e("SYNC getUpdate", "malformed url error", mue);
		} catch (IOException ioe) {
			Log.e("SYNC getUpdate", "io error", ioe);
		} catch (SecurityException se) {
			Log.e("SYNC getUpdate", "security error", se);
		}
		System.out.println("lol");
	}

	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}


