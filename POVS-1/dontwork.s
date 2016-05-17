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

xor r3,r3,r3
addu r3,r0,r1
addu r1,r1,r2
xor r2,r2,r2
addu r2,r0,r3
sw PrintfValue, r1
addi r14,r0,PrintParameter
trap 5

xor r3,r3,r3
addu r3,r0,r1
addu r1,r0,r2
xor r2,r2,r2
addu r2,r0,r3
sw PrintfValue, r1
addi r14,r0,PrintParameter
trap 5

xor r3,r3,r3
addu r3,r0,r1
addu r1,r0,r2
xor r2,r2,r2
addu r2,r0,r3
sw PrintfValue, r1
addi r14,r0,PrintParameter
trap 5

xor r3,r3,r3
addu r3,r0,r1
addu r1,r0,r2
xor r2,r2,r2
addu r2,r0,r3
sw PrintfValue, r1
addi r14,r0,PrintParameter
trap 5

xor r3,r3,r3
addu r3,r0,r1
addu r1,r0,r2
xor r2,r2,r2
addu r2,r0,r3
sw PrintfValue, r1
addi r14,r0,PrintParameter
trap 5
trap 0