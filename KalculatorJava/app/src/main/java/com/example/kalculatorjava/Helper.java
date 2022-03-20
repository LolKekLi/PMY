package com.example.kalculatorjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class Helper extends AppCompatActivity {

    String sinHelp ="Синус угла ( sin   α sin α) - отношение противолежащего этому углу катета к гипотенузе";

    String cosHelp = "Косинус угла ( cos α cosα) - отношение прилежащего катета к гипотенузе";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_helper);

        Intent intent = getIntent();

        String message = intent.getStringExtra("message");

        TextView help_text = findViewById(R.id.help_text);

        if (message == "sin") {
            help_text.setText(sinHelp);
        } else {
            help_text.setText(cosHelp);
        }

        Button close_button = findViewById(R.id.close_button);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMainActivity();
            }
        });
    }

    private void OpenMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}