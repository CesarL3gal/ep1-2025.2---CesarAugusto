package Pessoas.Medico;

import Consultas.Consultas;
import Pessoas.Pessoa;
import Pessoas.Medico.Especialidade;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Medico extends Pessoa {
    private String crm;
    private Especialidade especializacao;
    private double custoConsulta;
    private boolean valido;
    private ArrayList<Consultas> HistoricoConsulta = new ArrayList<>();

    //Construtores
    public Medico(){
        this.nome="";
        this.idade=0;
        this.cpf="";
        this.crm = "";
        this.especializacao = null;
        this.custoConsulta = 0.0;
        this.valido = false;
    }

        public Medico(String nome, int idade, String cpf, String crm, Especialidade especializacao, double custoConsulta ){
            super(nome,idade,cpf);
            this.idade = idade;
            this.crm = crm;
            this.especializacao = especializacao;
            this.custoConsulta = custoConsulta;
            this.valido = true;
        }

        //Formato Médico Nome,CPF,CRM,Idade,Especializacao;CustoConsulta
        public Medico(String info) {
            String[] partes = info.split(";");
            if (partes.length == 6) {
                try {
                    this.nome = partes[0];
                    this.cpf = partes[1].trim();
                    this.crm = partes[2].trim();
                    this.idade = Integer.parseInt(partes[3].trim());
                    this.especializacao = Especialidade.valueOf(partes[4].trim());
                    this.custoConsulta = Double.parseDouble(partes[5].trim());
                    this.valido = true;
                } catch (Exception e) {
                    System.err.println("Erro na formatação do Médico " + info);
                    this.nome = "Invalido";
                    this.idade = 0;
                    this.cpf = "";
                    this.crm = "";
                    this.especializacao = null;
                    this.custoConsulta = 0.0;
                    this.valido = false;
                }
            }
            else{
                System.err.println("Formatação invalida");
                this.valido=false;
            }
        }

        //Função
    public static Medico encontrarMedico(String cpf, ArrayList<Medico> lista){
        for (Medico s : lista) {
            if (s.getCpf() != null && s.getCpf().trim().equals(cpf)) {
                return s;
            }
        }
        return null;
    }
    public static Medico encontrarMedicoPorCrm(String crm, ArrayList<Medico> lista){
        for (Medico s : lista) {
            if (s.getCrm() != null && s.getCrm().trim().equals(crm)) {
                return s;
            }
        }
        return null;
    }
    public static Medico pegarHistoricoConsulta(Medico medico, ArrayList<Consultas> lista){
            medico.getHistoricoConsulta().clear(); // limpa o historico pra evitar erros
       for(Consultas c : lista){
           if(medico != null && c.getMedico()!= null && medico.getCrm().equals(c.getMedico().getCrm())){
             medico.adcionarConsultaHistorico(c);
           }
       }
       return medico;
    }

    //False - horario não disponivel
    //True - horario disponivel
    public static boolean horaDisponivel(Medico medico, LocalDateTime data){
            for(Consultas consulta : medico.getHistoricoConsulta()){
                if(data.isBefore(consulta.getData().plusMinutes(30)) && data.isAfter(consulta.getData())){
                    return false;
                }
            }
            return true;
    }

    public void adcionarConsultaHistorico(Consultas consulta){
        this.HistoricoConsulta.add(consulta);
    }
    public void setHistoricoConsulta(ArrayList<Consultas> historicoConsulta){
        this.HistoricoConsulta = historicoConsulta;
    }
    public ArrayList<Consultas> getHistoricoConsulta(){
        return this.HistoricoConsulta;
    }
    public void getInfo(){
            System.out.println("==========================");
            System.out.println("Nome : " + getNome());
            System.out.println("Idade : " + getIdade());
            System.out.println("CPF : " + getCpf());
            System.out.println("CRM : " + getCrm());
            System.out.println("Especialização : " + getEspecializacao());
            System.out.printf("Custo da Consulta : R$ %.2f \n", getCustoConsulta());
            System.out.println("Números de Consultas : " + getHistoricoConsulta().size());
            System.out.println("==========================");
    }

    //Setters
    public void setCrm(String crm){this.crm=crm;}
    public void setCustoConsulta(double custoConsultaconsulta){
            this.custoConsulta=custoConsultaconsulta;
    }
    public void setEspecializacao(Especialidade especializacao){this.especializacao=especializacao;}
    //Getters
    public boolean getValido(){
            return this.valido;
    }
    public String getCrm(){return this.crm;}
    public Especialidade getEspecializacao(){return this.especializacao;}
    public double getCustoConsulta(){
            return this.custoConsulta;
    }
}
