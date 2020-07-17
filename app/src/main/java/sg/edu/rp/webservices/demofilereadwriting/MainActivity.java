package sg.edu.rp.webservices.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnRead,btnWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);

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


    }
}
