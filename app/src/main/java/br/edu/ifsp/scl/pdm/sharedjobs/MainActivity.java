package br.edu.ifsp.scl.pdm.sharedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Objetos de blinding com as Views
    private EditText nomeCompletoEt;
    private EditText emailEt;
    private EditText telefoneRg;
    private RadioButton comercialRb;
    private RadioButton residencialRb;
    private CheckBox celularCheckBox;
    private EditText celularEt;
    private RadioGroup sexoRg;
    private EditText dateEd;
    private Spinner formacaoSp;
    private LinearLayout campusLl;
    private EditText anoFormaturaEt;
    private EditText anoConclusaoEt;
    private EditText instituiçãoEt;
    private EditText conclusãoGraduacaoEt;
    private EditText instituiçãoEnsinoEt;
    private EditText tituloEt;
    private EditText orientadorEt;
    private EditText vagasEt;
    private RadioGroup telSelecionadoRg;

    private RadioButton masculinoRb;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ligando (binding) objetos com as views
        bindViews();

        //Listener de item selecionado
        formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (((TextView) view).getText().equals("Fundamental e Médio")){
                    anoFormaturaEt.setVisibility(View.VISIBLE);
                    anoConclusaoEt.setVisibility(View.GONE);
                    instituiçãoEt.setVisibility(View.GONE);
                    conclusãoGraduacaoEt.setVisibility(View.GONE);
                    instituiçãoEnsinoEt.setVisibility(View.GONE);
                    tituloEt.setVisibility(View.GONE);
                    orientadorEt.setVisibility(View.GONE);
                    }

                else if (((TextView) view).getText().equals("Graduaçãoe  especialização")){
                    anoFormaturaEt.setVisibility(View.GONE);
                    anoConclusaoEt.setVisibility(View.VISIBLE);
                    instituiçãoEt.setVisibility(View.VISIBLE);
                    conclusãoGraduacaoEt.setVisibility(View.GONE);
                    instituiçãoEnsinoEt.setVisibility(View.GONE);
                    tituloEt.setVisibility(View.GONE);
                    orientadorEt.setVisibility(View.GONE);
            }
                else if (((TextView) view).getText().equals("Mestrado e doutorado")){
                    anoFormaturaEt.setVisibility(View.GONE);
                    anoConclusaoEt.setVisibility(View.GONE);
                    instituiçãoEt.setVisibility(View.GONE);
                    conclusãoGraduacaoEt.setVisibility(View.VISIBLE);
                    instituiçãoEnsinoEt.setVisibility(View.VISIBLE);
                    tituloEt.setVisibility(View.VISIBLE);
                    orientadorEt.setVisibility(View.VISIBLE);
                }
        }

            //Watcher para nome completo
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        campusLl.setVisibility(View.GONE);
        campusLl.setVisibility(View.VISIBLE);
    }

    private void bindViews(){
        nomeCompletoEt =findViewById(R.id.nomeCompletoEt);
        emailEt = findViewById(R.id.emailEt);
        telefoneRg = findViewById(R.id.telefoneRg);
        comercialRb = findViewById(R.id.comercialRb);
        residencialRb = findViewById(R.id.residencialRb);
        celularCheckBox = findViewById(R.id.celularCheckBox);
        celularEt = findViewById(R.id.celularEt);
        sexoRg = findViewById(R.id.sexoRg);
        dateEd = findViewById(R.id.dateEt);
        formacaoSp = findViewById(R.id.formacaoSp);
        campusLl =findViewById(R.id.campusLl);
        anoFormaturaEt = findViewById(R.id.anoFormaturaEt);
        anoConclusaoEt = findViewById(R.id.anoConclusaoEt);
        instituiçãoEt = findViewById(R.id.instituiçãoEt);
        conclusãoGraduacaoEt = findViewById(R.id.conclusãoGraduacaoEt);
        instituiçãoEnsinoEt = findViewById(R.id.instituiçãoEnsinoEt);
        tituloEt = findViewById(R.id.tituloEt);
        orientadorEt = findViewById(R.id.orientadorEt);
        vagasEt = findViewById(R.id.vagasEt);
        telSelecionadoRg = findViewById(R.id.telSelecionadoRg);

        masculinoRb = findViewById(R.id.masculinoRb);

    }

    private void cleanForm(){
        nomeCompletoEt.setText("");
        emailEt.setText("");
        telefoneRg.setText("");
        celularEt.setText("");
        masculinoRb.setChecked(true);
        dateEd.setText("");
        formacaoSp.setSelection(0);
        anoFormaturaEt.setText("");
        anoConclusaoEt.setText("");
        instituiçãoEt.setText("");
        conclusãoGraduacaoEt.setText("");
        instituiçãoEnsinoEt.setText("");
        tituloEt.setText("");
        orientadorEt.setText("");
        vagasEt.setText("");
    }

    private void saveForm(){
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(nomeCompletoEt.getText().toString()).append("\n");
        sumarioSb.append("Email: ").append(emailEt.getText().toString()).append("\n");
        getPhone();
        sumarioSb.append("Telefone: ").append(phone).append("\n");
        if(celularCheckBox.isChecked()){
            sumarioSb.append("Celular: ").append(celularEt.getText().toString()).append("\n");
        }
        sumarioSb.append("Data de nascimento: ").append(dateEd.getText().toString()).append("\n");

        sumarioSb.append("Formação: ").append(((TextView) formacaoSp.getSelectedView()).getText()).append("\n");
        int fundeMed = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Fundamental e médio");
        if(fundeMed == formacaoSp.getSelectedItemPosition()){
            sumarioSb.append("Ano de formatura:")
                    .append(anoFormaturaEt.getText().toString()).append("\n");
        }
        int gradEsp = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Graduação e especiação");
        if(gradEsp == formacaoSp.getSelectedItemPosition()){
            sumarioSb.append("Ano de conclusão:")
                    .append(anoConclusaoEt.getText().toString()).append("\n");
            sumarioSb.append("Instituição de ensino :")
                    .append(instituiçãoEt.getText().toString()).append("\n");
        }
        int mrsDoc = Arrays.asList(getResources().getStringArray(R.array.formacao)).indexOf("Mestrado e doutorado");
        if(mrsDoc == formacaoSp.getSelectedItemPosition()){
            sumarioSb.append("Ano de conclusão:")
                    .append(conclusãoGraduacaoEt.getText().toString()).append("\n");
            sumarioSb.append("Instituição de ensino:")
                    .append(instituiçãoEnsinoEt.getText().toString()).append("\n");
            sumarioSb.append("Título de monografia:")
                    .append(tituloEt.getText().toString()).append("\n");
            sumarioSb.append("Orientador:")
                    .append(orientadorEt.getText().toString()).append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (sexoRg.getCheckedRadioButtonId()){
            case R.id.masculinoRb:
                sumarioSb.append("Masculino");
                break;
            case R.id.femininoRb:
                sumarioSb.append("Feminino");
                break;
            default:
                break;
        }
        sumarioSb.append("\n");

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onClickButton(View view) {
        switch (view.getId()){
            case R.id.salvarBt:
                saveForm();
                break;
            case R.id.limparBt:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void getPhone(){
        switch (telSelecionadoRg.getCheckedRadioButtonId()){
            case R.id.residencialRb:
                phone = "Residencial";
                break;
            case R.id.comercialRb:
                phone = "Comercial";
                break;
        }
    }

    public void checkCelular(View view){
        if(celularCheckBox.isChecked()) {
            celularEt.setVisibility(View.VISIBLE);
        }
        else{
            celularEt.setVisibility(View.GONE);
            celularEt.setText("");
        }
    }
}