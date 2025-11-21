package com.tarefa.aula.util;

import com.tarefa.aula.exceptions.ValidacaoException;

public class DocumentoUtil {

     public static String retirarFormatacao(String documento) {
        if (documento == null) return null;

        documento = documento.replaceAll("\\D", "");

        if(documento.length() != 11 && documento.length() != 14)
            throw new ValidacaoException("documento inválido: " + documento);

        return documento;
    }
}
