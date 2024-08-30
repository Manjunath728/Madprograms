package com.example.madlabprograms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lab6 extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;

    Button btn;
    EditText phone, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
        btn = findViewById(R.id.lab6_button);
        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open contacts app to pick a contact
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
                pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
            }
        });

        btn.setOnClickListener(v -> {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone.getText().toString(), null, message.getText().toString(), null, null);
                Toast.makeText(this, "Send Message Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                // Retrieve the selected contact's phone number
                Cursor cursor = null;
                try {
                    Uri contactUri = data.getData();
                    cursor = getContentResolver().query(contactUri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        String phoneNumber = cursor.getString(phoneIndex);
                        // Set the selected phone number to the EditText field
                        phone.setText(phoneNumber);
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
    }
}
