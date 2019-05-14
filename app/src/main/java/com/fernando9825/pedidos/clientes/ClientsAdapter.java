package com.fernando9825.pedidos.clientes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernando9825.pedidos.R;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientViewHolder> {

    private Context mCtx;
    private List<Client> clientList;

    public ClientsAdapter(Context mCtx, List<Client> clientList) {
        this.mCtx = mCtx;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_view_client_list, null);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder clientViewHolder, int i) {
        Client client = clientList.get(i);


        if (client.getDireccion().isEmpty()) {
            client.setDireccion("-");
        }

        if (client.getTelefono().isEmpty()) {
            client.setTelefono("-");
        }


        clientViewHolder.textViewId.setText(client.getId().toString());
        clientViewHolder.textViewNombre.setText(client.getNombre());
        clientViewHolder.textViewDireccion.setText(client.getDireccion());
        clientViewHolder.textViewTelefono.setText(client.getTelefono());

    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {

        TextView textViewId, textViewNombre, textViewDireccion, textViewTelefono;

        public ClientViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.textViewId);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDireccion = itemView.findViewById(R.id.textViewDireccion);
            textViewTelefono = itemView.findViewById(R.id.textViewTelefono);

        }
    }
}
