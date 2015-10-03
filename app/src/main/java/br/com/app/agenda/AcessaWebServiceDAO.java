package br.com.app.agenda;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jr on 30/08/2015.
 */
public class AcessaWebServiceDAO {

    private static final String url = "http://192.168.0.104:8080/AgendaWS/services/ContatoDAO?wsdl";
    private static final String NAMESPACE = "http://agendaws.com.br";
    private static final String cadastrar = "salvarContato";
    private static final String alterar = "atualizarContato";
    private static final String excluirID = "excluirContatoID";
    private static final String excluirContato = "excluirContato";
    private static final String capturarContato = "capturarContato";
    private static final String listarAll = "listarTodos";

    public boolean salvarContato(Contato contato) {

        //enviar objeto do tipo usuario,

        SoapObject cadastroContato = new SoapObject(NAMESPACE, cadastrar);

        SoapObject soapContato = new SoapObject(NAMESPACE, "ctt");

        //soapContato.addProperty("con_cod",contato.getCon_cod());
        soapContato.addProperty("con_nome", contato.getCon_nome());
        soapContato.addProperty("con_numero", contato.getCon_numero());
        soapContato.addProperty("con_email", contato.getCon_email());

        cadastroContato.addProperty("ctt", soapContato);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(cadastroContato);
        envelope.implicitTypes = true;
        envelope.dotNet = true;

        try {
            HttpTransportSE http = new HttpTransportSE(url);
            http.debug = true;
            http.call("urn:" + cadastrar, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

            return Boolean.parseBoolean(resposta.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean atualizarContato(Contato ctt) {

        SoapObject cadastroContato = new SoapObject(NAMESPACE, alterar);

        SoapObject soapContato = new SoapObject(NAMESPACE, "ctt");

        soapContato.addProperty("con_cod",ctt.getCon_cod());
        soapContato.addProperty("con_nome", ctt.getCon_nome());
        soapContato.addProperty("con_numero", ctt.getCon_numero());
        soapContato.addProperty("con_email", ctt.getCon_email());

        cadastroContato.addProperty("ctt", soapContato);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(cadastroContato);
        envelope.implicitTypes = true;
        envelope.dotNet = true;

        try {
            HttpTransportSE http = new HttpTransportSE(url);
            http.debug = true;
            http.call("urn:" + alterar, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

            return Boolean.parseBoolean(resposta.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean excluirContato(Contato ctt) {
        SoapObject cadastroContato = new SoapObject(NAMESPACE, excluirContato);

        SoapObject soapContato = new SoapObject(NAMESPACE, "ctt");

        soapContato.addProperty("con_cod",ctt.getCon_cod());
        soapContato.addProperty("con_nome", ctt.getCon_nome());
        soapContato.addProperty("con_numero", ctt.getCon_numero());
        soapContato.addProperty("con_email", ctt.getCon_email());

        cadastroContato.addProperty("ctt", soapContato);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(cadastroContato);
        envelope.implicitTypes = true;
        envelope.dotNet = true;

        try {
            HttpTransportSE http = new HttpTransportSE(url);
            http.debug = true;
            http.call("urn:" + excluirContato, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

            return Boolean.parseBoolean(resposta.toString());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Contato> listarTodos() {
        ArrayList<Contato> listC = new ArrayList<>();

        SoapObject listarContatos = new SoapObject(NAMESPACE, listarAll);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarContatos);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(url);
        try {

            http.call("urn:" + listarAll, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;


            int count = result.getPropertyCount();

            for (int i = 0; i < count; i++) {

                Object soapObj = result.getProperty(i);

                if (soapObj instanceof SoapObject) {

                    SoapObject coluna = (SoapObject) soapObj;
                    Contato contato = new Contato();

                    contato.setCon_cod(Integer.parseInt(coluna.getProperty("con_cod").toString()));
                    contato.setCon_nome(coluna.getProperty("con_nome").toString());
                    contato.setCon_numero(coluna.getProperty("con_numero").toString());
                    contato.setCon_email(coluna.getProperty("con_email").toString());
                    listC.add(contato);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

        return listC;
    }

    public Contato capturarContato(int id) {
        Contato contato = null;

        SoapObject capturarCtt = new SoapObject(NAMESPACE, capturarContato);
        capturarCtt.addProperty("con_cod",id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(capturarCtt);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(url);
        try {

            http.call("urn:" + capturarContato, envelope);

            SoapObject result = (SoapObject) envelope.getResponse();

                    contato = new Contato();

                    contato.setCon_cod(Integer.parseInt(result.getProperty("con_cod").toString()));
                    contato.setCon_nome(result.getProperty("con_nome").toString());
                    contato.setCon_numero(result.getProperty("con_numero").toString());
                    contato.setCon_email(result.getProperty("con_email").toString());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

        return contato;
    }

    public boolean excluirContatoID(int id) {
        return excluirContato(new Contato(id, "", "", ""));
    }

}
