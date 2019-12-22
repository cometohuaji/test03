package hello;

import java.util.Random;

abstract class Formula02 {
	int Max = 100,Min = 0;
	int left,right,ans = 0;
	char sign = '+';
	//构造算式
	protected void create(char f_Sign) {
		Random rand = new Random();
		left = rand.nextInt(Max+1);
		right = rand.nextInt(Max+1);
		ans = get_Answer(left ,right);
		while(check(ans) == false) {
			left = rand.nextInt(Max+1);
			right = rand.nextInt(Max+1);
			ans = get_Answer(left ,right);
		}
		sign = f_Sign;
	}
	//子类中的结果判断
	abstract boolean check(int answer);
	abstract int get_Answer(int f_Left,int f_Right);
	//相等判断
	boolean equals(Formula02 test) {
		if(test.sign == sign) {
			return test.left == left && test.right == right ;
		}
		else return false;
	}
}

class create_Add extends Formula02 {
	//创建加法
	create_Add(){
		create('+');
	}
	//重写判断
	boolean check(int answer) {
		if(answer > Max)return false ;
		else return true ;
	}
	int get_Answer(int f_Left, int f_Right) {
		return f_Left + f_Right ;
	}
}

class create_Minus extends Formula02 {
	//创建减法
	create_Minus(){
		create('-');
	}
	//重写判断
	boolean check(int answer) {
		if(answer < Min)return false ;
		else return true ;
	}
	int get_Answer(int f_Left, int f_Right) {
		return f_Left - f_Right ;
	}
	public static void main(String[] args) {
		System.out.println();
	}
}