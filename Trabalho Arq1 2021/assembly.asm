#Trabalho de ARQ1 - Calculadora MIPS - Felipe H. Shulz / Mansur Sausen / Wiliam K. Franco

.data 
 
 .text 

  li $t0, 2
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

  li $t1, 3
  li $t2, 6
 add $t1, $t1, $t2
 mul $t0, $t0, $t1
  li $t1, 2
 div $t0, $t0 ,$t1
  li $t1, 2
 sub $t0, $t0, $t1
 
li $v0, 1 
move $a0, $t0 
syscall 

