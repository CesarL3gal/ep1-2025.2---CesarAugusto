import java.util.ArrayList;
import java.util.List;

public class Paciente{
    String nome;
    int idade;
    String cpf;
    String planoSaude;
    List<Consultas> consultasList= new ArrayList<>();
    List<Internacao> internacaoList = new ArrayList<>();
    //teste
    //Construtores
    public Paciente(){
            this.nome = "";
            this.idade = 0;
            this.cpf = "";
            this.planoSaude = "";
    }

    public Paciente(String nome, int idade, String cpf,  String planoSaude ){
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
            this.planoSaude = planoSaude;
    }

    public Paciente(String info){
                String[] partes= info.split(";");
                try {
                    this.nome = partes[0];
                    this.cpf = partes[1].trim();
                    this.idade = Integer.parseInt(partes[2]);
                    this.planoSaude = partes[3].trim();
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("Erro na formatação do Paciente");
                }
    }

    // Funções
    public int plano(String info){
            String[] planos = new String[5];
            planos[0]="Sem Plano"; //00  - 0% de desconto
            planos[1]="Militar";   //01  - 50% de desconto
            planos[2]="Politico";  //02  - 100% de desconto
            planos[3]="Especial";  //03  - 100% de desconto
            planos[4]="Particular";//04  - 30% de desconto

            if(info.equals(planos[1])){
                return 1;
            }
            else if (info.equals(planos[2])) {
                return 2;
            }
            else if (info.equals(planos[3])) {
                return 3;
            } else if (info.equals(planos[4])) {
                return 4;
            } else{
                return 0;
            }
        }


    public boolean compCpf(String cpf,ArrayList<String> lista){
        try {
            for (String s : lista) {
                String[] partes = s.split(";");
                if (partes[1].trim().equals(cpf)) {
                    return true;
                }
            }
            return false;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return true;
        }
    }
    public String pegaCpf(String info){
        String[] partes = info.split(";");
        return partes[1];
    }



    //Ordem do CSV Consulta : Paciente; CPF do Paciente; Medico; CPF do Medico; Data; Hora; Motivo
    public ArrayList<String> getConsultas(String cpf){
        ArrayList<String> consultasTotais = CSV.relerConsulta();
        ArrayList<String> consultas = new ArrayList<>();
        for(String s: consultasTotais){
            String[] partes = s.split(";");
            if(partes[1].trim().equals(cpf)){
                consultas.add(s);
            }
        }
        return consultas;
    }

    public String encontrarPessoa(String cpf, ArrayList<String> lista){
        for (String s : lista) {
            String[] partes = s.split(";");
            if (partes[1].trim().equals(cpf)) {
                return s;
            }
        }
        return null;
    }

    public Paciente encontrarPaciente(String cpf, ArrayList<Paciente> lista){
        for (Paciente s : lista) {
            if (s.getCpf().trim().equals(cpf)) {
                return s;
            }
        }
        return null;
    }

        public void setAll(String info){
            String[] partes= info.split(";");
            this.nome = partes[0];
            this.cpf = partes[1];
            this.idade = Integer.parseInt(partes[2]);
            this.planoSaude = partes[3];
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
        public void setConsultasList(List<Consultas> consultasList){
        this.consultasList=consultasList;
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
        public List<Consultas> getConsultasList(){
        return this.consultasList;
    }


        public void getInfo(){
            System.out.println("==========================");
            System.out.println("Nome : " + getNome());
            System.out.println("Idade : " + getIdade());
            System.out.println("CPF : " + getCpf());
            System.out.println("==========================");
        }
}
