package hello;

import java.util.Random;

public class Exercise_Sheet {
	//一行输出num个算式
	void formula_Print(Exercise02 test,int num) {
		test.cur = 0;
		int number = 1,now = 1;
		Formula02 temp;
		while(test.has_Next()) {
			temp = test.get_Now();
			System.out.printf("%3d.%3d %c%3d = ? ",now,temp.left,temp.sign,temp.right);
			if(number % num == 0) {
				number = 1;
				System.out.println("");
			}
			else {
				number++;
			}
			now++;
		}
	}
	//一行输出num个算式答案
	void formula_Answer(Exercise02 test,int num) {
		test.cur = 0;
		int number = 1,now = 1;
		Formula02 temp;
		while(test.has_Next()) {
			temp = test.get_Now();
			System.out.printf("%3d.%3d %c%3d =%3d ",now,temp.left,temp.sign,temp.right,temp.ans);
			if(number % num == 0) {
				number = 1;
				System.out.println("");
			}
			else {
				number++;
			}
			now++;
		}
	}
	//测试
	public static void main(String[] args) {
//		sheet.formula_Print(exercise, 5);
//		System.out.println("");
//		sheet.formula_Answer(exercise, 3);
	}
}
