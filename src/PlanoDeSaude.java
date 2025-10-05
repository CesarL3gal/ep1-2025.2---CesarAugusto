public enum PlanoDeSaude {
    MILITAR(1.0),
    CIVIL(0.2),
    NENHUM(0.0),
    PREMIUM(0.5);

    private final double desconto;

    PlanoDeSaude(double desconto){
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }
}
