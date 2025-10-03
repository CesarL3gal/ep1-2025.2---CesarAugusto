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

            CSV.write(Paciente.nome + ";" + Paciente.cpf + ";" + Paciente.idade + ";" + Paciente.planoSaude + ";" + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Paciente!");
        }

    }

    public static void CSV_Medico(Medico Medico) {

        try (FileWriter CSV = new FileWriter("Medicos.csv", true)) {

            CSV.write(Medico.nome + ";" + Medico.cpf + ";" + Medico.idade +  ";" + Medico.especializacao + ";" + "\n");

        } catch (IOException erro) {

            System.out.println("Erro ao Cadastrar Médico!");

        }

    }

    public static void CSV_Consulta(Consultas Consultas) {

        try (FileWriter CSV = new FileWriter("Consultas.csv", true)) {

            CSV.write(Consultas.nomePaciente + ";" + Consultas.pacienteCpf + ";" + Consultas.nomeMedico + ";" + Consultas.medicoCrm + ";" + Consultas.data + ";" + Consultas.motivo + "\n");

        } catch (IOException erro) {

            System.out.println("Erro ao Cadastrar Consulta!");

        }
    }

    public static void CSV_Internacao(Internacao Internacao){
        try (FileWriter CSV = new FileWriter("Internação.csv", true)){
            CSV.write(  ";"  );
        }
        catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Internação!");
        }
    }


    //Area das Leituras


    public static void lerMedico() {
        try {
            BufferedReader lerMedico = new BufferedReader(new FileReader("Medicos.CSV"));
            String linha;
            while ((linha = lerMedico.readLine()) != null) {

                String[] partes = linha.split(";");
                for (int i = 0; i < partes.length; i++) {
                    System.out.printf(partes[i] + " ");
                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
    }

    public static void lerConsulta() {
        try {
            BufferedReader lerConsultas = new BufferedReader(new FileReader("Consultas.CSV"));
            String linha;
            while ((linha = lerConsultas.readLine()) != null) {

                String[] partes = linha.split(";");
                for (int i = 0; i < partes.length; i++) {
                    System.out.printf(partes[i] + " ");
                }
                System.out.println();


            }
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
    }

    public static void lerPaciente() {
        try {
            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.CSV"));
            String linha;
            while ((linha = lerPaciente.readLine()) != null) {

                String[] partes = linha.split(";");
                for (int i = 0; i < partes.length; i++) {
                    System.out.printf(partes[i] + " ");
                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
    }


    // Resetar o Array List, usar toda vez que faz uma modificação, pra garantir que fique em tempo real
// Retorna uma leitura de um arquivo CSV como ArrayList
//Reler CSV dos Paceintes
    public static ArrayList<String> relerPaciente() {
        ArrayList<String> lista = new ArrayList<String>();
        try {

            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.CSV"));
            String linha;
            lista.clear();
            while ((linha = lerPaciente.readLine()) != null) {
                lista.add(linha);
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
    public static ArrayList<String> relerMedico() {
        ArrayList<String> lista = new ArrayList<String>();
        try {

            BufferedReader lerPaciente = new BufferedReader(new FileReader("Medicos.CSV"));
            String linha;
            lista.clear();
            while ((linha = lerPaciente.readLine()) != null) {
                lista.add(linha);
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
    public static ArrayList<String> relerConsulta() {
        ArrayList<String> lista = new ArrayList<String>();
        try {

            BufferedReader lerPaciente = new BufferedReader(new FileReader("Consultas.CSV"));
            String linha;
            lista.clear();
            while ((linha = lerPaciente.readLine()) != null) {
                lista.add(linha);
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