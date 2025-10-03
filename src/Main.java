import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        char entrada = '0';
        ArrayList<Paciente> listaPaciente = new ArrayList<>();
        ArrayList<Medico> listaMedico = new ArrayList<>();
        ArrayList<Consultas> listaConsulta = new ArrayList<>();
        ArrayList<Internacao> listaInterancao = new ArrayList<>();
        while(!(entrada == 'X')) {

            System.out.println("==========================");
            System.out.println("1:Medicos");
            System.out.println("2:Pacientes");
            System.out.println("3:Consultas");
            System.out.println("X:para sair");
            System.out.println("==========================");
            entrada = scan.next().toUpperCase().charAt(0);
            scan.nextLine();

            if(entrada<='6') {
                switch (entrada) {
                    case '1' -> {     //Area dos Médicos
                        char entrada2 = '0';
                        while (!(entrada2 == 'V')) {
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar Médicos");
                            System.out.println("2: Ver Médicos Cadastrados :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> {
                                    System.out.println("Escreva na ordem Nome;cpf;idade;telefone;dinheiro;especializacao;preco");
                                    String info = scan.nextLine();
                                    Medico medico = new Medico(info);
                                    medico.getInfo();
                                    CSV.CSV_Medico(medico);
                                }
                                case '2' -> {
                                    CSV.lerMedico();
                                }
                                case 'V' -> {
                                    break;
                                }
                            }
                        }
                    }



                    case '2' -> {    //Area dos Pacientes
                        char entrada2 = '0';
                        while (!(entrada2 == 'V')) {
                            listaPaciente=CSV.relerPaciente();
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar Pacientes");
                            System.out.println("2: Ver Pacientes Cadastrados :");
                            System.out.println("3: Pesquisar Paciente");
                            System.out.println("4: Ver histórico de consultas de um paciente");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { //1: Cadastrar Pacientes
                                    System.out.println("Escreva na ordem Nome;Cpf;Idade;Telefone;PlanoSaude;Dinheiro");
                                    String info = scan.nextLine();
                                    Paciente paciente = new Paciente(info);
                                    if(!paciente.compCpf(paciente.getCpf(), listaPaciente)) {
                                        CSV.CSV_Paciente(paciente);
                                        System.out.println("Pessoa registrada:");
                                        paciente.getInfo();
                                    }
                                    else{
                                        System.out.println("Não foi possível registrar");
                                    }
                                }
                                case '2' -> { //2: Ver Pacientes Cadastrados :
                                    CSV.lerPaciente();
                                }

                                case '3' ->{ //3: Pesquisar Paciente
                                    System.out.println("Escreva o CPF do Paciente");
                                    String cpf = scan.nextLine();
                                    Paciente paciente = new Paciente();
                                    listaPaciente=CSV.relerPaciente();
                                    if(paciente.compCpf(cpf, listaPaciente)) {
                                        Paciente pessoa = new Paciente();
                                        pessoa = Paciente.encontrarPaciente(cpf, listaPaciente);
                                        System.out.println("Pessoa encontrada:");
                                        paciente.getInfo();
                                    }
                                    else{
                                        System.out.println("CPF não encontrado");
                                    }
                                }
                                case '4' ->{ //4: Ver histórico de consultas de um paciente
                                    System.out.println("Escreva o CPF do Paciente");
                                    String cpf = scan.nextLine();
                                    Paciente paciente = new Paciente();
                                    ArrayList<Consultas> consultaPaciente = new ArrayList<>();
                                    consultaPaciente = paciente.getConsultas(cpf);
                                    if(paciente.compCpf(cpf, consultaPaciente)) {
                                        for (Consultas s : consultaPaciente) {
                                            System.out.println("Paciente: " + get. + ", Médico: " + partes[2] + ", Data: " + partes[4] + ", Hora: " + partes[5] + ", Motivo: " + partes[6]);
                                        }
                                    }
                                    else{
                                        System.out.println("Nenhum histórico de consultas encontrados para esse paciente");
                                    }
                                }
                                case 'V' -> {
                                    break;
                                }
                            }
                        }
                    }





                    case '3' -> {     // Area das Consultas
                        char entrada2 = '0';
                        while (!(entrada2 == 'V')) {
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar consultas");
                            System.out.println("2: Ver todas consultas cadastrados :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { // Cadastrar consulas
                                    System.out.println("Escreva Paciente;CPF do Paciente;Medico; CPF do Medico;Data;Hora;Motivo");
                                    String info = scan.nextLine();
                                    Consultas consulta = new Consultas(info,listaPaciente,listaMedico);
                                    listaConsulta=CSV.relerConsulta();
                                    if(!consulta.compCpf(consulta.pegaCpfMedico(info), listaConsulta)) {
                                        CSV.CSV_Consulta(consulta);
                                        System.out.println("Consulta Registrada:");
                                        consulta.getInfo();
                                    }
                                    else{
                                        System.out.println("CPF já encontrado");
                                        System.out.println("Não foi possível registrar");
                                    }
                                }
                                case '2' -> {
                                    CSV.lerConsulta();
                                }
                                case 'V' -> {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else if (entrada > '4' && entrada!='X'){
                System.out.println("Comando Burro");
                System.out.println("Tente Novamente");
            }
        }
        System.out.println("Saindo...");
        scan.close();

    }
}
