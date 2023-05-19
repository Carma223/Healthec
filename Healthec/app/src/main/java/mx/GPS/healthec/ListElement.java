package mx.GPS.healthec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListElement extends RecyclerView.Adapter<ListElement.EjercicioViewHolder>{
    Context context;
    List<Ejercicio> listaEjercicios;

    public ListElement(Context context, List<Ejercicio> listaEjercicios) {
        this.context = context;
        this.listaEjercicios = listaEjercicios;
    }



    @NonNull
    @Override
    public ListElement.EjercicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element2,parent,false);
        return new EjercicioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListElement.EjercicioViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvNombre.setText(listaEjercicios.get(position).getNombre());
        holder.tvDesc.setText(listaEjercicios.get(position).getDescripcion());
        holder.icon.setImageResource(listaEjercicios.get(position).getImagen());

        holder.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listaEjercicios.get(position).getNombre().equals("Cuello")){
                    Intent intent = new Intent(context, EstiramientosCuello.class);
                    context.startActivity(intent);
                }
                else if(listaEjercicios.get(position).getNombre().equals("Hombros")){
                    Intent intent = new Intent(context, EstiramientoHombros.class);
                    context.startActivity(intent);
                }
                else if(listaEjercicios.get(position).getNombre().equals("Espalda")){
                    Intent intent = new Intent(context, EstiramientoEspalda.class);
                    context.startActivity(intent);
                }
                else if(listaEjercicios.get(position).getNombre().equals("Piernas")){
                    Intent intent = new Intent(context, EstiramientoPiernas.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEjercicios.size();
    }

    public class EjercicioViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView tvNombre,tvDesc;
        Button btnIniciar;

        public EjercicioViewHolder(View itemView) {
            super(itemView);

            icon=itemView.findViewById(R.id.icon);
            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvDesc=itemView.findViewById(R.id.descripcion);
            btnIniciar=itemView.findViewById(R.id.btnIniciar);
        }
    }

}
