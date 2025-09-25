import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char entrada = '0';
        ArrayList<String> listaPaciente = new ArrayList<String>();
        ArrayList<String> listaMedico = new ArrayList<String>();
        ArrayList<String> listaConsulta = new ArrayList<String>();
        listaPaciente = CSV.relerPaciente();
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
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar Pacientes");
                            System.out.println("2: Ver Pacientes Cadastrados :");
                            System.out.println("3: Pesquisar Paciente");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> {
                                    System.out.println("Escreva na ordem Nome;Cpf;Idade;Telefone;PlanoSaude;Dinheiro");
                                    String info = scan.nextLine();
                                    listaPaciente=CSV.relerPaciente();
                                    Paciente paciente = new Paciente(info);
                                    if(!paciente.compCpf(paciente.pegaCpf(info), listaPaciente)) {
                                        CSV.CSV_Paciente(paciente);
                                        System.out.println("Pessoa Registrada");
                                        paciente.getInfo();
                                    }
                                    else{
                                        System.out.println("CPF já encontrado");
                                    }
                                }
                                case '2' -> {
                                    CSV.lerPaciente();
                                }
                                case '3' ->{ // Mudar Info
                                    System.out.println("Escreva o CPF do Paciente");
                                    Tester.lerArrayList(listaPaciente);

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
                            System.out.println("1: Cadastrar Consultas");
                            System.out.println("2: Ver Consultas Cadastrados :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { // Cadastrar consulas
                                    System.out.println("Escreva na ordem Paciente;Medico;Data;Hora;Motivo");
                                    String info = scan.nextLine();
                                    Consultas consulta = new Consultas(info);
                                    consulta.getInfo();
                                    CSV.CSV_Consulta(consulta);

                                }
                                case '2' -> {
                                    System.out.println("Bolsonaro Consultas");
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