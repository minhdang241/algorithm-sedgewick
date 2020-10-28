* *Sorting cost model*. When stuyding sorting algorithms, we count *compares* and *exchanges*. For algorithm that do not use exchanges, we count array *accesses*.

* *Extra memory*. The sorting algorithms are divided into two basic types: 
  
  * Those that sort *in place*.
  
  * Those that need enough extra memory to hold another copy of the array to be sorted.

* *Types of data* 



### Selection sort

**Step1:** find the smallest item in the array, and exchange it with the first entry.

**Step2**: find the next smallest item and exchange it with the second entry

Continue in this way until the entire array is sorted. 

The algorithm is called *selection sort* because it works by repeatedly selecting the smallest remaining item.

###### Proposition:

Selection sort uses ~n^2 / 2 compares and n exchanges to sort an array length n.



### Insertion sort

The algorithm considers the cards one at a time, inserting each into its proper place among those already considered (keeping them sorted).

###### Proposition:

The number of exchanges used by insertion sort is equal to the number of inversions in the array.



### Shellsort

Shellsort is a simple extension of insertion sort that gains speed by allowing exchanges of entries that are far apart, produce partially sorted arrays that can be efficiently sorted.

In this algorithm we compare elements that are distance apart rather than adjacent.

###### Proposition:

The number of compares used by shellsort is either O(N^5/4) or O(N^3/2)



### Merge Sort








