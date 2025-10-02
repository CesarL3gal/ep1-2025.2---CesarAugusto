public class Pessoa {
    String nome;
    int idade;
    String cpf;
    String telefone;
    long dinheiro;
    public Pessoa(){
        this.nome=null;
        this.idade=0;
        this.cpf=null;
        this.telefone=null;
        this.dinheiro=0;
    }
    public Pessoa(String nome,int idade, String cpf, String telefone, long dinheiro){
        this.nome=nome;
        this.idade=idade;
        this.cpf=cpf;
        this.telefone=telefone;
        this.dinheiro=dinheiro;
    }
}
