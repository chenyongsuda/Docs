#include "stdafx.h"
/**
	一个数的n次方
	下面问题未解决int越界的问题
**/
#define ERROR -1

// 递归形式
int 
pow_Rec(int x ,int y){
	if(x <= 0 && y < 0) return ERROR;
	if(1 == x) return 1;
	if(1 == y) return x;
	
	int value = 0;
	if((y % 2)== 0){//可以换成y & 1
		value = pow_Rec(x,y/2);
		value = value * value;
	}
	else{
		value = pow_Rec(x,(y-1)/2);
		value = value * value * x;
	}
	return value;
}

//非递归普通形式
int 
pow_NoRec(int x, int y){
	if(x <= 0 && y < 0) return ERROR;
	if(1 == x) return 1;
	
	int value = 1;
	for(int count = 1; count <= y; count++){
		value = value * x; 
	}
	return value;
}

//非递归改进形式
int 
pow_NoRecSpe(int x, int y){
	if(x <= 0 && y < 0) return ERROR;
	if(1 == x) return 1;
	if(1 == y) return 0;
	
	int value = x;
	int TCount = 0;
	bool isEven = false;
	if(0 == (y & 1)){
		isEven = true;
	}

	for(int count = 1; count <= (y>>1); count++){
			value *= value;
	}
	
	if(!isEven){
		value = value * x;
	}
	return value;

}
int main(int argc, char* argv[])     
{     
	int iRes = pow_NoRecSpe(5, 4);
    printf(" value %d!\n", iRes);          
    return 0;     
}     