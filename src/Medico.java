import java.util.ArrayList;

public class Medico {
    String nome;
    long idade;
    String cpf;
    String crm;
    String especializacao;
        public void Medico(){
            this.nome = "";
            this.idade = 0;
            this.cpf = "";
            this.crm=null;
            this.especializacao= "";
        }
        public void Medico(String nome, long idade, String cpf,String crm, String especializacao ){
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
            this.crm=crm;
            this.especializacao = especializacao;
        }
        //Formato Médico Nome,Idade,CPF,CRM,Especializacao
        public Medico(String info){
            String[] partes= info.split(";");
            this.nome = partes[0];
            this.idade = Long.parseLong(partes[1]);
            this.cpf = partes[2];
            this.crm=partes[3];
            this.especializacao = partes[4];
        }
    public Medico encontrarMedico(String cpf, ArrayList<Medico> lista){
        for (Medico s : lista) {
            if (s.getCpf().trim().equals(cpf)) {
                return s;
            }
        }
        return null;
    }

        public void setNome(String nome){
            this.nome=nome;
        }
        public void setCrm(String crm){this.crm=crm;}
        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
        public void setIdade(long idade) {
            this.idade = idade;
        }
        public void setEspecializacao(String especializacao){this.especializacao=especializacao;}

        public long getIdade(){
            return idade;
        }
        public String getCrm(){return crm;}
        public String getEspecializacao(){return especializacao;}
        public String getCpf() {
            return cpf;
        }
        public String getNome(){
            return nome;
        }



        public void getInfo(){
            System.out.println("==========================");
            System.out.println("Nome : " + getNome());
            System.out.println("Idade : " + getIdade());
            System.out.println("CPF : " + getCpf());
            System.out.println("==========================");
        }
    }
