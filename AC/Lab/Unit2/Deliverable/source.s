; ID: 71727346

         .data
v:   .asciiz "473"
m1:  .space 8
m2:  .space 8
U:   .word 5
         .text
main:
         xor     r1, r1, r1
		 nop
         nop
         nop
         nop
         nop
         nop
         nop
         xor     r2, r2, r2
         xor     r3, r3, r3
         xor     r4, r4, r4
         xor     r20, r20, r20
         xor     r10, r10, r10
         lw      r12, U(r0)
b: 
         lb      r20, v(r10)
         beqz    r20, f
         andi    r20, r20, 0x0F
         slt     r1, r20, r12
         beqz    r1, mU
         daddi   r1, r1, 1
         dadd    r3, r3, r20
         j       s
mU:
         daddi   r2, r2, 1
         dadd    r4, r4, r20
s:
         daddi   r10, r10, 1
         j       b
f:
         bnez    r1, h1
         daddi   r1, r1, 1
h1:
         bnez    r2, h2
         daddi   r2, r2, 1
h2:
         mtc1    r1, f5
         cvt.d.l f1, f5
         mtc1    r2, f5
         cvt.d.l f2, f5
         mtc1    r3, f5
         cvt.d.l f3, f5
         mtc1    r4, f5
         cvt.d.l f4, f5
         div.d   f3, f3, f1
         div.d   f4, f4, f2
         s.d     f3, m1(r0)
         s.d     f4, m2(r0)
         halt
