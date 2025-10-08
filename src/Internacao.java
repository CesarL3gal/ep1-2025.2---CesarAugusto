import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Internacao {
     private Paciente paciente;
     private LocalDateTime dataEntrada;
     private LocalDateTime dataSaida; // Null se ainda estiver internado
     private double custoTotal;
     private Quarto quarto;

    // CPF;DataEntrada;DataSaida;Quarto;Custo
    public Internacao(Paciente paciente, LocalDateTime entrada, LocalDateTime saida, double custo, int quarto){
        this.paciente=paciente;
        this.dataEntrada=entrada;
        this.dataSaida= saida;
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
        if(this.paciente == null){
            System.err.println("Paciente não encontrado");
            return;
        }
           try {
               this.dataEntrada = LocalDateTime.parse(partes[1].trim());
               if(partes[2]!=null && !partes[2].trim().isEmpty()) {
                   this.dataSaida = LocalDateTime.parse(partes[2].trim());
               }
               else{
                   this.dataSaida = null;
               }
           }
           catch (DateTimeParseException e){
               System.err.println("Erro na formatação das datas");
           }
           this.quarto = new Quarto(Integer.parseInt(partes[3].trim()));
           if(this.dataSaida!=null){
               this.quarto.setDesocupado();
           }
           this.custoTotal = Double.parseDouble(partes[4].trim());
       }
       catch (DateTimeParseException e ){
           System.err.println("Erro na formatação da Data da internação");
       }
       catch (ArrayIndexOutOfBoundsException e){
           System.err.println("Erro na formatação do CSV");
       }
       catch (Exception e) {
            System.err.println("Algo deu errado na criação da Internação");
       }
    }

    public static double getCustoDiario(Paciente paciente){
        double custo = 150.0;
        double desconto = paciente.getPlanoSaude().getDesconto();
        if(paciente.getIdade()>=60){
            return custo * (1 - 0.9);
        }
        return custo * (1 - desconto);
    }

    //Setters e Getters
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
        this.dataSaida= dataSaida;
    }
    //@Override
    public void setDataSaida(){
        this.dataSaida=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }
    public double getCustoTotal() {
        return custoTotal;
    }
    public void getInfo(){
        System.out.println("==========================");
        System.out.println("Paciente : " + getPaciente().getNome() + "  ,Plano : " + getPaciente().getPlanoSaude());
        System.out.println("DataEntrada : " + getDataEntrada() + " ,DataSaida: " + getDataSaida());
        if(getDataSaida()!=null){
            long diasInternado = ChronoUnit.DAYS.between(getDataEntrada(),getDataSaida());
            System.out.println("Dias internado : " + diasInternado);
        }
        System.out.printf("Custo Diario da Internação : R$ %.2f\n", getCustoDiario(getPaciente()));
        System.out.println("Numero do Quarto : " + getQuarto().getNumero());
        System.out.println("==========================");
    }
}
