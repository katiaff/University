# -*- coding: utf-8 -*-
"""
Game Of Life implementation
@author Carla Fernández

"""

from __future__ import division
import numpy as np
import matplotlib.pyplot as plt

rows = 30
cols = 40

def create_matrix():
    np.random.seed(0)
    X = np.zeros((rows, cols), dtype=bool)
    r = np.random.random((10, 20))
    X[10:20, 10:30] = (r > 0.75)
    
    return X


def life_step_1(X):
    for i in range(X.shape[0]):
        for j in range(X.shape[1]):
            if (is_overpopulated(X, i, j)):
                die(X, i, j)
            elif(is_reproduction(X, i, j)):
                revive(X, i, j)
            elif (is_stasis(X, i, j)):
                revive(X, i, j)
            elif(is_underpopulated(X, i, j)): # <------------ MISTAKE
                die(X, i, j)
            
           
    return X


def is_overpopulated(X, i, j):
    counter = count_surrounding_cells(X, i, j)
    return counter > 3
    
def is_reproduction(X, i, j):
    counter = count_surrounding_cells(X, i, j)
    return counter == 3

def is_stasis(X, i, j):
    counter = count_surrounding_cells(X, i, j)
    return counter == 2

def is_underpopulated(X, i, j):
    counter = count_surrounding_cells(X, i, j)
    return counter < 2

def die(X, i, j):
    X[i][j] = False

def revive(X, i, j):
    X[i][j] = True

def count_surrounding_cells(X, i, j):
    counter = 0
    for row in range(i-1,i+2):
        for col in range(j-1, j+2): 3 # <----------- MISTAKE
            if X[row][col]:
                counter+=1
    return counter


#----------------------------------------------------------------------
#PROGRAM

X = create_matrix()
plt.imshow(X, cmap=plt.cm.binary, interpolation='nearest');
X = life_step_1(X)
plt.imshow(X, cmap=plt.cm.binary, interpolation='nearest');


