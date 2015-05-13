package com.loxeras.softkeyenabler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;


public class MainActivity extends Activity {

    TextView tv;
    Button enable;
    String build;
    FileReader file = new FileReader();
    CardArrayRecyclerViewAdapter mCardArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enable = (Button) findViewById(R.id.enable);

        enable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    enableKey(file.read());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //tv = (TextView) findViewById(R.id.textView);
        //Getting root access.
        /*try {
            Process p = Runtime.getRuntime().exec("su");
            p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            build = file.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
       // reboot();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkKey(String build) {
        boolean result = false;

        if (build.contains("qemu.hw.mainkeys=0")) {
            tv.setText("Enabled");
            result = true;
        }
        return result;
    }

    private String enableKey(String build){
        if (build.contains("qemu.hw.mainkeys=1")) {
            build.replace("qemu.hw.mainkeys=1","qemu.hw.mainkeys=0");
        }else{
            build = build + "\n"+ "qemu.hw.mainkeys=0";
        }
        try {
            Process p = Runtime.getRuntime().exec("su");
            p.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        file.write(build);

        return build;
    }

    private void reboot(){
        try {
            Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c","reboot now"});
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}