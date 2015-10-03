package br.com.app.agenda;

/**
 * Created by Jr on 30/08/2015.
 */
public class Contato {

    private int con_cod;
    private String con_nome;
    private String con_numero;
    private String con_email;

    public Contato() {
        // TODO Auto-generated constructor stub
    }


    public Contato(int con_cod, String con_nome, String con_numero,
                   String con_email) {
        super();
        this.con_cod = con_cod;
        this.con_nome = con_nome;
        this.con_numero = con_numero;
        this.con_email = con_email;
    }


    public int getCon_cod() {
        return con_cod;
    }

    public void setCon_cod(int con_cod) {
        this.con_cod = con_cod;
    }

    public String getCon_nome() {
        return con_nome;
    }

    public void setCon_nome(String con_nome) {
        this.con_nome = con_nome;
    }

    public String getCon_numero() {
        return con_numero;
    }

    public void setCon_numero(String con_numero) {
        this.con_numero = con_numero;
    }

    public String getCon_email() {
        return con_email;
    }

    public void setCon_email(String con_email) {
        this.con_email = con_email;
    }


    @Override
    public String toString() {
        return "\nNome: " + con_nome + "\nNumero: " + con_numero + "\nEmail: " + con_email + "\n\n";
    }


}
