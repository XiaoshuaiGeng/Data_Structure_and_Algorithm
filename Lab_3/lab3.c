#include <stdio.h>
#include <string.h>
int top = -1;
char myStack[20];
int size(){
  return top+1;
}

int isEmpty(){//return 1 if empty
  return top==-1?1:0;
}

void push(char n) {
  if( (top+1) < sizeof(myStack))
    myStack[++top] = n;
}

void pop() {
  if(!isEmpty()) {
    myStack[top--] = '\0';
  }
}

int isMatching(char testcase[]){
  for(int i = 0; i < strlen(testcase); i++){

    if(testcase[i] =='('){
      push(testcase[i]);
    }else if(testcase[i] == ')'){
      if(isEmpty()){
        return 0 ;
      }
      pop();
    }
  }
  return isEmpty();
}
int main(){

  char case1[] = "((()";
  char case2[] = "))((";
  char case3[] = "(())()";

  printf("The input is: ((() %s\n",isMatching(case1)==1?"Balanced":"Unbalanced");
  memset(myStack,0,sizeof(myStack)); //reset the stack
  top = -1;
  printf("The input is:))(( %s\n",isMatching(case2)==1?"Balanced":"Unbalanced");
  memset(myStack,0,sizeof(myStack)); //reset the stack
  top = -1;
  printf("The input is:(())() %s\n",isMatching(case3)==1?"Balanced":"Unbalanced");
  return 0;
}
