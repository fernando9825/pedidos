package com.fernando9825.pedidos.pedidos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernando9825.pedidos.R;

import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder> {

    private Context mCtx;
    private List<Pedidos> pedidosList;

    public PedidosAdapter(Context mCtx, List<Pedidos> pedidosList) {
        this.mCtx = mCtx;
        this.pedidosList = pedidosList;
    }



    @NonNull
    @Override
    public PedidosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_view_listarpedidos, null);
        return new PedidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosViewHolder pedidosViewHolder, int i) {

        Pedidos pedidos = pedidosList.get(i);

        if (pedidos.getId_pedido().equals("Aún sin pedidos, presione el botón para crear uno nuevo")) {
            pedidosViewHolder.textViewId.setText("Aún sin pedidos, presione el botón para crear uno nuevo");
            pedidosViewHolder.textViewProducto.setText("");
            pedidosViewHolder.textViewCliente.setText("");
            pedidosViewHolder.textViewCantidad.setText("");
            pedidosViewHolder.textViewFecha.setText("");
            pedidosViewHolder.textViewTotal.setText("");
            pedidosViewHolder.textViewPrecio.setText("");
        } else {
            pedidosViewHolder.textViewId.setText("ID-PEDIDO: " + pedidos.getId_pedido());
            pedidosViewHolder.textViewProducto.setText("producto: " + pedidos.getProducto());
            pedidosViewHolder.textViewCliente.setText("cliente: " + pedidos.getCliente());
            pedidosViewHolder.textViewCantidad.setText("cantidad: " + pedidos.getCantidad());
            pedidosViewHolder.textViewFecha.setText("fecha: " + pedidos.getFecha());
            pedidosViewHolder.textViewTotal.setText("Total: $" + pedidos.getTotal());
            pedidosViewHolder.textViewPrecio.setText("precio: $" + pedidos.getPrecio());
        }


    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class PedidosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewProducto, textViewCliente, textViewCantidad, textViewFecha,
                textViewTotal, textViewPrecio;

        public PedidosViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.idPedidoTXT);
            textViewProducto = itemView.findViewById(R.id.productoTXT);
            textViewCliente = itemView.findViewById(R.id.clienteTXT);
            textViewCantidad = itemView.findViewById(R.id.cantidadTXT);
            textViewFecha = itemView.findViewById(R.id.fechaTXT);
            textViewTotal = itemView.findViewById(R.id.totalTXT);
            textViewPrecio = itemView.findViewById(R.id.precioTXT);
        }
    }
}
