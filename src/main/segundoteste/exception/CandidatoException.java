package main.segundoteste.exception;

import java.util.Map;

public class CandidatoException extends Exception {
    public CandidatoException(String message) {
        super(message);
    }

    public static void verificarCandidatoExistente(Map<Integer, String> candidatos, int codCandidato) throws CandidatoException {
        if (!candidatos.containsKey(codCandidato)) {
            throw new CandidatoException("Candidato não encontrado");
        }
    }
}
