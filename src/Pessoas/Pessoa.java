package Pessoas;

import Pessoas.Medico.Medico;
import Pessoas.Paciente.Paciente;

import java.util.ArrayList;

public class Pessoa {
    protected String nome;
    protected int idade;
    protected String cpf;
    public Pessoa(){
        this.nome="";
        this.idade=0;
        this.cpf="";
    }
    public Pessoa(String nome, int idade, String cpf){
        this.nome=nome;
        this.idade=idade;
        this.cpf=cpf;
    }
    public Pessoa(String info){
        String[] partes = info.split(";");
        try {
            this.nome = partes[0];
            this.idade = Integer.parseInt(partes[1]);
            this.cpf = partes[2];
        }
        catch (Exception e){
            System.err.println("Erro na formatação da Pessoa");
            this.nome="";
            this.cpf="";
            this.idade=0;
        }
    }
    //true é que achou
    //false não achou

    public static boolean CompararCpf(String cpf, ArrayList<Paciente> listaPaciente, ArrayList<Medico> listaMedico){
        for(Paciente s: listaPaciente){
            if(s.getCpf().trim().equals(cpf)){
                return true;
            }
        }
        for(Medico c : listaMedico){
            if(c.getCpf().trim().equals(cpf)){
                return true;
            }
        }
        return false;
    }


    public String getNome(){return nome;}
    public int getIdade(){return idade;}
    public String getCpf(){return cpf;}

    public void setNome(String nome){this.nome=nome;}
    public void setIdade(int idade){this.idade=idade;}
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
