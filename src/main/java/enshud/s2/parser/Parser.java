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
	}
	String[] jiku = new String[2048];
	String[] tokenname = new String[2048];
	int[] tokenID = new int[2048];
	int[] gyou = new int[2048];
	int n=0;
	int err=0;
	int tokensu=0;
	int pointsuu=0;
	point[] pinfo = new point[10000];

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
		
		programName();
		if (!(jiku[n].equals(";"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++; // プログラム名の次に ";" が出てこなければ構文エラー
		}
		n++;
		block();            // 別EBNFメソッド
		complexStatement(); // 別EBNFメソッド

		if (!(jiku[n].equals("."))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;

	}


	void programName() {
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}


	void block() {
		hensusengen();
		hukuprogramsengengun();
	}


	void complexStatement() {

		if (!(jiku[n].equals("begin"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;



		//System.out.println(tokenname[n]);



		bunnnonarabi();
		if (!(jiku[n].equals("end"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;

	}


	void hensusengen() {
		if ((jiku[n].equals("var"))) {
			n++;
			hensusengennarabi();
		}
	}


	void hukuprogramsengengun() {
		if ((jiku[n].equals("procedure"))) {
			while(true) {
				hukuprogramsengen();
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


	void hensusengennarabi() {
		while(true) {
			hensuumeinarabi();
			if (!(jiku[n].equals(":"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			kata();
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


	void hukuprogramsengen() {
		hukuprogramtoubu();
		hensusengen();
		complexStatement();
	}


	void hensuumeinarabi() {
		hensuumei();
		if((jiku[n].equals(","))) {
			while(true) {
				if(!(jiku[n].equals(","))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				hensuumei();
				if(!(jiku[n].equals(","))) {
					break;
				}
			}
		}
	}


	void kata() {
		if ((jiku[n].equals("integer"))||(jiku[n].equals("char"))||(jiku[n].equals("boolean"))) {
			n++;
		}else {
			hairetugata();
		}
	}


	void hensuumei() {
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}


	void hairetugata() {
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
		seisuu();
		if (!(jiku[n].equals(".."))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		seisuu();
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
		hyoujungata();
	}


	void seisuu() {
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

	void hyoujungata() {

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


	void hukuprogramtoubu() {
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
		kariparameter();
		if (!(jiku[n].equals(";"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
	}


	void kariparameter(){
		if ((jiku[n].equals("("))) {
			n++;
			kariparameterorder();
			if (!(jiku[n].equals(")"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;

		}
	}


	void kariparameterorder(){
		kariparameternameorder();
		if (!(jiku[n].equals(":"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		hyoujungata();
		if(jiku[n].equals(";")) {
			while(true) {
				if (!(jiku[n].equals(";"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				kariparameternameorder();
				if (!(jiku[n].equals(":"))) {
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				hyoujungata();
				if (!(jiku[n].equals(";"))) {
					break;
				}
			}
		}
	}


	void kariparameternameorder() {
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


	void bunnnonarabi() {
		bun();
		if (!(jiku[n].equals(";"))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if (!(jiku[n].equals("end"))) {
			while(true) {
				bun();
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


	void bun(){
		//System.out.println(tokenname[n]);
		if ((jiku[n].equals("if"))) {
			n++;
			siki();
			if (!(jiku[n].equals("then"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			complexStatement();
			if((jiku[n].equals("else"))) {
				n++;
				complexStatement();
			}
		}else if(jiku[n].equals("while")) {
			n++;
			siki();
			if (!(jiku[n].equals("do"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
			complexStatement();
		}else {
			kihonbun();
		}
	}


	void kihonbun() {
		if((tokenname[n].equals("SIDENTIFIER"))) {
			if(jiku[n+1].equals(":=")) {
				dainyuubun();
			}else if(jiku[n+1].equals("[")) {
				dainyuubun();
			}else {
				tetudukiyobidasi();
			}
		}else if((jiku[n].equals("readln"))||(jiku[n].equals("writeln"))) {
			nyuusyuturyokubun();
		}else if(jiku[n].equals("begin")) {
			complexStatement();
		}else {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;

		}
	}

	void dainyuubun() {
		sahen();
		if (!(jiku[n].equals(":="))) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		siki();
	}


	void sahen() {
		hensuu();
	}


	void hensuu() {
		if(!tokenname[n].equals("SIDENTIFIER")) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if(jiku[n].equals("[")) {
			n++;
			soeji();
			if (!(jiku[n].equals("]"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
		}
	}


	void soeji() {
		siki();
	}


	void tetudukiyobidasi() {
		if(!tokenname[n].equals("SIDENTIFIER")) {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}
		n++;
		if(jiku[n].equals("(")) {
			n++;
			sikinonarabi();
			if (!(jiku[n].equals(")"))) {
				if(err==0) {
					System.err.println("Syntax error: line "+gyou[n]);
				}
				err++;
			}
			n++;
		}

	}

	void sikinonarabi() {
		siki();
		if(jiku[n].equals(",")) {
			while(true) {
				if(!(jiku[n].equals(","))){
					if(err==0) {
						System.err.println("Syntax error: line "+gyou[n]);
					}
					err++;
				}
				n++;
				siki();

				if(!(jiku[n].equals(","))){
					break;
				}
			}
		}
	}


	void siki() {
		tanjunsiki();
		if((jiku[n].equals("="))||(jiku[n].equals("<>"))||(jiku[n].equals("<"))||(jiku[n].equals("<="))||(jiku[n].equals(">"))||(jiku[n].equals(">="))) {
			n++;
			tanjunsiki();
		}
	}


	void tanjunsiki() {
		if((jiku[n].equals("+"))||(jiku[n].equals("-"))) {
			n++;
		}
		kou();
		if((jiku[n].equals("+"))||(jiku[n].equals("-"))||(jiku[n].equals("or"))) {
			while(true) {
				n++;
				kou();
				if(!((jiku[n].equals("+"))||(jiku[n].equals("-"))||(jiku[n].equals("or")))) {
					break;
				}
			}
		}
	}

	void kou() {
		insi();
		if((jiku[n].equals("*"))||(jiku[n].equals("/"))||(jiku[n].equals("div"))||(jiku[n].equals("mod"))||(jiku[n].equals("and"))) {
			while(true) {
				n++;
				insi();
				if(!((jiku[n].equals("*"))||(jiku[n].equals("/"))||(jiku[n].equals("div"))||(jiku[n].equals("mod"))||(jiku[n].equals("and")))) {
					break;
				}
			}
		}
	}


	void insi() {
		if(tokenname[n].equals("SIDENTIFIER")) {
			hensuu();
		}else if(jiku[n].equals("(")) {
			n++;
			siki();
			if (!(jiku[n].equals(")"))) {
				System.out.println("insi1err");
			}
			n++;
		}else if(jiku[n].equals("not")) {
			n++;
			insi();
		}else {
			teisuu();
		}
	}


	void nyuusyuturyokubun() {
		if(jiku[n].equals("readln")) {
			n++;
			if(jiku[n].equals("(")) {
				n++;
				hensuunonarabi();
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
				sikinonarabi();
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


	void hensuunonarabi() {
		hensuu();
		if(jiku[n].equals(",")) {
			while(true) {
				n++;
				hensuu();
				if(!(jiku[n].equals(","))) {
					break;
				}
			}
		}
	}

	void teisuu() {
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

				program();
				if(err==0) {
				System.out.println("OK");
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
