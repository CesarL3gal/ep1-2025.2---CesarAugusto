import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        ArrayList<Paciente> listaPaciente = CSV.relerPaciente();
        ArrayList<Medico> listaMedico = CSV.relerMedico();
        ArrayList<Consultas> listaConsulta = CSV.relerConsulta(listaPaciente, listaMedico);
        ArrayList<Internacao> listaInterancao = CSV.relerInternacao(listaPaciente);
        for(Paciente p : listaPaciente){
            Paciente.pegarHistoricoConsulta(p,listaConsulta);
        }
        for(Medico m : listaMedico){
            Medico.pegarHistoricoConsulta(m,listaConsulta);
        }
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
                            System.out.println("1: Cadastrar Médicos :");
                            System.out.println("2: Ver Médicos Cadastrados :");
                            System.out.println("3: Pesquisar Medico :");
                            System.out.println("4: Ver Consultas Agendadas com o Médico :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { // Cadastrar Médico
                                    System.out.println("Escreva na ordem Nome;Cpf;Crm;Idade;Especializacao;CustoConsulta");
                                    String info = scan.nextLine();
                                    Medico medico = new Medico(info);
                                    if(Medico.encontrarMedicoPorCrm(medico.getCrm(),listaMedico) == null && medico.getValido()){
                                        CSV.CSV_Medico(medico);
                                        listaMedico.add(medico);
                                        System.out.println("Medico Cadastrado com Sucesso!");
                                        medico.getInfo();
                                    }
                                    else{
                                        System.out.println("Não foi possivel registrar, CRM já está sendo utilizado ou formatação invalida");
                                    }
                                }

                                case '2' ->{ //Ver lista de todos os médico
                                    listaMedico =CSV.relerMedico();
                                    for(Medico medico : listaMedico){
                                        int numero = listaMedico.indexOf(medico) + 1;
                                        System.out.println("Numero do Paciente : " + numero);
                                        try {
                                            System.out.println("Nome : " + medico.getNome() + " CPF : " + medico.getCpf() + " CRM : " + medico.getCrm());
                                            System.out.println("Idade : " + medico.getIdade() + " Especialização : " + medico.getEspecializacao());
                                            System.out.println();
                                        } catch (Exception e) {
                                            System.err.println("Erro na leitura do médico");
                                        }
                                    }
                                }
                                case '3'->{ // Pesquisar Médico
                                    System.out.println("Escreva o CRM do Médico");
                                    String Crm = scan.nextLine();
                                    Medico medico = Medico.encontrarMedicoPorCrm(Crm,listaMedico);
                                    if(medico != null) {
                                        System.out.println("Pessoa encontrada:");
                                        medico.getInfo();

                                    }
                                    else{
                                        System.out.println("Crm não encontrado");
                                    }
                                }

                                case '4'->{//4: Ver histórico de consultas de um Medico
                                    listaMedico = CSV.relerMedico();
                                    listaConsulta = CSV.relerConsulta(listaPaciente,listaMedico);
                                    System.out.println("Escreva o Crm do Medico");
                                    String crm = scan.nextLine();
                                    //Se não tiver medico com o crm
                                    if(Medico.encontrarMedicoPorCrm(crm,listaMedico)==null){
                                        System.out.println("Medico não encontrado");
                                        break;
                                    }
                                    else{
                                        Medico medico = Medico.encontrarMedicoPorCrm(crm,listaMedico);
                                        /*Ver consultas do Médico*/
                                        medico = Medico.pegarHistoricoConsulta(medico,listaConsulta);
                                        boolean achouConsulta = false;
                                        if(medico != null && medico.getHistoricoConsulta() != null) {
                                            for (Consultas c : medico.getHistoricoConsulta()) {
                                                    c.getInfo();
                                                    achouConsulta = true;
                                            }
                                        }
                                        if(!achouConsulta){
                                            System.out.println("Nenhum histórico de consultas encontrados para esse medico");
                                        }
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
                            System.out.println("1: Cadastrar Pacientes :");
                            System.out.println("2: Ver Pacientes Cadastrados :");
                            System.out.println("3: Pesquisar Paciente :");
                            System.out.println("4: Ver histórico de consultas de um Paciente :");
                            System.out.println("5: Mudar Plano de Saude ");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { //1: Cadastrar Pacientes
                                    System.out.println("Escreva na ordem Nome;Cpf;Idade;PlanoSaude");
                                    System.out.println("Planos: MILITAR, CIVIL, NENHUM ,PREMIUM");
                                    String info = scan.nextLine();
                                    Paciente paciente = new Paciente(info);
                                    if(Paciente.encontrarPessoa(paciente.getCpf(), listaPaciente) == null && paciente.getValido()) {
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
                                        System.out.println("==========================");
                                        int numero = listaPaciente.indexOf(paciente) + 1;
                                        System.out.println("Numero do Paciente : " + numero);
                                        System.out.println("Nome : " + paciente.getNome() +" CPF : " + paciente.getCpf());
                                        System.out.println("Idade : " + paciente.getIdade() + " Plano : " + paciente.getPlanoSaude().name());
                                        System.out.println("==========================");
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
                                    Paciente paciente =Paciente.encontrarPaciente(cpf,listaPaciente);
                                    if(paciente==null){
                                        System.out.println("Paciente não encontrado");
                                        break;
                                    }
                                    /*Ver consultas do paciente
                                    anda por cada consulta da lista de consultas e
                                    pega o paciente, depois o cpf, e compara com o cpf*/
                                    boolean achouConsulta = false;
                                    for(Consultas c : paciente.getHistoricoConsulta()){
                                            c.getInfo();
                                            achouConsulta = true;
                                    }
                                    if(!achouConsulta){
                                        System.out.println("Nenhum histórico de consultas encontrados para esse paciente");
                                    }
                                }
                                case'5' ->{
                                    System.out.println("Digite o CPF do paciente que vai alternar o plano : ");
                                    String cpf = scan.nextLine();
                                    Paciente paciente =Paciente.encontrarPaciente(cpf,listaPaciente);
                                    if(paciente==null){
                                        System.out.println("Paciente não encontrado");
                                        break;
                                    }
                                    System.out.println("Plano Atual: " + paciente.getPlanoSaude().name());
                                    System.out.println("Digite o novo plano : MILITAR, CIVIL, NENHUM ,PREMIUM");
                                    String novoPlano = scan.nextLine().toUpperCase();

                                    try{
                                        PlanoDeSaude plano = PlanoDeSaude.valueOf(novoPlano);
                                        paciente.setPlanoSaude(plano);
                                        CSV.CSV_Pacienteatualizar(listaPaciente);
                                        System.out.println("Plano de Saude Atualizado");
                                        paciente.getInfo();
                                    } catch (Exception e) {
                                        System.err.println("Plano de saude invalido");
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
                            System.out.println("1: Marcar consultas :");
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
                                    Medico temp = Medico.encontrarMedicoPorCrm(Crm.trim(),listaMedico);
                                    if(temp == null){
                                        System.out.println("Medico não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    Medico medico = Medico.pegarHistoricoConsulta(temp,listaConsulta);
                                    System.out.println("Escreva a data e hora da consulta no formato Ano-Mes-DiaTHH:MM ");
                                    String data = scan.nextLine();
                                    boolean horarioDisponivel = true;
                                    try {
                                        LocalDateTime Datafinal = LocalDateTime.parse(data.trim());
                                        horarioDisponivel = Medico.horaDisponivel(medico,Datafinal);
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
                                    listaPaciente = CSV.relerPaciente();
                                    listaMedico = CSV.relerMedico();
                                    listaConsulta =CSV.relerConsulta(listaPaciente,listaMedico);
                                    for(Consultas consulta : listaConsulta){
                                        try {
                                            int numero = listaConsulta.indexOf(consulta) + 1;
                                            System.out.println("Numero da Consulta : " + numero);
                                            System.out.println("Paciente : " + consulta.getPaciente().getNome() + " Medico : " + consulta.getMedico().getNome());
                                            System.out.println("Data : " + consulta.getData() + " Motivo : " + consulta.getMotivo());
                                            System.out.println();
                                        } catch (Exception e) {
                                            System.err.println("Erro em ler a consulta, medico ou paciente não existem");
                                        }
                                    }
                                }


                                case '3'->{ //Desmarcar Consulta

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


                    case '4'->{ // Area das Internações
                        char entrada2 = '0';
                        while (!(entrada2 == 'V')) {
                            System.out.println("==========================");
                            System.out.println("1: Marcar Internações :");
                            System.out.println("2: Ver todas Internações cadastrados :");
                            System.out.println("3: Finalizar Internação :");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
                                case '1' -> { // Cadastrar Internações
                                    // CPF;CRM;DataEntrada;DataSaida;Quarto;Custo
                                    System.out.println("Escreva o CPF do paciente:");
                                    String Cpf = scan.nextLine();
                                    Paciente paciente = Paciente.encontrarPaciente(Cpf,listaPaciente);
                                    if(paciente == null){
                                        System.out.println("Paciente não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    System.out.println("Data de entrada será considerada o momento atual");
                                    LocalDateTime dataEntrada = LocalDateTime.now();
                                    double custo = Internacao.getCustoFinal(paciente);
                                    System.out.println("Escreva número do Quarto da internação: ");
                                    int quarto = scan.nextInt();
                                    scan.nextLine();
                                        //Cria uma consulta com Paciente, dataEntrada, dataSaida, Custo e Quarto
                                    try {
                                        Internacao internacao = new Internacao(paciente, dataEntrada, null, custo, quarto);
                                        listaInterancao.add(internacao);
                                        CSV.CSV_Internacao(internacao);
                                        System.out.println("Internação Registrada:");
                                        internacao.getInfo();

                                    } catch (Exception e) {
                                      System.out.println("Falha no Cadastro da Internação");
                                    }
                                }



                                case '2' ->{//Ver todas as Internações
                                    listaPaciente = CSV.relerPaciente();
                                    listaInterancao =CSV.relerInternacao(listaPaciente);
                                    for(Internacao internacao : listaInterancao){
                                        try {
                                            int numero = listaInterancao.indexOf(internacao) + 1;
                                            System.out.println("Numero da Internação : " + numero);
                                            System.out.println("Paciente : " + internacao.getPaciente().getNome());
                                            System.out.println("Data de Entrada : " + internacao.getDataEntrada() + " Data de Saida : " + internacao.getDataSaida());
                                            System.out.println("Numero do Quarto : " + internacao.getQuarto().getNumero());
                                        } catch (Exception e) {
                                            System.err.println("Erro em ler a consulta, medico ou paciente não existem");
                                        }
                                    }
                                }



                                case '3' ->{ //Finalizar Internação
                                    System.out.println("Escreva o CPF do Paciente Internado");
                                    String cpf = scan.nextLine();

                                    Paciente paciente = Paciente.encontrarPaciente(cpf,listaPaciente);
                                    boolean encontrouInternação = false;
                                    if(paciente != null){
                                        for(Internacao internacao : listaInterancao){
                                            if(internacao.getPaciente() == paciente){
                                             internacao.getQuarto().setDesocupado();
                                             internacao.setDataSaida();
                                             internacao.getInfo();
                                             encontrouInternação = true;
                                             break;
                                            }
                                        }
                                    }
                                    if(encontrouInternação){
                                        CSV.CSV_InternacaoAtualizar(listaInterancao);
                                    }
                                    else{
                                        System.out.println("Internação não encontrada");
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
