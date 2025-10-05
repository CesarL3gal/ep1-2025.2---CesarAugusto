import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Internacao {
     private Paciente paciente;
     private LocalDateTime dataEntrada;
     private LocalDateTime dataSaida; // Null se ainda estiver internado
     private double custoTotal;
     private Quarto quarto;

    // CPF;DataEntrada;DataSaida;Quarto;Custo
    public Internacao(Paciente paciente, LocalDateTime entrada, LocalDateTime Saida, double custo, int quarto){
        this.paciente=paciente;
        this.dataEntrada=entrada;
        this.dataSaida=Saida;
        this.custoTotal=custo;
        this.quarto = new Quarto(quarto);
    }
    //Criar uma internação pelo CSV
    // CPF;DataEntrada;DataSaida;Quarto;Custo
        public Internacao(String info, ArrayList<Paciente> listaPaciente){
        // Formato da info Paciente,DataEntrada,DataSaida,Custo,Quarto
        String[] partes = info.split(";");
       try {
           this.paciente = Paciente.encontrarPaciente(partes[0].trim(),listaPaciente);
           try {
               this.dataEntrada = LocalDateTime.parse(partes[1].trim().formatted(CSV.Formata));
               this.dataSaida = LocalDateTime.parse(partes[2].trim().formatted(CSV.Formata));
           }
           catch (DateTimeParseException e){
               System.err.println("Erro na formatação das datas");
           }
           this.quarto = new Quarto(Integer.parseInt(partes[3].trim()));
           this.custoTotal = Double.parseDouble(partes[4].trim());
       }
       catch (DateTimeParseException e ){
           System.err.println("Erro na formatação da Data da internação");
       }
       catch (ArrayIndexOutOfBoundsException e){
           System.err.println("Erro na formatação do CSV");
       }
    }

    public double getCustoFinal(){
        if(paciente == null){
            return 0.0;
        }
        double custo = 20.0;
        double desconto = paciente.getPlanoSaude().getDesconto();
        return custo * (1-desconto);
    }
    public static double getCustoFinal(Paciente paciente){
        if(paciente == null){
            return 0.0;
        }
        double custo = 20.0;
        double desconto = paciente.getPlanoSaude().getDesconto();
        return custo * (1-desconto);
    }

    public Paciente getPaciente(){
        return paciente;
    }
    public Quarto getQuarto(){
        return quarto;
    }
    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }
    public LocalDateTime getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }
    public void setDataSaida(){
        this.dataSaida=LocalDateTime.now();
    }
    public double getCustoTotal() {
        return custoTotal;
    }
    public void getInfo(){
        System.out.println("==========================");
        System.out.println("Paciente : " + getPaciente().getNome() + "  ,Plano : " + getPaciente().getPlanoSaude());
        System.out.println("DataEntrada : " + getDataEntrada());
        System.out.println("DataSaida: " + getDataSaida());
        System.out.println("Custo com desconto : " + getCustoFinal());
        System.out.println("Numero do Quarto : " + getQuarto().getNumero());
        System.out.println("==========================");
    }
}
