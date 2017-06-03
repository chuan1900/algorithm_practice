#include<iostream>
using namespace std;

int count2(int n);

int main(void)
{
  int x = 13;
  cout<<count2(x)<<endl;
  return 0;
}

//解法二：
int count2(int n)
{
  int count = 0;//统计变量
  int factor = 1;//分解因子
  int lower = 0;//当前处理位的所有低位
  int higher = 0;//当前处理位的所有高位
  int curr =0;//当前处理位

  while(n/factor != 0)
  {
    lower = n - n/factor*factor;//求得低位
    curr = (n/factor)%10;//求当前位
    higher = n/(factor*10);//求高位

    switch(curr)
    {
      case 0:
        count += higher * factor;
        break;
      case 1:
        count += higher * factor + lower + 1;
        break;
      default:
        count += (higher+1)*factor;
    }

    factor *= 10;
  }

  return count;
}