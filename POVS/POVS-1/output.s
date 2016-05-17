.data
PrintfFormat: .asciiz "%d.\n"
		.align 2
PrintParameter: .word PrintfFormat
PrintfValue: .space 4
.global main
.text
main:
addi r3,r0,1
sw PrintfValue, r3
addi r14,r0,PrintParameter
trap 5
addi r3,r0,1
sw PrintfValue, r3
addi r14,r0,PrintParameter
trap 5
addi r3,r0,1
sw PrintfValue, r3
addi r14,r0,PrintParameter
trap 5
addi r3,r0,1
sw PrintfValue, r3
addi r14,r0,PrintParameter
trap 5
addi r3,r0,1
sw PrintfValue, r3
addi r14,r0,PrintParameter
trap 5
trap 0