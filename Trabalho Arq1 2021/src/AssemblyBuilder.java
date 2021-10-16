import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssemblyBuilder {

    public int asm(String expressao){
        String ope;
             File arq = new File("assembl.asm");  
        try(FileWriter fw = new FileWriter(arq)){		//FW = File whiter
            
        int x = 0;
        int t = 0;
        List<String> exp = new ArrayList<>();
        exp.addAll(Arrays.asList(expressao.split(" "))); 
        fw.write("#HENRIQUE HERMES && CARLOS VOGT\n");          
            if(exp.contains("f")){
                
            }else{
                fw.write(".data\n");
            }
            fw.write(".text\n");
            fw.write("   inicio: \n");
            if(exp.contains("f")){
                
            }else{
            fw.write("   li $v0, 1\n");
            }
        while(x!=1){
            if(exp.contains("1")){
                 if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t1, 1\n");
                }
                x = 1;             
            }
            if(exp.contains("2")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t2, 2\n");
                }
                x = 1;
            }
            if(exp.contains("3")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t3, 3\n");
                }
                x = 1;
            }
            if(exp.contains("4")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t4, 4\n");
                }
                x = 1;
            }
            if(exp.contains("5")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t5, 5\n");
                }
                x = 1;
            }
            if(exp.contains("6")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t6, 6\n");
                }
                x = 1;
            }
            if(exp.contains("7")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t7, 7\n");
                }
                x = 1;
            }
            if(exp.contains("8")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t8, 8\n");
                }
                x = 1;
            }
            if(exp.contains("9")){
                if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){       
                 }else{
                fw.write("li $t9, 9\n");
                }
                x = 1;
            }
      }
        if(exp.contains("n") ||exp.contains("r")||exp.contains("f")){
            if(exp.contains("n")){
                String auxFAT = exp.get(0);
                String auxFAT2 = exp.get(1);
                fw.write("li $s0,"+auxFAT+auxFAT2+"\n");
                fw.write("li $t0,"+auxFAT+auxFAT2+"\n");
                fw.write("li $s1, 1\n");
                fw.write("li $s2, 2\n");
                fw.write("li $s3, 1\n");
                fw.write("add $s4, $s0, $s1\n");
                fw.write("slt $t0, $s0, $s2\n");
                fw.write("beq $t0, $zero, Loop\n");
                fw.write("j LSair\n");
                fw.write("Loop: addi $s5, $s5, 1\n");
                fw.write("slt $t0, $s5,$s4\n");
                fw.write("beq $t0, $zero, LSair\n");
                fw.write("mul $s3, $s3, $s5\n");
                fw.write("j Loop\n");
                fw.write("LSair:\n");
                fw.write("la $a0, ($s3)\n");
                fw.write("syscall\n");
            }
            if(exp.contains("r")){
                String auxRAIZ = exp.get(0);      
                String auxRAIZ2 = exp.get(1);
                fw.write("li $s0,"+auxRAIZ+auxRAIZ2+"\n");
                fw.write("jal r\n");
                fw.write("r: mul $t0, $s1, 2\n");
                fw.write("add $t0,$t0,1\n");
                fw.write("sub $s0,$s0,$t0\n");
                fw.write("add $s1,$s1,1\n");
                fw.write("beq $s0,$zero, find\n");
                fw.write("j r\n");
                fw.write("find: la $a0, ($s1)\n");
                fw.write("syscall\n");            
            }
            if(exp.contains("f")){
                String auxFIBO = exp.get(0);
                String auxFIBO2 = exp.get(1);
                fw.write("li $s1, "+auxFIBO+auxFIBO2+"\n"); 
                fw.write("lw $t0, um\n");
                fw.write("lw $t1, dois\n");
                fw.write("li $t2, 2\n");
                fw.write("la $t3, mem\n");
                fw.write("loop: add $t0, $t0, $t1 \n");
                fw.write("add $t1, $t1, $t0\n");
                fw.write("sw $t0, 0($t3)\n");
                fw.write("sw $t1, 4($t3)\n");
                fw.write("li $v0, 1\n");
                fw.write("move $a0, $t0\n");
                fw.write("syscall\n");
                fw.write("move $a0,$t0\n");
                fw.write("li $v0, 4\n");
                fw.write("la $a0, space\n");
                fw.write("syscall\n");
                fw.write("li $v0, 1\n");
                fw.write("move $a0, $t1\n");
                fw.write("syscall\n");
                fw.write("li $v0, 4\n");
                fw.write("la $a0, space\n");
                fw.write("syscall\n");
                fw.write("addi $s3,$s3,1\n");
                fw.write("beq $s1,$s3,end\n");
                fw.write("addiu $t3, $t3, 8\n");
                fw.write("addiu $t2, $t2, 2\n");
                fw.write("addi $s2,$s2,2\n");
                fw.write("slt $t9,$s1,$s2\n");
                fw.write("bne $t9,$zero,end\n");
                fw.write("j loop\n");
                fw.write("jr $ra\n");
                fw.write("end:\n");
                fw.write(".data\n");
                fw.write("um: .word 1\n");
                fw.write("dois: .word 0\n");
                fw.write("mem: .space 4096\n");
                fw.write("space: .asciiz \" \"\n");              
            }
            
        }else{
        String aux; //VALORES
        String aux1; // VALORES
        String opeAux;
        String opeAux2;
        aux = exp.get(0);
        aux1 = exp.get(1);      
        ope = exp.get(2); // OPERAÇAO + - * / -> NA POSIÇAO "2"                
        if(ope.equals("+") || ope.equals("-") || ope.equals("*") || ope.equals("/")){            
        while(t != 1){
            for (int i = 0; i < 10; i++) {      // PRIMEIRA OPERAÇAO
                for (int j = 0; j < 10; j++) {               
                String q;
                String w;
                q = Integer.toString(i);
                w = Integer.toString(j);
                  if(ope.equals("*") && aux.equals(q) && aux1.equals(w)){      // PEGA O VALOR DA EXPRESSAO ( Q e W )   
                                          // PEGAR O REGISTRADOR CERTO 
                      fw.write("mult $t"+q+", $t"+w+"\n");
                      fw.write("mflo $s0\n");
                       t=1;
                          
                     }
                  if(ope.equals("/") && aux.equals(q) && aux1.equals(w)){         
                                           // PEGAR O REGISTRADOR CERTO 
                      fw.write("div $t"+q+", $t"+w+"\n");
                      fw.write("mflo $s0\n");
                       t=1;
                          }
                          
                  if(ope.equals("+") && aux.equals(q) && aux1.equals(w)){         
                         // PEGAR O REGISTRADOR CERTO 
                      fw.write("add $s0, $t"+q+", $t"+w+"\n");
                       t=1;
                          }
                          
                  if(ope.equals("-") && aux.equals(q) && aux1.equals(w)){         
                                         // PEGAR O REGISTRADOR CERTO 
                      fw.write("sub $s0, $t"+q+", $t"+w+"\n");
                       t=1;
                          }
                          
                  }
                }
            }
        }else{
            opeAux = exp.get(3);    // OPERAÇAO + - * / -> NA POSIÇAO "3"
            if(opeAux.equals("+") || opeAux.equals("-") || opeAux.equals("*") || opeAux.equals("/")){
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                String q;
                String aux2;
                aux2 = exp.get(2);
                String w;
                q = Integer.toString(i);
                w = Integer.toString(j);
                if(opeAux.equals("*") && aux2.equals(q) && aux1.equals(w)){      // PEGA O VALOR DA EXPRESSAO ( Q e W )   
                                          // PEGAR O REGISTRADOR CERTO 
                      fw.write("mult $t"+w+", $t"+q+"\n");
                      fw.write("mflo $s0\n");
                       
                     }
                  if(opeAux.equals("/") && aux2.equals(q) && aux1.equals(w)){         
                                           // PEGAR O REGISTRADOR CERTO 
                      fw.write("div $t"+w+", $t"+q+"\n");
                      fw.write("mflo $s0\n");
                       
                          }
                          
                  if(opeAux.equals("+") && aux2.equals(q) && aux1.equals(w)){         
                         // PEGAR O REGISTRADOR CERTO 
                      fw.write("add $s0, $t"+w+", $t"+q+"\n");
                      
                          }
                          
                  if(opeAux.equals("-") && aux2.equals(q) && aux1.equals(w)){         
                                         // PEGAR O REGISTRADOR CERTO 
                      fw.write("sub $s0, $t"+w+", $t"+q+"\n");
                       
                          }   
                   } 
                }
                
            for (int u = 0; u < 10; u++) {
                for (int y = 0; y < 10; y++) {
                String q;
                String w;
                String auxe;
                auxe = exp.get(0);
                String aux2;
                aux2 = exp.get(1);
                q = Integer.toString(u);
                w = Integer.toString(y);
                opeAux2 = exp.get(4);    // OPERAÇAO + - * / -> NA POSIÇAO "4"
                if(opeAux2.equals("+") && auxe.equals(q) && aux2.equals(w)){   // PEGA O VALOR DA EXPRESSAO ( Q e W )   
                     fw.write("add $s1, $s0, $t"+q+"\n");
                      
                                         fw.write("move $a0, $s1\n" +
                                            "syscall\n");
                          }
                      
                  if(opeAux2.equals("/") && auxe.equals(q) && aux2.equals(w)){         
                     fw.write("div $s0, $t"+q+"\n");
                      
                       fw.write("mflo $s1\n");
                                         fw.write("move $a0, $s1\n" +
                                            "syscall\n");
                          }
                  if(opeAux2.equals("*") && auxe.equals(q) && aux2.equals(w)){         
                     fw.write("mult $s0, $t"+q+"\n");
                      
                       fw.write("mflo $s1\n");
                                         fw.write("move $a0, $s1\n" +
                                            "syscall\n");
                          }
                  if(opeAux2.equals("-")){
                      if(auxe.equals(q) && aux2.equals(w) && opeAux.equals("+") || opeAux.equals("-")){
                     fw.write("sub $s1, $s0, $t"+q+"\n");
                      
                  fw.write("move $a0, $s1\n" +
                                            "syscall\n");}
                          } 
                  if(opeAux2.equals("-")){
                      if(auxe.equals(q) && aux2.equals(w) && opeAux.equals("/")){
                     fw.write("sub $s1, $s0, $t"+q+"\n");
                       
                  fw.write("move $a0, $s1\n" +
                                            "syscall\n");}
                      if(auxe.equals(q) && aux2.equals(w) && opeAux.equals("*")){
                     fw.write("sub $s1, $s0, $t"+q+"\n");
                      
                  fw.write("move $a0, $s1\n" +
                                            "syscall\n");}
                          }
                }
            }
            }
        }
            if(exp.size() > 3){
            for (int i = 0; i < 10; i++) {      // SEGUNDA OPERAÇAO
                for (int j = 0; j < 10; j++) {               
                String q;
                String w;
                String aux2;
                String ope2;
                aux2 = exp.get(3);
                ope2 = exp.get(4);
                q = Integer.toString(i);
                w = Integer.toString(j);
                  if(ope2.equals("+") && aux.equals(q) && aux2.equals(w)){   // PEGA O VALOR DA EXPRESSAO ( Q e W )   
                     fw.write("add $s4, $s0, $t"+w+"\n");

                                         fw.write("move $a0, $s4\n" +
                                            "syscall\n");
                          }
                      
                  if(ope2.equals("/") && aux.equals(q) && aux2.equals(w)){         
                     fw.write("div $s5, $s0, $t"+w+"\n");
                   
                                         fw.write("move $a0, $s5\n" +
                                            "syscall\n");
                          }
                  if(ope2.equals("*") && aux.equals(q) && aux2.equals(w)){         
                     fw.write("mult $s6, $s0, $t"+w+"\n");
                      
                                         fw.write("move $a0, $s6\n" +
                                            "syscall\n");
                          }
                  if(ope2.equals("-") && aux.equals(q) && aux2.equals(w)){         
                     fw.write("sub $s7, $s0, $t"+w+"\n");
                       
                  fw.write("move $a0, $s7\n" +
                                            "syscall\n");
                          }
                  }

                }
            }else{
                fw.write("move $a0, $s0\n" +
                                            "syscall\n");
            }
        
        }
        if(exp.contains("f")){
            
        }else{
        fw.write("fim:");
        }
  }catch(IOException e){
    }
        return 0;
}
}