package com.example.shop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.Model.CartModel;
import com.example.shop.Model.ProductModel;
import com.example.shop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetails extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten, txtgia, txtmota;
    Button btndatmua;
    int giaCTSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        anhXa();
        actionToolBar();
        getInformation();
        catchEventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGH:
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void catchEventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    ProductModel sanPham= getInformation();
                    boolean exit = false;
                    if (MainActivity.arrayListGioHang.size() > 0){
                        for (int i = 0 ; i < MainActivity.arrayListGioHang.size() ; i++){
                            if (MainActivity.arrayListGioHang.get(i).getId_product() == Integer.parseInt(sanPham.getId())){
                                MainActivity.arrayListGioHang.get(i).setQuantily(MainActivity.arrayListGioHang.get(i).getQuantily() +1);

                                MainActivity.arrayListGioHang.get(i).setPrice(MainActivity.arrayListGioHang.get(i).getQuantily() * Integer.parseInt(sanPham.getPrice()));
                                exit = true;
                            }
                        }
                        if (exit == false)
                            MainActivity.arrayListGioHang.add(new CartModel(null,Integer.parseInt(sanPham.getId()),sanPham.getProduct_name(), Integer.parseInt(sanPham.getPrice()), sanPham.getPicture(),1));
                    }else {

                        MainActivity.arrayListGioHang.add(new CartModel(null,Integer.parseInt(sanPham.getId()),sanPham.getProduct_name(), Integer.parseInt(sanPham.getPrice()), sanPham.getPicture(),1));
                    }
                    Intent intent = new Intent(getApplicationContext(), Cart.class);
                    startActivity(intent);
            }
        });
    }


    private ProductModel getInformation() {

        ProductModel sanpham = (ProductModel) getIntent().getSerializableExtra("thongtinsanpham");

        txtten.setText(sanpham.getProduct_name());
        giaCTSP = Integer.parseInt(sanpham.getPrice());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText ("Giá : " + decimalFormat.format(Integer.parseInt(sanpham.getPrice())) + " Đ");
        txtmota.setText (sanpham.getDescription());
        Picasso.with(getApplicationContext()).load(sanpham.getPicture()).placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imgChitiet);
        return sanpham;
    }

    private void actionToolBar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbarChitiet =findViewById (R.id.tb_ChiTietSanPhamCTSP);
        imgChitiet = findViewById(R.id.img_HinhAnhCTSP);
        txtten =  findViewById(R.id.txt_TenSanPhamCTSP) ;
        txtgia =  findViewById(R.id.txt_GiaCTSP);
        txtmota =  findViewById(R.id.txt_MoTaSanPhamCTSP);
        btndatmua = findViewById(R.id.btn_DatHang);

    }
}