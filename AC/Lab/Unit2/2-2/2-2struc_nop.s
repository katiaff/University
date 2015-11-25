.data

var1:   .double 3.5
var2:   .double 1.7
var3:   .double 2.8
var4:   .double 0.4
res1:    .space 8
res2:    .space 8

.code

main:
         l.d   f10, var1(r0)
         l.d   f20, var2(r0)
         l.d   f11, var3(r0)
         l.d   f21, var4(r0)
         add.d f0, f20, f10  			; floating-point addition f0 <- f20 + f10       
         add.d f1, f21, f11
         s.d   f0, res1(r0)      	; store f1 in res1
         s.d   f1, res2(r0)
         halt
