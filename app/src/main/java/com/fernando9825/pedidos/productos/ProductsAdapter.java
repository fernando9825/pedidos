package com.fernando9825.pedidos.productos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fernando9825.pedidos.R;

import java.util.List;

/**
 * Adaptador
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_view_product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //cargar imagen
        String string_imagen = product.getImage();
        if (TextUtils.isEmpty(string_imagen)) {
            string_imagen = "img/productos/nodisponible.jpg";
        }

        String txt_precio;
        if (product.getPrecio() == 0)
            txt_precio = "";
        else
            txt_precio = "$ " + product.getPrecio();


        String ruta_imagen = Product.URL_PRODUCT + string_imagen;
        Glide.with(mCtx)
                .load(ruta_imagen)
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getDescripcion());
        holder.textViewShortDesc.setText(product.getBarcode());
        holder.textViewPrice.setText(txt_precio);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
