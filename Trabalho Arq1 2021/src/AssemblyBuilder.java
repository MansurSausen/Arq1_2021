import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Stack;

public class AssemblyBuilder {

	String assemblyDoc = ".data \n \n \n .text \n"; // string com todo código convertido para assembly
	String[] registers = { "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9" };
	boolean[] registerUsed = { false, false, false, false, false, false, false, false, false, false };

	public void GenerateAssemblyByExpression(String expressao) {

		// guarda os dados da expressao numa lista
		/*
		 * List<String> list = new ArrayList<String>();
		 * list.addAll(Arrays.asList(expressao.split(" ")));
		 */

		StringTokenizer str = new StringTokenizer(expressao);

		int value1;
		int value2;
		Stack<Integer> stack = new Stack<Integer>();

		String s1;

		double valor = 0;

		while (str.hasMoreElements()) {
			s1 = str.nextToken();

			if (s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/")) {
				switch (s1) {
				case "+":
					value1 = stack.pop();
					value2 = stack.pop();
					stack.push(value1 + value2);
					// System.out.print(number1 + " + " + number2);
					WriteSum(value1, value2);
					valor = value1 + value2;
					break;
				case "-":
					value1 = stack.pop();
					value2 = stack.pop();
					// System.out.print(number1 +" - "+ number2);
					valor = value1 + value2;
					break;
				case "/":
					value1 = stack.pop();
					value2 = stack.pop();
					// System.out.print(number1 +" / "+ number2);
					valor = value1 + value2;
					break;
				case "*":
					value1 = stack.pop();
					value2 = stack.pop();
					stack.push(value1 * value2);
					// System.out.print(number1 +" * "+ number2);
					valor = value1 * value2;
					break;
				}
			} else {
				stack.push(Integer.parseInt(s1));
			}

		}

		System.out.println("Valor final: " + valor);
	}
	
	public void WriteSum(int value1, int value2) {
		
		int registrador1 = GetFreeRegister();
		registerUsed[registrador1] = true; // seta como registrador ocupado
		
		int registrador2 = GetFreeRegister(); // busca o index do registrador
		registerUsed[registrador2] = true; // seta como registrador ocupado
		
		AppendAssemblyArchive(" li " + registers[registrador1] + " " + value1);
		AppendAssemblyArchive(" addi " + registers[registrador2] + " " + registers[registrador1] + " " + value2);		
	}

	
	public void AppendAssemblyArchive(String operation) {
		assemblyDoc += "\n " + operation;
	}
	

	public int GetFreeRegister() {
		int register = 0;

		for (int x = register; x < registerUsed.length; x++) {
			if (registerUsed[x] == false) // pega o primeiro registrador com status não usado = false e para utilizar
			{
				register = x;
				//registerUsed[x] = true; // seta o registrador como usado

				break;
			}
		}

		return register;
	}
}