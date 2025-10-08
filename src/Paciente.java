import java.util.ArrayList;

public class Paciente extends Pessoa{
    private PlanoDeSaude planoSaude;
    private boolean valido;
    private ArrayList<Consultas> historicoConsulta = new ArrayList<>();
    private ArrayList<Internacao> historicoInternacao = new ArrayList<>();
    //Construtores
    public Paciente(){
            this.nome = "";
            this.idade = 0;
            this.cpf = "";
            this.planoSaude = PlanoDeSaude.NENHUM;
            this.valido=false;
    }

    public Paciente(String nome, int idade, String cpf,  PlanoDeSaude planoSaude ){
            super(nome,idade,cpf);
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
            this.planoSaude = PlanoDeSaude.valueOf(partes[3].trim().toUpperCase());
            this.valido = true;
        }
        catch (Exception e){
            System.err.println("Erro na formatação do Paciente");
            //corrigir o nullpointerexception
            this.valido = false;
            this.nome="INVALIDO";
            this.cpf="";
            this.idade=0;
            this.planoSaude=PlanoDeSaude.NENHUM;
        }
    }




    // Funções
//    public static String encontrarPessoa(String cpf, ArrayList<Paciente> lista){
//        for (Paciente s : lista) {
//            if (s.getCpf().trim().equals(cpf)) {
//                return s.toString();
//            }
//        }
//        return null;
//    }

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
            if(paciente != null && c.getPaciente() !=null && paciente.getCpf().trim().equals(c.getPaciente().getCpf().trim())){
                paciente.adcionarConsultaHistorico(c);
            }
        }
        return paciente;
    }

    public void adcionarConsultaHistorico(Consultas consulta){
        this.historicoConsulta.add(consulta);
    }
    public void setHistoricoConsulta(ArrayList<Consultas> historicoConsulta){
        this.historicoConsulta = historicoConsulta;
    }
    public ArrayList<Consultas> getHistoricoConsulta(){
        return this.historicoConsulta;
    }

    public static Paciente pegarHistoricoInternacao(Paciente paciente, ArrayList<Internacao> listaInternacao){
        for(Internacao i : listaInternacao){
            if(paciente!=null && i.getPaciente()!=null && paciente.getCpf().trim().equals(i.getPaciente().getCpf().trim())){
                paciente.adcionarInternacaoHistorico(i);
            }
        }
        return paciente;
    }

    public void adcionarInternacaoHistorico(Internacao internacao){
        this.historicoInternacao.add(internacao);
    }
    public void setHistoricoInternacao(ArrayList<Internacao> historicoInternacao){
        this.historicoInternacao = historicoInternacao;
    }
    public ArrayList<Internacao> getHistoricoInternacao(){
        return this.historicoInternacao;
    }

    public PlanoDeSaude getPlanoSaude(){
        return this.planoSaude;
    }

        public void getInfo(){
            System.out.println("==========================");
            System.out.println("Nome : " + getNome());
            System.out.println("Idade : " + getIdade());
            System.out.println("CPF : " + getCpf());
            System.out.println("Plano De Saude : " + getPlanoSaude());
            System.out.println("Número de consultas : " + getHistoricoConsulta().size());
            System.out.println("==========================");
        }




    public void setValido(boolean valido) {
        this.valido = valido;
    }
    public void setPlanoSaude(PlanoDeSaude planoSaude){
        this.planoSaude=planoSaude;
    }
    public boolean getValido(){
        return this.valido;
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
