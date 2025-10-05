import java.time.LocalDateTime;
import java.util.ArrayList;

public class Medico {
    String nome;
    int idade;
    String cpf;
    String crm;
    Especialidade especializacao;
    double custoConsulta;
    boolean valido;
    ArrayList<Consultas> HistoricoConsulta = new ArrayList<>();

        //Construtores
        public Medico(){
            this.nome = "";
            this.idade = 0;
            this.cpf = "";
            this.crm= "";
            this.especializacao= null;
            this.valido=false;
        }

        public Medico(String nome, int idade, String cpf,String crm, Especialidade especializacao ){
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
            this.crm=crm;
            this.especializacao = especializacao;
            this.valido=true;
        }

        //Formato Médico Nome,Idade,CPF,CRM,Especializacao
        public Medico(String info){
            String[] partes= info.split(";");
            try {
                this.nome = partes[0];
                this.cpf = partes[1];
                this.crm = partes[2];
                this.idade = Integer.parseInt(partes[3].trim());
                this.especializacao = Especialidade.valueOf(partes[4]);
                this.valido=true;
            }
            catch (Exception e){
                System.err.println("Erro na formatação do Médico");
                this.nome="Invalido";
                this.idade=0;
                this.cpf="";
                this.crm="";
                this.especializacao=null;
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
       for(Consultas c : lista){
           if(medico!= null && c.getMedico()!= null && medico.getCrm().equals(c.getMedico().getCrm())){
             medico.adcionarConsultaHistorico(c);
           }
       }
       return medico;
    }

    //False - horario não disponivel
    //True -horario disponivel
    public static boolean horaDisponivel(Medico medico, LocalDateTime data){
            for(Consultas consulta : medico.getHistoricoConsulta()){
                if(data.isBefore(consulta.getData().plusMinutes(30))){
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
            System.out.println("==========================");
        }

    public void setNome(String nome){
        this.nome=nome;
    }
    public void setCrm(String crm){this.crm=crm;}
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setEspecializacao(Especialidade especializacao){this.especializacao=especializacao;}

    public int getIdade(){
        return idade;
    }
    public String getCrm(){return crm;}
    public Especialidade getEspecializacao(){return especializacao;}
    public String getCpf() {
        return cpf;
    }
    public String getNome(){
        return nome;
    }
}
