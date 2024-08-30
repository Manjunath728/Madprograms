package com.example.madlabprograms;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

public class OpenLocalPDF {

    private static final String TAG = OpenLocalPDF.class.getSimpleName();

    private final WeakReference<Context> contextWeakReference;
    private final String fileName;

    public OpenLocalPDF(Context context, String fileName) {
        this.contextWeakReference = new WeakReference<>(context);
        this.fileName = fileName.endsWith(".pdf") ? fileName : fileName + ".pdf";
    }

    public void execute() {
        Context context = contextWeakReference.get();
        if (context != null) {
            new CopyFileAsyncTask().execute();
        }
    }

    private class CopyFileAsyncTask extends AsyncTask<Void, Void, Uri> {

        @Override
        protected Uri doInBackground(Void... voids) {
            Context context = contextWeakReference.get();
            if (context == null) return null;

            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
            try (InputStream in = context.getAssets().open(fileName);
                 OutputStream out = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
            } catch (IOException e) {
                Log.e(TAG, "Error copying file", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Uri fileUri) {
            super.onPostExecute(fileUri);

            Context context = contextWeakReference.get();
            if (context == null || fileUri == null) return;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(fileUri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(intent);
        }
    }
}
