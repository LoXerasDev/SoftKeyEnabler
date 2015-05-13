package com.loxeras.softkeyenabler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by dario.duff on 13.05.2015.
 */
public class FileReader {

    public static String read () throws Exception {
        //Liest das ausgewählte file aus
        File fl = new File("/system/build.prop/");
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    public void write(String message){
        String data=message.toString();
        FileOutputStream fos;
        //Try read file from
        try {
            File myFile = new File("/sdcard/","build.prop");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();
            //

            //Toast.makeText(getApplicationContext(),filename +"saved",Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    private static String convertStreamToString(InputStream is) throws Exception {
        //Konvertiert den Input stream aus dem File zu einem String und übergiebt diesen
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}
