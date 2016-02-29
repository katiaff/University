# -*- coding: utf-8 -*-
"""
Created on Wed Feb 24 18:09:31 2016

@author: uo244965
"""
import numpy as np
import sys

# -------EXERCISE 1----------
#
#def de2bi_a(num, digits):
#    ret = np.zeros((1, digits))
#    remainder = np.fix(num % 2)
#    result = num // 2
#    i = 0 
#    ret[0,i] = remainder
#    i+=1
#    while result > 1 and i < digits:
#        remainder = np.fix(result % 2)
#        result = result // 2    
#        ret[0,i] = remainder
#        i+=1
#    
#    ret[0,digits-1 ] = 1
#    return np.fliplr(ret)
    
#print de2bi_a(105.8125, 8)

#print bin(105)

    
# -------EXERCISE 2----------
#    
#def de2bi_b(num, digits):
#    ret = np.zeros((1, digits))
#    integer = 0
#    i = 0   
#    
#    if num * 2 > 1:
#        integer = 1
#        result = (num * 2) - 1  
#    else:
#        result = num * 2
#    ret[0,i] = integer
#    
#    i+=1
#    
#    while result < 1 and i < digits:
#        if result * 2 >= 1:
#            integer = 1
#            result = (result * 2) - 1  
#        else:
#            integer = 0
#            result = result * 2 
#        ret[0,i] = integer
#        i+=1
#
#
#    return ret
    
#print de2bi_b(.8125, 5) 
    
# -------EXERCISE 3----------

#print de2bi_b(.1, 30)


# -------EXERCISE 4----------

#print de2bi_a(80,8)

# -------EXERCISE 5----------

#bias = np.power(2, 8 - 1)
#print de2bi_a(80 + bias, 8)

# -------EXERCISE 6----------

#def sum_largest_float(bits):
#    result = 0
#    
#    if (bits == 32):
#        counter = -23
#        while counter <= 0:
#            result += 2** counter
#            counter += 1
#        result = result * 2**127
#        
#        
#    if (bits == 64):
#        counter = -52
#        while counter <= 0:
#            result += 2** counter
#            counter += 1
#        result = result * 2**1023
#    
#    return result
#
#
#output = sum_largest_float(64)
#print('{:.16e}'.format(output))
#print sys.float_info.max

# -------EXERCISE 7----------

# 64-bit floating point
#print 2**53
#32-bit floating point
#print 2**24

# -------EXERCISE 8----------

#output = 2**(-1022)
#print('{:.16e}'.format(output))
#sys.float_info.min














