package com.example.l5_20180805;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.l5_20180805.DataListaUsuariosClass;
import com.example.l5_20180805.MyAdapterListaUsuarios;

import java.util.ArrayList;
import java.util.List;

public class superadmin_lista_usuarios extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout lista_usuarios, lista_logs, nuevo_admin, inicio_nav, log_out;

    RecyclerView recyclerView;
    List<DataListaUsuariosClass> dataList;
    MyAdapterListaUsuarios adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.superadmin_lista_usuarios);


        initializeDrawer();
        setupRecyclerView();
    }

    private void initializeDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.menu_nav_superadmin_toolbar);
        menu.setOnClickListener(v -> openDrawer(drawerLayout));

        setupDrawerLinks();
    }

    private void setupDrawerLinks() {
        inicio_nav = findViewById(R.id.inicio_nav_superadmin);
        lista_usuarios = findViewById(R.id.lista_usuarios_nav);
        lista_logs = findViewById(R.id.lista_logs_nav);
        nuevo_admin = findViewById(R.id.nuevo_admin_nav);
        log_out = findViewById(R.id.cerrar_sesion);

        inicio_nav.setOnClickListener(v -> redirectActivity(this, SuperadminActivity.class));
        lista_usuarios.setOnClickListener(v -> redirectActivity(this, superadmin_lista_usuarios.class));
        nuevo_admin.setOnClickListener(v -> redirectActivity(this, superadmin_nuevo_admin.class));
        log_out.setOnClickListener(v -> Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show());
    }

    private void setupRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recyclerView_listausuarios_superadmin);
            searchView = findViewById(R.id.search_listausuarios_superadmin);
            searchView.clearFocus();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchList(newText);
                    return false;
                }
            });

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            dataList = new ArrayList<>();
            adapter = new MyAdapterListaUsuarios(this, dataList);
            recyclerView.setAdapter(adapter);

            addTestData();
        } catch (Exception e) {
            Toast.makeText(this, "Error setting up RecyclerView: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("RecyclerViewSetup", "Error setting up RecyclerView", e);
        }
    }

    private void addTestData() {
        try {
            DataListaUsuariosClass androidData = new DataListaUsuariosClass("Juan Perez", "Administrador", "Activo", "997134887","11223344", "jperez@example.com","Av. Principal 1200");
            dataList.add(androidData);
            androidData = new DataListaUsuariosClass("Liliana Garay",  "Supervisor", "Activo", "987123409","99887700", "lgaray@example.com","Av. Universitaria 1801");
            dataList.add(androidData);
            androidData = new DataListaUsuariosClass("Admino Baneado", "Administrador", "Inactivo", "944183400","22837700", "abaneado@example.com","Av. Universitaria 101");
            dataList.add(androidData);
            androidData = new DataListaUsuariosClass("Supo Baneado", "Supervisor", "Inactivo", "991432974", "12344321", "sbaneado@example.com","Av. Grau 820");
            dataList.add(androidData);

            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(this, "Error adding test data: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("TestDataError", "Error adding test data", e);
        }
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    private void searchList(String text) {
        List<DataListaUsuariosClass> dataSearchList = new ArrayList<>();
        for (DataListaUsuariosClass data : dataList) {
            if (data.getNombreUsuario().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}