package sg.edu.rp.c346.id20028056.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
    Button btnFilter;
    ArrayList<Movie> alMovie;
    Spinner spinner;
    ListView lv;
    CustomAdapter aaMovie;
    ArrayAdapter<String> aaRating;
    ArrayList<String> alRating;
    DBHelper db = new DBHelper(Display.this);
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.btnFilter);

        spinner = findViewById(R.id.spinner);
        alRating = new ArrayList<String>();

        aaRating = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alRating);
        load();
        spinner.setAdapter(aaRating);
        spinner.setSelected(false);



        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String choseRating=alRating.get(spinner.getSelectedItemPosition());
                    if (choseRating.equalsIgnoreCase("NONE"))
                    {
                       load();
                    }
                    else {
                        alMovie.clear();
                        alMovie.addAll(db.getByRating(choseRating));
                        aaMovie.notifyDataSetChanged();
                        Toast.makeText(Display.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    }



            }
        });

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                alMovie.clear();
//                Log.w("info",alRating.get(i)+"");
//                Toast.makeText(Display.this, alRating.get(i),
//                        Toast.LENGTH_SHORT).show();
//                if(!alRating.get(i).equalsIgnoreCase("NONE"))
//                {
////                    alMovie.addAll(db.getSongsByYear(Integer.parseInt(alYear.get(i))));
////                    aaMovie.notifyDataSetChanged();
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Toast.makeText(Display.this, "nth is selected",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie selected = alMovie.get(position);
                Intent i = new Intent(Display.this,
                        Edit.class);
                i.putExtra("Selected", selected);
                startActivity(i);
            }
        });

    }
        private void load()
        {
            alMovie = db.getAllMovies();
            aaMovie = new CustomAdapter(this,
                    R.layout.row, alMovie);
            lv.setAdapter(aaMovie);
            loadSpinner();
        }
        private void loadSpinner ()
        {
            alRating.clear();
            alRating.addAll(db.getRating());
            Toast.makeText(Display.this, alRating.get(0) + "",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onResume() {
            super.onResume();
            load();
        }
    }

