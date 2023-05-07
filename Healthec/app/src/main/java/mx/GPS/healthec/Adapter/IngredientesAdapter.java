package mx.GPS.healthec.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import mx.GPS.healthec.R;
import mx.GPS.healthec.modelos.Ingredientes;

public class IngredientesAdapter extends FirestoreRecyclerAdapter<Ingredientes, IngredientesAdapter.ViewHolder> {

    public IngredientesAdapter(@NonNull FirestoreRecyclerOptions<Ingredientes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Ingredientes model) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recetas, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Titulo;
        ImageView Imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.txtvTituloReceta);
            Imagen = itemView.findViewById(R.id.imgvRecetas);
        }
    }
}
