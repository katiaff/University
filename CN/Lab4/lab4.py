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
#x = np.linspace(0, 10)
#f = lambda x: np.cosh(x) * np.cos(x) - 1
#df = lambda x: np.cos(x)*np.sinh(x) - np.sin(x)*np.cosh(x)
#OX=0*f(x) 
#plt.figure(1)
#plt.plot(x,f(x))
#plt.plot(x,OX,'k-')         
#plt.xlabel('x')
#plt.ylabel('y')
#plt.show()
#
#tol = 10**(-6)
#newtonSol = lab3.newton1(f, df, 4, tol)
#print ("Newton solution with x0 = 4: ", newtonSol[0])
#newtonSolOK = lab3.newton1(f,df, 4.7, tol)
#print ("Newton solution with x0 = 4.7: ", newtonSolOK[0])
#print ("Newton solution with op.ridder, x0 = 4.7: ", op.newton(f, 4.7))

# ----------------EXERCISE 5--------------

def convergence(p, linA, linB, sol):
    xk = np.linspace(linA,linB, 200)
    limNewton = []
    for i in range(1, len(xk)):
        limNewton.append(np.abs(xk[i] - sol[0])/(np.abs(xk[i-1] - sol[0]))**p)
    plt.plot(limNewton)
    
#tol = 10**-6
#fA = lambda x : 1 - np.exp(-2*x) - x
#dfA = lambda x: 2*np.exp(-2*x) - 1
#fB = lambda x: x * np.log(x) - 1
#dfB = lambda x : 1 + np.log(x)
#
#solA = lab3.newton1(fA, dfA, 1, tol)
#
#solB = lab3.newton1(fB, dfB, 1.7, tol)
#
#print "Solution for a :", solA[0], "Iterations: ", solA[1]
#print "Residual for a:", np.abs(0 - fA(solA[0])), "\n"
#print "Solution for b :", solB[0], "Iterations: ", solB[1]
#print "Residual for B:", np.abs(0 - fB(solB[0]))
#    
#convergence(2, 3, 10)
    
# ----------------EXERCISE 6--------------

#f = lambda x : x**4 - 6*x**2 + 9
#df = lambda x: 4*x**3 - 12*x
#tol = 10**-6
#x = np.linspace(-3,3)
#OX=0*f(x) 
#plt.figure(1)
#plt.plot(x,f(x))
#plt.plot(x,OX,'k-')         
#plt.xlabel('x')
#plt.ylabel('y')
#plt.show()
#
#solNewtonA = lab3.newton1(f, df, -2, tol)
#solNewtonB = lab3.newton1(f, df, 2, tol)
#print "Newton's solutions: ", solNewtonA, solNewtonB, "\n"
#
#convergence(2,-1.5,1.5, solNewtonA)
#
#
#solSecantA = lab3.secant1(f, -3, -2, tol)
#solSecantB = lab3.secant1(f, 2, 3, tol)
#
#convergence(1.6,-1.6,1.5, solSecantA)
#
#print "Secant's solutions: ", solSecantA, solSecantB,"\n"
#
#solBisectionA = lab3.bisection2(f, -2, -1.5, tol)
#solBisectionB = lab3.bisection2(f, 1.5, 2, tol)
#
#convergence(1,-0.5,1.5, solBisectionA)
#
#print "Bisection's solutions: ", solBisectionA, solBisectionB,"\n"

# ---------------EXERCISE 7-------------------
fA = lambda x : x**3 - 1.2*x**2 - 8.19*x + 13.23
fB = lambda x : x**4 + 0.9*x**3 - 2.3*x**2 + 3.6*x - 25.2
fC = lambda x : x**4 + 2*x**3 - 7*x**2 + 3

x = np.linspace(-10, 10)
OX=0*fA(x) 
plt.figure(1)
plt.plot(x,fA(x))
plt.plot(x, fB(x))
plt.plot(x,fC(x))
plt.plot(x,OX,'k-')         
plt.xlabel('x')
plt.ylabel('y')
plt.show()








