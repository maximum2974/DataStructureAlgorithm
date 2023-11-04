# 1. 一大波数正在靠近——排序

## 1.1 最快最简单的排序——桶排序

尝试输入n个 0~1000  之间的整数，将它们从小到大排序。

如果需要对数据范围在 0~1000 之间的整数进行排序，我们需要 1001 个桶，来表示 0~1000 之间每一个数出现的次数。另外，此处的每一个桶的作用其实就是“标记”每个数出现的次数。

代码实现如下：
~~~c
#include<stdio.h>

int main()
{
	int book[1001],i,j,t,n;
	for(i = 0;i <= 1000; i++)
		book[i] = 0;
	scanf("%d",&n); //输入一个数n，表示接下来有n个数
	for(i = 1;i <= n; i++)//循环读入n个数，并进行桶排序
	{
		scanf("%d",&t);//把每个数读到变量t中
		book[t]++;//进行计数，对编号为t的桶放一个小旗子 
	}	
	for(i = 1000; i >= 0; i--)
		for(j = 1;j <= book[i]; j++)
			printf("%d ",i);
	
	getchar();getchar();
	//这里的getchar();用来暂停程序，以便查看程序输出的内容
	//也可以用system("pause");等来代替
	return 0;		
} 
~~~

## 1.2 邻居好说话——冒泡排序

==冒泡排序的基本思想是：每次比较两个相邻的元素，如果它们的顺序错误就把它们交换过来==

代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>
struct student{
    char name[21];
    char score;   
};
int main()
{
    struct student a[100],t;
    int i,j,n;
    scanf("%d",&n);
    for(i=1;i<=n;i++)
    {
        scanf("%s %d",a[i].name,&a[i].score);
    }
    for(i=1;i<=n-1;i++)
    {
        for(j=1;j<=n-i;j++)
        {
            if(a[j].score<a[j+1].score)
            {
                t=a[j];
                a[j]=a[j+1];
                a[j+1]=t;
            }
        }
    }
    for(i = 1;i <= n; i++){
        printf("%s\n",a[i].name);
    }
    system("pause");
    return 0;
}
~~~

## 1.3 最常用的排序——快速排序

快速排序之所以快是因为每次交换是跳跃式的，每次排序的时候设置一个基准点，将小于等于基准点的数全部放到基准点左边，将大于等于基准点的数全部放到基准点的右边。代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>
int a[101],n;//定义全局变量，这两个变量需要在子函数中使用

void quicksort(int left,int right)
{
    int i,j,t,temp;
    if(left > right)
        return;
    temp = a[left];//temp中存的就是基准数
    i = left;
    j = right;
    while(i != j)
    {
        //顺序很重要，要先从右往左找
        while(a[j] >= temp && i < j)
            j--;
        //再从左往右找
        while(a[i] <= temp && i < j)
            i++;

        //交换两个数在数组中的位置
        if(i < j)//当哨兵i和哨兵j没有相遇时
        {
            t = a[i];
            a[i] = a[j];
            a[j] = t;
        }  
    }    

    //最终将基准数归位
    a[left] = a[i];
    a[i] = temp;

    quicksort(left,i - 1); //继续处理左边的，这里是一个递归的过程
    quicksort(i + 1,right);//继续处理右边的 ，这里是一个递归的过程
}

int main(){
    int i,j,t;
    //读入数据
    scanf("%d",&n);
    for(i = 1;i <= n; i++)
        scanf("%d",&a[i]);

    quicksort(1,n); //快速排序调用

    //输出排序后的结果
    for(i = 1; i <= n; i++)
    {
        printf("%d ",a[i]);
    }    

    system("pause");
    return 0;
}
~~~

# 2. 栈、队列、链表

## 2.1 解密QQ号——队列

小哼向小哈询问qq号，小哈给了小哼一串加密过的数字，同时小哈也告诉了小哼解密规则。规则是这样的：首先将第 1 个数删除，紧接着将第 2 个数放到这串数的末尾，再将第 3 个数删除并将第 4 个数放到这串数的末尾，再将第 5 个数删除······直至剩下最后一个数，将最后一个数也删除。按照刚才删除的顺序，把这些删除的数连在一起就是小哈的qq号了。小哈给小哼加密过的一串数是 “6 3 1 7 5 8 9 2 4”

在这里将引入两个整型变量 head 和 tail。head 用来记录队列的队首（即第一位），tail 用来记录队列的队尾（即最后一位）的下一个位置。我们规定队首和队尾重合时，队列为空。

在队首删除一个数的操作是 head++
<img src="E:\Typora笔记\算法(Algorithms).assets\b7d2daf3c8b30837f905fc1ddd3d60f.jpg" alt="b7d2daf3c8b30837f905fc1ddd3d60f" style="zoom:50%;" />

在队尾增加一个数（假设这个数是 x）的操作是 q[tail] = x; tail++;

则代码实现如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int q[101] = {0,6,3,1,7,5,8,9,2,4},head,tail;
    head = 1;
    tail = 10;
    while(head < tail){
        printf("%d ",q[head]);
        head++;

        q[tail] = q[head];
        tail++;

        head++;
    }
    system("pause");
    return 0;
}
~~~

现在将队列的三个基本元素（一个数组，两个变量）封装为一个结构体类型，如下：
~~~c
struct queue{
    int data[100];
    int head;
    int tail;
};
~~~

那么定义这个结构体的方式如下：
~~~c
struct queue q;
~~~

访问结构体变量的内部成员的方式如下：
~~~c
q.head = 1;
q.tail = 1;
scanf("%d",&q.data[q.tail]);
~~~

以下这段代码就是使用结构体来实现队列的操作：
~~~c
#include<stdio.h>
#include<stdlib.h>

struct queue{
    int data[100];
    int head;
    int tail;
};

int main(){
    struct queue q;
    int i;
    q.head = 1;
    q.tail = 1;
    for(i = 1;i <= 9; i++){
        scanf("%d",&q.data[q.tail]);
        q.tail++;
    }
    while(q.head < q.tail){
        printf("%d ",q.data[q.head]);
        q.head++;

        q.data[q.tail] = q.data[q.head];
        q.tail++;
        
        q.head++;
    }

    system("pause");
    return 0;
}
~~~

## 2.2 解密回文——栈

所谓回文字符串就是指正读反读均相同的字符序列，通过栈这个数据结构我们将很容易判断一个字符串是否为回文。

完整代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(){
    char a[101],s[101];
    int i,len,mid,next,top;

    gets(a);
    len = strlen(a);
    mid = len / 2 - 1;

    top = 0; //栈的初始化
    //将mid前的字符依次入栈
    for(i = 0; i <= mid; i++){
        s[++top] = a[i];
    }

    if(len % 2 == 0){
        next = mid + 1;
    }else{
        next = mid + 2;
    }

    //开始匹配
    for(i = next;i <= len - 1; i++){
        if(a[i] != s[top]){
            break;
        }
        top--;
    }

    if(top == 0){
        printf("TES");
    }else{
        printf("NO");
    }

    system("pause");
    return 0;
}
~~~

## 2.3 纸牌游戏——小猫钓鱼

游戏规则是这样的：将一副扑克牌平均分成两份，每人拿一份。小哼先拿出手中的第一张扑克牌放在桌上，然后小哈也拿出手中的第一张扑克牌，并放在小哼刚打出的扑克牌的上面，就像这样两人交替出牌。出牌时，如果某人打出的牌与桌上某张牌的牌面相同，即可将两张相同的牌及其中间所夹的牌全部取走，并依次放到自己手中牌的末尾。当任意一人手中的牌全部出完时，游戏结束，对手获胜。

这个游戏中，小哼有两个操作，分别是出牌和赢牌。这恰好对应队列的两个操作，出牌就是出队，赢牌就是入队。小哈的操作哈小哼是一样的。而桌子就是一个栈，每打出一张牌放到桌上就相当于入栈。当有人赢牌的时候，依次将牌从桌上拿走，这就是相当于出栈。
~~~c
#include<stdio.h>
#include<stdlib.h>

struct queue{
    int data[100];
    int head;
    int tail;
};

struct stack{
    int data[10];
    int top;
};

int main(){
    struct queue q1,q2;
    struct stack s;
    int book[10];
    int i,t;

    //初始化队列
    q1.head = 1; q1.tail = 1;
    q2.head = 1; q2.tail = 1;
    //初始化栈
    s.top = 0;
    //初始化用来标记的数组，用来标记哪些牌已经在桌上
    for(i = 1;i <= 9; i++){
        book[i] = 0;
    }

    //依次向队列插入6个数
    //小哼手上的6张牌
    for(i = 1;i <= 6; i++){
        scanf("%d",&q1.data[q1.tail]);
        q1.tail++;
    }

    //小哈手上的6张牌
    for(i = 1;i <= 6; i++){
        scanf("%d",&q2.data[q2.tail]);
        q2.tail++;
    }

    while(q1.head < q1.tail && q2.head < q2.tail) //当队列不为空的时候执行循环
    {
        t = q1.data[q1.head];//小哼出一张牌
        //判断小哼当前打出的牌是否能赢牌
        if(book[t] == 0) //表明桌上没有牌面为t的牌
        {
            //小哼此轮没有赢牌
            q1.head++; //小哼已经打出一张牌，所以要把打出的牌出队
            s.top++;
            s.data[s.top] = t;//再把打出的牌放到桌上，即出栈
            book[t] = 1;//标记桌上现在已经有牌面为t的牌
        }
        else{
            //小哼此轮可以赢牌
            q1.head++;//小哼已经打出一张牌，所以要把打出的牌出队
            q1.data[q1.tail] = t;//紧接着把打出的牌放到手中牌的末尾
            q1.tail++;
            while(s.data[s.top] != t)//把桌上可以赢得的牌依次放到手中牌的末尾
            {
                book[s.data[s.top]] = 0;//取消标记
                q1.data[q1.tail] = s.data[s.top];//依次放入队尾
                q1.tail++;
                s.top--;//栈中少了一张牌，所以栈顶要减1
            }
            //收回桌面上牌面为t的牌
            book[s.data[s.top]] = 0;
            q1.data[q1.tail] = s.data[s.top];
            q1.tail++;
            s.top--;
        }
        if(q1.head == q1.tail) break; //如果小哼手中的牌已经打完了，游戏结束

        t = q2.data[q2.head]; //小哈出一张牌
        if(book[t] == 0)
        {
            q2.head++;
            s.top++;
            s.data[s.top] = t;
            book[t] = 1;
        }
        else
        {
            q2.head++;
            q2.data[q2.tail] = t;
            q2.tail++;
            while(s.data[s.top] != t)
            {
                book[s.data[s.top]] = 0;
                q2.data[q2.tail] = s.data[s.top];
                q2.tail++;
                s.top--;
            }
            book[s.data[s.top]] = 0;
            q2.data[q2.tail] = s.data[s.top];
            q2.tail++;
            s.top--;
        }
    }
    if(q2.head == q2.tail)
    {
        printf("小哼win\n");
        printf("小哼当前手中的牌是");
        for(i = q1.head;i <= q1.tail - 1; i++){
            printf(" %d",q1.data[i]);
        }
        if(s.top > 0)
        {
            printf("\n桌上的牌是");
            for(i = 1;i <= s.top; i++)
            {
                printf(" %d",s.data[i]);
            }
        }
        else
        {
            printf("\n桌上已经没有牌了");
        }
    }
    else
    {
        printf("小哈win\n");
        printf("小哈当前手中的牌是");
        for(i = q2.head;i <= q2.tail - 1; i++)
        {
            printf(" %d",q2.data[i]);
        }
        if(s.top > 0)
        {
        printf("\n桌上的牌是");
            for(i = 1;i <= s.top; i++)
            {
                printf(" %d",s.data[i]);
            }
        }
        else
        {
            printf("\n桌上已经没有牌了");
        }
    }

    system("pause");
    return 0;
}
~~~



# 3. 枚举！很暴力

## 3.1 坑爹的奥数

将数字1~9分别填入九个□中，使得□□□+□□□=□□□。并且每个数字只能使用一次使得等式成立。

~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int a[10],i,total = 0,book[10],sum;
    for(a[1] = 1;a[1] <= 9; a[1]++)
        for(a[2] = 1;a[2] <= 9; a[2]++)
            for(a[3] = 1;a[3] <= 9; a[3]++)
                for(a[4] = 1;a[4] <= 9; a[4]++)
                    for(a[5] = 1;a[5] <= 9; a[5]++)
                        for(a[6] = 1;a[6] <= 9; a[6]++)
                            for(a[7] = 1;a[7] <= 9; a[7]++)
                                for(a[8] = 1;a[8] <= 9; a[8]++)
                                    for(a[9] = 1;a[9] <= 9; a[9]++){
                                        for(i = 1;i <= 9; i++){
                                            book[i] = 0;
                                        }
                                        for(i = 1;i <= 9; i++){
                                            book[a[i]] = 1;
                                        }
                                        sum = 0;
                                        for(i = 1;i <= 9; i++){
                                            sum += book[i];
                                        }
                                        if(sum == 9 && a[1] * 100 + a[2] * 10 + a[3] + a[4] * 100 + a[5] * 10
                                        + a[6] == a[7] * 100 + a[8] * 10 + a[9]){
                                            total++;
                                            printf("%d%d%d + %d%d%d = %d%d%d\n",a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9]);
                                        }
                                    }
    printf("total=%d\n",total / 2);
    system("pause");
    return 0;
}
~~~

## 3.2 炸弹人

你只有一枚炸弹，但是这枚炸弹威力超强（杀伤距离超长，可以消灭杀伤范围内所有的敌人）。请问在哪里放置炸弹才可以消灭最多的敌人呢？


我们先将这个地图模型化。墙用 # 表示。这里有两种墙，一种是可以被炸掉的，另外一种是不可能被炸掉的。但是由于现在只有一枚炸弹，所以都用 # 表示，炸弹是不能穿墙的。敌人用 G 表示，空地用 . 表示，当然炸弹只能放在空地上。
实现的代码如下：

~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    char a[20][21]; //假设这里的地图大小不超过20*20
    int i,j,sum,map=0,p,q,x,y,n,m;
    //读入n和m，n表示有多少行字符，m表示每行有多少列
    scanf("%d %d",&n,&m);
    //读入n行字符
    for(i = 0;i <= n-1; i++){
        scanf("%s",a[i]);
    }

    for(i = 0;i <= n-1; i++){
        for(j = 0;j <= m-1; j++){
            //首先判断这个点是不是平地，是平地才可以放置炸弹
            if(a[i][j] == '.'){
                sum = 0;//sum用来计数（可以消灭的敌人数），所以需要初始化为0
                //将当前坐标i,j以便上下左右四个方向分别统计可以消灭的敌人数
                //向上统计可以消灭的敌人数
                x = i;
                y = j;
                while(a[x][y] != '#') //判断是不是墙，如果不是墙就继续
                {
                    //如果当前是敌人，则进行计数
                    if(a[x][y] == 'G') sum++;
                    x--;
                }
                //向下统计可以消灭的敌人数
                x = i;
                y = j;
                while(a[x][y] != '#')
                {
                    if(a[x][y] == 'G') sum++;
                    x++;
                }
                //向左统计可以消灭的敌人数
                x = i;
                y = j;
                while(a[x][y] != '#')
                {
                    if(a[x][y] == 'G') sum++;
                    y--;
                }
                //向右统计可以消灭的敌人数
                x = i;
                y = j;
                while(a[x][y] != '#')
                {
                    if(a[x][y] == 'G') sum++;
                    y++;
                }
                //更新map值
                if(sum > map)
                {
                    //如果当前点能消灭的敌人总数大于map，则更新map
                    map = sum;
                    //并用p和q记录当前点的坐标
                    p = i;
                    q = j;
                }
            }
        }
    }
    printf("将炸弹放置在(%d,%d),最多可以消灭%d个敌人\n",p,q,map);

    system("pause");
    return 0;
}
~~~

结果如下：

<img src="E:\Typora笔记\算法(Algorithms).assets\image-20230422230754422.png" alt="image-20230422230754422" style="zoom:50%;" />

其实这个算法有个问题，比如我们将地图 (6,11) 的墙改为平地，小人默认站在 (3,3) 这个位置，如右下图：

<img src="E:\Typora笔记\算法(Algorithms).assets\2b3953f4b0841eff38b0cd581f2555b.jpg" alt="2b3953f4b0841eff38b0cd581f2555b" style="zoom:50%;" />

根据我们之前的算法，应该将炸弹放置在 (1,11) 处，最多可以消灭 11 个敌人。其实小人根本无法走到 (1,11) 处。所以正确的答案应该是将炸弹放在 (7,11) 处，最多可以消灭 10 个敌人。我们在第四节第四章进行分析。

## 3.3 火柴棍等式

假如现在小哼手上有 m 根（m <= 24）火柴棍，那么小哼究竟可以拼出多少个不同的形如 A+B=C 的等式呢？
注意：

1. 加号与等号各自需要两根火柴棍
2. 如果 A≠B，则 A+B=C 与 B+A=C 视为不同的等式（A、B、C都大于0）
3. 所有根火柴棍必须全部用上

因为题目中最多只有 24 根火柴棍，除去 “+”和 “=” 占用的 4 根火柴棍，那么最多剩下 20 根火柴棍。而 0~9 这 10 个数字中，数字 1 需要用到的火柴棍最少，只需要 2 根火柴棍，而 20 根火柴棍最多能组成 10 个 1。因此A、B、C中的任意一个数都不能超过 11111。
那么我们只需要分别枚举 A、B、C，范围都是 0~11111。A 所使用的火柴棍的根数，加上 B 所使用的火柴棍的根数，再加上 C 所使用的火柴棍的根数，如果恰好等于 m-4 的话，则成功找到一组解。

实现代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int fun(int x){
    int num = 0; //用来计数的变量，一定记得初始化
    int f[10] = {6,2,5,5,4,5,6,3,7,6}; //用一个数组来记录0~9每个数字需要用多少根火柴棍
    while(x/10 != 0) //如果x/10的商不等于0的话，说明这个数至少有两位
    {
        //获得x的末尾数字并将此数所需要用到的火柴棍根数累加到num中
        num += f[x%10];
        x = x/10; //去掉x的末尾数字
    }
    //最后加上此时x所需用到的火柴棍的根数(此时x一定是一位数)
    num += f[x];
    return num; //返回需要火柴棍的总根数
}

int main()
{
    int a,b,c,m,i,sum = 0; //sum是用来计数的，因此一定要初始化为0
    scanf("%d",&m); //读入火柴棍的根数

    //开始枚举a和b
    for(a = 0;a <= 1111; a++){
        for(b = 0;b <= 1111; b++){
            c = a + b; //计算出c
            //fun是我们自己写的子函数，用来计算一个数所需要用火柴棍的总数
            /*当a使用的火柴棍根数 + b所使用的火柴棍根数 + c使用的火柴棍的根数之和恰好等于m-4，
            则成功地找出了一组解*/
            if(fun(a) + fun(b) + fun(c) == m-4){
                printf("%d+%d=%d\n",a,b,c);
                sum++;
            }
        }
    }
    printf("一共可以拼出%d个不同的等式",sum);
    system("pause");
    return 0;
}
~~~



# 4. 万能的搜索

## 4.1 不撞南墙不回头——深度优先搜索

输出一个数 n，输出 1~n 的全排列

~~~c
#include<stdio.h>
#include<stdlib.h>

int a[10],book[10],n;

void dfs(int step) //step表示现在站在第几个盒子面前
{
    int i;
    if(step == n+1) //如果站在第n+1个盒子面前，则表示前n个盒子已经放好扑克牌
    {
        //输出一种排列（1~n号盒子中的扑克牌编号）
        for(i = 1;i <= n; i++){
            printf("%d",a[i]);
        }
        printf("\n");

        return; //返回之前的一步（最近一个调用dfs函数的地方）
    }

    //此时站在第step个盒子面前，应该放哪张牌呢？
    //按照1、2、3·····n的顺序一一尝试
    for(i = 1;i <= n; i++){
        if(book[i] == 0){
            a[step] = i;
            book[i] = 1;//表示i号扑克牌已经不在手上
            dfs(step+1);
            book[i] = 0;
        }
    }
    return;
}

int main(){
    scanf("%d",&n);
    dfs(1);
    system("pause");
    return 0;
}
~~~

深度优先搜索的基本模型为：
~~~html
void dfs(int step){
    判断边界
    尝试每一种可能 for(i = 1;i <= n; i++)
    {
        继续下一步 dfs(step + 1)                        
    }
    返回                        
}
~~~

那么第三节第一章的内容有了另外一种的解决办法了：
将数字1~9分别填入九个□中，使得□□□+□□□=□□□。并且每个数字只能使用一次使得等式成立。

实现代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int a[10],book[10],total = 0;
void dfs(int step)
{
    int i;
    if(step == 10){
        if(a[1] * 100 + a[2] * 10 + a[3] + a[4] * 100 + a[5] * 10 + a[6] == a[7] * 100 + a[8] * 10 + a[9]){
            total++;
            printf("%d%d%d+%d%d%d=%d%d%d\n",
            a[1],a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9]);
        }
        return;
    }
    for(i = 1;i <= 9; i++){
        if(book[i] == 0){
            a[step] = i;
            book[i] = 1;
            dfs(step + 1);
            book[i] = 0;
        }
    }
    return;
}

int main(){
    dfs(1);
    printf("total=%d",total/2);
    system("pause");
    return 0;
}
~~~

## 4.2 解决小哈

迷宫由 n 行 m 列的单元格组成（n 和 m 都小于等于50），每个单元格要么是空地，要么是障碍物。你的任务是帮助小哼找到一条从迷宫的起点通往小哈所在位置的最短路径。注意障碍物是不能走的，当然小哼也不能走到迷宫之外。

~~~c
#include<stdio.h>
#include<stdlib.h>
int n,m,p,q,min=99999999;
int a[51][51],book[51][51];
void dfs(int x,int y,int step){
    int next[4][2] = {{0 , 1}, //向右走
                      {1 , 0}, //向下走
                      {0 , -1}, //向左走
                      {-1 , 0}}; //向上走
    int tx,ty,k;
    //判断是否到达小哈位置
    if(x == p && y== q){
        //更新最小值
        if(step < min){
            min = step;
        }
        return;
    }

    //枚举四种走法
    for(k = 0;k <= 3; k++){
        //计算下一个点的坐标
        tx = x + next[k][0];
        ty = y + next[k][1];
        //判断是否越界
        if(tx < 1 || tx > n || ty < 1 || ty > m){
            continue;
        }
        //判断该点是否为障碍物或者已经在路径中
        if(a[tx][ty] == 0 && book[tx][ty] == 0){
            book[tx][ty] = 1;
            dfs(tx,ty,step+1);
            book[tx][ty] = 0;
        }
    } 
    return;                 
}

int main(){
    int i,j,startx,starty;
    //读入n和m，n为行，m为列
    scanf("%d %d",&n,&m);
    //读入迷宫
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            scanf("%d",&a[i][j]);
        }
    }
    //读入起点和终点坐标
    scanf("%d %d %d %d",&startx,&starty,&p,&q);

    //从起点开始搜索
    book[startx][starty] = 1; //标记起点已经在路径中，防止后面重复走
    //第一个参数是起点的x坐标，第二个参数是起点的y坐标，第三个参数是初始步数为0
    dfs(startx,starty,0);

    //输出最短步数
    printf("%d",min);
    system("pause");
    return 0;
}
~~~

## 4.3 层层递进——广度优先搜索

在上一节中我们的方法是，先让小哼往右边走，然后一直尝试下去，直到走不通的时候再回到这里。这样是深度优先，可以通过函数的递归实现。现在介绍另外一种方法：通过 “一层一层” 扩展的方法来找到小哈。扩展时每发现一个点就将这个点加入到队列中，直至走到小哈的位置时为止。

~~~c
#include<stdio.h>
#include<stdlib.h>
struct note
{
    int x;//横坐标
    int y;//纵坐标
    int f;//亲戚再队列中的编号，本题不要求输出路径，可以不需要f
    int s;//步数
};

int main(){
    struct note que[2501]; //因为地图大小不超过50*50，因此队列扩展不会超过2500个

    int a[51][51] = {0}, book[51][51] = {0};
    //定义一个用于表示走的方向的数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走 
    int head,tail;
    int i,j,k,n,m,startx,starty,p,q,tx,ty,flag;
    scanf("%d %d",&n,&m);
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            scanf("%d",&a[i][j]);
        }
    }                  
    scanf("%d %d %d %d",&startx,&starty,&p,&q);

    //队列初始化
    head = 1;
    tail = 1;
    //往队列插入迷宫入口坐标
    que[tail].x = startx;
    que[tail].y = starty;
    que[tail].f = 0;
    que[tail].s = 0;
    tail++;
    book[startx][starty] = 1;

    flag = 0; //用来标记是否到达目标点，0表示暂时还没有到达，1表示到达
    //当队列不为空给的时候循环
    while(head < tail){
        //枚举四个方向
        for(k = 0;k <= 3; k++){
            //计算下一个目标
            tx = que[head].x + next[k][0];
            ty = que[head].y + next[k][1];

            //判断是否越界
            if(tx < 1 || tx > n || ty < 1 || ty > m)
            {
                continue;
            }

            //判断是否是障碍物或者已经在路上
            if(a[tx][ty] == 0 && book[tx][ty] == 0){
                //把这个点标记为已经走过
                //注意宽搜每个点只入队一次，所以和深搜不同，不需要将book数组还原
                book[tx][ty] = 1;
                //插入新的点到队列中
                que[tail].x = tx;
                que[tail].y = ty;
                que[tail].f = head; //因为这个点是从head扩展出来的，所以它的父亲是head
                que[tail].s = que[head].s+1; //步数是父亲的步数+1
                tail++;
            }

            //如果到目标点了，停止扩展，任务结束，退出循环
            if(tx == p && ty == q){
                flag = 1;
                break;
            }
        }
        if(flag == 1){
            break;
        }
        head++;
    }

    //打印队列中末尾最后一个点（目标点）的步数
    //注意tail是指向队列队尾（即最后一位）的下一个位置，所以这需要-1
    printf("%d", que[tail-1].s);

    system("pause");
    return 0;
}
~~~

## 4.4 再解炸弹人

按照第三章的方法，将炸弹放置在 (1,11) 处，最多可以消灭 11 个敌人（注意这里是从 0 行 0 列开始计算的）。但小人其实是无法走到 (1,11) 的。所以正确的答案是将炸弹放在 (7,11) 处，可以消灭 10 个敌人。可以用深度优先搜索或者是广度优先搜索来枚举出所有小人可以到达的点，然后在这些可以到达的点上来分别统计可以消灭的敌人数。

如下使用广度优先搜索：

~~~c
#include<stdio.h>
#include<stdlib.h>

struct note{
    int x; //横坐标
    int y; //纵坐标
};
char a[20][21]; //用来存储地图

int getnum(int i,int j){
    int sum,x,y;
    sum = 0; //sum用来计数，可以消灭的敌人数，所以需要初始化为0
    //将坐标i,j赋值到两个新变量x,y中，以便之后向上下左右四个方向统计可以消灭的敌人数
    //向上统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#') //判断的点是不是墙，如果不是墙就继续
    {
        //如果当前的点是敌人，则进行计数
        if(a[x][y] == 'G'){
            sum++;
        }
        //x--的作用是继续向上统计
        x--;
    }

    //向下统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#'){
        if(a[x][y] == 'G'){
            sum++;
        }
        //x++的作用是继续向下统计
        x++;
    }

    //向左统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#'){
        if(a[x][y] == 'G'){
            sum++;
        }
        //y--的作用是继续向右统计
        y--;
    }

    //向右统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#'){
        if(a[x][y] == 'G'){
            sum++;
        }
        //y++的作用是继续向左统计
        y++;
    }
    return sum;
}

int main(){
    struct note que[401]; //假设地图大小不超过20*20，因此队列扩展不会超过400个
    int head,tail;
    int book[20][20] = {0}; //定义一个标记数组并全部初始化为0
    int i,k,sum,max = 0,mx,my,n,m,startx,starty,tx,ty;
    //定义一个用于表示走的方向的数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走 
    //读入n和m，n表示有多少行字符，m表示每行有多少列
    scanf("%d %d %d %d",&n,&m,&startx,&starty);
    //读入n行字符
    for(i = 0;i <= n-1; i++){
        scanf("%s",a[i]);
    }                  

    //队列初始化
    head = 1;
    tail = 1;
    //往队列插入小人的起始坐标
    que[tail].x = startx;
    que[tail].y = starty;
    tail++;
    book[startx][starty] = 1;
    max = getnum(startx,starty);
    mx = startx;
    my = starty;
    //当队列不为空的时候循环
    while(head < tail){
        //枚举四个方向
        for(k = 0;k <= 3; k++){
            //尝试走的下一个点的坐标
            tx = que[head].x + next[k][0];
            ty = que[head].y + next[k][1];

            //判断是否越界
            if(tx < 0 || tx > n-1 || ty < 0 || ty > m-1){
                continue;
            }

            //判断是否为平地或者曾经走过
            if(a[tx][ty] == '.' && book[tx][ty] == 0){
                //每个点只入队一次，所以需要标记这个点已经走过
                book[tx][ty] = 1;
                //插入新扩展的点到队列中
                que[tail].x = tx;
                que[tail].y = ty;
                tail++;

                //统计当前新扩展的点可以消灭的敌人总数
                sum = getnum(tx,ty);
                if(sum > max){
                    max = sum;
                    mx = tx;
                    my = ty;
                }
            }
        }
        head++;
    }

    printf("将炸弹放置在(%d,%d)处,可以消灭%d个敌人\n",mx,my,max);

    system("pause");
    return 0;
}
~~~

如下使用深度优先搜索：
~~~c
#include<stdio.h>
#include<stdlib.h>

char a[20][21];
int book[20][20],max,mx,my,n,m;
int getnum(int i, int j){
    int sum,x,y;
    sum = 0;
    x = i;
    y = j;
    while(a[x][y] != '#') //判断的点是不是墙，如果不是墙就继续
    {
        //如果当前的点是敌人，则进行计数
        if(a[x][y] == 'G'){
            sum++;
        }
        //x--的作用是继续向上统计
        x--;
    }

    //向下统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#'){
        if(a[x][y] == 'G'){
            sum++;
        }
        //x++的作用是继续向下统计
        x++;
    }

    //向左统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#'){
        if(a[x][y] == 'G'){
            sum++;
        }
        //y--的作用是继续向右统计
        y--;
    }

    //向右统计可以消灭的敌人数
    x = i;
    y = j;
    while(a[x][y] != '#'){
        if(a[x][y] == 'G'){
            sum++;
        }
        //y++的作用是继续向左统计
        y++;
    }
    return sum;
}

void dfs(int x,int y){
    //定义一个用于表示走的方向的数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走 
    int k,sum,tx,ty;
    //计算当前这个点可以消灭的敌人总数
    sum = getnum(x,y);
    //更新max值
    if(sum > max){
        max = sum;
        mx = x;
        my = y;
    }    

    //枚举四个方向
    for(k = 0;k <= 3; k++){
        //下一个结点的坐标
        tx = x + next[k][0];
        ty = y + next[k][1];
        //判断是否越界
        if(tx < 0 || tx > n-1 || ty < 0 || ty > m-1){
            continue;
        }
        //判断是否围墙或者已经走过
        if(a[tx][ty] == '.' && book[tx][ty] == 0){
            book[tx][ty] = 1;
            dfs(tx,ty);
        }
    }
    return;              
}

int main(){
    int i,startx,starty;
    //读入n和m，n表示有多少行字符，m表示每行有多少列
    scanf("%d %d %d %d",&n,&m,&startx,&starty);
    //读入n行字符
    for(i = 0;i <= n-1; i++){
        scanf("%s",a[i]);
    }     
    book[startx][starty] = 1;
    max = getnum(startx,starty);
    mx = startx;
    my = starty;
    dfs(startx,starty);
    printf("将炸弹放置在(%d,%d)处,可以消灭%d个敌人\n",mx,my,max);

    system("pause");
    return 0;
}
~~~

## 4.5 宝岛探险

小哼决定去钓鱼岛探险，下面这个 10*10 的二维矩阵就是钓鱼岛的航拍地图。图中数字表示海拔，0 表示海洋，1~9 都表示陆地。小哼的飞机将会降落在(6,8)处，现在需要计算出小哼将落地所在岛的面积（即有多少个格子）。
<img src="E:\Typora笔记\算法(Algorithms).assets\image-20230506173405896.png" alt="image-20230506173405896" style="zoom:50%;" />

其实就是从(6,8)开始广度优先搜索，每次需要向上下左右四个方向扩展，当扩展出的点大于0时就加入队列，直到队列扩展完毕。所有被加入到队列的点的总数就是小岛的面积。假设地图的大小不超过50*50。代码实现如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

struct note{
    int x;//横坐标
    int y;//纵坐标
};
int main(){
    struct note que[2501];
    int head,tail;
    int a[51][51];
    int book[51][51] = {0};
    int i,j,k,sum,max=0,mx,my,n,m,startx,starty,tx,ty;
    //定义一个方向数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走 
    //读入n行m列以及小哼降落的坐标
    scanf("%d %d %d %d",&n,&m,&startx,&starty);
    //读入地图
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            scanf("%d",&a[i][j]);
        }
    }

    //队列初始化
    head = 1;
    tail = 1;
    //往队列中插入初始坐标
    que[tail].x = startx;
    que[tail].y = starty;
    tail++;
    book[startx][starty] = 1;
    sum = 1;
    //当队列不为空时循环
    while(head < tail){
        for(k = 0;k <= 3; k++){
            tx = que[head].x + next[k][0];
            ty = que[head].y + next[k][1];

            if(tx < 1 || tx > n || ty < 1 || ty > m){
                continue;
            }

            if(a[tx][ty] > 0 && book[tx][ty] == 0){
                sum++;
                book[tx][ty] = 1;
                que[tail].x = tx;
                que[tail].y = ty;
                tail++;
            }
        }
        head++;
    }
    printf("%d\n",sum);
    system("pause");
    return 0;
}
~~~

当然也可以用深度优先搜索来做，实现代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int a[51][51];
int book[51][51],n,m,sum;

void dfs(int x,int y){
    //定义一个方向数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走
    int k,tx,ty;
    for(k = 0;k <= 3; k++){
        tx = x + next[k][0];
        ty = y + next[k][1];

        if(tx < 1 || tx > n || ty < 1 || ty > m){
            continue;
        }

        if(a[tx][ty] > 0 && book[tx][ty] == 0){
            sum++;
            book[tx][ty] == 1;
            dfs(tx,ty);
        }
    }  
    return;              
}

int main(){
    int i,j,startx,starty;
    scanf("%d %d %d %d",&n,&m,&startx,&starty);
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            scanf("%d",&a[i][j]);
        }
    }
    book[startx][starty] = 1;
    sum = 1;
    dfs(startx,starty);
    printf("%d\n",sum);
    system("pause");
    return 0;
}
~~~

上面这种方法又叫着色法：以某个点为源点对其邻近的点进行着色。比如我们可以将上面的代码稍加改动，将小哼降落的岛都改为-1，表示该岛已经被小哼玩遍了，如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int a[51][51];
int book[51][51],n,m,sum;

void dfs(int x,int y,int color){
    //定义一个方向数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走
    int k,tx,ty;
    a[x][y] = color;
    for(k = 0;k <= 3; k++){
        tx = x + next[k][0];
        ty = y + next[k][1];

        if(tx < 1 || tx > n || ty < 1 || ty > m){
            continue;
        }

        if(a[tx][ty] > 0 && book[tx][ty] == 0){
            sum++;
            book[tx][ty] == 1;
            dfs(tx,ty,color);
        }
    }  
    return;              
}

int main(){
    int i,j,startx,starty;
    scanf("%d %d %d %d",&n,&m,&startx,&starty);
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            scanf("%d",&a[i][j]);
        }
    }
    book[startx][starty] = 1;
    sum = 1;
    dfs(startx,starty,-1);
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            printf("%3d ",a[i][j]);
        }
        printf("\n");
    }
    system("pause");
    return 0;
}
~~~

运行结果如下：

>   1   2   1   0   0   0   0   0   2   3
>   3   0   2   0  -1  -1  -1   0   1   2
>   4   0   1   0  -1  -1  -1  -1   0   1
>   3   2   0   0   0  -1  -1  -1   0   0
>   0   0   0   0   0   0  -1  -1  -1   0
>   0  -1  -1  -1   0  -1  -1  -1  -1   0
>   0  -1  -1  -1  -1  -1  -1  -1  -1   0
>   0   0  -1  -1  -1  -1  -1  -1   0   0
>   0   0   0  -1  -1  -1  -1   0   1   2
>   0   0   0   0   0   0   0   0   1   0

如果想知道这个地图中有多少个独立的小岛又该怎么做呢？只需要对地图上每一个大于0的点都进行一遍深度优先搜索即可。因为等于0的点是海洋，小于0的点是已经被染色的小岛，我们可以从(1,1)开始，一直枚举到(n,m)，对每个点进行尝试染色。

~~~c
#include<stdio.h>
#include<stdlib.h>

int a[51][51];
int book[51][51],n,m,sum;

void dfs(int x,int y,int color){
    //定义一个方向数组
    int next[4][2] = {{0,1}, //向右走
                      {1,0}, //向下走
                      {0,-1}, //向左走
                      {-1,0}}; //向上走
    int k,tx,ty;
    a[x][y] = color;
    for(k = 0;k <= 3; k++){
        tx = x + next[k][0];
        ty = y + next[k][1];

        if(tx < 1 || tx > n || ty < 1 || ty > m){
            continue;
        }

        if(a[tx][ty] > 0 && book[tx][ty] == 0){
            sum++;
            book[tx][ty] == 1;
            dfs(tx,ty,color);
        }
    }  
    return;              
}

int main(){
    int i,j,num = 0;
    scanf("%d %d",&n,&m);
    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            scanf("%d",&a[i][j]);
        }
    }

    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            if(a[i][j] > 0){
                num--;
                book[i][j] = 1;
                dfs(i,j,num);
            }
        }
    }

    for(i = 1;i <= n; i++){
        for(j = 1;j <= m; j++){
            printf("%3d ",a[i][j]);
        }
        printf("\n");
    }

    printf("有%d个小岛\n",-num);

    system("pause");
    return 0;
}
~~~

# 5. 图的遍历

## 5.1 深度和广度优先究竟是啥

深度优先遍历就是沿着图的某一条分支遍历直到末端，然后回溯，再沿着另一条进行同样的遍历，直到所有的顶点都被访问过为止。

在下面代码中，变量 cur 存储的是当前正在遍历的顶点，二维数组 e 存储就是图的边（邻接矩阵），数组 book 用来记录哪些顶点已经被访问过，变量 sum 用来记录已经访问过多少个顶点，变量 n 存储的是图的顶点的总个数。完整代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int book[101] = {0};
int sum = 0,n,e[101][101];

void dfs(int cur){
    int i;
    printf("%d ",cur);
    sum++; //每访问一个顶点，sum就加
    if(sum == n) return;
    for(int i = 1;i <= n; i++){
        if(e[cur][i] == 1 && book[i] == 0){
            book[i] = 1;
            dfs(i);
        }
    }
    return;
}

int main(){
    int i,j,m,a,b;
    scanf("%d %d",&n,&m);
    //初始化二维矩阵
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = 99999999;
        }
    }

    //读入顶点之间的边
    for(i = 1;i <= m; i++){
        scanf("%d %d",&a,&b);
        e[a][b] = 1;
        e[b][a] = 1; //这里是无向图，所以需要将e[b][a]赋值为1
    }

    //从1号顶点出发
    book[1] = 1;//标记1号顶点已访问
    dfs(1);//从1号顶点开始遍历

    system("pause");
    return 0;
}
~~~

广度优先遍历的主要思想是：首先以一个未被访问过的顶点作为起始顶点，访问其所有相邻的顶点，然后对每个相邻的顶点，再访问它们相邻的未被访问过的顶点，直到所有顶点都被访问过，遍历结束，代码实现如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int i,j,n,m,a,b,cur,book[101] = {0},e[101][101];
    int que[1001],head,tail;
    scanf("%d %d",&n,&m);
    //初始化二维矩阵
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = 99999999;
        }
    }

    //读入顶点之间的边
    for(i = 1;i <= m; i++){
        scanf("%d %d",&a,&b);
        e[a][b] = 1;
        e[b][a] = 1;
    }

    //队列初始化
    head = 1;
    tail = 1;

    //从1号顶点出发，将1号顶点加入队列
    que[tail] = 1;
    tail++;
    book[1] = 1;

    //当队列不为空时循环
    while(head < tail && tail <= n){
        cur = que[head];
        for(i = 1;i <= n; i++){
            if(e[cur][i] == 1 && book[i] == 0){
                que[tail] = i;
                tail++;
                book[i] = 1;
            }

            //如果tail大于n，则表明所有顶点都已经被访问过
            if(tail > n){
                break;
            }
        }
        head++;
    }

    for(i = 1;i < tail; i++){
        printf("%d ",que[i]);
    }

    system("pause");
    return 0;
}
~~~

## 5.2 城市地图——图的深度优先遍历

<img src="E:\Typora笔记\算法(Algorithms).assets\bccb01cb6a8b614905ecd4ebf0b263b.jpg" alt="bccb01cb6a8b614905ecd4ebf0b263b" style="zoom:50%;" />

数据给出如下：

> 5 8
> 1 2 2
> 1 5 10
> 2 3 3
> 3 1 4
> 3 4 4
> 4 5 5
> 5 3 3

第一行的 5 表示有 5 个城市（城市编号为 1~5），8 表示有 8 条公路。“a b c” 表示有一条路可以从城市 a 到城市 b，并且路程为 c 公里。小哼家在 1 号城市，小哈家在 5 号城市。现在请求出 1 号城市到 5 号城市的最短路程（也叫做最短路径）。

~~~c
#include<stdio.h>
#include<stdlib.h>

int book[101] = {0},e[101][101],min = 99999999,dis,n;

void dfs(int cur,int dis){
    int i;
    if(min < dis) return;
    if(cur == n){
        if(dis < min) min = dis;
        return;
    }

    for(i = 1;i <= n; i++){
        if(e[cur][i] != 99999999 && book[i] == 0){
            book[i] = 1;
            dfs(i,dis+e[cur][i]);
            book[i] = 0;
        }
    }
    return;
}

int main(){
    int i,j,m,a,b,c;
    scanf("%d %d",&n,&m);
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = 99999999;
        }
    }
    for(int i = 1;i <= m; i++){
        scanf("%d %d %d",&a,&b,&c);
        e[a][b] = c;
    }

    book[1] = 1;
    dfs(1,0);
    printf("%d",min);
    system("pause");
    return 0;
}
~~~

## 5.3 最小转机——图的广度优先遍历

小哼和小哈一同坐飞机去旅游，他们现在位于 1 号城市，目标是 5 号城市，可是 1 号城市并没有到 5 号城市的直航。不过小哼已经收集了很多航班的信息，现在小哼希望找到一种乘坐方式，使得转机的次数最少。

> 5 7 1 5
> 1 2
> 1 3
> 2 3
> 2 4
> 3 4
> 3 5
> 4 5

<img src="E:\Typora笔记\算法(Algorithms).assets\e38d1755b5bb1152de632eb3642d169.jpg" alt="e38d1755b5bb1152de632eb3642d169" style="zoom:50%;" />

第一行的 5 表示有 5 个城市（城市编号 1~5）,7 表示有 7 条航线，1 表示起点城市，5 表示目标城市。接下来 7 行每行是一条类似 “a b”这样的数据表示城市 a 和城市 b 之间有航线，也就是说城市 a 和城市 b 之间可以相互到达。

下面我们用广度优先搜索来解决，代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

struct note{
    int x; //城市编号
    int s; //转机次数
};

int main(){
    struct note que[2501];
    int e[51][51] = {0}, book[51] = {0};
    int head,tail;
    int i,j,n,m,a,b,cur,start,end,flag = 0;
    scanf("%d %d %d %d",&n,&m,&start,&end);
    //初始化二维矩阵
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = 99999999;
        }
    } 
    //读入城市之间的航班
    for(i = 1;i <= m; i++){
        scanf("%d %d",&a,&b);
        //注意这里是无向图
        e[a][b] = 1;
        e[b][a] = 1;
    }

    //队列初始化
    head = 1;
    tail = 1;

    //从start号城市出发，将start号城市加入队列
    que[tail].x = start;
    que[tail].s = 0;
    tail++;
    book[start] = 1;//标记start号城市已在队列中
    //当队列不为空的时候循环
    while(head < tail){
        cur = que[head].x;
        for(j = 1;j <= n; j++){
            if(e[cur][j] != 99999999 && book[i] == 0){
                que[tail].x = j;
                que[tail].s = que[head].s + 1;
                tail++;
                book[j] = 1;
            }
            if(que[tail-1].x == end){
                flag = 1;
                break;
            }
        }
        if(flag == 1){
            break;
        }
        head++;
    }

    printf("%d",que[tail-1].s);

    system("pause");
    return 0;
}
~~~

当然也可以用深度优先搜索解决，但是这里用广度优先搜索会更快。广度优先搜索更加适合用于所有边的权值相同的情况。

# 6. 最短路径

## 6.1 只有五行的算法——Floyd-Warshall

暑假，小哼准备去一些城市旅游。有些城市之间只有公路，有些城市之间则没有，如下图。为了节省经费以及方便计划旅程，小哼希望在出发之前知道任意两个城市之间的最短路程。

<img src="E:\Typora笔记\算法(Algorithms).assets\61da35539899f8dc3e70f7aa5914fcb.jpg" alt="61da35539899f8dc3e70f7aa5914fcb" style="zoom:50%;" />

这段代码的基本思想就是：最开始只允许经过 1 号顶点进行中转，接下来只允许经过 1 和 2 号顶点进行中转······允许经过 1~n 号所有顶点进行中转，求任意两点之间的最短路程。

~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int e[10][10],k,i,j,n,m,t1,t2,t3;
    int inf = 99999999;//用inf(infinity的缩写)存储一个我们认为的正无穷值
    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);

    //初始化
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = inf;
        }
    }

    //读入边
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&t1,&t2,&t3);
        e[t1][t2] = t3;
    }

    //Floyd-Warshall算法核心语句
    for(k = 1;k <= n; k++){
        for(i = 1;i <= n; i++){
            for(j = 1;j <= n; j++){
                if(e[i][j] > e[i][k] + e[k][j]){
                    e[i][j] = e[i][k] + e[k][j];
                }
            }
        }
    }

    //输出最终的结果
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            printf("%10d",e[i][j]);
        }
    }

    system("pause");
    return 0;
}
~~~

则Flyod-Warshall算法核心语句
~~~c
for(k = 1;k <= n; k++){
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(e[i][k] < inf && e[k][j] < inf && e[i][j] > e[i][k] + e[k][j]){
                e[i][j] = e[i][k] + e[k][j];
            }
        }
    }
}
~~~

## 6.2 Dijkstra算法——单源最短路

指定一个点（源点）到其余各个顶点的最短路径，也叫做 “单源最短路径”。例如求下图中的 1 号顶点到 2、3、4、5、6 号顶点的最短路径。

![693e6629cdb52e1eaa885aa51f4c019](E:\Typora笔记\算法(Algorithms).assets\693e6629cdb52e1eaa885aa51f4c019.jpg)

算法的基本思想是：每次找到离源点最近的一个顶点，然后以该顶点为中心进行扩展，最终得到源点到其余所有点的最短路径。基本步骤如下：

1. 将所有的顶点分为两部分：已知最短路程的顶点集合 P 和未知最短路径的顶点集合 Q。最开始，已知最短路径的顶点集合 P 中只有源点一个顶点。我们这里用一个 book 数组来记录哪些点在集合 P 中。例如对于某个顶点 i，如果 book[i] 为 1 则表示这个顶点在集合 P 中，如果 book[i] 为 0 则表示这个顶点在集合 Q 中。
2. 设置源点 s 到自己的最短路径为 0 即 dis[s] = 0。若存在有源点能直接到达的顶点 i，则把 dis[i] 设为 e[s] [i]。同时把所有其他（源点不能直接到达的）顶点的最短路径设为∞。
3. 在集合 Q 的所有顶点中选择一个离源点 s 最近的顶点 u（即 dis[u] 最小）加入到集合 P。并考察所有以点 u 为起点的边，对每一条进行松弛操作。例如存在一条从 u 到 v 的边，那么可以通过将边 u -> v 添加到尾部来拓展一条从 s 到 v 的路径，这条路径的长度是 dis[u] + e[u] [v]。如果这个值比目前已知的 dis[v] 的值要小，我们可以用新值来替代当前 dis[v] 中的值。
4. 重复第 3 步，如果集合 Q 为空，算法结束。最终 dis 数组中的值就是源点到所有顶点的最短路径。

完整的Dijikstra算法代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int e[10][10],dis[10],book[10],i,j,n,m,t1,t2,t3,u,v,min;
    int inf = 99999999; //用inf(infinity的缩写)存储一个我们认为的正无穷值
    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n ,&m);

    //初始化
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = inf;
        }
    }

    //读入边
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&t1,&t2,&t3);
        e[t1][t2] = t3;
    }

    //初始化dis数组，这里是1号顶点到其余各个顶点的初始路程
    for(i = 1;i <= n; i++){
        dis[i] = e[1][i];
    }

    //book数组初始化
    for(i = 1;i <= n; i++){
        book[i] = 0;
    }
    book[1] = 1;

    //Dijkstra算法核心句
    for(i = 1;i <= n-1; i++){
        //找到离1号最近的顶点
        min = inf;
        for(j = 1;j <= n; j++){
            if(book[j] == 0 && dis[j] < min){
                min = dis[j];
                u = j;
            }
        }
        book[u] = 1;
        for(v = 1;v <= n; v++){
            if(e[u][v] < inf){
                if(dis[v] > dis[u] + e[u][v]){
                    dis[v] = dis[u] + e[u][v];
                }
            }
        }
    }

    //输出最终结果
    for(i = 1;i <= n; i++){
        printf("%d ",dis[i]);
    }

    system("pause");
    return 0;
}
~~~

另外对于边数 M 少于 N^2^ 的稀疏图来说（我们把 M 远小于 N^2^ 的图称为稀疏图，而 M 相对较大的图称为稠密图），我们可以用邻接表来代替邻接矩阵。

## 6.3 Bellman-Ford——解决负向边

Dijkstra 算法虽然好，但是他不能解决带有负权边(边的权值为负数)的图。本节来介绍一个无论是思想上还是代码实现上都堪称完美的最短路算法：Bellman-Ford。先看看它长啥样。

~~~c
for(k = 1;k <= n-1; k++){
    for(i = 1;i <= m; i++){
        if(dis[v[i]] > dis[u[i]] + w[i]){
            dis[v[i]] = dis[u[i]] + w[i];
        }
    }
}
~~~

上面的代码中，外循环一共循环了 n-1 次（n为顶点个数），内循环循环了 m 次（m为边的个数），即枚举每一条边。dis 数组的作用跟 Dijkstra 算法一样，是用来记录源点到其余各个顶点的最短路径。u、v和 w 三个数组是用来记录边的信息。例如第 i 条边存储在u[i]、v[i] 和 w[i] 中，表示顶点 u[i] 到顶点 v[i] 这条边（u[i] -> v[i]）权值为 w[i]。

~~~c
if(dis[v[i]] > dis[u[i]] + w[i]){
    dis[v[i]] = dis[u[i]] + w[i];
}
~~~

上面两行代码的意思是：看看能否通过 u[i]->v[i]（权值为 w[i]）这条边，使得 1 号顶点到 v[i] 号顶点的距离变短。即 1 号顶点到 u[i] 号顶点的距离（dis[u[i]]）加上 u[i] -> v[i] 这条边（权值为 w[i]）的值是否会比原先 1 号顶点到 v[i] 号顶点的距离（dis[v[i]]）要小。这一点其实与 Djkstra 的 “松弛” 操作是一样的。现在我们要把所有的边都松弛一遍，代码如下：
~~~c
for(i = 1;i <= m; i++){
    if(dis[v[i]] > dis[u[i]] + w[i]){
        dis[v[i]] = dis[u[i]] + w[i];
    }
}
~~~

总结一下：因为最短路径上最多有 n-1 条边，因此 Bellman-Ford 算法最多有 n-1 个阶段。

Bellman-Ford 算法的完整的代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int dis[10],i,k,n,m,u[10],v[10],w[10];
    int inf = 99999999;//用 inf(infinity的缩写)存储一个我们认为的正无穷值
    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);

    //读入边
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&u[i],&v[i],&w[i]);
    }

    //初始化dis数组，这里是1号顶点到其余各个顶点的初始路程
    for(i = 1;i <= n; i++){
        dis[i] = inf;
    }
    dis[1] = 0;

    //Bellman-Ford算法核心语句
    for(k = 1;k <= n-1; k++){
        for(i = 1;i <= m; i++){
            if(dis[v[i]] > dis[u[i]] + w[i]){
                dis[v[i]] = dis[u[i]] + w[i];
            }
        }
    }

    //输出最终结果
    for(i = 1;i <= n; i++){
        printf("%d ",dis[i]);
    }

    system("pause");
    return 0;
}
~~~

==此外，Bellman-Ford 算法还可以检测一个图是否含有负权回路。如果在进行 n-1 轮松弛之后，仍然可以继续松弛，那么此图必然存在负权回路，关键代码如下：==

~~~c
//Bellman-Ford算法核心语句
for(k = 1;k <= n-1; k++){
    for(i = 1;i <= m; i++){
        if(dis[v[i]] > dis[u[i]] + w[i]){
            dis[v[i]] = dis[u[i]] + w[i];
        }
    }
}
//检测负权回路
flag = 0;
for(i = 1;i <= m; i++){
    if(dis[v[i]] > dis[u[i]] + w[i]) flag = 1;
}
if(flag == 1) printf("此图含有负权回路");
~~~

在实际操作中，Bellman-Ford 算法经常会在未达到 n-1 轮松弛前就已经计算出最短路，因此可以添加一个变量 check 用来标记数组 dis 在本轮松弛中是否发生了变化，如果没有变化，则可以提前跳出循环，代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int dis[10],i,k,n,m,u[10],v[10],w[10],check,flag;
    int inf = 99999999;//用 inf(infinity的缩写)存储一个我们认为的正无穷值
    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);

    //读入边
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&u[i],&v[i],&w[i]);
    }

    //初始化dis数组，这里是1号顶点到其余各个顶点的初始路程
    for(i = 1;i <= n; i++){
        dis[i] = inf;
    }
    dis[1] = 0;

    //Bellman-Ford算法核心语句
    for(k = 1;k <= n-1; k++){
        check = 0; //用来标记在本轮松弛中数组dis是否会发生更新
        for(i = 1;i <= m; i++){
            if(dis[v[i]] > dis[u[i]] + w[i]){
                dis[v[i]] = dis[u[i]] + w[i];
                check = 1;//数组dis发生更新，改变check的值
            }
        }
        if(check == 0) break; //如果数组dis没有更新，提前退出循环结束算法
    }

    //检测负权回路
    flag = 0;
    for(i = 1;i <= m; i++){
        if(dis[v[i]] > dis[u[i]] + w[i]) flag = 1;
    }

    if(flag == 1) printf("此图含有负权回路");
    else{
        //输出最终结果
        for(i = 1;i <= n; i++){
            printf("%d ",dis[i]);
        }
    }

    system("pause");
    return 0;
}
~~~

## 6.4 Bellman-Ford 的队列优化

每次选取队首顶点 u，对顶点 u 的所有出边进行松弛操作。例如有一条 u->v 的边，如果通过 u->v 这条边使得源点到顶点 v 的最短路程变短(dis[u] + e[u] [v] < dis[v])，且顶点 v 不在当前的队列中，就将顶点 v 放入队尾。需要注意的是，同一个顶点同时在队列中出现多次是毫无意义的，所以我们需要一个数组来判重（判断哪些点已经在队列中）。在对顶点 u 的所有出边松弛完毕后，就将顶点 u 出队。接下来不断从队列中取出新的队首顶点再进行如上操作，直至队列空为止。

![a4eb316c2c39faaba04f4b90b80ea26](E:\Typora笔记\算法(Algorithms).assets\a4eb316c2c39faaba04f4b90b80ea26.jpg)

实现代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int n,m,i,j,k;
    //u、v和w的数组大小要根据实际情况来设置，要比m的最大值要大1
    int u[8],v[8],w[8];
    //first要比n的最大值要大1，next要比m的最大值要大1
    int first[6],next[8];
    int dis[6] = {0},book[6] = {0};//book数组用来记录哪些顶点已经在队列中
    int que[101] = {0}, head = 1, tail = 1; //定义一个队列，并初始化队列
    int inf = 99999999; //用inf（infinity的缩写）存储一个我们认为的正无穷值
    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);
    //初始化dis数组，这里是1号顶点到其余各个顶点的初始路程
    for(i = 1;i <= n; i++){
        dis[i] = inf;
    }
    dis[1] = 0;
    //初始化book数组，初始化为0，刚开始都不在队列中
    for(i = 1;i <= n; i++){
        book[i] = 0;
    }
    //初始化first数组下标1~n的值为-1，表示1~n顶点暂时都没有边
    for(i = 1;i <= n; i++){
        first[i] = -1;
    }

    for(i = 1;i <= m; i++){
        //读入每一条边
        scanf("%d %d %d",&u[i],&v[i],&w[i]);
        //下面两句是建立邻接表的关键
        next[i] = first[u[i]];
        first[u[i]] = i;
    }

    //1号顶点入队
    que[tail] = 1; 
    tail++;
    book[1] = 1;//标记1号顶点已经入队

    while(head < tail){
        k = first[que[head]]; //当前需要处理的队首顶点
        while(k != -1){
            if(dis[v[k]] > dis[u[k]] + w[k]){
                dis[v[k]] = dis[u[k]] + w[k];
                //这的book数组用来判断顶点v[k]是否在队列中
                if(book[v[k]] == 0) //0表示不在队列中，将顶点v[k]加入队列中
                {
                    //下面两句是入队操作
                    que[tail] = v[k];
                    tail++;
                    book[v[k]] = 1;//同时标记顶点v[k]已经入队
                }
            }
            k = next[k];
        }
        //出队
        book[que[head]] = 0;
        head++;
    }

    //输出1号顶点到其余各个顶点的最短路径
    for(i = 1;i <= n; i++){
        printf("%d ",dis[i]);
    }

    system("pause");
    return 0;
}
~~~

# 7.神奇的树

## 7.1 堆——神奇的优先队列

假设有 14 个数，分别是 99、5、36、7、22、17、46、12、2、19、25、28、1 和 92，现在我们需要删除其中最小的数，并增加一个新数 23，再次求这 14 个数中最小的一个数。首先我们把这 14 个数按照最小堆的要求（就是所有父结点都比子结点要小）放入一棵完全二叉树，就像下面这棵树一样：
![3359f082d0d6905f641bc0a22753fd5](E:\Typora笔记\C++\算法(Algorithms).assets\3359f082d0d6905f641bc0a22753fd5.jpg)

很显然最小的数就在堆顶，假设存储这个堆的数组叫做h的话，最小数就是 h[1] 。接下来，我们将堆顶部的数删除。将新增加的数23放到堆顶。然后进行向下调整：
![49af4e0fc5bbc2332c908d57af7032d](E:\Typora笔记\C++\算法(Algorithms).assets\49af4e0fc5bbc2332c908d57af7032d.jpg)

向下调整的代码如下：
~~~c
void siftdown(int i) //传入一个需要向下调整的结点编号i，这里传入1，即从堆的顶点开始向下调整
{
    int t,flag = 0; //flag用来标记是否需要继续向下调整
    //当i结点有儿子（其实是至少有左儿子的情况下）并且有需要继续调整的时候，循环就执行
    while(i * 2 <= n && flag == 0){
        //首先判断它和左二子的关系，并用t记录值较小的结点编号
        if(h[i] > h[i*2]){
            t = i * 2;
        }else{
            t = i;
        }
        //如果它有右儿子，再对右儿子进行讨论
        if(i * 2 + 1 <= n){
            //如果右儿子的值更小，更新较小的结点编号
            if(h[t] > h[i*2+1]){
                t = i * 2 + 1;
            }
        }
        //如果发现最小的结点编号不是自己，说明子结点中有比父结点更小的
        if(t != i){
            swap(t,i); //交换它们，注意swap函数需要自己来写
            i = t; //更新i为刚才与它交换的儿子结点的编号，便于接下来继续向下调整
        }
        else{
            flag = 1;//否则说明当前的父结点已经比两个子结点都要小了，不需要再进行调整了
        }
    }
}
~~~

如果只是想新增一个值，而不是删除最小值，只需要直接将新元素插入到末尾，再根据情况判断新元素是否需要上移，直到满足堆的特性为止。如果堆的大小为 N（即有 N 个元素），那么插入一个新元素所需要的时间为 O(logN)。例如我们现在要新增一个数 3。
![40bbc6a232868cd907f9ba26b6e7441](E:\Typora笔记\C++\算法(Algorithms).assets\40bbc6a232868cd907f9ba26b6e7441.jpg)

向上调整的代码如下:
~~~c
void siftup(int i) //传入一个需要向上调整的结点编号i
{
    int flag = 0; //用来标记是否需要继续向上调整
    if(i == 1) return; //如果是堆顶，就返回，不需要调整了
    //不在堆顶，并且当前结点i的值比父结点小的时候就继续向上调整
    while(i != 1 && flag == 0){
        //判断是否比父结点的小‘
        if(h[i] < h[i/2]){
            swap(i,i/2); //交换它和它爸爸的位置
        }else{
            flag = 1; //表示已经不需要调整了，当前结点的值比父结点的值要大
        }
        i = i / 2; //更新编号i为它父结点的编号，从而便于下一次继续向上调整
    }
}
~~~

如何建立这个堆，可以从空的堆开始，然后依次往堆中插入每一个元素，直到所有数都被插入（转移到堆中）为止。因为插入第 i 个元素所用的时间是 O(log i)，所以插入所有元素的整体时间复杂度是 O(NlogN)，代码如下:
~~~c
n = 0;
for(i = 1;i <= m; i++){
    n++;
    h[n] = a[i];
    shifup(i);
}
~~~

当然还有一种创建堆的算法。把 n 个元素建立一个堆，首先我可以将这 n 个结点以自顶向下、从左到右的方式从 1 到 n 编码。这样就可以把这 n 个结点转换成一棵完全二叉树。紧接着从最后一个非叶结点（结点编号为 n/2）开始到根结点（结点编号为1），逐个扫描所有的结点，根据需要将当前结点向下调整，直到以当前结点为根结点的子树符合堆的特性。实现代码如下：
~~~c
for(i = n/2;i >= 1; i--){
    shifdown(i);
}
~~~

堆还有一个作用就是堆排序，与快速排序一样，堆排序的时间复杂度也是 O(NlogN)。堆排序的实现很简单，比如我们要进行从小到大排序，可以先建立最小堆，然后每次删除顶部元素并将顶部元素输出或者放入一个新的数组中，直到堆为空为止。最终输出的或者存放在新数组中的数就已经是排序好的了。
~~~c
//删除最大的元素
int deletemax(){
    int t;
    t = h[1]; //用一个临时变量记录堆顶点的值
    h[1] = h[n]; //将堆的最后一个点赋值到堆顶
    n--; //堆的元素减少
    shifdown(1); //向下调整
    return t; //返回之前记录的堆的顶点的最大值
}
~~~

建堆以及堆排序的完整代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int h[101]; //用来存放堆的数组
int n; //用来存储堆中元素的个数，也就是堆的大小

//交换函数，用来交换堆中的两个元素的值
void swap(int x,int y){
    int t;
    t = h[x];
    h[x] = h[y];
    h[y] = t;
}

//向下调整函数
void siftdown(int i) //传入一个需要向下调整的结点编号i，这里传入1，即从堆的顶点开始向下调整
{
    int t,flag = 0; //flag用来标记是否需要继续向下调整
    //当i结点有儿子（其实是至少有左儿子的情况下）并且有需要继续调整的时候，循环就执行
    while(i * 2 <= n && flag == 0){
        //首先判断它和左二子的关系，并用t记录值较小的结点编号
        if(h[i] > h[i*2]){
            t = i * 2;
        }else{
            t = i;
        }
        //如果它有右儿子，再对右儿子进行讨论
        if(i * 2 + 1 <= n){
            //如果右儿子的值更小，更新较小的结点编号
            if(h[t] > h[i*2+1]){
                t = i * 2 + 1;
            }
        }
        //如果发现最小的结点编号不是自己，说明子结点中有比父结点更小的
        if(t != i){
            swap(t,i); //交换它们，注意swap函数需要自己来写
            i = t; //更新i为刚才与它交换的儿子结点的编号，便于接下来继续向下调整
        }
        else{
            flag = 1;//否则说明当前的父结点已经比两个子结点都要小了，不需要再进行调整了
        }
    }
}

//建立堆函数
void create(){
    int i;
    //从最后一个非叶结点到第1个结点依次进行向上调整
    for(i = n/2;i >= 1; i--){
        siftdown(i);
    }
}

//删除最大元素
int deletemax(){
    int t;
    t = h[1];//用一个临时变量记录对顶点的值
    h[1] = h[n]; //将堆的最后一个点赋值到堆顶
    n--; //堆的元素减少1
    siftdown(1); //向下调整
    return t; //返回之前记录的堆的顶点的最大值
}

int main(){
    int i,num;
    //读入要排序的数字的个数
    scanf("%d",&num);

    for(i = 1;i <= num; i++){
        scanf("%d",&h[i]);
    }
    n = num;

    //建堆
    create();

    //删除顶部元素，连续删除n次，其实也就是从小到大把数输出来
    for(i = 1;i <= num; i++){
        printf("%d ",deletemax());
    }

    system("pause");
    return 0;
}
~~~

当然堆排序还有一种更好的方法。从小到大排序的时候不建立最小堆而建立最大堆，最大堆建立好后，最大的元素在h[1]，因为我们的需求是从小到大排序，希望最大的放在最后。因此我们将h[1] 和 h[n] 交换，此时 h[n] 就是数组中的最大元素。请注意，交换后需将 h[1] 向下调整以保持堆的特性。直到堆的大小变成1为止，此时数组 h 中的数就已经是排序好的了。代码如下：

~~~c
//堆排序
void heapsort(){
    while(n > 1){
        swap(1,n);
        n--;
        siftdown(1);
    }
}
~~~

完整的堆排序的代码如下，注意使用这种方法来进行从小到大排序需要建立最大堆：
~~~c
#include<stdio.h>
#include<stdlib.h>

int h[101]; //用来存放堆的数组
int n; //用来存储堆中元素的个数，也就是堆的大小

//交换函数，用来交换堆中的两个元素的值
void swap(int x,int y){
    int t;
    t = h[x];
    h[x] = h[y];
    h[y] = t;
}

//向下调整函数
void siftdown(int i) //传入一个需要向下调整的结点编号i，这里传入1，即从堆的顶点开始向下调整
{
    int t,flag = 0; //flag用来标记是否需要继续向下调整
    //当i结点有儿子（其实是至少有左儿子的情况下）并且有需要继续调整的时候，循环就执行
    while(i * 2 <= n && flag == 0){
        //首先判断它和左二子的关系，并用t记录值较小的结点编号
        if(h[i] > h[i*2]){
            t = i * 2;
        }else{
            t = i;
        }
        //如果它有右儿子，再对右儿子进行讨论
        if(i * 2 + 1 <= n){
            //如果右儿子的值更小，更新较小的结点编号
            if(h[t] > h[i*2+1]){
                t = i * 2 + 1;
            }
        }
        //如果发现最小的结点编号不是自己，说明子结点中有比父结点更小的
        if(t != i){
            swap(t,i); //交换它们，注意swap函数需要自己来写
            i = t; //更新i为刚才与它交换的儿子结点的编号，便于接下来继续向下调整
        }
        else{
            flag = 1;//否则说明当前的父结点已经比两个子结点都要小了，不需要再进行调整了
        }
    }
}

//建立堆函数
void create(){
    int i;
    //从最后一个非叶结点到第1个结点依次进行向上调整
    for(i = n/2;i >= 1; i--){
        siftdown(i);
    }
}

//堆排序
void heapsort(){
    while(n > 1){
        swap(1,n);
        n--;
        siftdown(1);
    }
}

int main(){
    int i,num;
    //读入要排序的数字的个数
    scanf("%d",&num);

    for(i = 1;i <= num; i++){
        scanf("%d",&h[i]);
    }
    n = num;

    //建堆
    create();

    //堆排序
    heapsort();

    //输出
    for(i = 1;i <= num; i++){
        printf("%d ",h[i]);
    }

    system("pause");
    return 0;
}
~~~

## 7.2 擒贼先擒王——并查集

并查集通过一个一个一维数组来实现，其本质是维护一个森林。刚开始的时候，森林的每个点都是孤立的，也可以理解为每个点就是一棵只有一个结点的树，之后通过一些条件，逐渐将这些树合成一棵大树。其实合并的过程就是 “认爹” 的过程。在 “认爹” 的过程中，要遵守 “靠左” 原则和 “擒贼先擒王” 原则。在每次判断两个结点是否已经在同一棵树中的时候（一棵树其实就是一个集合），也要注意必须求其根源，中间父亲结点（“ 小BOSS ”）是不能说明问题的，必须找到其祖宗（树的根结点），判断两个结点的祖宗是否在同一个根结点才行。代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int f[1000] = {0},n,m,k,sum = 0;
//这里是初始化，数组里面存的是自己数组下标的编号就好了
void init(){
    int i;
    for(i = 1;i <= n; i++){
        f[i] = i;
    }
}

//这里是找爹的递归函数，不停地去找爹，直到找到祖宗为止，其实就是去找犯罪团伙的最高领导人
int getf(int v){
    if(f[v] == v){
        return v;
    }else{
        /*
        这里是路径压缩，每次在函数返回的时候，顺带把路上遇到的人的“BOSS”改为最后找到的祖宗编号
        也就是犯罪团伙的最高领导人编号，这样可以提高今后找到犯罪团伙的最高领导人（其实就是树的祖先）
        的速度
        */ 

        f[v] = getf(f[v]);
        return f[v];
    }
}

//这里是合并两子集合的函数
void merge(int v,int u){
    int t1,t2;
    t1 = getf(v);
    t2 = getf(u);
    if(t1 != t2){ //判断两个结点是否在同一个集合中，即是否为同一个祖先
        f[t2] = t1;
        //“靠左”原则，左边变成右边的BOSS，即把右边的集合，作为左边集合的子集合
        //经过路径压缩以后，将f[u]的根的值也赋值为v的祖先f[t1]
    }
}

int main(){
    int i,x,y;
    scanf("%d %d",&n,&m);

    init();
    for(i = 1;i <= m; i++){
        scanf("%d %d",&x,&y);
        merge(x,y);
    }

    //最后扫描有多少个独立的犯罪团伙
    for(i = 1;i <= n; i++){
        if(f[i] == i){
            sum++;
        }
    }
    printf("%d\n",sum);

    system("pause");
    return 0;
}
~~~

# 8.更多精彩算法

## 8.1 镖局运表——图的最小生成树

### 8.1.1 Kruskal算法

首先按照边的权值进行从小到大排序，每次剩余的边中选择权值较小且边的两个顶点不在同一个集合内的边（就是不会产生回路的边），加入到生成树中，直到加入了 n-1 条边为止，代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

struct edge{
    int u;
    int v;
    int w;
}; //为了方便排序，这里创建了一个结构体用来存储边的关系

struct edge e[10]; //数组大小根据实际情况来设置，要比m的最大值大1
int n,m;
int f[7] = {0}, sum = 0, count = 0; //并查集需要用到的一些变量
//f数组大小根据实际情况来设置，要比n的最大值大1

void quicksort(int left,int right){
    int i,j;
    struct edge t;
    if(left > right){
        return;
    }
    i = left;
    j = right;
    while(i != j){
        while(e[j].w >= e[left].w && i < j){
            j--;
        }
        while(e[i].w <= e[left].w && i < j){
            i++;
        }
        if(i < j){
            t = e[i];
            e[i] = e[j];
            e[j] = t;
        }
    }
    t = e[left];
    e[left] = e[i];
    e[i] = t;

    quicksort(left,i-1);
    quicksort(i+1,right);

    return;
}

//并查集寻找祖先的函数
int getf(int v){
    if(f[v] == v){
        return v;
    }else{
        f[v] = getf(f[v]);
        return f[v];
    }
}

//并查集合并两子集合的函数
int merge(int v,int u){
    int t1,t2;
    t1 = getf(v);
    t2 = getf(u);
    if(t1 != t2){
        f[t2] = t1;
        return 1;
    }
    return 0;
}

int main(){
    int i;
    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);

    //读入边，这里使用一个结构体来存储边的关系
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&e[i].u,&e[i].v,&e[i].w);
    }
    quicksort(i,m);

    //并查集初始化
    for(i = 1;i <= n; i++){
        f[i] = i;
    }

    //kruskal算法核心部分
    for(i = 1;i <= m; i++){ //开始从小到大枚举每一条边
        //判断一条边的两个顶点是否已经连通，即判断是否已在同一个集合中’
        if(merge(e[i].u,e[i].v)) //如果目前尚未连通，则选用这条边
        {
            count++;
            sum = sum + e[i].w;
        }
        if(count == n-1){ //直到选用了n-1条边之后退出循环
            break;
        }
    }

    printf("%d",sum);

    system("pause");
    return 0;
}
~~~

### 8.1.2 Prim算法

Prim算法的流程如下：

1. 从任意一个顶点开始构造生成树，假设就从 1 号顶点。首先将顶点 1 加入到生成树中，用一个一维数组 book 来标记哪些顶点已经加入了生成树。
2. 用数组 dis 记录生成树到各个顶点的距离。最初生成树中只有 1 号顶点，有直连边时，数组 dis 中存储的是 1 号顶点到该顶点的边的权值，没有直连边的时候就是无穷大，即初始化 dis 数组。
3. 从数组 dis 中选出离生成树最近的顶点（假设这个顶点为 j）加入到生成树中（即在数组 dis 中找到最小值）。再以 j 为中间点，更新生成树到每一个非树顶点的距离（就是松弛），即如果 dis[k] > e[j] [k] 则更新 dis[k] = e[j] [k]。
4. 重复第 3 步，直到生成树中有 n 个顶点为止。

代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int main(){
    int n,m,i,j,k,min,t1,t2,t3;
    int e[7][7], dis[7], book[7] = {0}; //这里对book数组进行了初始化
    int inf = 99999999; //用inf(infinity的缩写)存储了一个我们认为的正无穷值
    int count = 0, sum = 0; //count用来记录生成树中顶点的个数，sum用来存储路径之和

    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);

    //初始化
    for(i = 1;i <= n; i++){
        for(j = 1;j <= n; j++){
            if(i == j) e[i][j] = 0;
            else e[i][j] = inf;
        }
    }

    //开始读入边
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&t1,&t2,&t3);
        //注意这里是无向图，所以需要将边反向再存储一遍
        e[t1][t2] = t3;
        e[t2][t1] = t3;
    }

    //初始化dis数组，这里是1号顶点到各个顶点的初始距离，因为当前生成树中只有1号顶点
    for(i = 1;i <= n; i++){
        dis[i] = e[1][i];
    }

    //Prim核心部分开始
    //将1号顶点加入生成树
    book[1] = 1; //这里用book来标记一个顶点是否已经加入生成树
    count++;
    while(count < n){
        min = inf;
        for(i = 1;i <= n; i++){
            if(book[i] == 0 && dis[i] < min){
                min = dis[i];
                j = i;
            }
        }    
        book[j] = 1;
        count++;
        sum = sum + dis[j];
        //扫描当前顶点j所有的边，再以j为中间点，更新生成树到每一个非树顶点的距离
        for(k = 1;k <= n; k++){
            if(book[k] == 0 && dis[k] > e[j][k]){
                dis[k] = e[j][k];
            }
        }
    }

    printf("%d",sum); //打印结果

    system("pause");
    return 0;
}
~~~

上面这种方法的时间复杂度是O(N^2^)。如果借助 “堆”，每次选边的时间复杂度是O(logM)，然后使用邻接表来存储图的话，整个算法的时间复杂度会降低到 O(MlogN)。那么如何使用堆来优化呢？我们需要 3 个数组，如下图。

![b3b9f12a1245b505d01244ed639f25a](E:\Typora笔记\C++\算法(Algorithms).assets\b3b9f12a1245b505d01244ed639f25a.jpg)

数组 dis 用来记录生成树到各个顶点的距离。数组 h 是一个最小堆，堆里面存储的是顶点编号。请注意，这里并不是按照顶点编号的大小来建立最小堆的，而是按照顶点在数组 dis 中所对应的值来建立这个最小值。此外还需要一个数组 pos 来记录每个顶点在最小堆中的位置。例如上图中，左边最小堆的圆圈中存储的是顶点编号，圆圈右下角的数是该顶点（圆圈里面的数）到生成树的最短距离，即数组 dis中存储的值，代码如下：
~~~c
#include<stdio.h>
#include<stdlib.h>

int dis[7], book[7] = {0}; //book数组用来记录哪些顶点已经放入生成树中
int h[7], pos[7], size; //h用来保存堆，pos用来存储每个顶点在堆中的位置，size为堆的大小

//交换函数，用来交换堆中两个元素的值
void swap(int x,int y){
    int t;
    t = h[x];
    h[x] = h[y];
    h[y] = t;

    //同步更新pos
    t = pos[h[x]];
    pos[h[x]] = pos[h[y]];
    pos[h[y]] = t;
}

//向下调整函数
void siftdown(int i){ //传入一个需要向下调整的结点编号
    int t, flag = 0; //flag用来标记是否需要继续向下调整
    while(i * 2 <= size && flag == 0){
        //比较i和它左二子i * 2在dis中的值，并用t记录值较小的结点编号
        if(dis[h[i]] > dis[h[i*2]]){
            t = i * 2;
        }else{
            t = i;
        }
        //如果它有右儿子，再对右儿子进行taolun
        if(i * 2 + 1 <= size){
            //如果右儿子的值更小，更新较小的结点编号
            if(dis[h[t]] > dis[h[i*2 + 1]]){
                t = i * 2 + 1;
            }
        }
        //如果发现最小的结点编号不是自己，说明子结点中有比父结点更小的
        if(t != i){
            swap(t,i);
            i = t; //更新i为刚才与它交换的儿子结点编号，便于接下来继续向上调整
        }else{
            flag = 1; //否则说明当前的父结点已经比两个子结点都要小了，不需要再进行调整了
        }
    }
}

void siftup(int i){ //传入一个需要向上调整的结点编号i
    int flag = 0; //用来标记是否需要继续向上调整
    if(i == 1) return; //如果是堆顶，就返回，不需要调整了
    //不在堆顶，并且当前结点i的值比父结点小的时候继续向上调整
    while(i != 1 && flag == 0){
        //判断是否比父结点的小
        if(dis[h[i]] < dis[h[i / 2]]){
            swap(i,i/2); //交换它和它爸爸的位置
        }else{
            flag == 1; //表示已经不需要调整了，当前结点的值比父结点的值要大
        }
        i = i / 2;//更新编号i为它父结点的编号，从而便于下一次继续向上调整
    }
}

//从堆顶取出一个元素
int pop(){
    int t;
    t = h[1]; //用一个临时变量记录堆顶点的位置
    pos[t] = 0; 
    h[1] = h[size]; //将堆的最后一个点赋值到堆顶
    pos[h[1]] = 1;
    size--; //堆的元素减少1
    siftdown(1); //向下调整
    return t; //返回之前记录的堆顶点
}

int main(){
    int n,m,i,j,k;
    //u、v、w和next的数组大小要根据实际情况来设置，此图是无向图，要比2*m的最大值要大1
    //first要比n的最大值要大1，要比2*m的最大值要大1
    int u[19],v[19],w[19],first[7],next[19];
    int inf = 99999999; //用inf(infinity的缩写)存储一个我们认为的正无穷值
    int count = 0,sum = 0; //count用来记录生成树中顶点的个数，sum用来存储路径之和

    //读入n和m，n表示顶点个数，m表示边的条数
    scanf("%d %d",&n,&m);

    //开始读入边
    for(i = 1;i <= m; i++){
        scanf("%d %d %d",&u[i],&v[i],&w[i]);
    }

    //这里是无向图，所以需要将所有的边再反向存储一次
    for(i = m + 1; i <= 2 * m; i++){
        u[i] = v[i - m];
        v[i] = u[i - m];
        w[i] = w[i - m];
    }

    //开始使用邻接表存储边
    for(i = 1;i <= n; i++){
        first[i] = -1;
    }

    for(i = 1;i <= 2 * m; i++){
        next[i] = first[u[i]];
        first[u[i]] = i;
    }

    //Prim核心部分开始
    //将1号顶点加入生成树
    book[1] = 1; //这里用book来标记一个顶点已经加入生成树
    count++;
    
    //初始化dis数组，这里是1号顶点到其余各个顶点的初始距离
    dis[1] = 0;
    for(i = 2;i <= n; i++){
        dis[i] = inf;
    }
    k = first[1];
    while(k != -1){
        dis[v[k]] = w[k];
        k = next[k];
    }
    //初始化堆
    size = n;
    for(i = 1;i <= size; i++){
        h[i] = i;
        pos[i] = i;
    }
    for(i = size / 2;i >= 1; i--){
        siftdown(i);
    }
    pop(); //先弹出一个堆顶元素，因为此时堆顶是1号顶点
    while(count < n){
        j = pop();
        book[j] = 1;
        count++;
        sum = sum + dis[j];

        //扫描当前顶点j所有的边，再以j为中间结点，进行松弛
        k = first[j];
        while(k != -1){
            if(book[v[k]] == 0 && dis[v[k]] > w[k]){
                dis[v[k]] = w[k]; //更新距离
                siftup(pos[v[k]]); //对该点在堆中进行向上调整
                //提示:pos[v[k]]存储的是顶点v[k]在堆中的位置
            }
            k = next[k];
        }
    }
    printf("%d",sum); 

    system("pause");
    return 0;
}
~~~

Kruskal 算法是一步步地将森林中的树进行合并，而 Prim 算法则是通过每次增加一条边来建立一棵树。Kruskal 算法更适用于稀疏图，没有使用堆优化的 Prim 算法适用于稠密图，使用了堆优化的 Prim 算法则更适用于稀疏图。
