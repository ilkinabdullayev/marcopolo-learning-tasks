# Algorithmic tasks

## 2.1. Exercise 1: 

### Description
Write in pseudo-code a function that given an array of numbers find the maximum number;

### Solution

Complexity  is O(n)

```
Function find max number in array
    Pass In: array

    INITIALIZE max to array[0]
    FOR i = 1 to array length - 1
        IF array[i] > max THEN
            SET max to array[i]
        ENDIF
    ENDFOR      

    Pass Out: max
Endfunction
```

## 2.2. Exercise 2:

Write a function in pseudo-code that given an array of numbers will return the ordered array (from smallest to bigger positive number).

### Solution

There are different kind of sorting algorithms like a bubble, selection, insertion and etc. I am going to pick up selection sort for that exercise. Complexity  is O(n^2)


```
Function sort number array from smallest to bigger
    Pass In: array


    FOR i = 0 to array length - 1
        SET minIndex to i
        
        FOR j = i + 1 to array length - 1
            IF array[minIndex] > array[j] THEN
                SET minIndex to j
            ENDIF
        ENDFOR
        
        SET SWAP to array[i]
        SET array[i] to array[minIndex]
        SET array[minIndex] to SWAP
    ENDFOR

    Pass Out: array
Endfunction
```

## 2.3. Exercise 3:

Write a class matrix that represent a square matrix NXN (with N number of columns equal to the number of rows) that expose 2 public functions:

### drawDiagonals:
Return a matrix NxN with all cells set a 0 excepts for the 2 diagonals set to a random number. Complexity  is O(n^2)


### Solution

```
Function generate matrix filled by zero elements except diagonals
    Pass In: N(size of matrix)

    INIT array to []

    FOR i = 0 to N - 1
         SET array[i] to []

         FOR j = 0 to N - 1
            SET generatedNumber to 0
            IF j == i || j == N - i - 1 THEN
                SET generatedNumber to RANDOM
            ENDIF


            SET arrayMatrix[i][j] to generatedNumber
         ENDFOR
    ENDFOR

    Pass Out: array
Endfunction
```