package com.didemuysal.generateqr;
// used libraries: 2
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText editTextQR;
    Button button1;
    Button button2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextQR = findViewById(R.id.editTextQR);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editTextQR.getText().toString();
                if(data.isEmpty()){
                    editTextQR.setError("Value required.");
                }else{
                    QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT ,500);
                    try {
                        Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                        imageView.setImageBitmap(qrBits);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Scanner.class));
            }
        });


    }
}