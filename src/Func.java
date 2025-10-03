import java.util.ArrayList;

public class Func {
    public static Paciente encontrarPaciente(String cpf, ArrayList<Paciente> lista){
        for (Paciente s : lista) {
            if (s.getCpf().trim().equals(cpf)) {
                return s;
            }
        }
        return null;
    }
    public Medico encontrarMedico(String crm, ArrayList<Medico> lista){
        for (Medico s : lista) {
            if (s.getCrm().trim().equals(crm)) {
                return s;
            }
        }
        return null;
    }
}
