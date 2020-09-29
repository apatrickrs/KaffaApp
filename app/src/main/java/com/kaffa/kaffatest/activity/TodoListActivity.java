package com.kaffa.kaffatest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.kaffa.kaffatest.R;
import com.kaffa.kaffatest.adapter.AdapterTodo;
import com.kaffa.kaffatest.helper.ConfigFirebase;
import com.kaffa.kaffatest.helper.RecyclerItemClickListener;
import com.kaffa.kaffatest.model.TODOTask;
import com.santalu.maskedittext.MaskEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TodoListActivity extends AppCompatActivity {

    private EditText fieldName, fieldTask;
    private MaskEditText fieldDate;
    private LinearLayout linearInsertTask;
    private Spinner spinnerCatTask;

    private Button btnNewTask, btnSaveTask, btnCancel;
    private RecyclerView recyclerUrgent, recyclerImportant, recyclerRemember;

    private List<TODOTask> todoListUrgent = new ArrayList<>();
    private List<TODOTask> todoListImportant = new ArrayList<>();
    private List<TODOTask> todoListRemember = new ArrayList<>();

    private TODOTask saveTask;

    private AdapterTodo adapterTodoUrgent;
    private AdapterTodo adapterTodoImportant;
    private AdapterTodo adapterTodoRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        spinnerCatTask = findViewById(R.id.spinnerCatTask);
        fieldName = findViewById(R.id.fieldTaskName);
        fieldDate = findViewById(R.id.fieldTaskDate);
        fieldTask = findViewById(R.id.fieldTaskTask);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);
        linearInsertTask = findViewById(R.id.linearInsertTask);
        btnNewTask = findViewById(R.id.btnNewTask);
        recyclerUrgent = findViewById(R.id.recyclerUrgent);
        recyclerImportant = findViewById(R.id.recyclerImportant);
        recyclerRemember = findViewById(R.id.recyclerRemember);

        linearInsertTask.setVisibility(View.INVISIBLE);

        configSpinner();
        configRecyclers();
        configBtns();
        searchForTasks();
    }

    private void configSpinner() {
        String[] spinnerCategories = new String[] {
                "URGENT", "IMPORTANT", "REMEMBER"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCatTask.setAdapter(adapter);
    }

    private void configBtns() {
        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearInsertTask.setAnimation(AnimationUtils.loadAnimation(TodoListActivity.this, R.anim.fade_in));
                linearInsertTask.setVisibility(View.VISIBLE);
            }
        });

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearInsertTask.setAnimation(AnimationUtils.loadAnimation(TodoListActivity.this, R.anim.fade_out));
                linearInsertTask.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void saveTask() {
        if (!fieldName.getText().toString().isEmpty()) {
            if (!fieldDate.getText().toString().isEmpty()) {
                if (!fieldTask.getText().toString().isEmpty()) {
                        saveTask = new TODOTask();
                        saveTask.setId(UUID.randomUUID().toString());
                        saveTask.setCategorie(spinnerCatTask.getSelectedItem().toString());
                        saveTask.setName(fieldName.getText().toString());
                        saveTask.setDate(fieldDate.getText().toString());
                        saveTask.setTodo(fieldTask.getText().toString());
                        saveTask.save();

                        Toast.makeText(this, "TASK SAVED !", Toast.LENGTH_SHORT).show();
                        linearInsertTask.setAnimation(AnimationUtils.loadAnimation(TodoListActivity.this, R.anim.fade_out));
                        linearInsertTask.setVisibility(View.INVISIBLE);
                        clearFields();
                } else
                    Toast.makeText(this, "FIELD TASK CAN'T BE EMPTY", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "FIELD DATE CAN'T BE EMPTY", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "FIELD NAME CAN'T BE EMPTY", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        fieldName.setText("");
        fieldDate.setText("");
        fieldTask.setText("");
    }

    private void configRecyclers() {
        recyclerUrgent.setLayoutManager(new LinearLayoutManager(this));
        recyclerUrgent.setHasFixedSize(true);

        adapterTodoUrgent = new AdapterTodo(todoListUrgent, this);

        recyclerUrgent.setAdapter(adapterTodoUrgent);

        recyclerUrgent.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerUrgent, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {
                TODOTask selectedTaskUrgent = todoListUrgent.get(position);
                selectedTaskUrgent.remove();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        /**************************************************************************/
        recyclerImportant.setLayoutManager(new LinearLayoutManager(this));
        recyclerImportant.setHasFixedSize(true);

        adapterTodoImportant = new AdapterTodo(todoListImportant, this);

        recyclerImportant.setAdapter(adapterTodoImportant);

        recyclerImportant.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerImportant, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {
                TODOTask selectedTaskImportant = todoListImportant.get(position);
                selectedTaskImportant.remove();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        /**************************************************************************/
        recyclerRemember.setLayoutManager(new LinearLayoutManager(this));
        recyclerRemember.setHasFixedSize(true);

        adapterTodoRemember = new AdapterTodo(todoListRemember, this);

        recyclerRemember.setAdapter(adapterTodoRemember);

        recyclerRemember.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerRemember, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {
                TODOTask selectedTaskRemember = todoListRemember.get(position);
                selectedTaskRemember.remove();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }

    private void searchForTasks() {
        ConfigFirebase.getFirebaseDatabase().child("todo").child("URGENT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                todoListUrgent.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    todoListUrgent.add(ds.getValue(TODOTask.class));
                }

                Collections.reverse(todoListUrgent);
                adapterTodoUrgent.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /**************************************************************************/
        ConfigFirebase.getFirebaseDatabase().child("todo").child("IMPORTANT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                todoListImportant.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    todoListImportant.add(ds.getValue(TODOTask.class));
                }

                Collections.reverse(todoListImportant);
                adapterTodoImportant.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /**************************************************************************/
        ConfigFirebase.getFirebaseDatabase().child("todo").child("REMEMBER").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                todoListRemember.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    todoListRemember.add(ds.getValue(TODOTask.class));
                }

                Collections.reverse(todoListRemember);
                adapterTodoRemember.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}