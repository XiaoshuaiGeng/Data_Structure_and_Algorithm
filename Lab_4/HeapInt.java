
public class HeapInt {
	private int heap[];
	private int n = 0;
	
	public HeapInt(int a[]) {
		n = a.length;
		for(int k = n/2;k>=1;k--) {
			sink(a,k,n);
			heap = a;
		}
	}
	
	public int removeMax() {
		
		int value = heap[0];
		swap(heap,0,n-1);
		n--;
		sink(heap,1,n);
		return value;
	}
	
	public void sink(int a[],int k, int n) {
		
		while(k*2 <= n) {
	    	
	    	int j = 2*k;
	    	if(j < n && less(a,j,j+1)) {
	    		j++;
	    	}
	    	if(!less(a,k,j)) {
	    		break;
	    	}
	    	exch(a,k,j);
	    	k = j;
	    }
	}
	public boolean less(int[] pq, int i, int j) {
        return (pq[i-1] < pq[j-1]);
    }

    public void exch(int[] pq, int i, int j) {
        int swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

	public void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
