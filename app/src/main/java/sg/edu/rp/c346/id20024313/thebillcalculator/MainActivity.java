package sg.edu.rp.c346.id20024313.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    //Step 1: Declare the field variables
    TextView AmountHeader;
    EditText AmountInput;
    TextView NumOfPaxHeader;
    EditText NumOfPaxInput;
    TextView DiscountHeader;
    EditText DiscountInput;
    RadioGroup PaymentOptions;
    Button Split;
    Button Reset;
    TextView TotalBill;
    TextView EachPays;
    ToggleButton SVS_Options;
    ToggleButton GST_Options;
    RadioButton CashOption;
    RadioButton PaynowOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Link the field variables to UI components in layout
        AmountHeader = findViewById(R.id.AmountHeader);
        AmountInput = findViewById(R.id.AmountInput);
        NumOfPaxHeader = findViewById(R.id.NumOfPaxHeader);
        NumOfPaxInput = findViewById(R.id.NumOfPaxInput);
        DiscountHeader = findViewById(R.id.DiscountHeader);
        DiscountInput = findViewById(R.id.discountInput);
        PaymentOptions = findViewById(R.id.PaymentOptions);
        Split = findViewById(R.id.Split);
        Reset = findViewById(R.id.Reset);
        TotalBill = findViewById(R.id.TotalBill);
        EachPays = findViewById(R.id.EachPays);
        SVS_Options = findViewById(R.id.SVS_options);
        GST_Options = findViewById(R.id.GST_options);
        CashOption = findViewById(R.id.CashOption);
        PaynowOption = findViewById(R.id.PaynowOption);

        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = 0.0;
                if(AmountInput.getText().toString().trim().length()!=0
                        || NumOfPaxInput.getText().toString().trim().length()!=0){
                    amount= Double.parseDouble(AmountInput.getText().toString());
                    double multiplier= 1.00;
                    if(SVS_Options.isChecked() && !GST_Options.isChecked()){
                        multiplier=1.1;
                    }else if(!SVS_Options.isChecked() && GST_Options.isChecked()) {
                        multiplier = 1.07;
                    }
                    else if(SVS_Options.isChecked() && GST_Options.isChecked()){
                        multiplier=1.177;
                    }
                    amount= amount*multiplier;
                    if(DiscountInput.getText().toString().trim().length()!=0) {
                        double discountValue = amount *
                                (Double.parseDouble(DiscountInput.getText().toString()) / 100);
                        amount = amount - discountValue;
                    }
                        TotalBill.setText(String.format("Total Bill: $%.2f",amount));

                    }
                    if(PaymentOptions.getCheckedRadioButtonId()==R.id.CashOption){

                        EachPays.setText(String.format("Each Pays: $%.2f in cash",amount/
                                Integer.parseInt(NumOfPaxInput.getText().toString())));
                    }else{
                        EachPays.setText(String.format("Each Pays: $%.2f via PayNow to 912345678",amount/
                                Integer.parseInt(NumOfPaxInput.getText().toString())));
                }
            }
        });

    }}
