package com.example.shop.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.Activity.Cart;
import com.example.shop.Activity.MainActivity;
import com.example.shop.Model.CartModel;
import com.example.shop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<CartModel> arrayListGioHang;
    public CartAdapter(Context context, ArrayList<CartModel> arrayListGioHang) {
        this.context = context;
        this.arrayListGioHang = arrayListGioHang;
    }
    @Override
    public int getCount() {
        return arrayListGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txt_TenGH, txt_GiaGH;
        public ImageView img_AnhGH;
        public Button btn_CongGH, btn_TruGH, btn_GiaTriGH;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart, null);

            viewHolder.img_AnhGH = convertView.findViewById(R.id.img_AnhGH);
            viewHolder.txt_TenGH = convertView.findViewById(R.id.txt_TenGH);
            viewHolder.txt_GiaGH = convertView.findViewById(R.id.txt_GiaGH);
            viewHolder.btn_CongGH = convertView.findViewById(R.id.btn_CongGH);
            viewHolder.btn_TruGH = convertView.findViewById(R.id.btn_TruGH);
            viewHolder.btn_GiaTriGH = convertView.findViewById(R.id.btn_GiaTriGH);

            convertView.setTag(viewHolder);
        }else viewHolder = (ViewHolder) convertView.getTag();

        CartModel gioHang = (CartModel) getItem(position);
        viewHolder.txt_TenGH.setMaxLines(1);
        viewHolder.txt_TenGH.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txt_TenGH.setText(gioHang.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat ("###,###,###" ) ;
        viewHolder.txt_GiaGH.setText(decimalFormat.format (gioHang.getPrice()) + " Ð");
        Picasso.with (context).load (gioHang.getPicture())
                .placeholder(R.drawable.loading)
                .error (R.drawable.error)
                .into (viewHolder.img_AnhGH);
        viewHolder.btn_GiaTriGH.setText (gioHang.getQuantily()+"");

        int soLuong = Integer.parseInt(viewHolder.btn_GiaTriGH.getText().toString());
        if (soLuong >= 5 ){
            viewHolder.btn_CongGH.setVisibility(View.INVISIBLE);
            viewHolder.btn_TruGH.setVisibility(View.VISIBLE);
        }else if (soLuong == 1){
            viewHolder.btn_TruGH.setVisibility(View.INVISIBLE);
        }else if (soLuong > 1 && soLuong <5){
            viewHolder.btn_CongGH.setVisibility(View.VISIBLE);
            viewHolder.btn_TruGH.setVisibility(View.VISIBLE);
        }

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btn_CongGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartModel tmp = MainActivity.arrayListGioHang.get(position);
                int soLuongMoi = tmp.getQuantily() +1 ;
                long giaCu = tmp.getPrice();
                long giaMoi = giaCu/tmp.getQuantily()*soLuongMoi;
                MainActivity.arrayListGioHang.get(position).setQuantily(soLuongMoi);
                MainActivity.arrayListGioHang.get(position).setPrice(giaMoi);
                DecimalFormat decimalFormat = new DecimalFormat ("###,###,###" ) ;
                finalViewHolder.txt_GiaGH.setText(decimalFormat.format (gioHang.getPrice()) + " Ð");
                Cart.EventUltil();
                if (soLuongMoi > 4){
                    finalViewHolder.btn_CongGH.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_TruGH.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_GiaTriGH.setText(soLuongMoi + "");
                }else{
                    finalViewHolder.btn_CongGH.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_TruGH.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_GiaTriGH.setText(soLuongMoi + "");
                }

            }
        });
        viewHolder.btn_TruGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartModel tmp = MainActivity.arrayListGioHang.get(position);
                int soLuongMoi = tmp.getQuantily() -1 ;
                long giaCu = tmp.getPrice();
                long giaMoi = giaCu/tmp.getQuantily()*soLuongMoi;
                MainActivity.arrayListGioHang.get(position).setQuantily(soLuongMoi);
                MainActivity.arrayListGioHang.get(position).setPrice(giaMoi);
                DecimalFormat decimalFormat = new DecimalFormat ("###,###,###" ) ;
                finalViewHolder.txt_GiaGH.setText(decimalFormat.format (gioHang.getPrice()) + " Ð");
                Cart.EventUltil();
                if (soLuongMoi < 2){
                    finalViewHolder.btn_CongGH.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_TruGH.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_GiaTriGH.setText(soLuongMoi + "");
                }else{
                    finalViewHolder.btn_CongGH.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_TruGH.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_GiaTriGH.setText(soLuongMoi + "");
                }
            }
        });


        return convertView;
    }
}
