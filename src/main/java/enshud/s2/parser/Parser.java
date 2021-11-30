package enshud.s2.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Parser {

	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// normalの確認
		new Parser().run("data/ts/normal01.ts");
		new Parser().run("data/ts/normal02.ts");

		// synerrの確認
		new Parser().run("data/ts/synerr01.ts");
		new Parser().run("data/ts/synerr02.ts");
	}

	/**
	 * TODO
	 *
	 * 開発対象となるParser実行メソッド．
	 * 以下の仕様を満たすこと．
	 *
	 * 仕様:
	 * 第一引数で指定されたtsファイルを読み込み，構文解析を行う．
	 * 構文が正しい場合は標準出力に"OK"を，正しくない場合は"Syntax error: line"という文字列とともに，
	 * 最初のエラーを見つけた行の番号を標準エラーに出力すること （例: "Syntax error: line 1"）．
	 * 入力ファイル内に複数のエラーが含まれる場合は，最初に見つけたエラーのみを出力すること．
	 * 入力ファイルが見つからない場合は標準エラーに"File not found"と出力して終了すること．
	 *
	 * @param inputFileName 入力tsファイル名
	 */

	class point {
		String teigi;
		int number;
		int kokazu;
		int[] ko = new int[1024];
		point() {
			this.teigi="";
			this.number=0;
			this.kokazu=0;
			for(int i=0;i<1024;i++) {
				this.ko[i]=0;
			}
		}
	}
	String[] jiku = new String[2048];
	String[] tokenname = new String[2048];
	int[] tokenID = new int[2048];
	int[] gyou = new int[2048];
	int n=0;
	int err=0;
	int tokensu=0;
	int pointsuu=0;
	point[] pinfo = new point[100000];

	void program() {
		int a = pointsuu;
		pinfo[a].teigi = "program";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pointsuu++;
		if (!(jiku[n].equals("program"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
			// まず "program" という文字が出てこなければ構文エラー
		}
		n++;

		programName(a);
		if (!(jiku[n].equals(";"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++; // プログラム名の次に ";" が出てこなければ構文エラー
		}
		n++;
		block(a);            // 別EBNFメソッド
		complexStatement(a); // 別EBNFメソッド

		if (!(jiku[n].equals("."))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;

	}


	void programName(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "programName";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}


	void block(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "block";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		hensusengen(a);
		hukuprogramsengengun(a);
	}


	void complexStatement(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "complexStatement";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;

		if (!(jiku[n].equals("begin"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;



		//System.out.println(tokenname[n]);



		bunnnonarabi(a);
		if (!(jiku[n].equals("end"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;

	}


	void hensusengen(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensusengen";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if ((jiku[n].equals("var"))) {
			n++;
			hensusengennarabi(a);
		}
	}


	void hukuprogramsengengun(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hukuprogramsengen";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if ((jiku[n].equals("procedure"))) {
			while(true) {
				hukuprogramsengen(a);
				if (!(jiku[n].equals(";"))) {
					System.out.println("procerr");
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				if (!(jiku[n].equals("procedure"))) {
					break;
				}
				if(n>=tokensu) {
					break;
				}
			}
		}
	}


	void hensusengennarabi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensusengennarabi";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		while(true) {
			hensuumeinarabi(a);
			if (!(jiku[n].equals(":"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			kata(a);
			if (!(jiku[n].equals(";"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			if (!(tokenname[n].equals("SIDENTIFIER"))) {
				break;
			}
		}
	}


	void hukuprogramsengen(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hukuprogramsengen";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		hukuprogramtoubu(a);
		hensusengen(a);
		complexStatement(a);
	}


	void hensuumeinarabi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuumeinarabi";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		hensuumei(a);
		if((jiku[n].equals(","))) {
			while(true) {
				if(!(jiku[n].equals(","))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				hensuumei(a);
				if(!(jiku[n].equals(","))) {
					break;
				}
			}
		}
	}


	void kata(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "kata";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if ((jiku[n].equals("integer"))) {
			int c = pointsuu;
			pinfo[c].teigi = "integer";
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
		}else if(jiku[n].equals("char")) {
			int c = pointsuu;
			pinfo[c].teigi = "char";
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
		}
		else if(jiku[n].equals("boolean")) {
			int c = pointsuu;
			pinfo[c].teigi = "boolean";
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
		}
		else {
			hairetugata(a);
		}
	}


	void hensuumei(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuumei";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}


	void hairetugata(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hairetugata";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if (!(jiku[n].equals("array"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if (!(jiku[n].equals("["))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		seisuu(a);
		if (!(jiku[n].equals(".."))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		seisuu(a);
		if (!(jiku[n].equals("]"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if (!(jiku[n].equals("of"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		hyoujungata(a);
	}


	void seisuu(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "seisuu";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(jiku[n].equals("+")) {
			n++;
		}else if(jiku[n].equals("-")) {
			n++;
		}

		if (!(tokenname[n].equals("SCONSTANT"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;

	}

	void hyoujungata(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hyoujungata";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;

		if(jiku[n].equals("integer")) {
			n++;
		}else if(jiku[n].equals("char")) {
			n++;
		}else if(jiku[n].equals("boolean")) {
			n++;
		}else {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
	}


	void hukuprogramtoubu(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hukuprogramtoubu";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if (!(jiku[n].equals("procedure"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		kariparameter(a);
		if (!(jiku[n].equals(";"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}


	void kariparameter(int b){
		int a = pointsuu;
		pinfo[a].teigi = "kariparameter";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if ((jiku[n].equals("("))) {
			n++;
			kariparameterorder(a);
			if (!(jiku[n].equals(")"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;

		}
	}


	void kariparameterorder(int b){
		int a = pointsuu;
		pinfo[a].teigi = "hensuumei";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		kariparameternameorder(a);
		if (!(jiku[n].equals(":"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		hyoujungata(a);
		if(jiku[n].equals(";")) {
			while(true) {
				if (!(jiku[n].equals(";"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				kariparameternameorder(a);
				if (!(jiku[n].equals(":"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				hyoujungata(a);
				if (!(jiku[n].equals(";"))) {
					break;
				}
			}
		}
	}


	void kariparameternameorder(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "kariparameternameorder";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if(jiku[n].equals(",")) {
			while(true) {
				if (!(jiku[n].equals(","))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				if (!(tokenname[n].equals("SIDENTIFIER"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				if (!(jiku[n].equals(","))) {
					break;
				}
			}
		}
	}


	void bunnnonarabi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "bunnnonarabi";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		bun(a);
		if (!(jiku[n].equals(";"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if (!(jiku[n].equals("end"))) {
			while(true) {
				bun(a);
				if (!(jiku[n].equals(";"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;

				if((jiku[n].equals("end"))) {
					break;
				}

				if(n>=tokensu) {
					break;
				}
			}
		}
	}


	void bun(int b){
		int a = pointsuu;
		pinfo[a].teigi = "bun";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		//System.out.println(tokenname[n]);
		if ((jiku[n].equals("if"))) {
			n++;
			siki(a);
			if (!(jiku[n].equals("then"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			complexStatement(a);
			if((jiku[n].equals("else"))) {
				n++;
				complexStatement(a);
			}
		}else if(jiku[n].equals("while")) {
			n++;
			siki(a);
			if (!(jiku[n].equals("do"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			complexStatement(a);
		}else {
			kihonbun(a);
		}
	}


	void kihonbun(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "kihonbun";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if((tokenname[n].equals("SIDENTIFIER"))) {
			if(jiku[n+1].equals(":=")) {
				dainyuubun(a);
			}else if(jiku[n+1].equals("[")) {
				dainyuubun(a);
			}else {
				tetudukiyobidasi(a);
			}
		}else if((jiku[n].equals("readln"))||(jiku[n].equals("writeln"))) {
			nyuusyuturyokubun(a);
		}else if(jiku[n].equals("begin")) {
			complexStatement(a);
		}else {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;

		}
	}

	void dainyuubun(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "dainyuubun";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		sahen(a);
		if (!(jiku[n].equals(":="))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		siki(a);
	}


	void sahen(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "sahen";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		hensuu(a);
	}


	void hensuu(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuu";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(!tokenname[n].equals("SIDENTIFIER")) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if(jiku[n].equals("[")) {
			n++;
			soeji(a);
			if (!(jiku[n].equals("]"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
		}
	}


	void soeji(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "soeji";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		siki(a);
	}


	void tetudukiyobidasi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "tetudukiyodobasi";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(!tokenname[n].equals("SIDENTIFIER")) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if(jiku[n].equals("(")) {
			n++;
			sikinonarabi(a);
			if (!(jiku[n].equals(")"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
		}

	}

	void sikinonarabi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "sikinonarabi";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		siki(a);
		if(jiku[n].equals(",")) {
			while(true) {
				if(!(jiku[n].equals(","))){
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				siki(a);

				if(!(jiku[n].equals(","))){
					break;
				}
			}
		}
	}


	void siki(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "siki";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		tanjunsiki(a);
		if((jiku[n].equals("="))||(jiku[n].equals("<>"))||(jiku[n].equals("<"))||(jiku[n].equals("<="))||(jiku[n].equals(">"))||(jiku[n].equals(">="))) {
			n++;
			tanjunsiki(a);
		}
	}


	void tanjunsiki(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "tanjunsiki";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if((jiku[n].equals("+"))||(jiku[n].equals("-"))) {
			n++;
		}
		kou(a);
		if((jiku[n].equals("+"))||(jiku[n].equals("-"))||(jiku[n].equals("or"))) {
			while(true) {
				n++;
				kou(a);
				if(!((jiku[n].equals("+"))||(jiku[n].equals("-"))||(jiku[n].equals("or")))) {
					break;
				}
			}
		}
	}

	void kou(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "kou";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		insi(a);
		if((jiku[n].equals("*"))||(jiku[n].equals("/"))||(jiku[n].equals("div"))||(jiku[n].equals("mod"))||(jiku[n].equals("and"))) {
			while(true) {
				n++;
				insi(a);
				if(!((jiku[n].equals("*"))||(jiku[n].equals("/"))||(jiku[n].equals("div"))||(jiku[n].equals("mod"))||(jiku[n].equals("and")))) {
					break;
				}
			}
		}
	}


	void insi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuumei";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(tokenname[n].equals("SIDENTIFIER")) {
			hensuu(a);
		}else if(jiku[n].equals("(")) {
			n++;
			siki(a);
			if (!(jiku[n].equals(")"))) {
				System.out.println("insi1err");
			}
			n++;
		}else if(jiku[n].equals("not")) {
			n++;
			insi(a);
		}else {
			teisuu(a);
		}
	}


	void nyuusyuturyokubun(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "nyuusyuturyokubun";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(jiku[n].equals("readln")) {
			n++;
			if(jiku[n].equals("(")) {
				n++;
				hensuunonarabi(a);
				if (!(jiku[n].equals(")"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
			}
		}else if(jiku[n].equals("writeln")) {
			n++;
			if(jiku[n].equals("(")) {
				n++;
				sikinonarabi(a);
				if (!(jiku[n].equals(")"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
			}

		}
	}


	void hensuunonarabi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuunonarabi";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		hensuu(a);
		if(jiku[n].equals(",")) {
			while(true) {
				n++;
				hensuu(a);
				if(!(jiku[n].equals(","))) {
					break;
				}
			}
		}
	}

	void teisuu(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "teisuu";
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(!((tokenname[n].equals("SCONSTANT"))||(tokenname[n].equals("SSTRING"))||(jiku[n].equals("false"))||(jiku[n].equals("true")))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}





	public void run(final String inputFileName) {
		for(int j=0;j<2048;j++) {
			jiku[j]="sioeeoiofewoefw";
			tokenname[j]="sioeeoiofewoefw";
		}
		int n=0;
		Path pa1 = Paths.get(inputFileName);

		if(Files.exists(pa1)){
			try(BufferedReader br = new BufferedReader(new FileReader(inputFileName)))
			{
				String line;
				while ((line = br.readLine()) != null) {
					//System.out.println(line);
					String[] itiji = line.split("	");
					jiku[tokensu]=itiji[0];
					tokenname[tokensu]=itiji[1];
					tokenID[tokensu]=Integer.parseInt(itiji[2]);
					gyou[tokensu]=Integer.parseInt(itiji[3]);

					tokensu++;
				}
				for(int s=0;s<100000;s++) {
					pinfo[s]=new point();
				}


				program();
				if(err==0) {
					System.out.println("OK");
					for(int s=0;s<pointsuu;s++) {
						System.out.println(pinfo[s].number);
						System.out.println(pinfo[s].teigi);
						System.out.println(pinfo[s].kokazu);
						System.out.println("ko");
						if(pinfo[s].kokazu>0) {
							for(int t=0;t<pinfo[s].kokazu;t++) {
								System.out.println(pinfo[s].ko[t]);
							}
						}
						System.out.println("\n");
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
				System.err.println("File not found");
			}
		}
		else{
			System.err.println("File not found");

		}

		// TODO


	}
	// TODO

}
