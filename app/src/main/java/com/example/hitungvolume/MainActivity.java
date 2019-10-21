package com.example.hitungvolume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtWidth, edtHeight, edtLenght;
    Button btnCalculate;
    TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLenght = findViewById(R.id.edt_lenght);
        btnCalculate = findViewById(R.id.btn_hitung);
        tvResult = findViewById(R.id.tv_hasil);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hitung) {
            String inputLength = edtLenght.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLenght.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                edtLenght.setError("Field ini harus berupa nomor yang valid");
            }
            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomor yang valid");
            }
            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomor yang valid");
            }
            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length*width*height;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    Double toDouble(String str) {
        try {
            return  Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
