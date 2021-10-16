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
	Stack<Integer> registerResult = new Stack<Integer>(); // pilha para registradores que precisarão ser chamados posteriormente

	public void GenerateAssemblyByExpression(String expressao) {

		// tokenn recebe a expressão a ser lida
		StringTokenizer str = new StringTokenizer(expressao);

		int value1;
		int value2;
		Stack<Integer> stack = new Stack<Integer>(); // pilha para realizar os cálculos

		String s1;

		int valor = 0;

		while (str.hasMoreElements()) {
			s1 = str.nextToken();

			if (s1.equals("+") || s1.equals("-") || s1.equals("*") || s1.equals("/")) {
				switch (s1) {
				case "+":
					value1 = stack.pop();
					value2 = stack.pop();
					
					/**
					 * value1 sempre está assumando um valor com uma contante nova durante a leitura, quando for o último elemento, o value1 vem com o valor
					 * da última operação feita, por isso lemos value2, por ser o valor mais antigo inserido na pilha, já que os valores das operações sempre são lidas
					 * direto no registrador
					 * **/
					if(stack.isEmpty() && !registerResult.isEmpty()) {
						BuildAssembly(value2, value1, "addi", stack.size());
						stack.push(value2 + value1);
						valor = value2 + value1;
					}else {
						BuildAssembly(value1, value2, "addi", stack.size());
						stack.push(value1 + value2);
						valor = value1 + value2;
					}

					break;
				case "-":
					value1 = stack.pop();
					value2 = stack.pop();
					
					if(stack.isEmpty() && !registerResult.isEmpty()) {
						BuildAssembly(value2, value1, "subi", stack.size());
						stack.push(value2 - value1);
						valor = value2 - value1;
					}else {
						BuildAssembly(value1, value2, "subi", stack.size());
						stack.push(value1 - value2);
						valor = value1 - value2;
					}

					break;
				case "/":
					value1 = stack.pop();
					value2 = stack.pop();
					
					if(stack.isEmpty() && !registerResult.isEmpty()) {
						BuildAssembly(value2, value1, "div", stack.size());
						stack.push(value2 / value1);
						valor = value2 / value1;
					}else {
						BuildAssembly(value1, value2, "div", stack.size());
						stack.push(value1 / value2);
						valor = value1 / value2;
					}

					break;
				case "*":
					value1 = stack.pop();
					value2 = stack.pop();

					if(stack.isEmpty() && !registerResult.isEmpty()) {
						BuildAssembly(value2, value1, "mul", stack.size());
						stack.push(value2 * value1);
						valor = value2 * value1;
					}else {
						BuildAssembly(value1, value2, "mul", stack.size());
						stack.push(value1 * value2);
						valor = value1 * value2;
					}
					
					break;
				}
			} else {
				stack.push(Integer.parseInt(s1));
			}

		}

		System.out.println("Valor final: " + valor);
	}
	
	public void BuildAssembly(int value1, int value2, String type ,int stackSize) {
		
		int registrador1 = 0;
		int registrador2 = 0;

		// se exister resultado de operação na pilha, pega esse registrador que possui o cálculo guardado, caso contrário pega um novo registrador
		if(registerResult.isEmpty()) {
			
			registrador1 = GetFreeRegister();
			registerUsed[registrador1] = true; // seta como registrador ocupado
			
			AppendAssemblyArchive(" li " + registers[registrador1] + " " + value1);

			registrador2 = GetFreeRegister(); // busca o index do registrador
			registerUsed[registrador2] = true; // seta como registrador ocupado
			
			AppendAssemblyArchive(type + " " + registers[registrador2] + " " + registers[registrador1] + " " + value2);	
						
		}else {
			
			registrador1 = registerResult.pop();
			
			registrador2 = GetFreeRegister(); // busca o index do registrador
			registerUsed[registrador2] = true; // seta como registrador ocupado
			
			/**
			 * value1 sempre está assumando um valor com uma contante nova durante a leitura, quando for o último elemento, o value1 vem com o valor
			 * da última operação feita, por isso lemos value2, por ser o valor mais antigo inserido na pilha, já que os valores das operações sempre são lidas
			 * direto no registrador
			 * **/
			AppendAssemblyArchive(type + " " + registers[registrador2] + " " + registers[registrador1] + " " + value1);	
		}
		
		registerResult.add(registrador2);
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
	
	public void AppendAssemblyArchive(String operation) {
		assemblyDoc += "\n " + operation;
	}
	
}