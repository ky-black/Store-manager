package com.example.shop.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.Model.ProductModel;
import com.example.shop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsByIDAdapter extends BaseAdapter {
    Context context;
    ArrayList<ProductModel> arrayListSanPham;

    public ProductsByIDAdapter(Context context, ArrayList<ProductModel> arrayListSanPham) {
        this.context = context;
        this.arrayListSanPham = arrayListSanPham;
    }

    @Override
    public int getCount() {
        return arrayListSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenSanPham,txtGiaSanPham, txtMoTa;
        public ImageView imgHinhAnhSanPham;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_product_by_type, null);

            viewHolder.txtTenSanPham = convertView.findViewById(R.id.txtTenSanPhamByType);
            viewHolder.txtGiaSanPham = convertView.findViewById(R.id.txtGiaSanPhamByType);
            viewHolder.txtMoTa = convertView.findViewById(R.id.txtMoTaSanPhamByType);
            viewHolder.imgHinhAnhSanPham =convertView.findViewById(R.id.imgSanPhamByType);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProductModel sanPham = (ProductModel) getItem(position);
        viewHolder.txtTenSanPham.setMaxLines(1);
        viewHolder.txtTenSanPham.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtTenSanPham.setText(sanPham.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaSanPham.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPham.getPrice()))+ "Đ" );
        viewHolder.txtMoTa.setMaxLines(2);
        viewHolder.txtMoTa.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTa.setText(sanPham.getDescription());
        Picasso.with(context).load(sanPham.getPicture()).placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.imgHinhAnhSanPham);
        return convertView;
    }
}
