package com.ps_bn.visitcard.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ps_bn.visitcard.R;

public class AboutActivity extends AppCompatActivity {
    private EditText editText;
    private static final String MSG_KEY = "message";
    private Button submitBtn;
    private ImageView vkLogo;
    private ImageView tgLogo;
    private ImageView instLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        editText = findViewById(R.id.msg_edit);
        submitBtn = findViewById(R.id.submit_message_btn);
        vkLogo = findViewById(R.id.vk_logo);
        tgLogo = findViewById(R.id.tg_logo);
        instLogo = findViewById(R.id.inst_logo);
        if (savedInstanceState != null) {
            editText.setText(savedInstanceState.getString(MSG_KEY));
        }

        //        There was used implicit intent
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                if (message.equals("")) {
                    Toast.makeText(AboutActivity.this, "Please fill message", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    Intent chosenIntent = Intent.createChooser(intent, "What do you prefer?");
                    startActivity(chosenIntent);
                }
            }
        });

        //        There was used explicit intent
        /*submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=editText.getText().toString();
                if (message.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill message", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL,"neome.ps@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Message from visit card");
                    intent.putExtra(Intent.EXTRA_TEXT,message);
                    Intent chosenIntent = Intent.createChooser(intent, "What do you prefer?");
                    startActivity(chosenIntent);
                }
            }
        });*/

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String s = editText.getText().toString();
        if (s != null) {
            outState.putString(MSG_KEY, s);
        }
    }

    public void openSocial(View view) {
        int id = view.getId();
        String url;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (id == vkLogo.getId()) {
            url = "https://vk.com/";
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } else if (id == tgLogo.getId()) {
            url = "https://web.telegram.org/";
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } else {
            url = "https://www.instagram.com/";
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}