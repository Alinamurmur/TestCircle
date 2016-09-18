package com.example.alink.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends Activity {

   String nameUser;
    EditText editName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editName = (EditText)findViewById(R.id.editText);

        editName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()== KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    if (editName.getText().toString().equals("")){
                        Toast.makeText(Main2Activity.this,"Нужно ввести имя",Toast.LENGTH_LONG).show();
                    }
                    else {
                        nameUser = editName.getText().toString();
                        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                        intent.putExtra("nameUser",nameUser);
                        startActivity(intent);
                    }

                    return true;
                }
                return false;
            }
        });


    }

    public void StartN(View view){
        if (editName.getText().toString().equals("")){
            Toast.makeText(this,"Нужно ввести имя",Toast.LENGTH_LONG).show();
        }
        else {
            nameUser = editName.getText().toString();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

}
