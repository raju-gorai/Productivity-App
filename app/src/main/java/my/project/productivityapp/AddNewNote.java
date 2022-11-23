package my.project.productivityapp;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        Button saveBtn = findViewById(R.id.saveBtn);


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(v -> {
            if (titleInput.length()== 0){
                Toast.makeText(getApplicationContext(),"Please enter title",Toast.LENGTH_SHORT).show();
            }else if(descriptionInput.length()==0){
                Toast.makeText(getApplicationContext(),"Please enter description",Toast.LENGTH_SHORT).show();
            }else {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}