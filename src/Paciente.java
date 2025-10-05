import java.util.ArrayList;
import java.util.List;

public class Paciente{
    String nome;
    int idade;
    String cpf;
    String planoSaude;
    boolean valido;
    ArrayList<Consultas> HistoricoConsulta = new ArrayList<>();
    ArrayList<Internacao> HistoricoInternacao = new ArrayList<>();
    //Construtores
    public Paciente(){
            this.nome = "";
            this.idade = 0;
            this.cpf = "";
            this.planoSaude = "";
            this.valido=false;
    }

    public Paciente(String nome, int idade, String cpf,  String planoSaude ){
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
            this.planoSaude = planoSaude;
            this.valido=true;
    }

    public Paciente(String info){
        try {
            String[] partes= info.split(";");

            if(partes.length != 4){
                throw new Exception("A linha deve conter 4 campos");
            }
            this.nome = partes[0];
            this.cpf = partes[1].trim();
            this.idade = Integer.parseInt(partes[2].trim());
            this.planoSaude = partes[3].trim();
            this.valido = true;
        }
        catch (Exception e){
            System.err.println("Erro na formatação do Paciente");
            //corrigir o nullpointerexception
            this.valido = false;
            this.nome="INVALIDO";
            this.cpf="";
            this.idade=0;
            this.planoSaude="";
        }
    }




    // Funções
    public static String encontrarPessoa(String cpf, ArrayList<Paciente> lista){
        for (Paciente s : lista) {
            if (s.getCpf().trim().equals(cpf)) {
                return s.toString();
            }
        }
        return null;
    }

    public static Paciente encontrarPaciente(String cpf, ArrayList<Paciente> lista){
        for (Paciente s : lista) {
            if (s.getCpf().trim().equals(cpf)) {
                return s;
            }
        }
        return null;
    }

    public static Paciente pegarHistoricoConsulta(Paciente paciente, ArrayList<Consultas> lista){
        for(Consultas c : lista){
            if(paciente.getCpf().trim().equals(c.getPaciente().getCpf().trim())){
                paciente.adcionarConsultaHistorico(c);
            }
        }
        return paciente;
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

    public void adcionarInternacaoHistorico(Internacao internacao){
        this.HistoricoInternacao.add(internacao);
    }
    public void setHistoricoInternacao(ArrayList<Internacao> historicoInternacao){
        this.HistoricoInternacao = historicoInternacao;
    }
    public ArrayList<Internacao> getHistoricoInternacao(){
        return this.HistoricoInternacao;
    }

    public void setNome(String nome){
        this.nome=nome;
    }
    public void setCpf(String cpf) {
            this.cpf = cpf;
        }
    public void setIdade(int idade) {
            this.idade = idade;
        }

    public void setPlanoSaude(String planoSaude){
        this.planoSaude=planoSaude;
    }
    public int getIdade(){
            return idade;
        }
    public String getCpf() {
            return cpf;
        }
    public String getNome(){
            return nome;
        }

    public String getPlanoSaude(){
        return this.planoSaude;
    }

        public void getInfo(){
            System.out.println("==========================");
            System.out.println("Nome : " + getNome());
            System.out.println("Idade : " + getIdade());
            System.out.println("CPF : " + getCpf());
            System.out.println("Plano De Saude : " + getPlanoSaude());
            System.out.println("==========================");
        }
//    }
//        return consultas;
//        }
//            }
//                consultas.add(c);
//            if(c.paciente.getCpf().trim().equals(cpf)){
//        for(Consultas c: consultasTotais){
//        ArrayList<Consultas> consultas = new ArrayList<>();
//        ArrayList<Consultas> consultasTotais = CSV.relerConsulta();
//    public ArrayList<Consultas> getConsultas(String cpf){
//Ordem do CSV Consulta : CPF do Paciente; CPF do Medico; Data; Hora; Motivo
//    public void setAll(String info){
//            String[] partes= info.split(";");
//            this.nome = partes[0];
//            this.cpf = partes[1];
//            this.idade = Integer.parseInt(partes[2]);
//            this.planoSaude = partes[3];
//        }
}
