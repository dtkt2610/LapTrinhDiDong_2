package com.dinhthikimthoa.de02_onthi;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dinhthikimthoa.de02_onthi.Adapter.ProductAdapter;
import com.dinhthikimthoa.de02_onthi.Database.ProductDB;
import com.dinhthikimthoa.de02_onthi.Model.Product;
import com.dinhthikimthoa.de02_onthi.databinding.ActivitySqliteBinding;

import java.util.ArrayList;
import java.util.List;

public class Sqlite extends AppCompatActivity {
    ActivitySqliteBinding binding;
    ProductDB db;
    ProductAdapter adapter;
    ArrayList<Product> listPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqliteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prepareDB();
        loadDB();
        addEvents();
    }

    private void addEvents() {
        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                OpenDialogProductDetail(listPro.get(position));
                return false;
            }
        });
    }

    private void OpenDialogProductDetail(Product p) {
        Dialog dialog = new Dialog(Sqlite.this);
        dialog.setContentView(R.layout.dialog_details);
        EditText edtMaSp = dialog.findViewById(R.id.edtMaSp);
        edtMaSp.setText(p.getMaSp());
        EditText edtTenSp = dialog.findViewById(R.id.edtTenSp);
        edtTenSp.setText(p.getTenSp());
        EditText edtGiaSp = dialog.findViewById(R.id.edtGiaSp);
        edtGiaSp.setText(Double.toString(p.getGiaSp()));

        Button btnXoa = dialog.findViewById(R.id.btnXoa);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(p.getMaSp());
                loadDB();
                Toast.makeText(Sqlite.this, "Delete Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button btnThoat = dialog.findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void loadDB() {
        adapter = new ProductAdapter(Sqlite.this, R.layout.item_list, getDataFromDB());
        binding.lvProduct.setAdapter(adapter);
    }

    private List<Product> getDataFromDB() {
        listPro = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM "+ ProductDB.TABLE_NAME);
        if (cursor != null){
            while (cursor.moveToNext()){
                listPro.add(new Product(cursor.getString(0),cursor.getString(1),cursor.getDouble(2)));
            }
            cursor.close();
        }
        return listPro;
    }

    private void prepareDB() {
        db = new ProductDB(this);
        db.CreateSampleData();
    }
}