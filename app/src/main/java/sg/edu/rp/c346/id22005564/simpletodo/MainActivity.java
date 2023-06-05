package sg.edu.rp.c346.id22005564.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextText;
    Button button;
    Button button2;
    Button button3;
    ListView listView;
    Spinner spinner;

    ArrayList<String> taskList;
    ArrayAdapter<String> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);

        taskList = new ArrayList<>();

        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);


        listView.setAdapter(taskAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if (selectedItem.equals("Add a new task")) {
                    enableAddMode();
                } else if (selectedItem.equals("Remove a task")) {
                    enableRemoveMode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextText.getText().toString();
                taskList.add(task);
                taskAdapter.notifyDataSetChanged();
                editTextText.getText().clear();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                taskAdapter.notifyDataSetChanged();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String positionText = editTextText.getText().toString();

                if (!positionText.isEmpty()) {
                    int pos = Integer.parseInt(positionText);
                    if (pos >= 1 && pos <= taskList.size()) {
                        int actualIndex = pos - 1;
                        taskList.remove(actualIndex);
                        taskAdapter.notifyDataSetChanged();
                        editTextText.getText().clear();
                    } else {
                        // Display an error message if the index is out of range
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Display an error message if no position is entered
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void enableAddMode() {
        button.setEnabled(true); // Enable the Add button
        button2.setEnabled(true); // Enable the Clear button
        button3.setEnabled(false); // Disable the Delete button
        editTextText.setHint("Type in a new task here");
    }

    private void enableRemoveMode() {
        button.setEnabled(false); // Disable the Add button
        button2.setEnabled(true); // Enable the Clear button
        button3.setEnabled(true); // Enable the Delete button
        editTextText.setHint("Type in the index of the task to be removed");
    }
}
