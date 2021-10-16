import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Compilador MIPS para uma calculadora de n�meros inteiros");
		System.out.println("Desenvolvedores: Felipe && Manser && Wiliam");
		
		System.out.println("Insira a express�o a ser convertida para Assembly");
		System.out.print("-> ");
		
		Scanner scann = new Scanner(System.in);
		String expressao = scann.nextLine();
		
		System.out.println("Express�o " + expressao);		
		System.out.println("....Gerando a express�o Assembly!");
		
		AssemblyBuilder builder = new AssemblyBuilder();	
		//builder.asm(expressao);	
		builder.GenerateAssemblyByExpression(expressao);
	}
}
