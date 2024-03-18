package com.navig.res_platinum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView textTitulo, plateTitle, textPrize, textTotal, textPedidos;
    private ImageView imagePlate;
    private int cont = 0;
    private int total = 0;
    // clave,valor name, cantidad
    HashMap<String, Integer> namePrice = new HashMap<>();
    private final Plate[] plates = new Plate[27];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referenciar();
    }

    public void referenciar() {
        textTitulo = findViewById(R.id.textView);
        imagePlate = findViewById(R.id.imageView);
        plateTitle = findViewById(R.id.textView2);
        textPrize = findViewById(R.id.textPrize);
        textTotal = findViewById(R.id.textTotal2);
        textPedidos = findViewById(R.id.textPedidos2);
    }

    public void irHistorico(View view) {
        //Intent
//        Intent pc = new Intent(this, ReporteActivity.class);
//        startActivity(pc);
        // consultar namePrice
    }

    public void irAnterior(View view) {
        cont -= 1;
        if (cont < 0) {
            cont = 26;
        }
        showImage(cont);
    }

    public void irSiguiente(View view) {
        cont += 1;
        if (cont > 26) {
            cont = 1;
        }
        showImage(cont);
    }

    public void comprar(View view) {
        try {
            int price = Integer.parseInt(textPrize.getText().toString());
            total += price;
            textPedidos.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid price format.");
        }

        String name = plateTitle.getText().toString();

        int quantity = namePrice.getOrDefault(name, 0) + 1;
        namePrice.put(name, quantity);

        // 4. Update textTotal with the number of items (or other meaningful value)
        textTotal.setText(String.valueOf(namePrice.size())); // Consider a more informative display
    }

    public void devolver(View view) {
        // 1. Get the price from the text field (handle potential exceptions)
        try {
            int price = Integer.parseInt(textPrize.getText().toString());
            total -= price;
            textPedidos.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            // Handle invalid input gracefully (e.g., show an error message)
            System.out.println("Error: Invalid price format.");
        }

        // 2. Get the name from the plate title
        String name = plateTitle.getText().toString();

        // 3. Update namePrice with quantity (handle non-existent items and negative quantities)
        int quantity = namePrice.getOrDefault(name, 0) - 1;

        // Check if quantity becomes negative after decrement
        if (quantity >= 0) {
            namePrice.put(name, quantity);
        } else {
            // Handle case where quantity goes negative (e.g., warn user, reset to 0)
            System.out.println("Warning: Cannot return more items than purchased for " + name);
            // You can choose to reset the quantity to 0 here if appropriate:
            // namePrice.put(name, 0);
        }

        // 4. Update textTotal with a meaningful value
        textTotal.setText(String.valueOf(getTotalQuantity())); // Consider a more informative display
    }

    // Helper method to get total quantity (optional)
    private int getTotalQuantity() {
        int totalQuantity = 0;
        for (int value : namePrice.values()) {
            totalQuantity += value;
        }
        return totalQuantity;
    }

    public void facturar(View view) {
        ArrayList<String> nombresPlatos = new ArrayList<>(namePrice.keySet());
        HashMap<String, Integer> cantidades = namePrice;
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("nombresPlatos", nombresPlatos);
        System.out.println(nombresPlatos);
        bundle.putSerializable("cantidades", (Serializable) cantidades);
        System.out.println(cantidades);
        Intent intent = new Intent(this, ReporteActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showImage(int c) {
        switch (c) {
            case 1:
                imagePlate.setImageResource(R.drawable.p01);
                plateTitle.setText("CHARQUEKAN");
                textPrize.setText("25");
                break;
            case 2:
                imagePlate.setImageResource(R.drawable.p02);
                plateTitle.setText("PAPA LA HUANCAYNA");
                textPrize.setText("30");
                break;
            case 3:
                imagePlate.setImageResource(R.drawable.p03);
                plateTitle.setText("MAJADITO");
                textPrize.setText("15");
                break;
            case 4:
                imagePlate.setImageResource(R.drawable.p04);
                plateTitle.setText("PIQUE MACHO");
                textPrize.setText("50");
                break;
            case 5:
                imagePlate.setImageResource(R.drawable.p05);
                plateTitle.setText("HAMBURGUESA SIMPLE");
                textPrize.setText("18");
                break;
            case 6:
                imagePlate.setImageResource(R.drawable.p06);
                plateTitle.setText("LOMO MONTADO");
                textPrize.setText("20");
                break;
            case 7:
                imagePlate.setImageResource(R.drawable.p07);
                plateTitle.setText("PLATO PACEÑO");
                textPrize.setText("30");
                break;
            case 8:
                imagePlate.setImageResource(R.drawable.p08);
                plateTitle.setText("SAJTA DE POLLO");
                textPrize.setText("25");
                break;
            case 9:
                imagePlate.setImageResource(R.drawable.p09);
                plateTitle.setText("MILANESA DE CARNE");
                textPrize.setText("25");
                break;
            case 10:
                imagePlate.setImageResource(R.drawable.p10);
                plateTitle.setText("RAMEN CON POLLO");
                textPrize.setText("20");
                break;
            case 11:
                imagePlate.setImageResource(R.drawable.p11);
                plateTitle.setText("POLLO DORADO");
                textPrize.setText("50");
                break;
            case 12:
                imagePlate.setImageResource(R.drawable.p12);
                plateTitle.setText("SALCHIPAPA");
                textPrize.setText("18");
                break;
            case 13:
                imagePlate.setImageResource(R.drawable.p13);
                plateTitle.setText("PULPO AL GUSTO");
                textPrize.setText("45");
                break;
            case 14:
                imagePlate.setImageResource(R.drawable.p14);
                plateTitle.setText("CHICHARRON POLLO");
                textPrize.setText("30");
                break;
            case 15:
                imagePlate.setImageResource(R.drawable.p15);
                plateTitle.setText("SOPITA DE FIDEO");
                textPrize.setText("10");
                break;
            case 16:
                imagePlate.setImageResource(R.drawable.p16);
                plateTitle.setText("CHICHARRON CERDO");
                textPrize.setText("40");
                break;
            case 17:
                imagePlate.setImageResource(R.drawable.p17);
                plateTitle.setText("ISPI");
                textPrize.setText("25");
                break;
            case 18:
                imagePlate.setImageResource(R.drawable.p18);
                plateTitle.setText("CHAIRO");
                textPrize.setText("20");
                break;
            case 19:
                imagePlate.setImageResource(R.drawable.p19);
                plateTitle.setText("FILETE CON PURÉ");
                textPrize.setText("30");
                break;
            case 20:
                imagePlate.setImageResource(R.drawable.p20);
                plateTitle.setText("AJI DE FIDEO");
                textPrize.setText("18");
                break;
            case 21:
                imagePlate.setImageResource(R.drawable.p21);
                plateTitle.setText("SUSHI SUELTITOS");
                textPrize.setText("12");
                break;
            case 22:
                imagePlate.setImageResource(R.drawable.p22);
                plateTitle.setText("SUSHI COMPLETO");
                textPrize.setText("70");
                break;
            case 23:
                imagePlate.setImageResource(R.drawable.p23);
                plateTitle.setText("AJI DE RACACHA");
                textPrize.setText("15");
                break;
            case 24:
                imagePlate.setImageResource(R.drawable.p24);
                plateTitle.setText("FILETE MIGÑON");
                textPrize.setText("45");
                break;
            case 25:
                imagePlate.setImageResource(R.drawable.p25);
                plateTitle.setText("POCION DE TRUCHA DORADA");
                textPrize.setText("25");
                break;
            case 26:
                imagePlate.setImageResource(R.drawable.p26);
                plateTitle.setText("SILPANCHO");
                textPrize.setText("25");
                break;
            default:
                imagePlate.setImageResource(R.drawable.menu);
                plateTitle.setText("");
                textPrize.setText("");
                break;
        }
    }
}
