import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class CSV {

    //Area das Escritas
    public static void CSV_Paciente(Paciente Paciente) {
        try (FileWriter CSV = new FileWriter("Pacientes.csv", true)) {
            CSV.write(Paciente.getNome() + ";" + Paciente.getCpf() + ";" + Paciente.getIdade() + ";" + Paciente.getPlanoSaude().name() + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Paciente!");
        }
    }

    public static void CSV_Pacienteatualizar(ArrayList<Paciente> listaPacientes) {
        try (FileWriter CSV = new FileWriter("Pacientes.csv", false)) { // false para sobrescrever
            for (Paciente paciente : listaPacientes) {
                CSV.write(paciente.getNome() + ";" + paciente.getCpf() + ";" + paciente.getIdade() + ";" + paciente.getPlanoSaude().name() + "\n");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao atualizar o arquivo de Pacientes!");
        }
    }

    public static void CSV_Medico(Medico Medico) {

        try (FileWriter CSV = new FileWriter("Medicos.csv", true)) {
            CSV.write(Medico.getNome() + ";" + Medico.getCpf() + ";" + Medico.getCrm() +  ";" + Medico.getIdade() + ";" + Medico.getEspecializacao().name() + ";" + Medico.getCustoConsulta() + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Médico!");
        }

    }

    public static void CSV_Medicoatualizar(ArrayList<Medico> listamedico){
        try(FileWriter CSV = new FileWriter("Medicos.csv", false)){
            for(Medico medico : listamedico){
                CSV.write(medico.getNome() + ";" + medico.getCpf() + ";" + medico.getCrm() + ";" + medico.getIdade() + ";" + medico.getEspecializacao().name() + ";" + medico.getCustoConsulta() + "\n");
            }
        }catch(IOException erro){
            System.out.println("Erro ao atualizar o arquivo dos Médicos");
        }

    }

    public static void CSV_Consulta(Consultas Consultas) {
        try (FileWriter CSV = new FileWriter("Consultas.csv", true)) {
            CSV.write(Consultas.getPaciente().getCpf() + ";" + Consultas.getMedico().getCrm() + ";" + Consultas.getData().toString() + ";" + Consultas.getMotivo() + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Consulta!");
        }
    }
    public static void CSV_ConsultaAtualizar(ArrayList<Consultas> listaConsulta){
        try (FileWriter CSV = new FileWriter("Consultas.csv", false)) {
            for(Consultas consultas : listaConsulta) {
                CSV.write(consultas.getPaciente().getCpf() + ";" + consultas.getMedico().getCrm() + ";" + consultas.getData().toString() + ";" + consultas.getMotivo() + "\n");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao Atualizar Internação!");
        }
    }


    // CPF;DataEntrada;DataSaida;Quarto;Custo
    public static void CSV_Internacao(Internacao internacao) {
        String dataEntrada = internacao.getDataEntrada().toString();
        String dataSaida;
        if(internacao.getDataSaida()!=null) {
            dataSaida = internacao.getDataSaida().toString();
        }
        else {
            dataSaida = "";
        }
        try (FileWriter CSV = new FileWriter("Internacao.csv", true)) {
            CSV.write(internacao.getPaciente().getCpf() + ";" + dataEntrada + ";" + dataSaida + ";" + internacao.getQuarto().getNumero() + ";" + Internacao.getCustoDiario(internacao.getPaciente()) + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Internação!");
        }
    }

    public static void CSV_InternacaoAtualizar(ArrayList<Internacao> listaInternacao){
        try (FileWriter CSV = new FileWriter("Internacao.csv", false)) {
            for(Internacao internacao : listaInternacao) {
                String dataSaida;
                if(internacao.getDataSaida()!=null) {
                    dataSaida = internacao.getDataSaida().toString();
                }
                else {
                    dataSaida = "";
                }
                String dataEntrada = internacao.getDataEntrada().toString();
                CSV.write(internacao.getPaciente().getCpf() + ";" + dataEntrada + ";" + dataSaida + ";" + internacao.getQuarto().getNumero() + ";" + Internacao.getCustoDiario(internacao.getPaciente()) + "\n");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao Atualizar Internação!");
        }
    }






    //Area das Leituras


// Retorna uma leitura de um arquivo CSV como ArrayList
//Reler CSV dos Paceintes
    public static ArrayList<Paciente> relerPaciente() {
        ArrayList<Paciente> lista = new ArrayList<>();
        File arquivo = new File("Pacientes.csv");
        try {
            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.csv"));
            String linha;
            while ((linha = lerPaciente.readLine()) != null) {
                Paciente paciente = new Paciente(linha);
                lista.add(paciente);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivo do Paciente");
            try {
                arquivo.createNewFile();
                System.out.println("Criando arquivo...");
            } catch (IOException ex) {
                System.out.println("Falhou ao criar o arquivo");
            }
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }

    //Reler CSV dos Médicos
    public static ArrayList<Medico> relerMedico() {
        ArrayList<Medico> lista = new ArrayList<>();
        File arquivo = new File("Medicos.csv");
        try {
            BufferedReader lerMedico = new BufferedReader(new FileReader("Medicos.csv"));
            String linha;
            while ((linha = lerMedico.readLine()) != null) {
                Medico medico = new Medico(linha);
                lista.add(medico);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivo do Médico");
            try {
                arquivo.createNewFile();
                System.out.println("Criando arquivo...");
            } catch (IOException ex) {
                System.out.println("Falhou ao criar o arquivo");
            }
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }


    //Reler CSV das Consultas
    public static ArrayList<Consultas> relerConsulta(ArrayList<Paciente> paciente, ArrayList<Medico> medico) {
        ArrayList<Consultas> lista = new ArrayList<>();
        File arquivo = new File("Consultas.csv");
        try {
            BufferedReader lerConsulta = new BufferedReader(new FileReader("Consultas.csv"));
            String linha;
            while ((linha = lerConsulta.readLine()) != null) {
                Consultas consultas = new Consultas(linha,paciente,medico);
                lista.add(consultas);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivo da Consulta");
            try {
                arquivo.createNewFile();
                System.out.println("Criando arquivo...");
            } catch (IOException ex) {
                System.out.println("Falhou ao criar o arquivo");
            }
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }

    public static ArrayList<Internacao> relerInternacao(ArrayList<Paciente> paciente) {
        ArrayList<Internacao> lista = new ArrayList<>();
        File arquivo = new File("Internacao.csv");
        try {
            BufferedReader lerInternacao = new BufferedReader(new FileReader("Internacao.csv"));
            String linha;
            while ((linha = lerInternacao.readLine()) != null) {
                Internacao internacao = new Internacao(linha, paciente);
                lista.add(internacao);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivo da Internação");
            try {
                arquivo.createNewFile();
                System.out.println("Criando arquivo...");
            } catch (IOException ex) {
                System.out.println("Falhou ao criar o arquivo");
            }
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }
}