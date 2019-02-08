.data
PrintfFormat: .asciiz "%d.\n"
		.align 2
PrintParameter: .word PrintfFormat
PrintfValue: .space 4
.global main
.text
main:
xor r0,r0,r0
xor r1,r1,r1
addi r1,r0,1
xor r2,r2,r2

addi r5,r0,15

Loop:
xor r3,r3,r3
add r3,r0,r1
add r1,r1,r2
xor r2,r2,r2
addu r2,r0,r3
nop
sw PrintfValue, r1
addi r14,r0,PrintParameter
trap 5
subi r5,r5,1
bnez r5,Loop
nop
trap 0