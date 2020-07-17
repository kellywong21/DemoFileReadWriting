package sg.edu.rp.webservices.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnRead,btnWrite;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        tv = findViewById(R.id.tv);

        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
        final File folder = new File(folderLocation);
        if (folder.exists()==false){
            boolean result = folder.mkdir();
            if (result == true){
                Log.d("File Read/Write","Folder created");
            }
        }

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                    File targetFile = new File(folderLocation,"data.txt");
                    FileWriter writer = new FileWriter(targetFile,true);
                    writer.write("Hello World" +"\n");
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Failed to write!",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                    File targetFile = new File(folderLocation, "data.txt");

                    if (targetFile.exists() == true) {
                        String data = "";
                        try {
                            FileReader reader = new FileReader(targetFile);
                            BufferedReader br = new BufferedReader(reader);
                            StringBuilder stringBuilder = new StringBuilder();

                            String line = br.readLine();
                            while (line != null){

                                data += line + "\n";
                                line = br.readLine();
                            }
                            br.close();
                            reader.close();

                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this,"Failed to read!",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        tv.setText(data);

                        Log.d("Content",data);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
