package main.segundoteste;

import main.segundoteste.exception.CandidatoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Segundo {

    private Map<Integer, String> candidatos;
    private Map<Integer, String> statusCandidatos;
    private int contador;

    public Segundo() {
        candidatos = new HashMap<Integer, String>();
        statusCandidatos = new HashMap<Integer, String>();
        contador = 1;
    }

    public int iniciarProcesso(String nome) {
        int codCandidato = contador++;
        candidatos.put(codCandidato, nome);
        statusCandidatos.put(codCandidato, "Recebido");
        return codCandidato;
    }

    public void marcarEntrevista(int codCandidato) throws CandidatoException {
        String status = statusCandidatos.get(codCandidato);
        CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
        if (status.equals("Qualificado") || status.equals("Aprovado")) {
            throw new CandidatoException("Candidato já participa do processo");
        } else {
            statusCandidatos.put(codCandidato, "Qualificado");
        }
    }

    public void desqualificarCandidato(int codCandidato) throws CandidatoException {
        CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
        candidatos.remove(codCandidato);
        statusCandidatos.remove(codCandidato);
    }

    public String verificarStatusCandidato(int codCandidato) throws CandidatoException {
        String status = statusCandidatos.get(codCandidato);
        CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
        return status;
    }

    public void aprovarCandidato(int codCandidato) throws CandidatoException {
        String status = statusCandidatos.get(codCandidato);
        CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
        if (status.equals("Aprovado") || status.equals("Recebido")) {
            throw new CandidatoException("Candidato já participa do processo");
        } else {
            statusCandidatos.put(codCandidato, "Aprovado");
        }
    }

    public List<String> obterAprovados() {
        List<String> aprovados = new ArrayList<>();

        for (int codCandidato : statusCandidatos.keySet()) {
            String status = statusCandidatos.get(codCandidato);

            if (status.equals("Aprovado")) {
                String nome = candidatos.get(codCandidato);
                aprovados.add(nome);
            }
        }
        return aprovados;
    }
}
