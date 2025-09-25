public class Medico {
    String nome;
    long idade;
    String cpf;
    String telefone;
    long dinheiro;
    String especializacao;
    long preco;
        public void Medico(){
            this.nome = "";
            this.idade = 0;
            this.cpf = "";
            this.telefone = "";
            this.dinheiro = 0;
            this.especializacao= "";
            this.preco = 0;
        }
        public void Medico(String nome, long idade, String cpf, String telefone, long dinheiro, String especializacao, long preco ){
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
            this.telefone = telefone;
            this.dinheiro = dinheiro;
            this.especializacao = especializacao;
            this.preco = preco;
        }

        public Medico(String info){
            String[] partes= info.split(";");
            this.nome = partes[0];
            this.idade = Long.parseLong(partes[2]);
            this.cpf = partes[1];
            this.telefone = partes[3];
            this.dinheiro = Long.parseLong(partes[4]);
            this.especializacao = partes[5];
            this.preco = Long.parseLong(partes[6]);
        }
        public void setNome(String nome){
            this.nome=nome;
        }
        public void setTelefone(String telefone){
            this.telefone=telefone;
        }
        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
        public void setIdade(long idade) {
            this.idade = idade;
        }
        public void setDinheiro(long dinheiro){
            this.dinheiro=dinheiro;
        }
        public void setPreco(long preco){this.preco = preco; }

        public long getIdade(){
            return idade;
        }
        public String getCpf() {
            return cpf;
        }
        public long getDinheiro(){
            return dinheiro;
        }
        public String getNome(){
            return nome;
        }
        public String getTelefone(){
            return telefone;
        }
        public long getPreco()
        {
            return preco;
        }
        public void getInfo(){
            System.out.println("==========================");
            System.out.println("Nome : " + getNome());
            System.out.println("Idade : " + getIdade());
            System.out.println("CPF : " + getCpf());
            System.out.println("Telefone : " + getTelefone());
            System.out.println("Preço = " + getPreco());
            System.out.println("==========================");
        }
    }
