package com.navig.res_platinum;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReporteActivity extends AppCompatActivity {
    private TextView textPlates, textTotal;
    private TableLayout tableLayout;
    private HashMap<String, Integer> preciosPlatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        referenciar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ArrayList<String> nombresPlatos = bundle.getStringArrayList("nombresPlatos");
            HashMap<String, Integer> cantidades = (HashMap<String, Integer>) bundle.getSerializable("cantidades");
            TableLayout tableLayout = findViewById(R.id.tablePro);
            if (nombresPlatos != null) {
                for (int i = 0; i < nombresPlatos.size(); i++) {
                    String nombrePlato = nombresPlatos.get(i);
                    int cantidad = cantidades.get(nombrePlato);
                    TableRow tableRow = new TableRow(this);
                    TextView nombreTextView = new TextView(this);
                    nombreTextView.setText(nombrePlato);
                    TextView precioTextView = new TextView(this);
                    precioTextView.setText(String.valueOf(calcularPrecio(nombrePlato)));
                    TextView cantidadTextView = new TextView(this);
                    cantidadTextView.setText(String.valueOf(cantidad));
                    TextView totalTextView = new TextView(this);
                    totalTextView.setText(String.valueOf(cantidad * calcularPrecio(nombrePlato)));
                    tableRow.addView(nombreTextView);
                    tableRow.addView(precioTextView);
                    tableRow.addView(cantidadTextView);
                    tableRow.addView(totalTextView);
                    tableLayout.addView(tableRow);
                }
            }
            double totalFactura = calcularTotalFactura(cantidades, preciosPlatos);
            textTotal.setText("Total a pagar: " + totalFactura);

        }
    }
    public double calcularTotalFactura(HashMap<String, Integer> cantidades, HashMap<String, Integer> preciosPlatos) {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : cantidades.entrySet()) {
            String nombrePlato = entry.getKey();
            int cantidadPlato = entry.getValue();
            int precioPlato = preciosPlatos.get(nombrePlato);
            total += cantidadPlato * precioPlato;
        }
        return total;
    }

    private int calcularPrecio(String nombrePlato) {
        if (preciosPlatos == null) {
            preciosPlatos = new HashMap<>();
            // Llena la tabla con los precios de cada plato
            preciosPlatos.put("CHARQUEKAN", 25);
            preciosPlatos.put("PAPA LA HUANCAYNA", 30);
            preciosPlatos.put("MAJADITO", 15);
            preciosPlatos.put("PIQUE MACHO", 50);
            preciosPlatos.put("HAMBURGUESA SIMPLE", 18);
            preciosPlatos.put("LOMO MONTADO", 20);
            preciosPlatos.put("PLATO PACEÑO", 30);
            preciosPlatos.put("SAJTA DE POLLO", 25);
            preciosPlatos.put("MILANESA DE CARNE", 25);
            preciosPlatos.put("RAMEN CON POLLO", 20);
            preciosPlatos.put("POLLO DORADO", 50);
            preciosPlatos.put("SALCHIPAPA", 18);
            preciosPlatos.put("PULPO AL GUSTO", 45);
            preciosPlatos.put("CHICHARRON POLLO", 30);
            preciosPlatos.put("SOPITA DE FIDEO", 10);
            preciosPlatos.put("CHICHARRON CERDO", 40);
            preciosPlatos.put("ISPI", 25);
            preciosPlatos.put("CHAIRO", 20);
            preciosPlatos.put("FILETE CON PURÉ", 30);
            preciosPlatos.put("AJI DE FIDEO", 18);
            preciosPlatos.put("SUSHI SUELTITOS", 12);
            preciosPlatos.put("SUSHI COMPLETO", 70);
            preciosPlatos.put("AJI DE RACACHA", 15);
            preciosPlatos.put("FILETE MIGÑON", 45);
            preciosPlatos.put("POCION DE TRUCHA DORADA", 25);
            preciosPlatos.put("SILPANCHO", 25);
        }
        return preciosPlatos.get(nombrePlato);
    }
    public void referenciar() {
        textPlates = findViewById(R.id.textView4);
        tableLayout = findViewById(R.id.tablePro);
        textTotal = findViewById(R.id.textView5);
    }
}