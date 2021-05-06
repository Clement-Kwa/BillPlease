package sg.edu.rp.c346.id20002694.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //editable fields
    EditText editAmt;
    EditText editPax;
    EditText editDis;

    //calculate sums and reset values buttons
    Button btnSplit;
    Button btnReset;

    //toggle buttons
    ToggleButton tgSVS;
    ToggleButton tgGST;

    //radio group for cash or paynow
    RadioGroup radioGrp;

    //Result of the transaction
    TextView result1;
    TextView result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmt = findViewById(R.id. editAmt);
        editPax = findViewById(R.id. editPax);
        editDis = findViewById(R.id. editDis);
        radioGrp = findViewById(R.id. radioGrp);
        tgGST = findViewById(R.id. tgGST);
        tgSVS = findViewById(R.id. tgSVS);
        result1 = findViewById(R.id. result1);
        result2 = findViewById(R.id. result2);
        btnSplit = findViewById(R.id. btnSplit);
        btnReset = findViewById(R.id. btnReset);

        //calculate the result when split is clicked
        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //convert text entered to string
                double amountEntered = Double.parseDouble(editAmt.getText().toString().trim());
                double amountPax = Double.parseDouble(editPax.getText().toString().trim());
                double GSTCharge = 0;
                double SVSCharge = 0;


                if (amountEntered>0){

                    if (tgGST.isChecked()==true){
                        GSTCharge = 0.07;
                    }

                    //svs
                    if (tgSVS.isChecked()==true){
                        SVSCharge = 0.1;
                    }

                    double totalBill = amountEntered * (1 + GSTCharge + SVSCharge);

                    //discount
                    if(editDis.getText().toString().trim().length()>0){
                        //convert the discount
                        totalBill*=1-Double.parseDouble(editDis.getText().toString())/100;
                    }

                    String append = " in cash";
                    if(radioGrp.getCheckedRadioButtonId() == R.id.rbPayNow){
                        append=" via PayNow to 912345678";
                    }

                    //calculate everything + discount

                    result1.setText("Total Bill: $"+totalBill);

                    double totalBillSplit=0;
                    if(amountPax>1){
                        totalBillSplit=totalBill/amountPax;
                        result2.setText("Each pays: $"+totalBillSplit+append);
                    }
                    else{
                        result2.setText("Each pays: $"+totalBill+append);
                    }
                }





            }
        });
    }
}