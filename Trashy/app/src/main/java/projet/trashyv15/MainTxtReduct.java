package projet.trashyv15;
import android.app.Activity;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainTxtReduct
{
    public static String redux()
    {
        App.databasePutNotification(0);
        String polygone="";
        int id=0;

        String URL = "http://www-etud.iro.umontreal.ca/~beaurevg/allo2.csv";
        try {

            final File destination = File.createTempFile("allo", "txt");

            Thread th_download = new Thread(new Runnable() {
                public void run() {
                    String URL = "http://www-etud.iro.umontreal.ca/~beaurevg/allo2.csv";
                    AnalyseLine.downloadFileFromURL(URL, destination);
                }
            });
            th_download.start();
            String line = null;
            String[] lineTab;
            String output;
            String lineTmp="";
            boolean lineComplete=true;
            int lineCount=0;
            try {
                th_download.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try
            {
                FileReader fileReader = new FileReader(destination.getAbsolutePath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                File fileW = File.createTempFile("out", "txt");
                try
                {
                    FileWriter fileWriter = new FileWriter(fileW);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    while((line = bufferedReader.readLine()) != null)
                    {
                        if(!lineComplete){
                            line=lineTmp+line;
                            lineComplete=true;
                        }
                        lineTab = line.split(",");
                        if(lineTab.length < 9){
                            lineTmp=line;
                            lineComplete = false;
                        }
                        else if(lineTab.length > 9)
                            System.out.println(lineTab[0]+lineTab[1]+lineTab[2]+lineTab[3]+lineTab[4]);
                        else{
                            if(lineCount>0){
                                AnalyseLine.analyseLine(lineTab,id++);
                                output = "";//lineTmp;
                                if(!output.equals("")){
                                    bufferedWriter.write(output);
                                    bufferedWriter.newLine();}
                            }
                            lineCount++;
                        }
                    }
                    bufferedWriter.close();
                }
                catch(IOException e)
                {
                    //e.printStackTrace();
                    System.out.println("Error writing to file '" + fileW.getAbsolutePath() + "'");
                }
                bufferedReader.close();
            }
            catch(FileNotFoundException e)
            {
                //e.printStackTrace();
                System.out.println("Unable to open file '" + destination.getAbsolutePath() + "'");
            }
            catch(IOException e)
            {
                //e.printStackTrace();
                System.out.println("Error reading file '" + destination.getAbsolutePath() + "'");
            }
            destination.delete();

        }catch(IOException e){
            System.out.println("AFHDJHFJKDSHFJKDSHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
            return polygone;
        }
        return polygone;
    }
}
