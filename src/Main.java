import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<Paciente> listaPaciente = CSV.relerPaciente();
        ArrayList<Medico> listaMedico = CSV.relerMedico();
        ArrayList<Consultas> listaConsulta = CSV.relerConsulta(listaPaciente, listaMedico);
        //ArrayList<Internacao> listaInterancao = CSV.relerInternacao(listaPaciente, listaMedico);
        Scanner scan = new Scanner(System.in);
        char entrada = '0';


        while(!(entrada == 'X')) {
            System.out.println("==========================");
            System.out.println("1: Medicos");
            System.out.println("2: Pacientes");
            System.out.println("3: Consultas");
            System.out.println("4: Internações");
            System.out.println("X: para sair");
            System.out.println("==========================");
            entrada = scan.next().toUpperCase().charAt(0);
            scan.nextLine();

                switch (entrada) {
                    case '1' -> {     //Area dos Médicos
                        char entrada2 = '0';

                        while (entrada2 != 'V') {
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar Médicos");
                            System.out.println("2: Ver Médicos Cadastrados :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> {
                                    System.out.println("Escreva na ordem Nome;Cpf;Crm;Idade;Especializacao");
                                    String info = scan.nextLine();
                                    Medico medico = new Medico(info);
                                    if(Medico.encontrarMedicoPorCrm(medico.getCrm(),listaMedico) == null){
                                        CSV.CSV_Medico(medico);
                                        listaMedico.add(medico);
                                        System.out.println("Medico Cadastrado com Sucesso!");
                                        medico.getInfo();
                                    }
                                    else{
                                        System.out.println("Não foi possivel registrar, CPF já está sendo utilizado");
                                    }
                                }

                                case '2' ->{
                                    listaMedico =CSV.relerMedico();
                                    for(Medico medico : listaMedico){
                                        int numero = listaMedico.indexOf(medico) + 1;
                                        System.out.println("Numero do Paciente : " + numero);
                                        System.out.println("Nome : " + medico.getNome() + " CPF : " + medico.getCpf() + " CRM : " + medico.getCrm());
                                        System.out.println("Idade : " + medico.getIdade() + " Especialização : " + medico.getEspecializacao());
                                        System.out.println();
                                    }
                                }


                                case 'V' -> {
                                    break;
                                }
                                default -> System.out.println("Comando Invalido");
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
                            System.out.println("4: Ver histórico de consultas de um paciente");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { //1: Cadastrar Pacientes
                                    System.out.println("Escreva na ordem Nome;Cpf;Idade;PlanoSaude");
                                    String info = scan.nextLine();
                                    Paciente paciente = new Paciente(info);
                                    if(Paciente.encontrarPessoa(paciente.getCpf(), listaPaciente) == null && paciente.valido) {
                                        CSV.CSV_Paciente(paciente);
                                        listaPaciente.add(paciente);
                                        System.out.println("Paciente registrado com Sucesso:");
                                        paciente.getInfo();
                                    }
                                    else{
                                        System.out.println("Não foi possível registrar, CPF já está sendo utilizado ou formatação invalido");
                                    }
                                }
                                case '2' -> { //2: Ver Pacientes Cadastrados :
                                    listaPaciente =CSV.relerPaciente();
                                    for(Paciente paciente : listaPaciente){
                                        int numero = listaPaciente.indexOf(paciente) + 1;
                                        System.out.println("Numero do Paciente : " + numero);
                                        System.out.println("Nome : " + paciente.getNome() +" CPF : " + paciente.getCpf());
                                        System.out.println("Idade : " + paciente.getIdade() + " Plano : " + paciente.getPlanoSaude());
                                        System.out.println();
                                    }
                                }

                                case '3' ->{ //3: Pesquisar Paciente
                                    System.out.println("Escreva o CPF do Paciente");
                                    String cpf = scan.nextLine();
                                    Paciente paciente = Paciente.encontrarPaciente(cpf,listaPaciente);
                                    if(paciente != null) {
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
                                    //Se não tiver paciente com o cpf
                                    if(Paciente.encontrarPaciente(cpf,listaPaciente)==null){
                                        System.out.println("Paciente não encontrado");
                                        break;
                                    }
                                    /*Ver consultas do paciente
                                    anda por cada consulta da lista de consultas e
                                    pega o paciente, depois o cpf, e compara com o cpf*/
                                    boolean achouConsulta = false;
                                    for(Consultas c :listaConsulta){
                                        if(c.getPaciente().getCpf().trim().equals(cpf.trim())){
                                            c.getInfo();
                                            achouConsulta = true;
                                        }
                                    }
                                    if(!achouConsulta){
                                        System.out.println("Nenhum histórico de consultas encontrados para esse paciente");
                                    }
                                }
                                case 'V' -> {
                                    break;
                                }
                                default ->{
                                    System.out.println("Comando Invalido");
                                }
                            }
                        }
                    }





                    case '3' -> {     // Area das Consultas
                        char entrada2 = '0';
                        while (!(entrada2 == 'V')) {
                            System.out.println("==========================");
                            System.out.println("1: Marcar consultas");
                            System.out.println("2: Ver todas consultas cadastrados :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { // Cadastrar consulas
                                    System.out.println("Escreva o CPF do paciente:");
                                    String Cpf = scan.nextLine();
                                    //Cria um paciente
                                    Paciente paciente = Paciente.encontrarPaciente(Cpf,listaPaciente);
                                    if(paciente == null){
                                        System.out.println("Paciente não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    System.out.println("Escreva o Crm do Médico");
                                    String Crm = scan.nextLine();
                                    //Cria um médico
                                    Medico medico = Medico.encontrarMedicoPorCrm(Crm.trim(),listaMedico);
                                    if(medico == null){
                                        System.out.println("Medico não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    Medico.pegarHistoricoConsulta(medico,listaConsulta);
                                    System.out.println("Escreva a data e hora da consulta no formato Ano-Mes-DiaTHH:MM: ");
                                    String data = scan.nextLine();
                                    boolean horarioDisponivel = true;
                                    try {
                                        LocalDateTime Datafinal = LocalDateTime.parse(data.trim());
                                        for(Consultas c : medico.getHistoricoConsulta()) {
                                            if (Datafinal.equals(c.getData())){
                                                System.out.println("Medico já tem consulta marcada no mesmo horario");
                                                horarioDisponivel=false;
                                                break;
                                            }
                                        }
                                    }
                                    catch (Exception e){
                                        System.err.println("Formato Incorreto da Data");
                                        break;
                                    }
                                    if(horarioDisponivel) {
                                        System.out.println("Escreva o Motivo da consulta: ");
                                        String motivo = scan.nextLine();
                                        //Cria uma consulta com paciente, medico, data e motivo
                                        LocalDateTime Datafinal = LocalDateTime.parse(data.trim());
                                        Consultas consulta = new Consultas(paciente, medico, Datafinal, motivo);
                                        listaConsulta.add(consulta);
                                        CSV.CSV_Consulta(consulta);
                                        System.out.println("Consulta Registrada:");
                                        consulta.getInfo();
                                    }
                                    else {
                                        System.out.println("Cancelando o Cadastro por Horario conflitante");
                                    }
                                }



                                case '2' ->{//Ver todas as consultas
                                    listaConsulta =CSV.relerConsulta(listaPaciente,listaMedico);
                                    for(Consultas consulta : listaConsulta){
                                        int numero = listaConsulta.indexOf(consulta) + 1;
                                        System.out.println("Numero da Consulta : " + numero);
                                        System.out.println("Paciente : " + consulta.getPaciente().getNome() +" Medico : " + consulta.getMedico().getNome());
                                        System.out.println("Data : " + consulta.getData() + " Motivo : " + consulta.getMotivo());
                                        System.out.println();
                                    }
                                }

                                case 'V' -> {
                                    break;
                                }
                                default -> {
                                    System.out.println("Comando Invalido");
                                }
                            }
                        }
                    }
                    case 'X'->{
                        break;
                    }
                    default -> {
                        System.out.println("Comando Invalido");
                    }
                }
        }
        System.out.println("Saindo...");
        scan.close();

    }
}
