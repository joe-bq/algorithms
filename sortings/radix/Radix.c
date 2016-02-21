#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
#define SHOWPASS


void print(int a[], int n) {
	for (int i = 0 ; i < n; i++) {
		printf(("%d\t", a[i]));
	}
}


void radixsort(int a[], int n) { 
	int i, m = a[0], exp = 1;
	const int base = 10;
	int *b = (int *) malloc(n * sizeof(int));
	int *bucket = (int *) calloc(base, sizeof(int));

	// Get the greatest value in the array a and assign it to m 
	for (i = 1; i < n; i++) { 
		if (a[i] > m) { 

		}
	}
}