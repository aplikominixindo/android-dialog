package id.inixindosurabaya.androiddialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    // mengambil class Calendar
    Calendar myCalendar = Calendar.getInstance();

    // komponen dalam layout
    private Button btnOpenAlertDialog;
    private Button btnOpenConfirmSingle;
    private Button btnOpenConfirmMultiple;
    private Button btnOpenListviewDialog;
    private Button btnOpenEditTextDialog;
    private Button btnOpenDatePickerDialog;
    private Button btnOpenTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialisasi komponen dalam layout
        btnOpenAlertDialog = findViewById(R.id.btnOpenAlertDialog);
        btnOpenConfirmSingle = findViewById(R.id.btnOpenConfirmSingle);
        btnOpenConfirmMultiple = findViewById(R.id.btnOpenConfirmMultiple);
        btnOpenListviewDialog = findViewById(R.id.btnOpenListView);
        btnOpenEditTextDialog = findViewById(R.id.btnOpenEditText);
        btnOpenDatePickerDialog = findViewById(R.id.btnOpenDatePicker);
        btnOpenTimePickerDialog = findViewById(R.id.btnOpenTimePicker);

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

        // event handling button confirm dialog multiple choice
        btnOpenConfirmMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmDialogMultiple();
            }
        });

        // event handling button list view dialog
        btnOpenListviewDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListviewDialog();
            }
        });

        // event handling button edit text dialog
        btnOpenEditTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEdittextDialog();
            }
        });

        // event handling button date picker dialog
        btnOpenDatePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog();
            }
        });

        // event handling button time picker dialog
        btnOpenTimePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePickerDialog();
            }
        });
    }

    private void openTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view,
                                          int hourOfDay,
                                          int minute) {
                        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        myCalendar.set(Calendar.MINUTE, minute);

                        String time = DateFormat.getTimeInstance(
                                DateFormat.MEDIUM).format(myCalendar.getTime());
                        Toast.makeText(getApplicationContext(),
                                "Time: " + time,
                                Toast.LENGTH_LONG).show();
                    }
                }, myCalendar.get(Calendar.HOUR_OF_DAY),
                    myCalendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void openDatePickerDialog() {
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view,
                                                  int year,
                                                  int month,
                                                  int dayOfMonth) {
                                myCalendar.set(Calendar.YEAR, year);
                                myCalendar.set(Calendar.MONTH, month);
                                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                String date = DateFormat.getDateInstance(
                                        DateFormat.MEDIUM).format(myCalendar.getTime());
                                Toast.makeText(getApplicationContext(),
                                        "Date: " + date,
                                        Toast.LENGTH_LONG).show();
                            }
                        }, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void openEdittextDialog() {
        // siapkan layout dahulu
        LinearLayout layout =
                new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // buat edit text
        final EditText editEmail = new EditText(MainActivity.this);
        editEmail.setHint("Email address");
        editEmail.setPadding(6, 6, 6, 6);
        editEmail.setInputType(
                InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        );
        editEmail.setSelection(editEmail.getText().length());

        final EditText editPassword = new EditText(MainActivity.this);
        editPassword.setHint("Your password");
        editPassword.setPadding(6, 6, 6, 6);
        editPassword.setInputType(
                InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        editPassword.setSelection(editPassword.getText().length());

        // set edittext supaya masuk kedalam layout
        layout.addView(editEmail);
        layout.addView(editPassword);

        // buat dialog
        final AlertDialog dialog = new
                AlertDialog.Builder(MainActivity.this)
                .setTitle("LOGIN")
                .setMessage("Please Login Here")
                .setView(layout)
                .setPositiveButton("LOGIN",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // validasi email
                                String email = editEmail.getText().toString();
                                if (!validEmail(email)) {
                                    Toast.makeText(getApplicationContext(),
                                            "Email tidak valid!",
                                            Toast.LENGTH_LONG).show();
                                    return;
                                }
                                // validasi password
                                String password = editPassword.getText().toString();
                                if (!validPassword(password)) {
                                    Toast.makeText(getApplicationContext(),
                                            "Password kurang dari 8 karakter!",
                                            Toast.LENGTH_LONG).show();
                                    return;
                                }

                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("INFORMASI")
                                        .setMessage("\nEmail: " + email +
                                                    "\nPassword: " + password)
                                        .setPositiveButton("OK", null)
                                        .setCancelable(false)
                                        .show();
                            }
                        })
                .setNegativeButton("CANCEL", null)
                .setCancelable(false)
                .create();
        dialog.show();
    }

    private boolean validPassword(String password) {
        if (password != null && password.length() > 7) {
            return true;
        }
        return false;
    }

    private boolean validEmail(String email) {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
                "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*" +
                "(\\.[A-Za-z]{2,})$";
        Pattern myPattern = Pattern.compile(pattern);
        Matcher matcher = myPattern.matcher(email);
        return matcher.matches();
    }

    private void openListviewDialog() {
        // ambil array
        final String[] nama = getResources()
                .getStringArray(R.array.list_view_dialog);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("DAFTAR NAMA")
                .setItems(nama,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Nama dipilih: " + nama[which],
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setPositiveButton("OK", null)
                .setNegativeButton("CANCEL", null)
                .setCancelable(false)
                .show();
    }

    private void openConfirmDialogMultiple() {
        // ambil array
        String[] multipleItems = getResources()
                .getStringArray(R.array.confirm_multiple);
        // buat status belum ada yg dicentang
        final boolean[] itemTerpilih = {
                false, false, false,
                false, false, false
        };
        // convert array menjadi list
        final List<String> multipleList =
                Arrays.asList(multipleItems);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("DAFTAR FILM")
                .setMultiChoiceItems(multipleItems,
                        itemTerpilih,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                // ubah status item dari false menjadi true
                                itemTerpilih[which] = isChecked;
                                // ambil item yg statusnya true saja
                                String myItems = multipleList.get(which);
                                // tampilkan
                                Toast.makeText(getApplicationContext(),
                                        "Index film: " + which + "\n" +
                                        "Judul Film: " + myItems,
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < itemTerpilih.length; i++){
                                    boolean cek = itemTerpilih[i];
                                    if (cek) {
                                        Toast.makeText(getApplicationContext(),
                                                "Film Pilihan: " +
                                                multipleList.get(i),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        })
                .setNegativeButton("CANCEL", null)
                .setCancelable(false)
                .show();
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
