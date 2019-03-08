package com.challenge.roomlocalstorage.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.challenge.roomlocalstorage.R;
import com.challenge.roomlocalstorage.data.entities.User;
import com.challenge.roomlocalstorage.data.repo.Repository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstNameEditText;
    private EditText lastNameEdittext;
    private Button saveButton;
    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        firstNameEditText = findViewById(R.id.first_name_edittext);
        lastNameEdittext = findViewById(R.id.last_name_edittext);
        recyclerView = findViewById(R.id.user_recycler_view);
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);

        repository = new Repository(getApplication());

        // setup recyclerView
        userAdapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View view) {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEdittext.getText().toString();

        if (!firstName.isEmpty() || !lastName.isEmpty()) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userAdapter.addUser(user);

            repository.insertUser(user);

        } else {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = repository.getAllUsers();
                userAdapter.setUserList(users);
            }
        });
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.onDestroy();
    }
}
