package com.mahdi.mrt.xxxmxkxkxlxl.Interfaces;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.mahdi.mrt.xxxmxkxkxlxl.R;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    public EditText firstname, lastname, uN, pW, emailAddress, phoneNum;
    String LOG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_signup);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        uN = (EditText) findViewById(R.id.username);
        pW = (EditText) findViewById(R.id.password);
        emailAddress = (EditText) findViewById(R.id.email);
        phoneNum = (EditText) findViewById(R.id.mobilePhone);
        OnButtonClick();
    }

    public void OnButtonClick() {
        Button Return = (Button) findViewById(R.id.loginReturn);
        Button createAccountBtn = (Button) findViewById(R.id.createAccountButton);
        final ImageButton username = (ImageButton) findViewById(R.id.usernameHelpButton);
        ImageButton password = (ImageButton) findViewById(R.id.passwordHelpButton);

        //Upon being pressed, user is taken back to the login page
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        //Shows a dialog box that informs the user to enter an initial username
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);
                dialog.setMessage("1. باید بیش از 8 حرف باشد")
                        .setCancelable(false)
                        .setNegativeButton("باشه", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog box = dialog.create();
                box.setTitle("USERNAME CONDITIONS");
                box.show();
            }
        });

        //Shows a dialog box that informs the user to enter an initial password
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);
                dialog.setMessage("1.باید بیش از 8 حرف باشد ")
                        .setCancelable(false)
                        .setNegativeButton("باشه", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog box = dialog.create();
                box.setTitle("PASSWORD CONDITIONS");
                box.show();
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap postData = new HashMap();
                postData.put("fName", firstname.getText().toString());
                postData.put("lName", lastname.getText().toString());
                postData.put("pNumber", phoneNum.getText().toString());
                postData.put("username", uN.getText().toString());
                postData.put("password", pW.getText().toString());
                postData.put("email", emailAddress.getText().toString());
                PostResponseAsyncTask taskInsert = new PostResponseAsyncTask(SignUpActivity.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        if (s.contains("success")) {
                            Log.d(LOG, s);
                            Toast.makeText(SignUpActivity.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        //If the username (or password) has under 8 characters, or the phone number length is less than 11, then an error box presents itself
                        else if (s.contains("failed") || uN.getText().toString().length() < 8 || pW.getText().toString().length() < 8 || phoneNum.getText().toString().length() != 11) {
                            AlertDialog.Builder dialogBox = new AlertDialog.Builder(SignUpActivity.this);
                            dialogBox.setMessage("خطا..."+"\n1.   تکراری است username"+"\n2. کمتر از 8 حرف می باشد username و password"
                                    + "\n3. شماره تلفن همراه اشتباه است")
                                    .setCancelable(false)
                                    .setNegativeButton("باشه", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();//closes the dialog box
                                        }
                                    });
                            AlertDialog dialog = dialogBox.create();
                            dialog.setTitle("خطا!");
                            dialog.show();
                        }

                    }
                });
                taskInsert.execute("http://mrtaskhim.ir/bazar/register.php/");
            }
        });
    }
}

