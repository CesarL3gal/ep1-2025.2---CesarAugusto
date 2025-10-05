import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Consultas {
   private Paciente paciente;
   private Medico medico;
   private LocalDateTime data;
   private String motivo;

    public Consultas(Paciente paciente,Medico medico, LocalDateTime data, String motivo){
        this.paciente = paciente;
        this.medico = medico;
        this.data = data; //Formato aaaa-mm-ddthh:mm
        this.motivo = motivo;
    }

    public Consultas(String info, Paciente paciente, Medico medico){
        String[] partes= info.split(";");
        try {
            this.paciente = paciente;
            this.medico = medico;
            this.data = LocalDateTime.parse(partes[2].trim());
            this.motivo = partes[3];
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Formatação Errada");
        }
        catch (DateTimeParseException e){
            System.err.println("Erro na formatação da data");
        }
    }
    //Construtor CSV
    //cpf paciente, crm medico, data e hora, motivo
    public Consultas(String info, ArrayList<Paciente> listaPaciente, ArrayList<Medico> listaMedicos){
        try {
            String[] partes= info.split(";");
            String cpf=partes[0].trim();
            String crm=partes[1].trim();

            this.paciente=Paciente.encontrarPaciente(cpf,listaPaciente);
            this.medico=Medico.encontrarMedicoPorCrm(crm,listaMedicos);
            this.data = LocalDateTime.parse(partes[2]);
            this.motivo = partes[3];
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Erro na formatação da Consulta");
        }
        catch (DateTimeParseException e){
            System.err.println("Erro na formatação da Data");
        }
    }

//    //Compara CPF com todos os termos do Array List
//    //Ordem do CSV: Paciente; CPF do Paciente; Medico; CRM do Medico; Data; Hora; Motivo
//    public boolean compCpf(String cpf, ArrayList<String> lista){
//        for (String s : lista) {
//            String[] partes = s.split(";");
//            if (partes[1].trim().equals(cpf.trim())) {
//                return true;
//            }
//        }
//        return false;
//    }
//    public String pegaCpfPaciente(String info){
//        String[] partes = info.split(";");
//        return partes[1];
//    }
//    public String pegaCpfMedico(String info){
//        String[] partes = info.split(";");
//        return partes[3];
//    }
    public double getCustoFinal(){
        if(medico == null || paciente == null){
            return 0.0;
        }
        double custo = medico.getCustoConsulta();
        double desconto = paciente.getPlanoSaude().getDesconto();
        return custo * (1 - desconto);
    }

    public Paciente getPaciente(){
        return paciente;
    }
    public Medico getMedico() {
        return medico;
    }
    public String getMotivo() {
        return motivo;
    }
    public LocalDateTime getData() {
        return data;
    }
    public String getPacienteCpf(){return paciente.getCpf();}
    public String getMedicoCrm(){return medico.getCrm();}



    public void getInfo(){
        System.out.println("==========================");
        System.out.println("Paciente : " + getPaciente().getNome() + "Plano : " + getPaciente().getPlanoSaude());
        System.out.println("Medico : " + getMedico().getNome());
        System.out.println("Data : " + getData());
        System.out.println("Motivo : " + getMotivo());
        System.out.println("Custo com desconto : " + getCustoFinal());
        System.out.println("==========================");
    }
}

