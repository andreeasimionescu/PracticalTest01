package ro.pub.cs.systems.eim.practicaltest01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01MainActivity extends AppCompatActivity {
    private EditText leftEditText;
    private EditText rightEditText;
    private Button pressMeButton, pressMeTooButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_test01_main);

        leftEditText = (EditText)findViewById(R.id.editText_left);
        rightEditText = (EditText)findViewById(R.id.editText_right);

        pressMeButton = (Button)findViewById(R.id.left_button);
        pressMeTooButton = (Button)findViewById(R.id.right_button);

        leftEditText.setText(String.valueOf(0));
        rightEditText.setText(String.valueOf(0));

        pressMeButton.setOnClickListener(buttonClickListener);
        pressMeTooButton.setOnClickListener(buttonClickListener);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("left", leftEditText.getText().toString() );
        outState.putString("right", rightEditText.getText().toString() );
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey("left")) {
            leftEditText.setText(String.valueOf(savedInstanceState.getString("left")));
        } else {
            leftEditText.setText(0);
        }
        if(savedInstanceState.containsKey("right")) {
            rightEditText.setText(String.valueOf(savedInstanceState.getString("right")));
        } else {
            rightEditText.setText(0);
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
    @Override
        public void onClick(View view) {
            int leftNumberOfClicks = Integer.valueOf(leftEditText.getText().toString());
            int rightNumberOfClicks = Integer.valueOf(rightEditText.getText().toString());
        if (view.getId() == R.id.left_button) {
            leftNumberOfClicks++;
            leftEditText.setText(String.valueOf(leftNumberOfClicks));
        } else if (view.getId() == R.id.right_button) {
            rightNumberOfClicks++;
            rightEditText.setText(String.valueOf(rightNumberOfClicks));
        }

        }
    }
}