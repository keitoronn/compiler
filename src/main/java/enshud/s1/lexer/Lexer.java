package enshud.s1.lexer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
public class Lexer {

	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// normalの確認
		new Lexer().run("data/pas/normal01.pas", "tmp/out1.ts");
		new Lexer().run("data/pas/normal02.pas", "tmp/out2.ts");
		new Lexer().run("data/pas/normal03.pas", "tmp/out3.ts");
	}

	/**
	 * TODO
	 *
	 * 開発対象となるLexer実行メソッド．
	 * 以下の仕様を満たすこと．
	 *
	 * 仕様:
	 * 第一引数で指定されたpasファイルを読み込み，トークン列に分割する．
	 * トークン列は第二引数で指定されたtsファイルに書き出すこと．
	 * 正常に処理が終了した場合は標準出力に"OK"を，
	 * 入力ファイルが見つからない場合は標準エラーに"File not found"と出力して終了すること．
	 *
	 * @param inputFileName 入力pasファイル名
	 * @param outputFileName 出力tsファイル名
	 */
	String[] bunri = {"=","<>","<=","<",">=",">",":=","+","-","*","(",")","[","]",";",":","..",",","."};
	String[] Jiku = {"and","array","begin","boolean","char","div","do","else","end","false","if","integer","mod","not","of","or","procedure","program","readln","then","true","var","while","writeln","=","<>","<","<=",">=",">","+","-","*","(",")","[","]",";",":","..",":=",",","."};
	String[] Token = {"SAND","SARRAY","SBEGIN","SBOOLEAN","SCHAR","SDIVD","SDO","SELSE","SEND","SFALSE","SIF","SINTEGER","SMOD","SNOT","SOF","SOR","SPROCEDURE","SPROGRAM","SREADLN","STHEN","STRUE","SVAR","SWHILE","SWRITELN","SEQUAL","SNOTEQUAL","SLESS","SLESSEQUAL","SGREATEQUAL","SGREAT","SPLUS","SMINUS","SSTAR","SLPAREN","SRPAREN","SLBRACKET","SRBRACKET","SSEMICOLON","SCOLON","SRANGE","SASSIGN","SCOMMA","SDOT","SIDENTIFIER","SCONSTANT","SSTRING"};

	public int mojiitti(String s) {
		int aide=-1;
		String slash="/";
		for(int i=0;i<=23;i++) {

			if(Objects.equals(s, Jiku[i])) {
				aide=i;
			}
		}
		if(aide==-1) {
			if(s.equals(slash)) {
				aide=5;
			}
			else if(s.matches("[+-]?\\d*(\\.\\d+)?")) {
				aide=44;
			}
			else {
				aide=43;
			}
		}
		return aide;
	}
	public void kakikomi(String bun,String j) {
		/*try {
			FileWriter file = new FileWriter(j);
			PrintWriter pw = new PrintWriter(new BufferedWriter(file));
			pw.println(bun);
			pw.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}*/

	}
	public void run(final String inputFileName, final String outputFileName) {

		// TODO

		Path pa1 = Paths.get(inputFileName);
		Path pa2 = Paths.get(outputFileName);

		//if(Files.exists(pa2)) {
		if(Files.exists(pa1)){
			try {
				int posi=0;
				char curmoji;
				char nexmoji;
				int commentcheck=0;
				int quotecheck=0;

				String oritext = Files.readString(pa1);

				try(BufferedReader br = new BufferedReader(new FileReader(inputFileName)))
				{
					try {
						FileWriter file = new FileWriter(outputFileName);
						PrintWriter pw = new PrintWriter(new BufferedWriter(file));
						String line;
						int linecount=0;
						while ((line = br.readLine()) != null) {
							linecount++;
							char[] c = line.toCharArray();
							int n=0;
							int m=0;
							String tan ="";
							String mojire="";
							int mojisuu=line.length();
							int mojiretu=0;
							int comout=0;
							String syuturyoku="";
							String space="	";
							while(n<mojisuu) {
								int ID=-1;
								int IDtan=-1;
								if(comout==0) {
									if(mojiretu==0) {
										if(c[n]=='{') {
											comout=1;
											n++;
										}
										else if(c[n]=='\'') {
											mojiretu=1;
											mojire="";
											mojire=mojire+c[n];
											n++;

										}

										else if(c[n]==' ') {
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											tan ="";
											n++;

										}
										else if(c[n]=='<') {
											n++;
											if(n<mojisuu) {
												if(c[n]=='>') {
													n++;
													ID=25;
												}else if(c[n]=='=') {
													n++;
													ID=27;
												}else {
													ID=26;
												}
											}else {
												ID=26;
											}

											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;

											pw.println(syuturyoku);

											tan ="";

										}
										else if(c[n]=='>') {
											n++;
											if(n<mojisuu) {
												if(c[n]=='=') {
													n++;
													ID=28;
												}else {
													ID=29;
												}
											}else {
												ID=29;
											}
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}

											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]==':') {
											n++;
											if(n<mojisuu) {
												if(c[n]=='=') {
													n++;
													ID=40;
												}else {
													ID=38;
												}
											}else {
												ID=38;
											}
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;

											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='.') {
											n++;
											if(n<mojisuu) {
												if(c[n]=='.') {
													n++;
													ID=39;
												}else {
													ID=42;
												}
											}else {
												ID=42;
											}
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='+') {
											n++;
											ID=30;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;

											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='-') {
											n++;
											ID=31;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='*') {
											n++;
											ID=32;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='(') {
											n++;
											ID=33;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]==')') {
											n++;
											ID=34;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='[') {
											n++;
											ID=35;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]==']') {
											n++;
											ID=36;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]==';') {
											n++;
											ID=37;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]==',') {
											n++;
											ID=41;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='=') {
											n++;
											ID=24;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku=Jiku[ID]+space+Token[ID]+space+ID+space+linecount;

											pw.println(syuturyoku);
											tan ="";
										}
										else if(c[n]=='/') {
											n++;
											ID=5;
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
											syuturyoku="/"+space+Token[ID]+space+ID+space+linecount;


											pw.println(syuturyoku);
											tan ="";
										}
										else {
											tan=tan+c[n];
											n++;
										}
										if(n>=mojisuu) {
											if(tan!="") {
												IDtan=mojiitti(tan);
												syuturyoku=tan+space+Token[IDtan]+space+IDtan+space+linecount;

												pw.println(syuturyoku);

											}
										}
									}
									else {
										mojire=mojire+c[n];
										if(c[n]=='\'') {
											mojiretu=0;
											syuturyoku=mojire+space+Token[45]+space+"45"+space+linecount;

											pw.println(syuturyoku);
										}
										n++;

									}
								}else {
									if(c[n]=='}') {
										comout=0;
									}
									n++;
								}
							}


						}
						pw.close();
						System.out.println("OK");

					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
						System.out.println("File not found");
					}

				}
				catch (IOException e) {
					System.out.println("File not found");
					e.printStackTrace();
				}
				/* while(true) {
						 posi++;
						 if(posi>oritext.length()) {
							 break;
						 }else {
							 curmoji=ori[posi];
						 }
						 if(posi<=oritext.length()-1) {
							 nexmoji=ori[posi+1];
						 }
						 if(curmoji=='{') {
							 commentcheck=1;
						 }
						 if(commentcheck==1) {
							 String nstr = String.valueOf(curmoji);
							 tango=tango+nstr;
							 if(curmoji=='}') {
								 commentcheck=0;
								 System.out.println(tango);
								 tango=new String();
							 }
						 }
						 else{
							 if(curmoji=='\''&&quotecheck==0) {
								 quotecheck=1;
								 String nstr = String.valueOf(curmoji);
								 tango=tango+nstr;
							 }
							 else if(quotecheck==1) {
								 String nstr = String.valueOf(curmoji);
								 tango=tango+nstr;

								 if(curmoji=='\'') {
									 quotecheck=0;
									 System.out.println(tango);
									 tango=new String();
								 }
							 }
							 else {
								 if(curmoji=='a'&&ori[posi+1]=='n'&&ori[posi+2]=='d') {
									 if(ori[posi+4]==' ') {
										 tango = "and";
										 posi=posi+4;
										 System.out.println(tango);
										 tango=new String();
									 }
								 }
								 else if(curmoji=='p'&&ori[posi+1]=='r'&&ori[posi+2]=='o'&&ori[posi+3]=='g'&&ori[posi+4]=='r'&&ori[posi+5]=='a'&&ori[posi+6]=='m') {
									 if(ori[posi+7]!='.') {
										 tango="position";
										 posi=posi+7;
										 System.out.println(tango);
										 tango=new String();
									 }else if(ori[posi+7]==' '&&ori[posi+7]=='	') {
										 tango="position";
										 posi=posi+7;
										 System.out.println(tango);
										 tango=new String();

									 }

									 System.out.println("bduyige");
								 }
							 }

						 }
					 }*/



			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				System.out.println("File not found");
			}
		}else{
			System.err.println("File not found");

		}
		//}else {
		//System.err.println("File not found");

		//}

	}
}
