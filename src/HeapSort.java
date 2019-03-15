/*
Author: Michael Mussler
10/22/2016
Implement MaxHeap
*/







public class HeapSort
{
    private static int[] array;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;
    
  
    public static void heap(int []arr){
        n=arr.length-1;
        for(int i=n/2;i>=0;i--){
            maxheap(arr,i);
        }
    }
     public static void maxheap(int[] arr, int i){ 
        left=2*i;
        right=2*i+1;
        if(left <= n && arr[left] > arr[i]){
            largest=left;
        }
        else{
            largest=i;
        }
        
        if(right <= n && arr[right] > arr[largest]){
            largest=right;
        }
        if(largest!=i){
            Switch(i,largest);
            maxheap(arr, largest);
        }
    }
     
      public static void sort(int []arr){
        array=arr;
        heap(arr);
        
        for(int i=n;i>0;i--){
            Switch(0, i);
            n=n-1;
            maxheap(arr, 0);
        }
    }
     
     
     public static void Switch(int i, int j){
        int t=array[i];
        array[i]=array[j];
        array[j]=t; 
        }

       public static void main(String[] args) {
        int []a1={45, 90, 85, 62};
        sort(a1);
        for(int i=0;i<a1.length;i++){
            System.out.print(a1[i] + " ");
        }
    }
}