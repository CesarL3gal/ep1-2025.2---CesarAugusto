import java.util.ArrayList;

public class Consultas {
    String paciente;
    String pacienteCpf;
    String medico;
    String medicoCpf;
    String data;
    String hora;
    String motivo;

    public Consultas(String paciente,String pacienteCpf, String medico,String medicoCpf, String data, String hora, String motivo){
        this.paciente = paciente;
        this.pacienteCpf = pacienteCpf;
        this.medico = medico;
        this.medicoCpf = medicoCpf;
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Consultas(String info){
        String[] partes= info.split(";");
        this.paciente = partes[0];
        this.pacienteCpf = partes[1];
        this.medico = partes[2];
        this.medicoCpf = partes[3];
        this.data = partes[4];
        this.hora = partes[5];
        this.motivo = partes[6];
    }

    //Compara CPF com todos os termos do Array List
    //Ordem do CSV : Paciente; CPF do Paciente; Medico; CPF do Medico; Data; Hora; Motivo
    public boolean compCpf(String cpf, ArrayList<String> lista){
        for (String s : lista) {
            String[] partes = s.split(";");
            if (partes[1].trim().equals(cpf)) {
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
        return paciente;
    }
    public String getMedico() {
        return medico;
    }
    public String getHora() {
        return hora;
    }
    public String getMotivo() {
        return motivo;
    }
    public String getData() {
        return data;
    }
    public void getInfo(){
        System.out.println("==========================");
        System.out.println("Paciente : " + getPaciente());
        System.out.println("Medico : " + getMedico());
        System.out.println("Data : " + getData());
        System.out.println("Hora : " + getHora());
        System.out.println("Motivo : " + getMotivo());
        System.out.println("==========================");
    }
}

