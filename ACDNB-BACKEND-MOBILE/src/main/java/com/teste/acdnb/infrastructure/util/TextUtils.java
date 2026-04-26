package com.teste.acdnb.infrastructure.util;
import java.text.Normalizer;

public class TextUtils {

    public static String normalizarCidade(String nome) {
        if (nome == null) return null;

        return Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toUpperCase()
                .trim();
    }
}
