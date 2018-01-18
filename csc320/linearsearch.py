from __future__ import division
from random import *

def linearsearch(a,v):
    n = len(a)
    for i in range(n):
        if( v == a[i]):
            return i
    return None


A = []
for i in range(100):
    A.append(i+1)
sumIdx = 0
for i in range(40000):
    sumIdx += linearsearch(A,randint(1,100))

print(sumIdx)
print(sumIdx/40000)