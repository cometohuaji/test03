package hello;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import com.csvreader.CsvWriter;

public class Exercise02 {
	ArrayList<Formula02> formula_List = new ArrayList<Formula02>();
	int cur = 0;
	//新增num个混合算式
	void create_MixFormula(int num) {
		Random rand = new Random();
		Formula02 formula;
		int flag ;
		for(int i = 1;i <= num ;i++) {
			flag = rand.nextInt(2);
			if(flag == 1) {
				create_Add temp = new create_Add();
				temp.create('+');
				while(formula_List.contains(temp)) {
					temp.create('+');
				}
				formula = temp;
			}
			else {
				create_Minus temp = new create_Minus();
				temp.create('-');
				while(formula_List.contains(temp)) {
					temp.create('-');
				}
				formula = temp;
			}
			formula_List.add(formula);
		}
	}
	//新增num个加法算式
	void create_AddFormula(int num) {
		Random rand = new Random();
		Formula02 formula;
		for(int i = 1;i <= num ;i++) {
			create_Add temp = new create_Add();
			temp.create('+');
			while(formula_List.contains(temp)) {
				temp.create('+');
			}
			formula = temp;
			formula_List.add(formula);
		}
	}
	//新增num个减法算式
		void create_MinusFormula(int num) {
			Random rand = new Random();
			Formula02 formula;
			for(int i = 1;i <= num ;i++) {
				create_Minus temp = new create_Minus();
				temp.create('-');
				while(formula_List.contains(temp)) {
					temp.create('-');
				}
				formula = temp;
				formula_List.add(formula);
			}
		}
	//判断是否到结尾
	boolean has_Next() {
		return cur <= formula_List.size()-1 ;
	}
	//返回当前位置formula
	public Formula02 get_Now() {
		return formula_List.get(cur++);
	}
}
