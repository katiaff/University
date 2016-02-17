# -*- coding: utf-8 -*-
"""
Game Of Life implementation
@author Carla Fernández

"""

from __future__ import division
import numpy as np
import matplotlib.pyplot as plt
import timeit

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
           


def life_step_2(X):
    """Game of life step using scipy tools"""
    from scipy.signal import convolve2d
    nbrs_count = convolve2d(X, np.ones((3, 3)), mode='same', boundary='wrap') - X
    return (nbrs_count == 3) | (X & (nbrs_count == 2))    
    # | and & are for bitwise "or" and "and" (used for arrays)
    # returns True if nbrs_count = 3 or (X[i,j] = 1 and nbrs_count = 2)


#----------------------------------------------------------------------

#Exercise1
X = create_matrix()
plt.figure()
plot = plt.imshow(X, cmap=plt.cm.binary, interpolation='nearest')
plt.draw()

#Exercise2
steps = 40
while steps >= 0:
    X = life_step_1(X)
    plot.set_data(X)
    plt.draw()
    plt.pause(0.001)
    steps-=1

#Exercise3
X = create_matrix()
X1 = life_step_1(X)
X2 = life_step_2(X)
np.max(np.abs(X1-X2))

tic=timeit.default_timer()
for i in range(0,1000):
    X = life_step_1(X)                      # replace with your statements
toc=timeit.default_timer()
time1 = toc-tic

tic=timeit.default_timer()
for i in range(0,1000):
    X = life_step_2(X)                      # replace with your statements
toc=timeit.default_timer()
time2 = toc-tic

print "time1: ",time1," time2: ",time2





