package mx.GPS.healthec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorSonidos extends RecyclerView.Adapter<AdaptadorSonidos.SonidoViewHolder> {

    MediaPlayer mediaPlayer;
    Context context;
    List<Sonido> listaSonidos;

    public AdaptadorSonidos(Context context, List<Sonido> listaSonidos) {
        this.context = context;
        this.listaSonidos = listaSonidos;
    }



    @NonNull
    @Override
    public AdaptadorSonidos.SonidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element,parent,false);
        return new SonidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdaptadorSonidos.SonidoViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context,R.anim.slide));
        holder.tvNombre.setText(listaSonidos.get(position).getNombre());
        holder.tvDesc.setText(listaSonidos.get(position).getDescripcion());
        holder.btnReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    mediaPlayer.stop();
                }
                if (listaSonidos.get(position).getSonido().equals("sonidoUno")){
                    mediaPlayer=MediaPlayer.create(context,R.raw.naturaleza);
                    mediaPlayer.start();
                }else if(listaSonidos.get(position).getSonido().equals("sonidoDos")) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.meditacion);
                    mediaPlayer.start();
                }
                else if(listaSonidos.get(position).getSonido().equals("sonidoTres")) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.asmr);
                    mediaPlayer.start();
                }
                else if(listaSonidos.get(position).getSonido().equals("sonidoCuatro")) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.lluvia);
                    mediaPlayer.start();
                }
                else if(listaSonidos.get(position).getSonido().equals("sonidoCinco")) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.piano);
                    mediaPlayer.start();
                }
            }
        });
        holder.btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
            }
        });


    }


    @Override
    public int getItemCount() {
        return listaSonidos.size();
    }

    public class SonidoViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView tvNombre,tvDesc;
        Button btnReproducir,btnDetener;
        CardView cv;

        public SonidoViewHolder(View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.cv);
            icon=itemView.findViewById(R.id.icon);
            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvDesc=itemView.findViewById(R.id.descripcion);
            btnDetener=itemView.findViewById(R.id.btnDetener);
            btnReproducir=itemView.findViewById(R.id.btnReproducir);
        }
    }

}
