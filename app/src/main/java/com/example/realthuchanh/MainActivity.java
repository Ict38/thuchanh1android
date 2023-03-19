package com.example.realthuchanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.realthuchanh.Adapter.DuAnAdapter;
import com.example.realthuchanh.model.DuAn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DuAnAdapter.DuAnItemListener, SearchView.OnQueryTextListener {
    private DuAnAdapter adapter;
    private RecyclerView recyclerView;
    private CheckBox finish;
    private EditText name,st,dl;
    private Button add,update;
    private SearchView searchView;
    private int rListPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingVariable();
        spinnerSetUp();
        searchView.setOnQueryTextListener(this);
        st.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    st.setText(d + "/" + (m+1) + "/" + y);
                }
            },y,m,d);
            dialog.show();
        });

        dl.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    dl.setText(d + "/" + (m+1) + "/" + y);
                }
            },y,m,d);
            dialog.show();
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname = name.getText().toString();
                String tst = st.getText().toString();
                String tdl = dl.getText().toString();
                if(tname.isEmpty() || tst.isEmpty() || tdl.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui Long Nhap Day Du", Toast.LENGTH_SHORT).show();
                }
                else{
                    adapter.add(new DuAn(tname,tst,tdl,finish.isChecked()));
                    Toast.makeText(MainActivity.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname = name.getText().toString();
                String tst = st.getText().toString();
                String tdl = dl.getText().toString();
                if(tname.isEmpty() || tst.isEmpty() || tdl.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui Long Nhap Day Du", Toast.LENGTH_SHORT).show();
                }
                else{
                    adapter.update(rListPosition,new DuAn(tname,tst,tdl,finish.isChecked()));
                    Toast.makeText(MainActivity.this, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                    update.setEnabled(false);
                    add.setEnabled(true);
                }
            }
        });
    }

    private void spinnerSetUp() {
        List<DuAn> duAns = new ArrayList<>();
        DuAn duan1 = new DuAn("Thuc Hanh","1/2/2023","1/3/2023",true);
        DuAn duan2 = new DuAn("NGhi","3/2/2023","4/3/2023",false);
        duAns.add(duan1);
        duAns.add(duan2);
        adapter = new DuAnAdapter(duAns,this);
        adapter.setDuAnItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    private void mappingVariable() {
        recyclerView = findViewById(R.id.rview);
        finish = findViewById(R.id.finish);
        name = findViewById(R.id.name);
        st = findViewById(R.id.st);
        dl = findViewById(R.id.dl);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        rListPosition = position;
        DuAn duan = adapter.getItemAt(position);
        name.setText(duan.getName());
        st.setText(duan.getSt());
        dl.setText(duan.getDl());
        if(duan.isFinish()) finish.setChecked(true);
        else finish.setChecked(false);
        add.setEnabled(false);
        update.setEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<DuAn> filterList = new ArrayList<>();
        for(DuAn i : adapter.getbList()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this, "NOT FOUND", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.filterList(filterList);
        }
    }
}