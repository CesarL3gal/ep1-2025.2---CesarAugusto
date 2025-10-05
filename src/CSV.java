import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSV {

    public static final DateTimeFormatter Formata = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


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

    public static void CSV_Consulta(Consultas Consultas) {

        try (FileWriter CSV = new FileWriter("Consultas.csv", true)) {
            String data = Consultas.getData().format(Formata);
            CSV.write(Consultas.getPaciente().getCpf() + ";" + Consultas.getMedico().getCrm() + ";" + data + ";" + Consultas.getMotivo() + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Consulta!");
        }
    }


    // CPF;DataEntrada;DataSaida;Quarto;Custo
    public static void CSV_Internacao(Internacao internacao) {
        String dataEntrada = internacao.getDataEntrada().format(Formata);
        String dataSaida="";
        try (FileWriter CSV = new FileWriter("Internacao.csv", true)) {
            CSV.write(internacao.getPaciente().getCpf() + ";" + dataEntrada + ";" + dataSaida + ";" + internacao.getQuarto().getNumero() + ";" + internacao.getCustoFinal() + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Internação!");
        }
    }

    public static void CSV_InternacaoAtualizar(ArrayList<Internacao> listaInternacao){
        try (FileWriter CSV = new FileWriter("Internacao.csv", false)) {
            for(Internacao internacao : listaInternacao) {
                String dataEntrada = internacao.getDataEntrada().format(Formata);
                String dataSaida = internacao.getDataSaida().format(Formata);
                CSV.write(internacao.getPaciente().getCpf() + ";" + dataEntrada + ";" + dataSaida + ";" + internacao.getQuarto().getNumero() + ";" + internacao.getCustoFinal() + "\n");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao Atualizar Internação!");
        }
    }






    //Area das Leituras


    // Resetar o Array List, usar toda vez que faz uma modificação, pra garantir que fique em tempo real
// Retorna uma leitura de um arquivo CSV como ArrayList
//Reler CSV dos Paceintes
    public static ArrayList<Paciente> relerPaciente() {
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        try {
            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.csv"));
            String linha;
            while ((linha = lerPaciente.readLine()) != null) {
                Paciente paciente = new Paciente(linha);
                lista.add(paciente);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }

    //Reler CSV dos Médicos
    public static ArrayList<Medico> relerMedico() {
        ArrayList<Medico> lista = new ArrayList<>();
        File file = new File ("Medicos.csv");
        try {
            BufferedReader lerMedico = new BufferedReader(new FileReader("Medicos.csv"));
            String linha;
            while ((linha = lerMedico.readLine()) != null) {
                Medico medico = new Medico(linha);
                lista.add(medico);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }


    //Reler CSV das Consultas
    public static ArrayList<Consultas> relerConsulta(ArrayList<Paciente> paciente, ArrayList<Medico> medico) {
        ArrayList<Consultas> lista = new ArrayList<>();
        File file = new File ("Consultas.csv");
        try {

            BufferedReader lerConsulta = new BufferedReader(new FileReader("Consultas.csv"));
            String linha;
            while ((linha = lerConsulta.readLine()) != null) {
                Consultas consultas = new Consultas(linha,paciente,medico);
                lista.add(consultas);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }

    public static ArrayList<Internacao> relerInternacao(ArrayList<Paciente> paciente) {
        ArrayList<Internacao> lista = new ArrayList<>();
        File file = new File ("Internacao.csv");
        try {
            BufferedReader lerInternacao = new BufferedReader(new FileReader("Internacao.csv"));
            String linha;
            while ((linha = lerInternacao.readLine()) != null) {
                Internacao internacao = new Internacao(linha, paciente);
                lista.add(internacao);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }
}