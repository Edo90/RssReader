package com.example.android.rssreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.rssreader.dto.FeedDto;

public class NewFeed extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.rsslistsql.REPLY";

    private EditText mNameView;
    private EditText mLinkView;
    private ImageButton ibSaveRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);

        mNameView = findViewById(R.id.etName);
        mLinkView = findViewById(R.id.etLink);
        ibSaveRss = findViewById(R.id.ibSaveRss);

        ibSaveRss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent(getApplicationContext(),MainActivity.class);
                if(TextUtils.isEmpty(mLinkView.getText()) || TextUtils.isEmpty(mNameView.getText())){
                    setResult(RESULT_CANCELED,replyIntent);
                }else{
                    String name, link;
                    name = mNameView.getText().toString();
                    link = mLinkView.getText().toString();

                    FeedDto feedDto = new FeedDto(name,link);
                    replyIntent.putExtra(EXTRA_REPLY,feedDto);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "klk wawawa", Toast.LENGTH_SHORT).show();
    }
}
