#include <string.h>
#include <stdlib.h>
#include <memory.h>

#ifndef SHOWPASS
#define SHOWPASS
#endif

void printRadix(int a[], int count) { 
	int i = 0;
	for (; i < count; i++) {
		printf("%d\t", a[i]);
	}
}

void radixSort(int a[], int count) {
	int exp = 1;
	const int base = 10;

	int *b = (int *) malloc(count * sizeof(int));
	int *bucket = (int *) calloc(base, sizeof(int));

	// we need to fist initiaze the buckets
	int i;
	int m = a[0];

	// we need to first to know the largest number of all.
	for (i = 1; i < count; i++) {
		if (m < a[i]) 
			m = a[i];
	}

	for (i = 0; i < count; i++) 
		b[i] = a[i];

	for (;  exp < m; exp *= base) {

		// we have to initialize the buckets... 

#ifdef SHOWPASS
		printRadix(a, count);
#endif

		memset(bucket, 0, base * sizeof(int));
		// first we need to count each size of bucket.
		for (i = 0; i < count; i++) { 
			bucket[ (b[i] / exp ) % base]++;
		}

		// how many element until the bucket[i]?
		for (i = 1; i < base; i++) {
			bucket[i] = bucket[i-1] + bucket[i];
		}

		// then we walk the bucket backwards.
		for (i = count - 1; i >= 0; i--) { 
			a[--bucket[ (b[i] / exp ) % base ]] = b[i];
		}

		// now we copy the content back to a.
		for (i = 0; i < count; i++) {
			b[i] = a[i];
		}

#ifdef SHOWPASS
		printRadix(a, count);
#endif 

	}

}

int main(int argc, int argv[]) {

	int count = 10;
	int a[] = {11, 210, 356, 2356, 5467, 9, 56732, 6783291, 547, 9845};

	printf("before sorting, the numbers are\n");
	printRadix(a, count);
	radixSort(a, count);

	printRadix(a, count);
}
