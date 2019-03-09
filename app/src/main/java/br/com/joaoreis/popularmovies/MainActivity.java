package br.com.joaoreis.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int N_COLUMNS = 2;
    RecyclerView recyclerView;
    MoviePosterAdapter moviePosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }

    private void setupViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, N_COLUMNS);
        moviePosterAdapter = new MoviePosterAdapter();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviePosterAdapter);

    }
}
