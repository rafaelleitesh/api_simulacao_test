package utils;

import com.github.javafaker.Faker;

public class NomeRandom {

public static String geraNome() {

    Faker geradorNome = new Faker();
    String nome = geradorNome.name().fullName();
    return nome;
    }

}
