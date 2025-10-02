import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa{
//        String nome;
//        int idade;
//        String cpf;
//        String telefone;
//        String dinheiro;
    super(nome,idade,cpf,telefone,dinheiro);
        String planoSaude;
        List<Consultas> consultasList= new ArrayList<>();

    public Paciente(){
//            this.nome = "";
//            this.idade = 0;
//            this.cpf = "";
//            this.telefone = "";
//            this.dinheiro = "";
            this.planoSaude = "";
    }
    public Paciente(String nome, int idade, String cpf, String telefone, String dinheiro, String planoSaude ){
//            this.nome = nome;
//            this.idade = idade;
//            this.cpf = cpf;
//            this.telefone = telefone;
//            this.dinheiro = dinheiro;
            this.planoSaude = planoSaude;
    }

    public Paciente(String info){
                String[] partes= info.split(";");
//                this.nome = partes[0];
//                this.cpf = partes[1];
//                this.idade = Integer.parseInt(partes[2]);
//                this.telefone = partes[3];
//                this.dinheiro = partes[5];
                this.planoSaude = partes[4];
    }
    public int plano(String info){
            String[] planos = new String[4];
            planos[0]="Sem Plano"; //00  - 0% de desconto   HAHAHA POBRE MAKE DE L
            planos[1]="Militar";   //01  - 50% de desconto
            planos[2]="Politico";  //02  - 100% de desconto   PEC DA BLINDAGEM HOSPITALAR
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
            for (String s : lista) {
                String[] partes = s.split(";");
                if (partes[1].trim().equals(cpf)) {
                    return true;
                }
            }
        return false;
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
        return "Não encontrado";
    }

        public void setAll(String info){
            String[] partes= info.split(";");
//            this.nome = partes[0];
//            this.cpf = partes[1];
//            this.idade = Integer.parseInt(partes[2]);
//            this.telefone = partes[3];
//            this.dinheiro = partes[5];
            this.planoSaude = partes[4];
        }


//        public void setNome(String nome){
//            this.nome=nome;
//        }
//        public void setTelefone(String telefone){
//            this.telefone=telefone;
//        }
//        public void setCpf(String cpf) {
//            this.cpf = cpf;
//        }
//        public void setIdade(int idade) {
//            this.idade = idade;
//        }
//        public void setDinheiro(String dinheiro){
//            this.dinheiro=dinheiro;
//        }
    public void setPlanoSaude(String planoSaude){
        this.planoSaude=planoSaude;
    }
    public void setConsultasList(List<Consultas> consultasList){
        this.consultasList=consultasList;
    }

//        public int getIdade(){
//            return idade;
//        }
//        public String getCpf() {
//            return cpf;
//        }
//        public String getDinheiro(){
//            return dinheiro;
//        }
//        public String getNome(){
//            return nome;
//        }
//        public String getTelefone(){
//            return telefone;
//        }
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
            System.out.println("Telefone : "+getTelefone());
            System.out.println("==========================");
        }
}
