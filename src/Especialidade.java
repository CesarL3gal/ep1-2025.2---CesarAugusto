public enum Especialidade {
    Cardiologia("Cardiologia"),
    Pediatria("Pediatria"),
    Ortopedia("Ortopedia"),
    Clinica("Clinica"),
    Neurologista("Neurologista"),
    Dermatologista("Dermatologista");
    final String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}
