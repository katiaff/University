# -*- coding: utf-8 -*-
"""
Created on Wed Mar 02 18:08:01 2016

@author: uo244965
"""
from __future__ import division
import numpy as np
import matplotlib.pyplot as plt


# -------EXERCISE 1----------

#def bisection1(f, a, b, maxIter):
#    ak = a
#    bk = b
#    for k in range(maxIter):
#        xk = (ak + bk)/2
#        if f(ak)*f(xk) < 0:
#            bk = xk
#        else:
#            ak = xk
#    return xk
#
#
#
#    
#f = lambda x : x**3 - 2*x**2 + 1 
#a = -1.0; b = -0.1; n = 20
#sol1 = bisection1(f,a,b,n)
#print(sol1)
#
#
#a = 0.5; b = 1.1; n = 20
#sol2 = bisection1(f,a,b,n)
#print(sol2)
#
#a = 1.5; b = 2.0; n = 20
#sol3 = bisection1(f,a,b,n)
#print(sol3)

#--------EXERCISE 2------------

#def bisection2(f, a, b, tol):
#    ak = a
#    bk = b
#    xk0 = 0
#    xk1 = 0
#    iters = 0
#    while (True):
#        xk0 = xk1
#        xk1 = (ak + bk)/2    
#        if (np.abs(xk1 - xk0)/np.abs(xk1)) < tol:
#            return [xk1, iters]
#        elif f(ak)*f(xk1) < 0:
#            bk = xk1
#            iters+=1
#        else:
#            ak = xk1
#            iters+=1
#    return [xk1, iters]
#
#f = lambda x : x**3 - 2*x**2 + 1 
#
#a = -1.0; b = -0.1; tol = 1e-6
#sol1 = bisection2(f,a,b,tol)
#print(sol1)
#
#a = 0.5; b = 1.1; tol =1e-6
#sol2 = bisection2(f,a,b,tol)
#print(sol2)
#
#
#a = 1.5; b = 2.0;  tol =1e-6
#sol3 = bisection2(f,a,b,tol)
#print(sol3) 



#--------EXERCISE 3----------












