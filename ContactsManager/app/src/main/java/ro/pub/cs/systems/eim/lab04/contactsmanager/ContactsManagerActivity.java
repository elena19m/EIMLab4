package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {
    private EditText nameEditText = null;
    private EditText phoneNumberEditText = null;
    private EditText emailEditText = null;
    private EditText addressEditText = null;
    private EditText jobTitleEditText = null;
    private EditText companyEditText = null;
    private EditText websiteEditText = null;
    private EditText imEditText = null;

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button1) {
                // show
                Button show = (Button) findViewById(R.id.button1);
                if (show.getText().toString().contains("Show")) {
                    show.setText("Hide Additional Info");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.layout_hide);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    show.setText("SHow Additional Info");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.layout_hide);
                    layout.setVisibility(View.INVISIBLE);
                }
            } else if (v.getId() == R.id.button2) {
                // save
                String name = nameEditText.getText().toString();
                String phone = phoneNumberEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String jobTitle = jobTitleEditText.getText().toString();
                String company = companyEditText.getText().toString();
                String website = websiteEditText.getText().toString();
                String im = imEditText.getText().toString();
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            } else if (v.getId() == R.id.button3) {
                //cancel
                finish();
            }

        }
    }

    private ButtonListener buttonListener = new ButtonListener();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        Button show = (Button) findViewById(R.id.button1);
        Button save = (Button) findViewById(R.id.button2);
        Button cancel = (Button) findViewById(R.id.button3);

        nameEditText = (EditText) findViewById(R.id.editText1);
        phoneNumberEditText = (EditText) findViewById(R.id.editText2);
        emailEditText = (EditText) findViewById(R.id.editText3);
        addressEditText = (EditText) findViewById(R.id.editText4);
        jobTitleEditText = (EditText) findViewById(R.id.editText5);
        companyEditText = (EditText) findViewById(R.id.editText6);
        websiteEditText = (EditText) findViewById(R.id.editText7);
        imEditText = (EditText) findViewById(R.id.editText8);

        show.setOnClickListener(buttonListener);
        save.setOnClickListener(buttonListener);
        cancel.setOnClickListener(buttonListener);
    }
}
