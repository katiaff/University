# -*- coding: utf-8 -*-
from __future__ import division
import numpy as np
import matplotlib.pyplot as plt

rows = 17
cols = 17

def life_step_1(X):
    for i in range(X.shape[0]):
        for j in range(X.shape[1]):
            counter = count_surrounding_cells(X, i, j)
            if (counter > 3):
                die(X, i, j)
            elif(counter == 3):
                revive(X, i, j)
            elif (counter == 2):
                revive(X, i, j)
            elif(counter < 2):
                die(X, i, j)
          
    return X

def die(X, i, j):
    X[i][j] = False

def revive(X, i, j):
    X[i][j] = True

def count_surrounding_cells(X, i, j):
    counter = 0
    if i != rows-1 and j != cols -1:
        for row in range(i-1,i+2):
            for col in range(j-1, j+2):
                if X[row][col]:
                    counter+=1
                        
    if i == rows-1 and j != cols -1:
        for row in range(i-1,i+1):
            for col in range(j-1, j+2):
                if X[row][col]:
                    counter+=1
        row = 0
        for col in range(j-1, j+2):
            if X[row][col]:
                counter+=1
                    
    if j == cols-1 and i != rows - 1:
        for row in range(i-1,i+2):
            for col in range(j-1, j+1):
                if X[row][col]:
                    counter+=1
        col = 0
        for row in range(i-1, i+2):
            if X[row][col]:
                counter+=1
    
    if j == cols - 1 and i == rows - 1:
        for row in range(i-1,i+1):
            for col in range(j-1, j+1):
                if X[row][col]:
                       counter+=1
        row = 0
        col = 0
        if X[row][col]:
            counter+=1
        
                    
    return counter-1
    

    
    ##### Experiment PULSAR
X = np.zeros((rows, cols))
X[2, 4:7] = 1
X[4:7, 7] = 1
X += X.T
X += X[:, ::-1]
X += X[::-1, :]

new = np.zeros_like(X)   # comment here after filling life_step_1
#new = life_step_1(X)    # uncomment here after filling life_step_1
plt.figure()
plt.imshow(X, cmap=plt.cm.binary, interpolation='nearest');
plt.figure()
plt.imshow(new, cmap=plt.cm.binary, interpolation='nearest');

# solution is
new_sol = np.loadtxt('output.txt')
plt.figure()
plt.imshow(new_sol, cmap=plt.cm.binary, interpolation='nearest');
