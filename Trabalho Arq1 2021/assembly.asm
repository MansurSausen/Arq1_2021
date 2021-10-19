#Trabalho de ARQ1 - Calculadora MIPS - Felipe H. Shulz / Mansur Sausen / Wiliam K. Franco

.data 
 
 .text 

  li $t0, 4
  li $t1, 2
 
li $s0, 1
jal calcula_potencia1
j fim_potencia1
calcula_potencia1: 
beqz $t1, fim_calc_pot1
subi $t1, $t1, 1 
mul $s0,$t0, $s0 
j calcula_potencia1
fim_calc_pot1:
jr $ra 
fim_potencia1: 
move $t0, $s0

  li $t1, 81
 
li $s0, 0 
li $s1, 1 
jal calc_raiz1
j fim_raiz1
calc_raiz1: 
beqz $t1, fim_calc_raiz1
sub $t1, $t1, $s1
addi $s1, $s1, 2
addi $s0, $s0, 1
j calc_raiz1
fim_calc_raiz1:
move $t1, $s0
jr $ra
fim_raiz1: 

 add $t0, $t0, $t1
  li $t1, 6
 
li $s0, 1 
jal calc_fat1
j fim_fatorial1 
calc_fat1: 
beqz $t1, fim_calc_fat1 
mul $s0, $s0,$t1
subi $t1, $t1, 1
j calc_fat1
fim_calc_fat1: 
jr $ra 
fim_fatorial1: 
move $t1, $s0
  li $t2, 2
  li $t3, 4
 mul $t2, $t2, $t3
 add $t1, $t1, $t2
 add $t0, $t0, $t1
li $v0, 1 
move $a0, $t0 
syscall 

