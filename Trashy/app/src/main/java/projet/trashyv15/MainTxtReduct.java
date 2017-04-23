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
    public static void redux(final Activity context)
    {

        String fileNameR = Environment.getExternalStorageDirectory() + "/" + "allo.txt" ;
        String URL = "http://donnees.ville.montreal.qc.ca/dataset/2df0fa28-7a7b-46c6-912f-93b215bd201e/resource/87b2cd0c-e38b-4081-a58c-494a9590b882/download/pdo-collectes.csv";
        File destination = new File(fileNameR);

        Thread th_download = new Thread(new Runnable() {
            public void run() {
                String fileNameR = "allo.txt";
                String URL = "http://donnees.ville.montreal.qc.ca/dataset/2df0fa28-7a7b-46c6-912f-93b215bd201e/resource/87b2cd0c-e38b-4081-a58c-494a9590b882/download/pdo-collectes.csv";
                File destination = new File(fileNameR);
                AnalyseLine.downloadFileFromURL(context,URL, destination);
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
                        System.out.println("TRABRARNAK");
                    else{
                        if(lineCount>0){
                            lineTmp=AnalyseLine.analyseLine(lineTab);
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
    }
}
