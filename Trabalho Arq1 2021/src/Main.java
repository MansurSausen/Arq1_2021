import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Compilador MIPS para uma calculadora de números inteiros");
		System.out.println("Desenvolvedores: Felipe && Manser && Wiliam");
		
		System.out.println("Insira a expressão a ser convertida para Assembly");
		System.out.print("-> ");
		
		Scanner scann = new Scanner(System.in);
		String expressao = scann.nextLine();
		
		System.out.println("Expressão " + expressao);		
		System.out.println("....Gerando a expressão Assembly!");
		
		AssemblyBuilder builder = new AssemblyBuilder();	
		//builder.asm(expressao);	
		builder.GenerateAssemblyByExpression(expressao);
	}
}
