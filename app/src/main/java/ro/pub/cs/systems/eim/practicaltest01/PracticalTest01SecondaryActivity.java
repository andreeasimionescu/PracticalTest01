package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView numberOfClicksTextView;
    private Button okButton, cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent resultIntent = new Intent();
            if (view.getId() == R.id.ok_button) {
                resultIntent.putExtra("result_message", "OK button clicked");
                setResult(RESULT_OK, resultIntent);
            } else if (view.getId() == R.id.cancel_button) {
                resultIntent.putExtra("result_message", "Cancel button clicked");
                setResult(RESULT_CANCELED, resultIntent);
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary); // setContentView trebuie apelat mai întâi

        // Initializează componentele UI
        numberOfClicksTextView = findViewById(R.id.number_of_clicks_text_view);
        okButton = findViewById(R.id.ok_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Setează listener pentru butoane
        okButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);

        // Obține numărul de clicuri din Intent și setează-l în TextView
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("number_of_clicks")) {
            int numberOfClicks = intent.getIntExtra("number_of_clicks", -1);
            numberOfClicksTextView.setText(String.valueOf(numberOfClicks));
        }

        // Aplicația margini (insets) pentru Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }
}
