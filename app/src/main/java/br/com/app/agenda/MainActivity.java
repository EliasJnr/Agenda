package br.com.app.agenda;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar,btnListar;
    AcessaWebServiceDAO contatodao=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnCadastrar=(Button)findViewById(R.id.btn_cadastrar);
        btnListar=(Button)findViewById(R.id.btn_listar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contatodao = new AcessaWebServiceDAO();

                boolean resultado = contatodao.salvarContato(new Contato(0,"truuueeee","34346","m34342df@jfudf.com"));

                Toast.makeText(MainActivity.this,"Resultado: "+resultado,Toast.LENGTH_SHORT).show();
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contatodao = new AcessaWebServiceDAO();
               // ArrayList<Contato> strContato = contatodao.listarTodos();
                //Log.i("LISTA: ",""+strContato.toString());
               //Toast.makeText(MainActivity.this,"Lista: "+strContato.toString(),Toast.LENGTH_LONG).show();
                //Contato contato = contatodao.capturarContato(3);
                //Toast.makeText(MainActivity.this,"Lista: "+contato.toString(),Toast.LENGTH_LONG).show();
             //  Boolean saber = contatodao.atualizarContato(new Contato(2,"jose","666","jose@gmail.com"));
               // Toast.makeText(MainActivity.this,"Lista: "+saber.toString(),Toast.LENGTH_LONG).show();
                boolean excluir = contatodao.excluirContatoID(9);
                Toast.makeText(MainActivity.this,"aaa: "+excluir,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* public class AcessaTask extends AsyncTask<String, Void, Void> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Salvando...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... args) {
            AcessaWebServiceDAO dao = new AcessaWebServiceDAO();
            Contato ctt = new Contato();
            //ctt.setCon_cod(2343);
            ctt.setCon_nome("murilo");
            ctt.setCon_numero("92837234");
            ctt.setCon_email("murilo@gmail.com");
            boolean resultado = dao.salvarContato(ctt);
            Log.d("EXEMPLOOO: ",resultado+" ");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(dialog.isShowing()){
                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Salvo!!",Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
