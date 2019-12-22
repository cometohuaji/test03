package hello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.*;  

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class textcsv {
	private Formula02 te;
	//判断上次是否有题未做完
	int have_LastFile() {
		String pathCSV= "E:\\test\\practice\\practice.csv";
		ArrayList<String[]> lstFile = new ArrayList<String[]>();
		try {
			BufferedReader bufr = new BufferedReader(new FileReader("E:\\test\\practice\\practice.csv"));
			if((bufr.readLine()) == null) {
				System.out.println("当前没有未做完的试题，从题库中抽取一套试题");
				return 0;
			}
			else {
				System.out.println("当前有未做完的题，继续上次的练习。");
				return 1;
			}
		} catch (IOException  e) {
			e.printStackTrace();
		}
		return 0;
	}
	//得到上次未做完的题
	Exercise02 get_LastFile() {
		Exercise02 temp = new Exercise02();
		try {
            BufferedReader bufr = new BufferedReader(new FileReader("E:\\test\\practice\\practice.csv"));
			String line = null; 
			while((line = bufr.readLine())!= null) {
				String[] s=line.split(",");
				int s1=Integer.parseInt(s[0]),s2=Integer.parseInt(s[1]);
				if(s[2] == "+") {
					create_Add te = new create_Add();
					te.sign = '+';
					te.left = s1;
					te.right = s2;
					te.ans = s1+s2;
					temp.formula_List.add(te);
				}
				else {
					create_Minus te = new create_Minus();
					te.sign = '-';
					te.left = s1;
					te.right = s2;
					te.ans = s1-s2;
					temp.formula_List.add(te);
				}
				//temp.formula_List.add(te);
			}
			bufr.close();
			File file =new File("E:\\test\\practice\\practice.csv");
	        file.delete();
	        file.createNewFile();
			return temp ;
		} catch (IOException  e) {
			e.printStackTrace();
		}
		return temp;
	}
	//得到新的试题
	Exercise02 get_NewFile(String type,int number) {
		Exercise02 temp = new Exercise02();
		String pathCSV = "E:\\test\\exercise\\";
		pathCSV+=(type+"_exercise"+number+".csv");
		try {
            BufferedReader bufr = new BufferedReader(new FileReader(pathCSV));
			String line = null; 
			int num = 0;
			while((line = bufr.readLine())!= null) {
				String[] s=line.split(",");
				if(num == 0) {
					num = 1;
					continue ;
				}
				int s1=Integer.parseInt(s[0]),s2=Integer.parseInt(s[1]);
				if(s[2] == "+") {
					create_Add te = new create_Add();
					te.sign = '+';
					te.left = s1;
					te.right = s2;
					te.ans = s1+s2;
					temp.formula_List.add(te);
				}
				else {
					create_Minus te = new create_Minus();
					te.sign = '-';
					te.left = s1;
					te.right = s2;
					te.ans = s1-s2;
					temp.formula_List.add(te);
				}
				//temp.formula_List.add(te);
			}
			bufr.close();
			return temp ;
		} catch (IOException  e) {
			e.printStackTrace();
		}
		return temp;
	}
	//输出到文件中type为类型，number为编号
	void print_Exercise(Exercise02 temp, String type, int number) {
		String pathCSVWrite = "E:\\test\\exercise\\";
		pathCSVWrite+=(type+"_exercise"+number+".csv");
		try{
			CsvWriter csvWriter = new CsvWriter(pathCSVWrite,',',Charset.forName("gb2312"));
			String[] csvHeader = {"a","b","类型"};
			csvWriter.writeRecord(csvHeader);
			for(int i=0;i<temp.formula_List.size();i++){
				String[] csvContent = new String[5];
				Formula02 tp = temp.formula_List.get(i);
				csvContent[0] = (tp.left+"");
				csvContent[1] = (tp.right+"");
				csvContent[2] = (tp.sign+"");
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	//记录未做完的试题
	void print_HasExercise(Exercise02 te,int number) {
		String pathCSVWrite = "E:\\test\\practice\\practice.csv";
		try{
			CsvWriter csvWriter = new CsvWriter(pathCSVWrite,',',Charset.forName("gb2312"));
			for(int i=number;i<te.formula_List.size();i++){
				String[] csvContent = new String[5];
				Formula02 tp = te.formula_List.get(i);
				csvContent[0] = (tp.left+"");
				csvContent[1] = (tp.right+"");
				csvContent[2] = (tp.sign+"");
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	//输出批改结果
	void print_Answer(Exercise02 temp,int[] num,int t) {
		String pathCSVWrite = "E:\\test\\judge\\judge.csv";
		try{
			CsvWriter csvWriter = new CsvWriter(pathCSVWrite,',',Charset.forName("gb2312"));
			String[] csvHeader = {"a","  ","b"," = ","正确结果","回答结果"};
			csvWriter.writeRecord(csvHeader);
			for(int i=0;i<t;i++){
				String[] csvContent = new String[5];
				Formula02 tp = temp.formula_List.get(i);
				csvContent[0] = (tp.left+"");
				csvContent[1] = (tp.sign+"");
				csvContent[2] = (tp.right+"");
				csvContent[3] = (tp.ans+"");
				csvContent[4] = (num[i]+"");
				csvWriter.writeRecord(csvContent);
			}
			csvWriter.close();
			System.out.println("---已完成读写操作---");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	//新建习题库
	void create_P() {
		textcsv f = new textcsv();
		for(int i = 0; i <50 ;i++) {
			Exercise02 exercise = new Exercise02();
			exercise.create_MixFormula(50);
			f.print_Exercise(exercise, "mix", i+1);
		}
		for(int i = 0; i <50 ;i++) {
			Exercise02 exercise = new Exercise02();
			exercise.create_AddFormula(50);
			f.print_Exercise(exercise, "add", i+1);
		}
		for(int i = 0; i <50 ;i++) {
			Exercise02 exercise = new Exercise02();
			exercise.create_MinusFormula(50);
			f.print_Exercise(exercise, "minus", i+1);
		}
	}
	public static void main(String[] args) {
		textcsv f = new textcsv();
		Exercise02 now = new Exercise02();
		f.create_P();
		if(f.have_LastFile() == 0) {
			int s1,s2;
			String type;
			Random rand = new Random();
			s1 = rand.nextInt(3);
			s2 = rand.nextInt(15)+1;
			if(s1 == 0) {
				type = "mix";
			}
			else if(s1 == 1) {
				type = "add";
			}
			else {
				type = "minus";
			}
			now = f.get_NewFile(type,s2);
		}
		else {
			now = f.get_LastFile();
		}
		Scanner in = new Scanner(System.in); 
		int[] Ans = new int[100];
		int tt = 0;
		System.out.println("输入666退出答题");
		for(int i = 0;i < now.formula_List.size(); i++) {
			Formula02 temp = now.formula_List.get(i);
			System.out.printf("%3d.%3d %c%3d =  ? ",i+1,temp.left,temp.sign,temp.right);
			Ans[i] = in.nextInt();
			if(Ans[i] == 666) {
				f.print_HasExercise(now,tt);
				break ;
			}
			else {
				tt++;
			}
		}
		f.print_Answer(now,Ans,tt);
	}
}
