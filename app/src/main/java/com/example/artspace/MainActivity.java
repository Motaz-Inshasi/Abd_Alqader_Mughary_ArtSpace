package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "current_index";

    private final int[] images = {
            R.drawable.art1, R.drawable.art2, R.drawable.art3,
            R.drawable.art4, R.drawable.art5, R.drawable.art6
    };
    private final String[] titles = {
            "Mona Lisa", "The Starry Night", "The Scream",
            "Girl with a Pearl Earring", "The Great Wave off Kanagawa", "The Birth of Venus"
    };
    private final String[] artists = {
            "Leonardo da Vinci", "Vincent van Gogh", "Edvard Munch",
            "Johannes Vermeer", "Katsushika Hokusai", "Sandro Botticelli"
    };
    private final String[] years = {
            "1503", "1889", "1893", "1665", "1831", "1486"
    };
    private final String[] urls = {
            "https://en.wikipedia.org/wiki/Mona_Lisa",
            "https://en.wikipedia.org/wiki/The_Starry_Night",
            "https://en.wikipedia.org/wiki/The_Scream",
            "https://en.wikipedia.org/wiki/Girl_with_a_Pearl_Earring",
            "https://en.wikipedia.org/wiki/The_Great_Wave_off_Kanagawa",
            "https://en.wikipedia.org/wiki/The_Birth_of_Venus"
    };

    private int currentIndex = 0;

    private TextView tvCounter, tvTitle, tvArtistYear;
    private ImageView imgArtwork;
    private Button btnPrevious, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtistYear);
        imgArtwork = findViewById(R.id.imgArtwork);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        btnNext.setOnClickListener(v -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                updateArtwork();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtwork();
            }
        });

        imgArtwork.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("title", titles[currentIndex]);
            intent.putExtra("artist", artists[currentIndex]);
            intent.putExtra("year", years[currentIndex]);
            intent.putExtra("url", urls[currentIndex]);
            startActivity(intent);
        });

        updateArtwork();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }

    private void updateArtwork() {
        imgArtwork.setImageResource(images[currentIndex]);
        tvTitle.setText(titles[currentIndex]);
        tvArtistYear.setText(artists[currentIndex] + " (" + years[currentIndex] + ")");
        tvCounter.setText((currentIndex + 1) + "/" + images.length);

        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < images.length - 1);
    }
}
