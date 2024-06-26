package com.example.l5_20180805;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.l5_20180805.superadmin_perfil_admin;
import com.example.l5_20180805.R;

import java.util.List;

public class MyAdapterListaUsuarios extends RecyclerView.Adapter<com.example.l5_20180805.MyViewHolder> {

    private Context context;
    private List<DataListaUsuariosClass> dataList;

    public void setSearchList(List<DataListaUsuariosClass> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public MyAdapterListaUsuarios(Context context, List<DataListaUsuariosClass> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public com.example.l5_20180805.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.superadmin_item_lista_usuarios, parent, false);
        return new com.example.l5_20180805.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            DataListaUsuariosClass item = dataList.get(position);
            holder.rec_nombre.setText(item.getNombreUsuario());
            holder.rec_status.setText(item.getEstado());
            holder.rec_rol.setText(item.getRol());

            // Change the color and image based on status
            if ("Activo".equalsIgnoreCase(item.getEstado())) {
                holder.rec_status.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
                holder.statusImageView.setImageResource(R.drawable.baseline_check_circle_outline_24);
            } else {
                holder.rec_status.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
                holder.statusImageView.setImageResource(R.drawable.baseline_error_24);
            }

            holder.recCard.setOnClickListener(v -> {
                Intent intent = new Intent(context, superadmin_perfil_admin.class);
                intent.putExtra("Nombre", item.getNombreUsuario());
                intent.putExtra("Estado", item.getEstado());
                intent.putExtra("Telefono", item.getTelefono());
                intent.putExtra("Rol", item.getRol());
                intent.putExtra("Correo", item.getCorreo());
                intent.putExtra("Direccion", item.getDireccion());
                intent.putExtra("DNI", item.getDni());
                context.startActivity(intent);
            });
        } catch (Exception e) {
            Log.e("AdapterError", "Error at position " + position + ": " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView statusImageView;
    TextView rec_nombre, rec_status, rec_rol;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);

        rec_nombre = itemView.findViewById(R.id.textView_name_cardRC_superadmin);
        rec_status = itemView.findViewById(R.id.textView_status_cardRC_superadmin);
        rec_rol = itemView.findViewById(R.id.textView_rol_cardRC_superadmin);
        statusImageView = itemView.findViewById(R.id.imageView_active_status);
        recCard = itemView.findViewById(R.id.recCard_item_listusuarios_superadmin);
    }
}


