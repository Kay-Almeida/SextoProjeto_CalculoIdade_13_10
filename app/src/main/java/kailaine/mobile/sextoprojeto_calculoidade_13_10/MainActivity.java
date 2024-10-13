package kailaine.mobile.sextoprojeto_calculoidade_13_10;
/*
 *@author:<Kailaine Almeida de Souza RA: 1110482313026>
 */
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etDia;
    private EditText etMes;
    private EditText etAno;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etDia = findViewById(R.id.etDia);
        etMes = findViewById(R.id.etMes);
        etAno = findViewById(R.id.etAno);
        tvRes = findViewById(R.id.tvRes);

        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calc());
    }
    private void calc() {
        int diaNasc = Integer.parseInt(etDia.getText().toString());
        int mesNasc = Integer.parseInt(etMes.getText().toString());
        int anoNasc = Integer.parseInt(etAno.getText().toString());

        Calendar hoje = Calendar.getInstance();
        int diaAtual = hoje.get(Calendar.DAY_OF_MONTH);
        int mesAtual = hoje.get(Calendar.MONTH) + 1;
        int anoAtual = hoje.get(Calendar.YEAR);

        int idadeAnos = anoAtual - anoNasc;
        int idadeMeses = mesAtual - mesNasc;
        int idadeDias = diaAtual - diaNasc;

        if (idadeDias < 0) {
            idadeMeses--;

            Calendar cal = Calendar.getInstance();
            cal.set(anoAtual, mesAtual - 2, 1); // Mês anterior
            int diasNoMesAnterior = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            idadeDias += diasNoMesAnterior;
        }

        if (idadeMeses < 0) {
            idadeAnos--;
            idadeMeses += 12;
        }

        tvRes.setText(idadeAnos + " anos, " + idadeMeses + " meses, e " + idadeDias + " dias.");

        etDia.setText("");
        etMes.setText("");
        etAno.setText("");
    }

}