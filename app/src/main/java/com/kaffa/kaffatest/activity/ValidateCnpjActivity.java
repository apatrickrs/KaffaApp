package com.kaffa.kaffatest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaffa.kaffatest.R;
import com.kaffa.kaffatest.helper.ValidateCNPJ;
import com.santalu.maskedittext.MaskEditText;

public class ValidateCnpjActivity extends AppCompatActivity {

    private LinearLayout linearVerify;
    private TextView textResultVerify;
    private MaskEditText fieldCNPJ;
    private Button btnNext, btnVerify, btnVerifyOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_cnpj);

        linearVerify = findViewById(R.id.linearVerify);
        textResultVerify = findViewById(R.id.textVerify);
        fieldCNPJ = findViewById(R.id.fieldCNPJ);
        btnVerify = findViewById(R.id.btnVerify);
        btnNext = findViewById(R.id.btnNext);
        btnVerifyOk = findViewById(R.id.btnOkVerify);
        linearVerify.setVisibility(View.INVISIBLE);

        configBtns();
    }

    private void configBtns() {
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVerify.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
                checkCNPJ();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextExcercise();
            }
        });

        btnVerifyOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVerify.setVisibility(View.VISIBLE);
                btnVerify.setAnimation(AnimationUtils.loadAnimation(ValidateCnpjActivity.this, R.anim.fade_in));
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setAnimation(AnimationUtils.loadAnimation(ValidateCnpjActivity.this, R.anim.fade_in));
                linearVerify.setAnimation(AnimationUtils.loadAnimation(ValidateCnpjActivity.this, R.anim.fade_out));
                linearVerify.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void checkCNPJ() {
        if (!fieldCNPJ.getRawText().toString().isEmpty() && ValidateCNPJ.isCNPJ(fieldCNPJ.getRawText().toString())) {
            configDialog("is valid !");
        } else {
            configDialog("is not valid !");
        }
    }

    private void configDialog(String text) {
        textResultVerify.setText("The CNPJ: \n" + fieldCNPJ.getText().toString() + '\n'
                + fieldCNPJ.getRawText().toString() + "\n " + text);
        linearVerify.setVisibility(View.VISIBLE);
        linearVerify.setAnimation(AnimationUtils.loadAnimation(ValidateCnpjActivity.this, R.anim.fade_in_result));
    }

    private void nextExcercise() {
        ActivityCompat.startActivity(this, new Intent(this, TodoListActivity.class),
                ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle());
    }
}