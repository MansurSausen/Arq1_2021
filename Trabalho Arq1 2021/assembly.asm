#Trabalho de ARQ1 - Calculadora MIPS - Felipe H. Schulz / Mansur Sausen / Wiliam K. Franco

.data 
 
 .text 

 li $t0, 49
 
li $s0, 0 
li $s1, 1 
jal calc_raiz1
j fim_raiz1
calc_raiz1: 
beqz $t0, fim_calc_raiz1
sub $t0, $t0, $s1
addi $s1, $s1, 2
addi $s0, $s0, 1
j calc_raiz1
fim_calc_raiz1:
move $t0, $s0
jr $ra
fim_raiz1: 

 li $t1, 2
 li $t2, 9
 
li $s0, 0 
li $s1, 1 
jal calc_raiz2
j fim_raiz2
calc_raiz2: 
beqz $t2, fim_calc_raiz2
sub $t2, $t2, $s1
addi $s1, $s1, 2
addi $s0, $s0, 1
j calc_raiz2
fim_calc_raiz2:
move $t2, $s0
jr $ra
fim_raiz2: 

 mul $t1, $t1, $t2
 li $t2, 1
 div $t1, $t1 ,$t2
 li $t2, 3
 mul $t1, $t1, $t2
 add $t0, $t0, $t1
 li $t1, 4
 sub $t0, $t0, $t1
 
li $v0, 1 
move $a0, $t0 
syscall 

