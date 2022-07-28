package sg.edu.rp.c346.id20028056.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        this.parent_context =context;
        layout_id=resource;
        this.movieList =objects;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView title=rowView.findViewById(R.id.tvTitle);
        TextView year=rowView.findViewById(R.id.tvYear);
        ImageView rating=rowView.findViewById(R.id.rating);
        TextView genre=rowView.findViewById(R.id.tvGenre);


        // Obtain the Android Version information based on the position
        Movie movie = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        title.setText(movie.getTitle());
        year.setText(movie.getYear()+"");

        switch (movie.getRating())
        {
            case "G":
                rating.setImageResource(R.drawable.rating_g);
                break;
            case "PG":
                rating.setImageResource(R.drawable.rating_pg);
                break;
            case "PG13":
                rating.setImageResource(R.drawable.rating_pg13);
                break;
            case "M18":
                rating.setImageResource(R.drawable.rating_m18);
                break;
            case "R21":
                rating.setImageResource(R.drawable.rating_r21);
                break;
            case "NC16":
                rating.setImageResource(R.drawable.rating_nc16);
                break;
        }
        genre.setText(movie.getGenre());

        return rowView;
    }

}
