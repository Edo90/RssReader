package com.example.android.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.rssreader.dto.FeedDto;

public class NewFeed extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.rsslistsql.REPLY";

    private EditText mNameView;
    private EditText mLinkView;
    private Button bSaveRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);

        mNameView = findViewById(R.id.etName);
        mLinkView = findViewById(R.id.etLink);
        bSaveRss = findViewById(R.id.bSaveRss);

        bSaveRss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent(getApplicationContext(), MainActivity.class);
                String link = setHttpCorrectForm(mLinkView.getText().toString());
                String name = mNameView.getText().toString();
                if (TextUtils.isEmpty(mLinkView.getText()) || TextUtils.isEmpty(mNameView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                    Toast.makeText(NewFeed.this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show();
                } else {
                    FeedDto feedDto = new FeedDto(name, link);
                    replyIntent.putExtra(EXTRA_REPLY, feedDto);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }

            }
        });
    }

    private String setHttpCorrectForm(String link) {

        if (!link.startsWith("http://") || !link.startsWith("https://")) {
            return "http://" + link.toLowerCase();
        } else {
            return link;
        }

    }

//    private class ValidateLink extends AsyncTask<String,Void,Boolean> {
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            try {
//                HttpURLConnection conn = (HttpURLConnection) new URL(strings[0]).openConnection();
//                conn.setUseCaches(false);
//                //  conn.connect();
//                int status = conn.getResponseCode();
//                conn.disconnect();
//                if(status == 200){
//                    return true;
//                }else {
//                    return false;
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return false;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            } catch (Exception e){
//                e.printStackTrace();
//                return false;
//            }
//        }
//
//    }
}
