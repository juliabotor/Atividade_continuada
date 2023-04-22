package br.gov.cesarschool.poo.fidelidade.util;

public class ValidadorCPF {
    private ValidadorCPF() {}
    
    public static boolean ehCpfValido(String cpf) {
        cpf = cpf.replaceAll("\\D+", "");
        
        if (cpf.length() != 11) {
            return false;
        }
        
        boolean todosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }
        
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int primeiroDigito = resto < 2 ? 0 : 11 - resto;
        
        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
            return false;
        }
        
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int segundoDigito = resto < 2 ? 0 : 11 - resto;
        
        if (Character.getNumericValue(cpf.charAt(10)) != segundoDigito) {
            return false;
        }
        
        return true;
    }
}

