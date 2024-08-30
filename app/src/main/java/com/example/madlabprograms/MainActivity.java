package com.example.madlabprograms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.madlabprograms.OpenLocalPDF;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button lab1,pdf1;
    Button lab2,pdf2;
    Button lab3,pdf3;
    Button lab4,pdf4;
    Button lab5,pdf5;
    Button lab6,pdf6;
    private static final String FILE_NAME = "lab1.pdf";
    private PdfRenderer mPdfRenderer;
    private PdfRenderer.Page mPdfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and set click listeners
        lab1 = findViewById(R.id.lab1);
        lab2 = findViewById(R.id.lab2);
        lab3 = findViewById(R.id.lab3);
        lab4 = findViewById(R.id.lab4);
        lab5 = findViewById(R.id.lab5);
        lab6 = findViewById(R.id.lab6);

        pdf1 = findViewById(R.id.pdf1);
        pdf2 = findViewById(R.id.pdf2);
        pdf3 = findViewById(R.id.pdf3);
        pdf4 = findViewById(R.id.pdf4);
        pdf5 = findViewById(R.id.pdf5);
        pdf6 = findViewById(R.id.pdf6);


        lab1.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Lab1.class);
            startActivity(intent);
        });

        lab2.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Lab2.class);
            startActivity(intent);
        });

        lab3.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Lab3.class);
            startActivity(intent);
        });

        lab4.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Lab4.class);
            startActivity(intent);
        });

        lab5.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Lab5.class);
            startActivity(intent);
        });

        lab6.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), Lab6.class);
            startActivity(intent);
        });

        pdf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to choose a PDF file from storage
                new OpenLocalPDF(v.getContext(), "lab1").execute();
            }
        });
        pdf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to choose a PDF file from storage
                new OpenLocalPDF(v.getContext(), "lab2").execute();
            }
        });
        pdf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to choose a PDF file from storage
                new OpenLocalPDF(v.getContext(), "lab3").execute();
            }
        });
        pdf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to choose a PDF file from storage
                new OpenLocalPDF(v.getContext(), "lab4").execute();
            }
        });
        pdf5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to choose a PDF file from storage
                new OpenLocalPDF(v.getContext(), "lab5").execute();
            }
        });
        pdf6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to choose a PDF file from storage
                new OpenLocalPDF(v.getContext(), "lab6").execute();
            }
        });



    }


    private void CopyReadPDFFromAssets()
    {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "lab1.pdf");
        try
        {
            in = assetManager.open("lab1.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyPdfFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("exception", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/lab1.pdf"),
                "application/pdf");

        startActivity(intent);
    }

    private void copyPdfFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }


}
