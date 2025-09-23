public class Consultas {
    String paciente;
    String medico;
    String data;
    String hora;
    String motivo;
    public Consultas(String paciente, String medico, String data, String hora, String motivo){
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Consultas(String info){
        String[] partes= info.split(";");
        this.paciente = partes[0];
        this.medico = partes[1];
        this.data = partes[2];
        this.hora = partes[3];
        this.motivo = partes[4];
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

