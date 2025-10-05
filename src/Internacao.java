import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Internacao {
    private Paciente paciente;
    private Medico medico;
    private LocalDate dataEntrada;
    private LocalDate dataSaida; // Null se ainda estiver internado
    private double custoTotal;
    private Quarto quarto;


    public Internacao(Paciente paciente, Medico medico, LocalDate entrada, LocalDate Saida, double custo, int quarto){
        this.paciente=paciente;
        this.medico=medico;
        this.dataEntrada=entrada;
        this.dataSaida=Saida;
        this.custoTotal=custo;
        this.quarto = new Quarto(quarto);
    }
    //Criar uma internação
    public Internacao(String info, ArrayList<Paciente> listaPaciente ,ArrayList<Medico> listaMedico){
        // Formato da info Paciente,Medico,DataEntrada,DataSaida,Custo,Quarto
        String[] partes = info.split(";");
       try {
           this.paciente = Paciente.encontrarPaciente(partes[0],listaPaciente);
           this.medico = Medico.encontrarMedico(partes[1],listaMedico);

           try {
               this.dataEntrada = LocalDate.parse(partes[2].trim());
               this.dataSaida = LocalDate.parse(partes[3].trim());
           }
           catch (DateTimeParseException e){
               System.err.println("Erro na formatação das datas");
           }
           this.custoTotal = Double.parseDouble(partes[4].trim());
           this.quarto = new Quarto(Integer.parseInt(partes[5].trim()));
       }
       catch (ArrayIndexOutOfBoundsException e){
           System.err.println("Erro na formatação do CSV");
       }
    }
}
