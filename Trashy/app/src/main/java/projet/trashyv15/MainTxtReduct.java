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
        String polygone="";
        int id=0;
        String fileNameR = Environment.getExternalStorageDirectory() + "/" + "allo.txt" ;
        String URL = "http://www-etud.iro.umontreal.ca/~beaurevg/Original.txt";
        File destination = new File(fileNameR);

        Thread th_download = new Thread(new Runnable() {
            public void run() {
                String fileNameR = "allo.txt";
                String URL = "http://www-etud.iro.umontreal.ca/~beaurevg/Original.txt";
                File destination = new File(fileNameR);
                AnalyseLine.downloadFileFromURL(URL, destination);
            }
        });
        th_download.start();
        String line = null;
        String[] lineTab;
        String output;
        String fileNameW = Environment.getExternalStorageDirectory() + "/" + "out.txt";
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
            FileReader fileReader = new FileReader(fileNameR);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try
            {
                FileWriter fileWriter = new FileWriter(fileNameW);
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
                            lineTmp=AnalyseLine.analyseLine(lineTab,id++);
                            polygone=lineTmp;
                            output = lineTmp;
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
                System.out.println("Error writing to file '" + fileNameW + "'");
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            //e.printStackTrace();
            System.out.println("Unable to open file '" + fileNameR + "'");
        }
        catch(IOException e)
        {
            //e.printStackTrace();
            System.out.println("Error reading file '" + fileNameR + "'");
        }
        destination.delete();
        return polygone;
    }
}
