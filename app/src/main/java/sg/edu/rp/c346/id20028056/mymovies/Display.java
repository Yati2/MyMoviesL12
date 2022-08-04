package sg.edu.rp.c346.id20028056.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {

                LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog= inflater.inflate(R.layout.activity_edit,null);

                AlertDialog.Builder myBuilder= new AlertDialog.Builder(Display.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Demo 3-Text Input Dialog");



                //processes
                EditText etID=viewDialog.findViewById(R.id.etID);
                EditText etGenre=viewDialog.findViewById(R.id.etGenre);
                Spinner spinner1=viewDialog.findViewById(R.id.spinner);
                EditText etTitle=viewDialog.findViewById(R.id.etTitle);
                EditText etYear=viewDialog.findViewById(R.id.etYear);

                Movie movie = alMovie.get(position);

                etID.setEnabled(false);
                etTitle.setText(movie.getTitle());
                etGenre.setText(movie.getGenre());
                etYear.setText(movie.getYear()+"");
                etID.setText(movie.get_id()+"");

                int count=spinner1.getCount();
                String realRating=movie.getRating();
                for(int a=0;a<count;a++)
                {
                    String rating=(String)spinner1.getItemAtPosition(a);
                    if(rating.equalsIgnoreCase(realRating))
                    {
                        spinner1.setSelection(a);
                    }
                }
                myBuilder.setNeutralButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder myBuilder= new AlertDialog.Builder(Display.this);

                        myBuilder.setTitle("Danger");
                        myBuilder.setMessage("Are you sure you want to discard the changes");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Do not discard",null);
                        myBuilder.setNegativeButton("Discard",null);
                        AlertDialog myDialog=myBuilder.create();
                        myDialog.show();
                    }
                });

                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder myBuilder= new AlertDialog.Builder(Display.this);

                        myBuilder.setTitle("Danger");
                        myBuilder.setMessage("Are you sure you want to delete the movie");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Cancle",null);
                        myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper db=new DBHelper(Display.this);
                                db.deleteMovie(movie.get_id());
                                load();
                            }
                        });

                        AlertDialog myDialog=myBuilder.create();
                        myDialog.show();


                    }
                });

                myBuilder.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        movie.updateDetails(etTitle.getText().toString(),etGenre.getText().toString(),
                                Integer.parseInt(etYear.getText().toString()),spinner1.getSelectedItem().toString());
                        db.updateMovie(movie);
                        db.close();
                         load();
                    }
                });
                AlertDialog myDialog= myBuilder.create();
                myDialog.show();

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

