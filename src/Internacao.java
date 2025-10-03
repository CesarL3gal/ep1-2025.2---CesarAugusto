import java.time.LocalDate;
import java.util.ArrayList;

public class Internacao {
    private Paciente paciente;
    private Medico medico;
    private LocalDate dataEntrada;
    private LocalDate dataSaida; // Null se ainda estiver internado
    private double custoTotal;
    private int quarto;


    public Internacao(Paciente paciente, Medico medico, LocalDate entrada, LocalDate Saida, double custo, int quarto){
        this.paciente=paciente;
        this.medico=medico;
        this.dataEntrada=entrada;
        this.dataSaida=Saida;
        this.custoTotal=custo;
        this.quarto=quarto;
    }

    public Internacao(String info, ArrayList<Paciente> listaPaciente ,ArrayList<Medico> listaMedico){ // Formato da info Paciente
        String[] partes = info.split(";");
        Paciente pessoa = paciente.encontrarPaciente(partes[0],listaPaciente);
        Medico medico1 = medico.encontrarMedico(partes[1],listaMedico);
       try {
           this.paciente = pessoa;
           this.medico = medico1;
           this.dataEntrada = LocalDate.parse(partes[2]);
           this.dataSaida = LocalDate.parse(partes[3]);
           this.custoTotal = Double.parseDouble(partes[4]);
           this.quarto = Integer.parseInt(partes[5]);
       }
       catch (ArrayIndexOutOfBoundsException e){
           System.err.println("Erro na formatação do CSV");
       }
    }
}
