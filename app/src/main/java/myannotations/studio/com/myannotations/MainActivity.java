package myannotations.studio.com.myannotations;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private ImageView saveButton;
    private static final String NAME_FILE = "file_annotation.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.textId);
        saveButton = (ImageView) findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedText = text.getText().toString();
                saveOnFile(typedText);
                Toast.makeText(MainActivity.this, "Anotações salvas com sucesso.", Toast.LENGTH_SHORT).show();
            }
        });

        if(readFile() != null){
            text.setText( readFile() );
        }

    }

    private void saveOnFile(String typedText) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NAME_FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(typedText);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
    }

    private String readFile(){
        String result = "";

        try{
            InputStream file = openFileInput(NAME_FILE);
            if(file != null){
                //Read file
                InputStreamReader inputStreamReader = new InputStreamReader(file);
                //Buffer to read file
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //Recover files text.

                String fileLine = "";

                while ((fileLine = bufferedReader.readLine()) != null){

                    result+= fileLine;

                }
            file.close();
            }
        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
        return result;
    }
}
