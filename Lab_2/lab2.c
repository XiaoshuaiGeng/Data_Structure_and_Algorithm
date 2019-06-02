#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main(){
  int startindex[15];//startindex
  int endindex[15];//endindex
  int sequence[15];//the 15 integers
  int segment_sum[15];//for the maxsum

  srand((unsigned int)time(NULL));
  for(int a=0;a<10;a++){
    int start=-1;int end=-1; int maxsum=0; int i=1; int j=1; int sum=0;//all the elements I need
    for(int b=0;b<15;b++){
      sequence[b]=rand() % 200-100;//random
    }
    for(int c = 0; c < 15;c++){//print  the sequence
      printf("%d[%d] ",sequence[c],c);
    }
    printf("\n");
    for(;j<15;j++){
      sum=sum+sequence[j];//get the sum

      if(sum<0){//negative situation
        i=j+1;
        sum=0;
        startindex[j] = -1;
        endindex[j] = -1;
      }else{
        startindex[j] = i;
        endindex[j] = j;
      }
      segment_sum[j] = sum;
    }
    for(int n = 1; n < 15; n++){
      if(segment_sum[n] > maxsum){
        maxsum = segment_sum[n];
        start = startindex[n];
        end = endindex[n];
      }
    }
    printf("The value of maximum sequence is: %d, start at index[%d],end at index[%d]\n",maxsum,start,end);
    printf("\n" );
    //print the answer
  }
  return 0;
}
