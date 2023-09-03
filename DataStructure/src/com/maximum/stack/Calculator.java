package com.maximum.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "70*2*2-5+1-5+3-4";
        ArrayStack1 numStack = new ArrayStack1(10);
        ArrayStack1 operStack = new ArrayStack1(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";

        while(true){
            ch = expression.substring(index, index + 1).charAt(0);
            if(operStack.isOper(ch)){ //如果是运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop两个数
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);

                        //把运算的结果入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }else{
                //如果是数，就直接入栈
                //分析思路:
                //1.当处理多位数时,不能发现是一个数就立即入栈，因为他可能是多位数
                //2.在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                keepNum += ch;

                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一位字符是不是数字
                    if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }

            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行
        while(true){
            //如果符号栈为空,则计算到最后的结果,数栈中只有一个数字[结果]
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        //将数栈的最后数,pop出,就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }
}

class ArrayStack1{
    private int maxSize;
    private int[] stack;
    private int top = -1;//top表示栈顶，初始化为-1

    public ArrayStack1(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法,可以返回当前栈顶的值,但是不是真正的pop
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈-push
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空,没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况,遍历时,需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空,没有数据~");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("Stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级,优先级用数字表示
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        } else if(oper == '+' || oper == '-') {
            return 0;
        } else{
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0; //res 用于存放计算的结果
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}