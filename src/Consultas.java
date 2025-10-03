import java.time.LocalDateTime;
import java.util.ArrayList;


public class Consultas {
    String nomePaciente;
    String pacienteCpf;
    String nomeMedico;
    String medicoCrm;
    LocalDateTime data;
    String motivo;

    public Consultas(String paciente,String pacienteCpf, String medico,String medicoCrm, LocalDateTime data, String motivo){
        this.nomePaciente = paciente;
        this.pacienteCpf = pacienteCpf;
        this.nomeMedico = medico;
        this.medicoCrm = medicoCrm;
        this.data = data;
        this.motivo = motivo;
    }

    public Consultas(String info){
        String[] partes= info.split(";");
        this.nomePaciente = partes[0];
        this.pacienteCpf = partes[1];
        this.nomeMedico = partes[2];
        this.medicoCrm = partes[3];
        this.data = LocalDateTime.parse(partes[4]);
        this.motivo = partes[6];
    }

    //Compara CPF com todos os termos do Array List
    //Ordem do CSV: Paciente; CPF do Paciente; Medico; CRM do Medico; Data; Hora; Motivo
    public boolean compCpf(String cpf, ArrayList<String> lista){
        for (String s : lista) {
            String[] partes = s.split(";");
            if (partes[1].trim().equals(cpf.trim())) {
                return true;
            }
        }
        return false;
    }
    public String pegaCpfPaciente(String info){
        String[] partes = info.split(";");
        return partes[1];
    }
    public String pegaCpfMedico(String info){
        String[] partes = info.split(";");
        return partes[3];
    }


    public String getPaciente(){
        return nomePaciente;
    }
    public String getMedico() {
        return nomeMedico;
    }
    public String getMotivo() {
        return motivo;
    }
    public LocalDateTime getData() {
        return data;
    }
    public String getPacienteCpf(){return pacienteCpf;}
    public String getMedicoCrm(){return medicoCrm;}



    public void getInfo(){
        System.out.println("==========================");
        System.out.println("Paciente : " + getPaciente());
        System.out.println("Medico : " + getMedico());
        System.out.println("Data : " + getData());
        System.out.println("Motivo : " + getMotivo());
        System.out.println("==========================");
    }
    //teste
}

