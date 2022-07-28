package sg.edu.rp.c346.id20028056.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnShow;
    EditText etTitle, etGenre,etYear;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert=findViewById(R.id.btnInsert);
        btnShow=findViewById(R.id.btnShow);
        etGenre =findViewById(R.id.etGenre);
        etTitle=findViewById(R.id.etTitle);
        etYear=findViewById(R.id.etYear);
        spinner=findViewById(R.id.spinner);



        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Display.class);
                startActivity(i);
            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(MainActivity.this);
                long inserted_id=db.insertSong(etTitle.getText().toString(), etGenre.getText().toString(),
                        Integer.parseInt(etYear.getText().toString()),spinner.getSelectedItem().toString());

                if(inserted_id!=-1)
                {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    clear();
                }

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        clear();
    }

    private void clear()
    {
        etGenre.setText("");
        etTitle.setText("");
        etYear.setText("");
        spinner.setSelection(0);

    }

}