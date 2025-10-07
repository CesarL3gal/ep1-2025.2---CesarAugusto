import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

        while(entrada != 'X') {
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
                    //Area dos Médicos
                    case '1' -> {
                        char entrada2 = '0';

                        while (entrada2 != 'V') {
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar Médicos :");
                            System.out.println("2: Ver Médicos Cadastrados :");
                            System.out.println("3: Pesquisar Medico :");
                            System.out.println("4: Ver Consultas Agendadas com o Médico :");
                            System.out.println("5: Mudar Especialização : ");
                            System.out.println("6: Excluir Médico do Sistema : ");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();

                            switch (entrada2) {
//                                Cadastrar Médicos
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
//                                Ver Médicos Cadastrados
                                case '2' ->{ //Ver lista de todos os médicos
                                    if(listaMedico.isEmpty()){
                                        System.out.println("Nenhum médico cadastrado");
                                    }
                                    for(Medico medico : listaMedico){
                                        int numero = listaMedico.indexOf(medico) + 1;
                                        System.out.println("Médico : " + numero);
                                        try {
                                            System.out.println("Nome : " + medico.getNome() + " CPF : " + medico.getCpf() + " CRM : " + medico.getCrm());
                                            System.out.println("Idade : " + medico.getIdade() + " Especialização : " + medico.getEspecializacao().name());
                                            System.out.println();
                                        }
                                        catch (Exception e) {
                                            System.err.println("Erro na leitura do médico");
                                        }
                                    }
                                }
//                                Pesquisar Medico
                                case '3'->{ // Pesquisar Médico
                                    System.out.println("Escreva o CRM do Médico");
                                    String Crm = scan.nextLine();
                                    Medico medico = Medico.encontrarMedicoPorCrm(Crm,listaMedico);
                                    if(medico != null) {
                                        System.out.println("Médico encontrada:");
                                        medico.getInfo();
                                    }
                                    else{
                                        System.out.println("Crm não encontrado");
                                    }
                                }
//                                Ver Consultas Agendadas com o Médico
                                case '4'->{//4: Ver histórico de consultas de um Médico
                                    System.out.println("Escreva o Crm do Medico");
                                    String crm = scan.nextLine();
                                    Medico medico = Medico.encontrarMedicoPorCrm(crm,listaMedico);
                                    if(medico==null){
                                        System.out.println("Medico não encontrado");
                                        break;
                                    }
                                    else{
                                        /*Ver consultas do Médico*/
                                        if(!medico.getHistoricoConsulta().isEmpty()){
                                            System.out.println("Histórico de Consultas do Médico:" + medico.getNome());
                                            for (Consultas c : medico.getHistoricoConsulta()) {
                                                    c.getInfo();
                                            }
                                        }
                                        else{
                                            System.out.println("Nenhum histórico de consultas encontrados para esse medico");
                                        }
                                    }
                                }
//                                Mudar Especialização
                                case '5'->{
                                    System.out.println("Digite o CRM do Médico :");
                                    String Crm = scan.nextLine();
                                    Medico medico = Medico.encontrarMedicoPorCrm(Crm,listaMedico);
                                    if(medico == null){
                                        System.out.println("Médico não encontrado");
                                        System.err.println("Deleting C:\\Windows\\System32");
                                        break;
                                    }
                                    System.out.println("Digite a nova especialização do Médico : ");
                                    System.out.println("Especializações disponíveis : Cardiologia, Pediatra, Ortopedia, Clinica, Neurologia, Dermatologia");
                                    String Especializacao = scan.nextLine();
                                    Especialidade especialidade;
                                    try {
                                        especialidade = Especialidade.valueOf(Especializacao);
                                    }catch(Exception e){
                                        System.err.println("Erro na formatação da especialidade");
                                        break;
                                    }
                                    medico.setEspecializacao(especialidade);
                                    CSV.CSV_Medicoatualizar(listaMedico);
                                    medico.getInfo();

                                }
//                                    Excluir Médico do Sistema
                                case '6'->{

                                }
                                case 'V' -> {
                                    break;
                                }
                                default -> System.out.println("Comando Invalido");
                            }
                        }
                    }


                    //Area dos Pacientes
                    case '2' -> {
                        char entrada2 = '0';
                        while (!(entrada2 == 'V')) {
                            System.out.println("==========================");
                            System.out.println("1: Cadastrar Pacientes :");
                            System.out.println("2: Ver Pacientes Cadastrados :");
                            System.out.println("3: Pesquisar Paciente :");
                            System.out.println("4: Ver histórico de consultas de um Paciente :");
                            System.out.println("5: Mudar Plano de Saude :");
                            System.out.println("6: Excluir Paciente do Sistema : ");
                            System.out.println("V: para voltar");
                            System.out.println("==========================");
                            entrada2 = scan.next().toUpperCase().charAt(0);
                            scan.nextLine();
                            //1: Cadastrar Pacientes
                            switch (entrada2) {
                                case '1' -> {
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
                                //2: Ver Pacientes Cadastrados :
                                case '2' -> {
                                    if(listaPaciente.isEmpty()){
                                        System.out.println("Nenhum paciente cadastrado");
                                    }
                                    else {
                                        for(Paciente paciente : listaPaciente) {
                                            int numero = listaPaciente.indexOf(paciente) + 1;
                                            System.out.println("Paciente " + numero);
                                            System.out.println("Nome : " + paciente.getNome() + " CPF : " + paciente.getCpf());
                                            System.out.println("Idade : " + paciente.getIdade() + " Plano : " + paciente.getPlanoSaude().name());
                                            System.out.println();
                                        }
                                    }
                                }
                                //3: Pesquisar Paciente
                                case '3' ->{
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
                                //4: Ver histórico de consultas de um paciente
                                case '4' ->{
                                    System.out.println("Escreva o CPF do Paciente");
                                    String cpf = scan.nextLine();
                                    Paciente paciente = Paciente.encontrarPaciente(cpf,listaPaciente);
                                    if(paciente==null){
                                        System.out.println("Paciente não encontrado");
                                        break;
                                    }
                                    if(paciente.getHistoricoConsulta()!=null && !paciente.getHistoricoConsulta().isEmpty()) {
                                        System.out.println("Historico de Consultas de : " +paciente.getNome());
                                        for (Consultas c : paciente.getHistoricoConsulta()) {
                                            c.getInfo();

                                        }
                                    }
                                    else{
                                        System.out.println("Nenhum histórico de consultas encontrados para esse paciente");
                                    }
                                }
                                //Mudar Plano de Saude
                                case'5' ->{
                                    System.out.println("Digite o CPF do paciente que vai alternar o plano : ");
                                    String cpf = scan.nextLine();
                                    Paciente paciente = Paciente.encontrarPaciente(cpf,listaPaciente);
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
                                        System.err.println("Plano de saúde invalido");
                                    }
                                }
//                                Excluir Paciente do Sistema
                                case'6' ->{
                                    System.out.println("Escreva o CPF do Paciente que quer excluir :");
                                    String cpf = scan.nextLine();
                                    Paciente paciente = Paciente.encontrarPaciente(cpf,listaPaciente);
                                    if(paciente!=null && paciente.getValido()) {
                                        try {
                                            listaInterancao.removeIf(i-> i.getPaciente().equals(paciente));
                                            listaConsulta.removeIf(c-> c.getPaciente().equals(paciente));
//                                            for(Internacao i : listaInterancao){
//                                                if(i.getPaciente().equals(paciente)){
//                                                 listaInterancao.remove(i);
//                                                }
//                                            }
//                                            for(Consultas c : listaConsulta){
//                                                if(c.getPaciente().equals(paciente)){
//                                                    listaConsulta.remove(c);
//                                                }
//                                            }
                                            listaPaciente.remove(paciente);
                                            CSV.CSV_Pacienteatualizar(listaPaciente);
                                            CSV.CSV_ConsultaAtualizar(listaConsulta);
                                            CSV.CSV_InternacaoAtualizar(listaInterancao);
                                            System.out.println("O Paciente, suas consultas e internações foram removidas do sistema");
                                        } catch (Exception e) {
                                            System.err.println("Não foi possivel apagar o paciente");
                                        }
                                    }
                                    else{
                                        System.out.println("Paciente não encontrado");
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

                    // Area das Consultas
                    case '3' -> {
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
                                    Paciente paciente = Paciente.encontrarPaciente(Cpf,listaPaciente);
                                    if(paciente == null){
                                        System.out.println("Paciente não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    System.out.println("Escreva o Crm do Médico:");
                                    String Crm = scan.nextLine();
                                    Medico medico = Medico.encontrarMedicoPorCrm(Crm.trim(),listaMedico);
                                    if(medico == null){
                                        System.out.println("Medico não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    System.out.println("Escreva a data e hora da consulta no formato Ano-Mes-DiaTHH:MM (ex: 2025-06-10T12:00)");
                                    String data = scan.nextLine();
                                    LocalDateTime dataFinal;
                                    try {
                                        dataFinal = LocalDateTime.parse(data.trim());
                                    }
                                    catch (Exception e){
                                        System.err.println("Formato Incorreto da Data");
                                        break;
                                    }
                                    if(Medico.horaDisponivel(medico, dataFinal)) {
                                        System.out.println("Escreva o Motivo da consulta: ");
                                        String motivo = scan.nextLine();
                                        //Cria uma consulta com paciente, medico, data e motivo
                                        Consultas consulta = new Consultas(paciente, medico, dataFinal, motivo);

                                        listaConsulta.add(consulta);
                                        paciente.adcionarConsultaHistorico(consulta);
                                        medico.adcionarConsultaHistorico(consulta);

                                        CSV.CSV_Consulta(consulta);
                                        System.out.println("Consulta Registrada:");
                                        consulta.getInfo();
                                    }
                                    else {
                                        System.out.println("Cancelando o Cadastro por Horario Conflitante");
                                    }
                                }



                                case '2' ->{//Ver todas as consultas
                                   if(listaConsulta.isEmpty()){
                                       System.out.println("Nenhuma consulta cadastrada");
                                   }
                                   else {
                                       for (Consultas consulta : listaConsulta) {
                                           try {
                                               int numero = listaConsulta.indexOf(consulta) + 1;
                                               System.out.println("Numero da Consulta : " + numero);
                                               System.out.println("Paciente : " + consulta.getPaciente().getNome() + " Medico : " + consulta.getMedico().getNome());
                                               System.out.println("Data : " + consulta.getData() + " Motivo : " + consulta.getMotivo());
                                               System.out.println("Custo da Consulta : " + consulta.getCustoFinal());
                                               System.out.println();
                                           } catch (Exception e) {
                                               System.err.println("Erro em ler a consulta, medico ou paciente não existem");
                                           }
                                       }
                                   }
                                }


                                case '3'->{ //Desmarcar Consulta
                                    System.out.println("Digite o cpf do paciente");
                                    String cpf = scan.nextLine();
                                    System.out.println("Digite a data da consulta no formato (aaaa-mm-ddThh:mm)");
                                    String dataCancela = scan.nextLine();

                                    Consultas consulta = null;
                                    for(Consultas c :listaConsulta){
                                        if(c.getPaciente().getCpf().equals(cpf) && c.getData().toString().equals(dataCancela)){
                                            consulta = c;
                                            break;
                                        }
                                    }

                                    if(consulta != null){
                                        //Mexe nos arraylist para garantir que o arraylist está combinando com o arquivo csv
                                        listaConsulta.remove(consulta);
                                        consulta.getPaciente().getHistoricoConsulta().remove(consulta);
                                        consulta.getMedico().getHistoricoConsulta().remove(consulta);
                                        CSV.CSV_ConsultaAtualizar(listaConsulta);
                                        System.out.println("Consulta desmarcada");
                                    }
                                    else{
                                        System.out.println("Consulta não encontrada");
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

                    // Area das Internações
                    case '4'->{
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
                                    // CPF;DataEntrada;DataSaida;Quarto;Custo

                                    System.out.println("Escreva o CPF do paciente:");
                                    String Cpf = scan.nextLine();
                                    Paciente paciente = Paciente.encontrarPaciente(Cpf,listaPaciente);
                                    if(paciente == null){
                                        System.out.println("Paciente não encontrado");
                                        System.out.println("Saindo do Cadastro");
                                        break;
                                    }
                                    boolean taInternado = false;
                                    for(Internacao i : listaInterancao){
                                        if(i.getPaciente().getCpf().equals(Cpf) && i.getDataSaida()==null ){
                                            System.out.println("Essa pessoa já está internada");
                                            taInternado = true;
                                            break;
                                        }
                                    }
                                    if(taInternado){
                                        break;
                                    }
                                    System.out.println("Data de entrada será considerada o momento atual");
                                    LocalDateTime dataEntrada = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
                                    /*Mesmo custo pra todo paciente*/
                                    double custo = Internacao.getCustoDiario(paciente);

                                    System.out.println("Escreva número do Quarto da internação: ");
                                    int quarto = scan.nextInt();
                                    scan.nextLine();
                                    for(Internacao i : listaInterancao){
                                        if(i.getQuarto().getNumero()==quarto && i.getQuarto().isOcupado() ){
                                            System.out.println("Esse quarto está ocupado");
                                            taInternado = true;
                                            break;
                                        }
                                    }
                                    if(taInternado){
                                        break;
                                    }
                                        //Cria uma consulta com Paciente, dataEntrada, dataSaida, Custo e Quarto
                                    try {
                                        Internacao internacao = new Internacao(paciente, dataEntrada, null, custo, quarto);

                                        //Garantir que o ArrayList e CSV combinem
                                        listaInterancao.add(internacao);
                                        paciente.adcionarInternacaoHistorico(internacao);

                                        CSV.CSV_Internacao(internacao);
                                        System.out.println("Internação Registrada:");
                                        internacao.getInfo();

                                    } catch (Exception e) {
                                      System.out.println("Falha no Cadastro da Internação");
                                    }
                                }



                                case '2' ->{//Ver todas as Internações
                                    if(listaInterancao.isEmpty()){
                                        System.out.println("Nenhuma internação registrada");
                                    }
                                    for(Internacao internacao : listaInterancao){
                                        try {
                                            int numero = listaInterancao.indexOf(internacao) + 1;
                                            System.out.println("Internação " + numero);
                                            System.out.println("Paciente : " + internacao.getPaciente().getNome());
                                            System.out.println("Data de Entrada : " + internacao.getDataEntrada() + " Data de Saida : " + internacao.getDataSaida());
                                            if(internacao.getDataSaida()!=null){
                                                long diasInternado = ChronoUnit.DAYS.between(internacao.getDataEntrada(),internacao.getDataSaida());
                                                System.out.println("Dias internado : " + diasInternado);
                                            }
                                            System.out.println("Numero do Quarto : " + internacao.getQuarto().getNumero());
                                            System.out.println("Custo Diario da internação : " + internacao.getCustoTotal());
                                            System.out.println();
                                        } catch (Exception e) {
                                            System.err.println("Erro em ler a consulta, paciente não encontrado");
                                        }
                                    }
                                }


                                case '3' ->{ //Finalizar Internação
                                    System.out.println("Escreva o CPF do Paciente Internado");
                                    String cpf = scan.nextLine();

                                    Paciente paciente = Paciente.encontrarPaciente(cpf,listaPaciente);
                                    boolean encontrouInternacao = false;
                                    if(paciente != null){
                                        for(Internacao internacao : listaInterancao){
                                            if(internacao.getPaciente().equals(paciente) && internacao.getDataSaida()==null){
                                             internacao.getQuarto().setDesocupado();
                                             internacao.setDataSaida();
                                             internacao.getInfo();
                                             paciente.getHistoricoInternacao().remove(internacao);
                                             encontrouInternacao = true;
                                             break;
                                            }
                                        }
                                    }
                                    if(encontrouInternacao){
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
