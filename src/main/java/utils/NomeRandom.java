package utils;

import com.github.javafaker.Faker;

public class NomeRandom {

public static String geraNome() {

    Faker faker = new Faker();
    String nome = faker.name().fullName();
    return nome;
    }

}
