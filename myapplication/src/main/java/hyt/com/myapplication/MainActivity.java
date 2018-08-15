package hyt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.Os;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(String packa : BlackList.blakList){
                    if ( OsUtils. checkApplication(getApplicationContext() ,packa)){
                        OsUtils.uninstall(getApplicationContext(),packa);
                    }
                    try {
                        Thread.sleep(3*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        
    }
}
