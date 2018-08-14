package id.inixindosurabaya.androiddialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // komponen dalam layout
    private Button btnOpenAlertDialog;
    private Button btnOpenConfirmSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialisasi komponen dalam layout
        btnOpenAlertDialog = findViewById(R.id.btnOpenAlertDialog);
        btnOpenConfirmSingle = findViewById(R.id.btnOpenConfirmSingle);

        // event handling button alert dialog
        btnOpenAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // buat method untuk perintah open alert dialog
                openAlertDialog();
            }
        });

        // event handling button confirm dialog single choice
        btnOpenConfirmSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method untuk perintah open confirm dialog single
                openConfirmDialogSingle();
            }
        });
    }

    private void openConfirmDialogSingle() {
        // ambil array
        String[] singleItems = getResources()
                .getStringArray(R.array.confirm_single);
        // convert array menjadi list
        final List<String> singleList =
                Arrays.asList(singleItems);
        int item = 0;
        final int[] itemTerpilih = { 0 };

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Choose your gender: ")
                .setSingleChoiceItems(singleItems,
                        item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemTerpilih[0] = which;
                                Toast.makeText(getApplicationContext(),
                                        "Index gender: " + which,
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Gender anda : " +
                                        singleList.get(itemTerpilih[0]),
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("CANCEL", null)
                .setCancelable(false)
                .show();
    }

    private void openAlertDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Alert Dialog")
                .setMessage("Ini adalah alert dialog android")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Anda klik tombol OK!",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Anda klik tombol CANCEL!",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setNeutralButton("NEUTRAL", null)
                .setCancelable(false)
                .show();
    }
}
