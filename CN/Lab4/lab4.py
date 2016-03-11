# -*- coding: utf-8 -*-
"""
Created on Wed Mar 09 18:13:21 2016

@author: uo244965
"""
from __future__ import division
import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as op
import lab3

# ---------EXERCISE 1--------------

#x=np.linspace(-10,10)
#f = lambda x : x**3 - 5.2*x**2 - 5.5*x + 9.8 
#OX=0*f(x) 
#
#zero = lab3.bisection2(f,-5, -1.2, 1e-6)
#zero2 = lab3.bisection2(f,0, 2, 1e-6)
#zero3 = lab3.bisection2(f,2.1, 5.9, 1e-6)
#print ("zero: ",zero[0]," zero2",  zero2[0], " zero3",  zero3[0])
#
#plt.figure(1)
#plt.plot(x,f(x))
#plt.plot(x,OX,'k-')         
#plt.xlabel('x')
#plt.ylabel('y')
#
#plt.plot(zero[0], f(zero[0]), 'ro')
#plt.plot(zero2[0], f(zero2[0]), 'ro')
#plt.plot(zero3[0], f(zero3[0]), 'ro')
#plt.show()
#
#print ("Difference: ", op.ridder(f, -5,-1.2,xtol = 1e-6) - zero[0])
#
#
## ----------EXERCISE 2--------------
#
#f = lambda x: np.sin(x) - 0.1*x
#tol = 10**(-3)
#df = lambda x: np.cos(x) - 0.1
#sol = lab3.newton2(f, df, 1, 20)
#print (sol)
#
#print (op.newton(f, 1))
#
#
#
## -----------EXERCISE 3------------
#
#f = lambda x: x**3 - 75
#df = lambda x : 3*x**2
#x=np.linspace(-10,10)
#OX=0*f(x) 
#tol = 10**(-6)
#newtonSol = lab3.newton1(f, df, 1, tol )
#print ("Newton solutions and iters: ", newtonSol[0], newtonSol[1])
#
#bisSol = lab3.bisection2(f,0, 10, tol)
#print ("Bisection solutions and iters: ", bisSol[0], bisSol[1])
#
#secantSol = lab3.secant1(f, 0, 10, tol)
#print ("Secant solutions and iters: ", secantSol[0], secantSol[1])
#
#plt.figure(1)
#plt.plot(x,f(x))
#plt.plot(x,OX,'k-')         
#plt.xlabel('x')
#plt.ylabel('y')
#
#plt.plot(bisSol[0], f(bisSol[0]), 'ro')
#
#print ("Newton vs Bisection difference: ", newtonSol[0] - bisSol[0])
#print ("Newton vs Secant difference: ", newtonSol[0] - secantSol[0])
#print ("Bisection vs Secant difference: ", bisSol[0] - secantSol[0])
#plt.show()


# ---------------EXERCISE 4-------------
x = np.linspace(0, 10)
f = lambda x: np.cosh(x) * np.cos(x) - 1
df = lambda x: np.cos(x)*np.sinh(x) - np.sin(x)*np.cosh(x)
OX=0*f(x) 
plt.figure(1)
plt.plot(x,f(x))
plt.plot(x,OX,'k-')         
plt.xlabel('x')
plt.ylabel('y')
plt.show()

tol = 10**(-6)
newtonSol = lab3.newton1(f, df, 4, tol)
print ("Newton solution with x0 = 4: ", newtonSol[0])
newtonSolOK = lab3.newton1(f,df, 4.7, tol)
print ("Newton solution with x0 = 4.7: ", newtonSolOK[0])
print ("Newton solution with op.ridder, x0 = 4.7: ", op.newton(f, 4.7))












