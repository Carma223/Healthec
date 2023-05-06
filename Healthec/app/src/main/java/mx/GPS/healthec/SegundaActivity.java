package mx.GPS.healthec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        TextView textView=findViewById(R.id.textViewNombre);
        textView.setText(getIntent().getStringExtra("nombre"));
    }
}