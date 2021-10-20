import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Stack;

public class AssemblyBuilder {

	String assemblyDoc = ".data \n \n .text \n"; // string com todo código convertido para assembly
	String[] registers = { "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s2", "$s3", "$s4", "$s5", "$s6",  "$s7" };

	public void GenerateAssemblyByExpression(String expressao) {

		// token recebe a expressão a ser lida
		StringTokenizer str = new StringTokenizer(expressao);

		//Stack<Integer> stack = new Stack<Integer>(); // pilha para realizar os cálculos

		String s1;

        int value1, register_index = 0, qtd_raiz = 0, qtd_fatorial = 0, qtd_potencia = 0;

		while (str.hasMoreElements()) {
			s1 = str.nextToken();
                        
			if (s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/") || s1.equals("s") || s1.equals("f") || s1.equals("p") || s1.equals("x")) {
				switch (s1) {
                                    
				case "+":
                    register_index--; // altera índice para pegar os dois últimos registradores utilizados para fazer operação
                    AppendAssemblyArchive("add" + " " + registers[register_index-1] + ", " + registers[register_index-1] + ", " + registers[register_index]);
                    break;
                                        
				case "-":
                    register_index--; // altera índice para pegar os dois últimos registradores utilizados para fazer operação
                    AppendAssemblyArchive("sub" + " " + registers[register_index-1] + ", " + registers[register_index-1] + ", " + registers[register_index]);
                    break;
                                        
				case "/":
                    register_index--; // altera índice para pegar os dois últimos registradores utilizados para fazer operação
                    AppendAssemblyArchive("div" + " " + registers[register_index-1] + ", " + registers[register_index-1] + " ," + registers[register_index]);
                    break;
                                        
				case "*":
                    register_index--; // altera índice para pegar os dois últimos registradores utilizados para fazer operação
                    AppendAssemblyArchive("mul" + " " + registers[register_index-1] + ", " + registers[register_index-1] + ", " + registers[register_index]);
                    break;
                                        
                case "x":
                    register_index--; // altera índice para pegar os dois últimos registradores utilizados para fazer operação
                    AppendAssemblyArchive("mul" + " " + registers[register_index-1] + ", " + registers[register_index-1] + ", " + registers[register_index]);
                    break;
                                        
                case "s": // raiz quadrada
                     qtd_raiz++;
                     AppendAssemblyArchive(
                     "\nli $s0, 0 \n" +
                     "li $s1, 1 \n" +
                     "jal calc_raiz"+qtd_raiz+"\n"+
                     "j fim_raiz"+qtd_raiz+"\n"+
                     "calc_raiz"+qtd_raiz+": \n"+
                     "beqz " + registers[register_index-1]+ ", fim_calc_raiz"+qtd_raiz+"\n"+
                     "sub " + registers[register_index-1]+ ", " + registers[register_index-1] + ", " + "$s1\n" +
                     "addi $s1, $s1, 2\n"+
                     "addi $s0, $s0, 1\n"+
                     "j calc_raiz"+qtd_raiz+"\n" +
                     "fim_calc_raiz"+qtd_raiz+":\n"+
                     "move "+registers[register_index-1]+ ", $s0\n"+
                     "jr $ra\n" +
                     "fim_raiz"+qtd_raiz+": \n");
                     break;
                                    
                case "f": // fatorial
                     qtd_fatorial++;
                     AppendAssemblyArchive(
                     "\nli $s0, 1 \n" +
                     "jal calc_fat" + qtd_fatorial + "\n" +
                     "j fim_fatorial" + qtd_fatorial + " \n" +
                     "calc_fat" + qtd_fatorial + ": \n" +
                     "beqz " + registers[register_index-1] + ", fim_calc_fat" + qtd_fatorial + " \n" +
                     "mul $s0, $s0," + registers[register_index-1] +
                     "\nsubi " + registers[register_index-1] + ", " + registers[register_index-1] + ", 1\n" +
                     "j calc_fat" + qtd_fatorial + "\n"+
                     "fim_calc_fat" + qtd_fatorial + ": \n"+
                     "jr $ra \n" +
                     "fim_fatorial" + qtd_fatorial + ": \n" +
                     "move " + registers[register_index-1] + ", $s0");
                     break;
                                    
                 case "p": // potência
                      register_index --;
                      qtd_potencia++;
                      AppendAssemblyArchive(
                      "\nli $s0, 1\n" +
                      "jal calcula_potencia" + qtd_potencia + "\n" +
                      "j fim_potencia" + qtd_potencia + "\n" +
                      "calcula_potencia" + qtd_potencia + ": \n" +
                      "beqz " + registers[register_index] + ", " + "fim_calc_pot" + qtd_potencia + "\n" +
                      "subi " + registers[register_index] + ", " + registers[register_index] + ", 1 \n" +
                      "mul $s0," + registers[register_index-1] + ", $s0 \n" +
                      "j calcula_potencia" + qtd_potencia + "\n" +
                      "fim_calc_pot" + qtd_potencia + ":\n" +
                      "jr $ra \n" +
                      "fim_potencia" + qtd_potencia + ": \n" +
                      "move " + registers[register_index-1] + ", $s0\n");
                       break;
                                    
				}
			} else {
				value1 = Integer.parseInt(s1);
				AppendAssemblyArchive("li " + registers[register_index] + ", " + value1);
				register_index++;
			}

		}
		
		// Chamada para apresentar resultado final no MARS
		assemblyDoc += "\n \n"
				+ "li $v0, 1 \n"
				+ "move $a0, $t0 \n"
				+ "syscall \n";
               
		System.out.println("String: \n\n" + assemblyDoc);

        // GERAR ARQUIVO ASSEMBLY .asm
		try {
			generateArchive();
			System.out.println("\n -> Arquivo .asm gerado com sucesso");
		}catch(Exception ex) {
			System.out.println("\n -> Não foi possível gerar o arquivo .asm");
		}	
	}

	public void AppendAssemblyArchive(String operation) {
		assemblyDoc += "\n " + operation;
	}
	
	public void generateArchive() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("Assembly.asm");
		out.println("#Trabalho de ARQ1 - Calculadora MIPS - Felipe H. Schulz / Mansur Sausen / Wiliam K. Franco\n");
		out.println(assemblyDoc);
		out.close();
	}
	
}