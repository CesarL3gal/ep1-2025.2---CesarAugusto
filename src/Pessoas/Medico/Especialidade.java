package Pessoas.Medico;

public enum Especialidade {
    Cardiologia("Cardiologia"),
    Pediatra("Pediatra"),
    Ortopedia("Ortopedia"),
    Clinica("Clinica"),
    Neurologia("Neurologia"),
    Dermatologia("Dermatologia");
    final String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}
