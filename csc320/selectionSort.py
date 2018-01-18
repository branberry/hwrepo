def selectionSort(A):
    n = len(A)
    
    for i in range(n - 1):
        minVal = A[i]
        print(A)
        for j in range(i,n):
            if(A[j] < minVal):
                minVal = A[j]
                minLoc = j
        if A[i] != minVal:
            temp = A[i]
            A[i] = minVal
            A[minLoc] = temp
    return A
        

s = selectionSort([4, 2, 1, 5,3,9,8,12,21,32,45,16,91,87])
print(s)