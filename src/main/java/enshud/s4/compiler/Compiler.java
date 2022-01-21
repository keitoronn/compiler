package enshud.s4.compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import enshud.casl.CaslSimulator;

public class Compiler {

	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// Compilerを実行してcasを生成する
		new Compiler().run("data/ts/normal16.ts", "tmp/out.cas");

		// 上記casを，CASLアセンブラ & COMETシミュレータで実行する
		CaslSimulator.run("tmp/out.cas", "tmp/out.ans");
	}

	/**
	 * TODO
	 *
	 * 開発対象となるCompiler実行メソッド．
	 * 以下の仕様を満たすこと．
	 *
	 * 仕様:
	 * 第一引数で指定されたtsファイルを読み込み，CASL IIプログラムにコンパイルする．
	 * コンパイル結果のCASL IIプログラムは第二引数で指定されたcasファイルに書き出すこと．
	 * 構文的もしくは意味的なエラーを発見した場合は標準エラーにエラーメッセージを出力すること．
	 * （エラーメッセージの内容はChecker.run()の出力に準じるものとする．）
	 * 入力ファイルが見つからない場合は標準エラーに"File not found"と出力して終了すること．
	 *
	 * @param inputFileName 入力tsファイル名
	 * @param outputFileName 出力casファイル名
	 */

	class point {
		String teigi;
		String goku;
		int number;
		int kokazu;
		int tnum;
		int[] ko = new int[1024];
		point() {
			this.teigi="";
			this.goku="";
			this.number=0;
			this.kokazu=0;
			this.tnum=0;
			for(int i=0;i<1024;i++) {
				this.ko[i]=0;
			}
		}
	}
	class hensuse{
		String[] namae = new String[1024];
		int[] tnum = new int[1024];
		String[] hyoujun = new String[1024];
		String[] kata = new String[1024];
		String[] mojiretusen = new String[1024];
		String[] kari=new String[1024];
		String varsen ;
		int[] bango = new int[1024];
		int[] yousosu = new int[1024];
		int[] mojiretunagasa = new int[1024];
		int hensukazu = 0;
		int hensumemo =0;
		int inthensukazu=0;
		int charhensukazu=0;
		int boolhensukazu=0;
		int mojiretukazu=0;
		int ifkazu=0;
		int whilekazu=0;
		int kankeikazu=0;
		String proname="";
		hensuse() {
			for(int i=0;i<1024;i++) {
				this.namae[i]="";
				this.hyoujun[i]="";
				this.mojiretusen[i]="";
				this.varsen="";
				this.hensukazu=0;
				this.hensumemo=0;
				this.mojiretukazu=0;
				this.kari[i]="";
				this.mojiretunagasa[i]=0;
				this.tnum[i]=0;
				this.kata[i]="";
				this.bango[i]=0;
				this.yousosu[i]=0;
				this.ifkazu=0;
				this.whilekazu=0;
				this.kankeikazu=0;
				this.proname="";
			}
		}
	}
	String[] jiku = new String[2048];
	String[] tokenname = new String[2048];
	int[] tokenID = new int[2048];
	int[] gyou = new int[2048];
	int ifkazu=0;
	int whilekazu=0;
	int kankeikazu;
	int n=0;
	int err=0;
	int tokensu=0;
	int pointsuu=0;
	int chou=0;
	int prosu=0;
	int imahuku=0;
	point[] pinfo = new point[100000];
	hensuse[] huku = new hensuse[100];
	String[] hensu =new String[1024];
	String[] hukuproname = new String[1024];
	String varsen="";
	int hukuprosu=0;
	int hensusu=0;
	int mojiretukazu=0;
	String mojiteigi="";
	String inthensumemo="";
	String cas="";
	String[] mojiretusen =new String[1024];
	int[] hukuprostuck= new int[256];
	int[] mojiretunagasa = new int[1024];
	void program() {
		int a = pointsuu;
		pinfo[a].teigi = "program";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].teigi = "hukuprogramsengengun";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if ((jiku[n].equals("integer"))) {
			hyoujungata(a);
		}else if(jiku[n].equals("char")) {
			hyoujungata(a);
		}
		else if(jiku[n].equals("boolean")) {
			hyoujungata(a);
		}
		else {
			hairetugata(a);
		}
	}


	void hensuumei(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuumei";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;

		if(jiku[n].equals("integer")) {
			int c = pointsuu;
			pinfo[c].teigi = "integer";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
		}else if(jiku[n].equals("char")) {
			int c = pointsuu;
			pinfo[c].teigi = "char";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
		}else if(jiku[n].equals("boolean")) {
			int c = pointsuu;
			pinfo[c].teigi = "boolean";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		hukuproname[hukuprosu]=jiku[n];
		hukuprosu++;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].teigi = "kariparameterorder";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		int c = pointsuu;
		pinfo[c].teigi = "karipara";
		pinfo[c].goku = jiku[n];
		pinfo[c].tnum = n;
		pinfo[c].number = pointsuu;
		pinfo[c].kokazu = 0;
		pinfo[a].ko[pinfo[a].kokazu]=c;
		pinfo[a].kokazu++;
		pointsuu++;
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
				int d = pointsuu;
				pinfo[d].teigi = "karipara";
				pinfo[d].goku = jiku[n];
				pinfo[d].tnum = n;
				pinfo[d].number = pointsuu;
				pinfo[d].kokazu = 0;
				pinfo[a].ko[pinfo[a].kokazu]=d;
				pinfo[a].kokazu++;
				pointsuu++;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		hensuumei(a);
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		siki(a);
	}


	void tetudukiyobidasi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "tetudukiyobidasi";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		tanjunsiki(a);
		if((jiku[n].equals("="))||(jiku[n].equals("<>"))||(jiku[n].equals("<"))||(jiku[n].equals("<="))||(jiku[n].equals(">"))||(jiku[n].equals(">="))) {

			int c = pointsuu;
			pinfo[c].teigi = "kankeienzansi";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
			tanjunsiki(a);
		}
	}


	void tanjunsiki(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "tanjunsiki";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if((jiku[n].equals("+"))||(jiku[n].equals("-"))) {
			int c = pointsuu;
			pinfo[c].teigi = "hugou";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;
			n++;
		}
		kou(a);
		if((jiku[n].equals("+"))||(jiku[n].equals("-"))||(jiku[n].equals("or"))) {
			while(true) {
				int c = pointsuu;
				pinfo[c].teigi = "kahouenzansi";
				pinfo[c].goku = jiku[n];
				pinfo[c].tnum = n;
				pinfo[c].number = pointsuu;
				pinfo[c].kokazu = 0;
				pinfo[a].ko[pinfo[a].kokazu]=c;
				pinfo[a].kokazu++;
				pointsuu++;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		insi(a);
		if((jiku[n].equals("*"))||(jiku[n].equals("/"))||(jiku[n].equals("div"))||(jiku[n].equals("mod"))||(jiku[n].equals("and"))) {
			while(true) {

				int c = pointsuu;
				pinfo[c].teigi = "jouhouenzansi";
				pinfo[c].goku = jiku[n];
				pinfo[c].tnum = n;
				pinfo[c].number = pointsuu;
				pinfo[c].kokazu = 0;
				pinfo[a].ko[pinfo[a].kokazu]=c;
				pinfo[a].kokazu++;
				pointsuu++;
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
		pinfo[a].teigi = "insi";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
	//


	void hensuunonarabi(int b) {
		int a = pointsuu;
		pinfo[a].teigi = "hensuunonarabi";
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
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
		pinfo[a].goku = jiku[n];
		pinfo[a].tnum = n;
		pinfo[a].number = pointsuu;
		pinfo[a].kokazu = 0;
		pinfo[b].ko[pinfo[b].kokazu]=a;
		pinfo[b].kokazu++;
		pointsuu++;
		if(tokenname[n].equals("SCONSTANT")){
			int c = pointsuu;
			pinfo[c].teigi = "integer";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;

		}else if(tokenname[n].equals("SSTRING")) {
			int c = pointsuu;
			pinfo[c].teigi = "char";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;

		}else if(jiku[n].equals("false")) {
			int c = pointsuu;
			pinfo[c].teigi = "boolean";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;

		}else if(jiku[n].equals("true")) {
			int c = pointsuu;
			pinfo[c].teigi = "boolean";
			pinfo[c].goku = jiku[n];
			pinfo[c].tnum = n;
			pinfo[c].number = pointsuu;
			pinfo[c].kokazu = 0;
			pinfo[a].ko[pinfo[a].kokazu]=c;
			pinfo[a].kokazu++;
			pointsuu++;

		}else {
			if(err==0) {
				System.err.println("Syntax error: line "+gyou[n]);
			}
			err++;
		}

		n++;
	}

	//服プログラムごとの変数宣言をわける！
	void hensuumemory() {
		for(int x=0;x<pointsuu;x++) {
			if(pinfo[x].teigi.equals("hensusengen")) {
				for(int s=0;s<pinfo[x].kokazu;s++) {
					if(pinfo[pinfo[x].ko[s]].teigi.equals("hensusengennarabi")) {
						for(int t=0;t<pinfo[pinfo[x].ko[s]].kokazu;t++) {
							if(pinfo[pinfo[pinfo[x].ko[s]].ko[t]].teigi.equals("hensuumeinarabi")) {
								for(int u=0;u<pinfo[pinfo[pinfo[x].ko[s]].ko[t]].kokazu;u++) {
									if(hensusu>1) {
										for(int v=0;v<hensusu;v++) {
											if(err==0&&pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].goku.equals(huku[prosu].namae[v])&&(chou==0)) {
												System.err.println("Semantic error: line "+gyou[pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].tnum]);
												chou++;
												err++;
											}
										}
									}
									if(chou==0) {
										huku[prosu].namae[hensusu]=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].goku;
										huku[prosu].tnum[hensusu]=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].tnum;
										for(int v=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].number;v<pointsuu;v++) {
											if(pinfo[v].teigi.equals("hyoujungata")) {
												huku[prosu].hyoujun[hensusu]=pinfo[v].goku;
												break;
											}
										}
										for(int v=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].number;v<pointsuu;v++) {
											if(pinfo[v].teigi.equals("kata")) {
												if(pinfo[pinfo[v].ko[0]].teigi.equals("hairetugata")) {
													huku[prosu].kata[hensusu]="hairetugata";
													boolean isNumeric1 =  pinfo[pinfo[pinfo[v].ko[0]].ko[1]].goku.matches("[+-]?\\d*(\\.\\d+)?");
													boolean isNumeric2 =  pinfo[pinfo[pinfo[v].ko[0]].ko[0]].goku.matches("[+-]?\\d*(\\.\\d+)?");
													if(isNumeric1&&isNumeric2) {

														huku[prosu].yousosu[hensusu]= Integer.parseInt(pinfo[pinfo[pinfo[v].ko[0]].ko[1]].goku)-Integer.parseInt(pinfo[pinfo[pinfo[v].ko[0]].ko[0]].goku)+1;
													}
												}
												else {
													huku[prosu].kata[hensusu]="hyoujungata";
													huku[prosu].yousosu[hensusu]=1;
												}
												break;
											}
										}
										hensusu++;
									}

								}
							}
						}
					}
				}
				huku[prosu].hensukazu=hensusu+1;
				prosu++;
				hensusu=0;
				break;
			}
		}
		int[] a= new int[1024];
		saikisearch("hukuprogramsengen",0,a);
		if(a[0]>0) {
			int i=0;
			while(a[i]>0) {
				int[] c= new int[1024];
				c=search("hukuprogramtoubu",a[i]);
				if(c[0]>0) {
					int h=0;
					while(c[h]>0) {
						int[] e= new int[1024];
						saikisearch("kariparameterorder",c[h],e);
						if(e[0]>0) {
							int g=0;
							while(e[g]>0) {
								int[] namnam=new int[1024];
								namnam=search("kariparameternameorder",e[g]);
								int[] hyojun=new int[1024];
								hyojun=search("hyoujungata",e[g]);
								int v=0;
								if(namnam[0]>0) {
									while(namnam[v]>0) {
										int[] kari=search("karipara",namnam[v]);
										int w=0;
										while(kari[w]>0) {
											huku[prosu].namae[hensusu]=pinfo[kari[w]].goku;
											huku[prosu].tnum[hensusu]=pinfo[kari[w]].tnum;
											huku[prosu].hyoujun[hensusu]=pinfo[hyojun[v]].goku;
											huku[prosu].kari[hensusu]="kari";
											huku[prosu].kata[hensusu]="hyoujungata";
											huku[prosu].yousosu[hensusu]=1;
											hensusu++;
											w++;
										}
										v++;
									}
								}
								g++;
							}
						}
						h++;
					}
				}
				int[] d=new int[1024];
				c=search("hensusengen",a[i]);
				int x =c[0];
				for(int s=0;s<pinfo[x].kokazu;s++) {
					if(pinfo[pinfo[x].ko[s]].teigi.equals("hensusengennarabi")) {
						for(int t=0;t<pinfo[pinfo[x].ko[s]].kokazu;t++) {
							if(pinfo[pinfo[pinfo[x].ko[s]].ko[t]].teigi.equals("hensuumeinarabi")) {
								for(int u=0;u<pinfo[pinfo[pinfo[x].ko[s]].ko[t]].kokazu;u++) {
									if(hensusu>1) {
										for(int v=0;v<hensusu;v++) {
											if(err==0&&pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].goku.equals(huku[prosu].namae[v])&&(chou==0)) {
												System.err.println("Semantic error: line "+gyou[pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].tnum]);
												chou++;
												err++;
											}
										}
									}
									if(chou==0) {
										huku[prosu].namae[hensusu]=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].goku;
										huku[prosu].tnum[hensusu]=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].tnum;
										for(int v=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].number;v<pointsuu;v++) {
											if(pinfo[v].teigi.equals("hyoujungata")) {
												huku[prosu].hyoujun[hensusu]=pinfo[v].goku;
												break;
											}
										}
										for(int v=pinfo[pinfo[pinfo[pinfo[x].ko[s]].ko[t]].ko[u]].number;v<pointsuu;v++) {
											if(pinfo[v].teigi.equals("kata")) {
												if(pinfo[pinfo[v].ko[0]].teigi.equals("hairetugata")) {
													huku[prosu].kata[hensusu]="hairetugata";
													boolean isNumeric1 =  pinfo[pinfo[pinfo[v].ko[0]].ko[1]].goku.matches("[+-]?\\d*(\\.\\d+)?");
													boolean isNumeric2 =  pinfo[pinfo[pinfo[v].ko[0]].ko[0]].goku.matches("[+-]?\\d*(\\.\\d+)?");
													if(isNumeric1&&isNumeric2) {

														huku[prosu].yousosu[hensusu]= Integer.parseInt(pinfo[pinfo[pinfo[v].ko[0]].ko[1]].goku)-Integer.parseInt(pinfo[pinfo[pinfo[v].ko[0]].ko[0]].goku)+1;
													}
												}
												else {
													huku[prosu].kata[hensusu]="hyoujungata";
													huku[prosu].yousosu[hensusu]=1;
												}
												break;
											}
										}
										hensusu++;
									}

								}
							}
						}
					}
				}

				huku[prosu].hensukazu=hensusu+1;
				prosu++;
				hensusu=0;
				i++;
			}

		}

	}
	void kariparamemory() {
		/*	int[] pro = new int[1024];
		saikisearch("kariparameter",0,pro);
		int m=0;
		while(pro[m]>0) {
			int[] a = new int[1024];
			saikisearch("kariparameterorder",pro[m],a);
			for(int i=0;i<1024;i++) {
				if(a[i]>0) {
					int[] b = new int[1024];
					b=search("kariparameternameorder",a[i]);
					for(int j=0;j<1024;j++) {
						if(b[j]>0) {
							int hyo=0;
							for(int l=b[j]; l<1024;l++) {
								if(pinfo[l].teigi.equals("hyoujungata")) {
									hyo=l;
								}
							}

							int[] c = new int[1024];
							c=search("karipara",b[j]);
							for(int k=0;k<1024;k++) {
								if(c[k]>0) {

									System.out.println("A"+m);
									huku[m+1].namae[huku[m+1].hensukazu]=pinfo[c[k]].goku;
									huku[m+1].tnum[huku[m+1].hensukazu]=pinfo[c[k]].tnum;
									huku[m+1].hyoujun[huku[m+1].hensukazu]=pinfo[hyo].goku;
									huku[m+1].yousosu[huku[m+1].hensukazu]=1;
									huku[m+1].hensukazu++;
									hensusu++;
								}
							}
						}
					}

					prosu++;
					hensusu=0;
				}
			}
			m++;
		}
		 */
	}

	int[] search(String tan,int n) {
		int[] a = new int[1024];
		int p=0;
		for(int i=0;i<pinfo[n].kokazu;i++) {
			if(pinfo[pinfo[n].ko[i]].teigi.equals(tan)) {
				a[p]=pinfo[n].ko[i];
				p++;
			}
		}
		return a;
	}

	void saikisearch(String tan,int n,int a[]) {
		int[] b = new int[1024];
		if(pinfo[n].kokazu>0) {
			for(int i=0;i<pinfo[n].kokazu;i++) {
				if(pinfo[pinfo[n].ko[i]].teigi.equals(tan)) {
					for(int j=0;j<1024;j++) {
						if(a[j]==0) {
							a[j]=pinfo[n].ko[i];
							break;
						}
					}
				}
				saikisearch(tan,pinfo[n].ko[i],a);
			}
		}

	}




	void hensudeclare() {
		int[] b = new int[1024];
		int detekita=0;
		/*for(int i=0;i<pointsuu;i++) {
			if(pinfo[i].teigi.equals("hukuprogramsengen")) {
				prosu++;
				int[] a = new int[1024];
				saikisearch("hensuumei",i,a);
				for(int j=0;j<1024;j++) {
					if(a[j]>0) {
						System.out.println(pinfo[a[j]].goku);
						for(int k=0;k<huku[prosu].hensukazu;k++) {
							if(pinfo[a[j]].goku.equals(huku[prosu].namae[k])) {
								detekita++;
							}
						}
						for(int k=0;k<huku[0].hensukazu;k++) {
							if(pinfo[a[j]].goku.equals(huku[0].namae[k])) {
								detekita++;
							}
						}
						if(detekita==0) {
							System.err.println("Semantic error: line "+gyou[pinfo[a[j]].tnum]);
							err++;
						}
						detekita=0;
					}
				}
			}

		}*/
		int[] a = new int[1024];
		saikisearch("hensuumei",pinfo[0].ko[2],a);
		for(int j=0;j<1024;j++) {
			if(a[j]>0) {
				for(int k=0;k<huku[0].hensukazu;k++) {
					if(pinfo[a[j]].goku.equals(huku[0].namae[k])) {
						detekita++;

					}
				}
				if(detekita==0&&err==0) {
					System.err.println("Semantic error: line "+gyou[pinfo[a[j]].tnum]);
					err++;
				}
				detekita=0;
			}
		}
	}


	void soejicheck() {
		int[] a = new int[1024];
		saikisearch("soeji",pinfo[0].ko[2],a);

		for(int j=0;j<1024;j++) {
			int[] b = new int[1024];
			if(a[j]>0) {
				saikisearch("hensuu",a[j],b);
				for(int i=0;i<1024;i++) {
					if(b[i]>0) {
						for(int p=0;p<prosu;p++) {
							for(int q=0;q<huku[p].hensukazu;q++) {
								if(pinfo[b[i]].goku.equals(huku[p].namae[q])) {
									if(huku[p].hyoujun[q].equals("boolean")&&err==0) {
										System.err.println("Semantic error: line "+gyou[pinfo[b[i]].tnum]);
										err++;
									}
								}

							}
						}
					}
				}

			}
		}
	}

	void arraycheck() {
		int[] a = new int[1024];
		saikisearch("hensuu",0,a);
		for(int i=0;i<1024;i++) {
			if(a[i]>0) {
				for(int p=0;p<prosu;p++) {
					for(int q=0;q<huku[p].hensukazu;q++) {
						if(pinfo[a[i]].goku.equals(huku[p].namae[q])) {
							if(huku[p].kata[q].equals("hairetugata")) {
								if((!(pinfo[pinfo[a[i]].ko[1]].teigi.equals("soeji")))&&err==0) {//子の数見てる
									System.err.println("Semantic error: line "+gyou[pinfo[a[i]].tnum]);
									err++;
								}
							}
						}
					}
				}
			}
		}
	}


	void joukensikicheck() {
		int[] a = new int[1024];
		saikisearch("bun",0,a);
		for(int i=0;i<1024;i++) {
			if(pinfo[a[i]].goku.equals("if")) {
				int n;
				n=pinfo[a[i]].ko[0];
				int[] b = new int[1024];
				saikisearch("siki",a[i],b);
				for(int j=0;j<1024;j++) {
					if(b[j]>0) {
						int[] c = new int[1024];
						saikisearch("siki",b[j],c);
						if((!(c[0]>0))&&pinfo[b[j]].kokazu==1 &&j==0){
							for(int p=0;p<prosu;p++) {
								for(int q=0;q<huku[p].hensukazu;q++) {
									if(pinfo[b[j]].goku.equals(huku[p].namae[q])) {
										if(huku[p].hyoujun[q].equals("integer")&&err==0) {
											System.err.println("Semantic error: line "+gyou[pinfo[b[j]].tnum]);
											System.err.println(b[j]);
											err++;
										}
										else if(huku[p].hyoujun[q].equals("char")&&err==0) {
											System.err.println("Semantic error: line "+gyou[pinfo[b[j]].tnum]);
											err++;
										}
									}

								}
							}

						}
					}
				}
			}
			if(pinfo[a[i]].goku.equals("while")) {
				int n;
				n=pinfo[a[i]].ko[0];
				if(pinfo[n].kokazu==1) {
					int[] b = new int[1024];
					saikisearch("hensuumei",n,b);
					for(int j=0;j<1024;j++) {
						if(b[j]>0) {
							for(int p=0;p<prosu;p++) {
								for(int q=0;q<huku[p].hensukazu;q++) {
									if(pinfo[b[j]].goku.equals(huku[p].namae[q])) {
										if(huku[p].hyoujun[q].equals("integer")&&err==0) {
											System.err.println("Semantic error: line "+gyou[pinfo[b[j]].tnum]);
											err++;
										}
										else if(huku[p].hyoujun[q].equals("char")&&err==0) {
											System.err.println("Semantic error: line "+gyou[pinfo[b[j]].tnum]);
											err++;
										}
									}

								}
							}
						}
					}
				}
			}
		}
	}


	void hienzancheck() {

		for(int i=0;i<1024;i++) {
			int[] b = new int[1024];
			b=search("kou",i);
			if(b[1]>0) {
				String[] nani= new String[1024];
				for(int j=0;j<1024;j++) {
					nani[j]="";
				}
				int kazu=0;
				for(int j=0;j<1024;j++) {
					if(b[j]>0) {
						if(pinfo[pinfo[pinfo[b[j]].ko[0]].ko[0]].teigi.equals("teisuu")) {
							nani[kazu]=pinfo[pinfo[pinfo[pinfo[b[j]].ko[0]].ko[0]].ko[0]].teigi;
							if(kazu>=1) {
								if((!nani[kazu].equals(nani[kazu-1]))&&err==0 ){
									System.err.println("Semantic error: line "+gyou[pinfo[b[j]].tnum]);
									err++;
								}
							}
							kazu++;
						}else {
							for(int p=0;p<1;p++) {
								for(int q=0;q<huku[p].hensukazu;q++) {
									if(pinfo[b[j]].goku.equals(huku[p].namae[q])) {
										nani[kazu]=huku[p].hyoujun[q];
										kazu++;
									}
								}
							}
						}
					}

				}
				int[] c = new int[1024];
			}
			b=search("insi",i);
			if(b[1]>0) {
				String[] nani= new String[1024];
				for(int j=0;j<1024;j++) {
					nani[j]="";
				}
				int kazu=0;
				for(int j=0;j<1024;j++) {
					if(b[j]>0) {
						if(pinfo[pinfo[b[j]].ko[0]].teigi.equals("teisuu")) {
							nani[kazu]=pinfo[pinfo[pinfo[b[j]].ko[0]].ko[0]].teigi;
							if(kazu>=1) {
								if((!nani[kazu].equals(nani[kazu-1]))&&err==0 ){
									System.err.println("Semantic error: line "+gyou[pinfo[b[j]].tnum]);
									err++;
								}
							}
							kazu++;
						}else {
							for(int p=0;p<1;p++) {
								for(int q=0;q<huku[p].hensukazu;q++) {
									if(pinfo[b[j]].goku.equals(huku[p].namae[q])) {
										nani[kazu]=huku[p].hyoujun[q];
										kazu++;
									}
								}
							}
						}
					}

				}
			}

		}

	}

	void dainyucheck() {
		int[] a = new int[1024];
		saikisearch("dainyuubun",0,a);
		for(int j=0;j<1024;j++) {
			if(a[j]>0) {
				String sa;
				String uhe;
				sa="";
				uhe="";
				int[] b = new int[1024];
				for(int p=0;p<=prosu;p++) {
					for(int q=0;q<huku[p].hensukazu;q++) {
						if(pinfo[pinfo[a[j]].ko[0]].goku.equals(huku[p].namae[q])) {
							sa=huku[p].hyoujun[q];
						}
					}
				}
				saikisearch("hensuumei",pinfo[a[j]].ko[1],b);
				for(int k=0;k<1024;k++) {
					if(b[k]>0) {
						for(int p=0;p<=prosu;p++) {
							for(int q=0;q<huku[p].hensukazu;q++) {
								if(pinfo[b[k]].goku.equals(huku[p].namae[q])) {
									uhe=huku[p].hyoujun[q];
								}
							}
						}
						break;
					}
				}
				int[] c = new int[1024];
				saikisearch("teisuu",pinfo[a[j]].ko[1],c);
				for(int k=0;k<1024;k++) {
					if(c[k]>0&&pinfo[c[k]].ko[0]>0) {
						uhe=pinfo[pinfo[c[k]].ko[0]].teigi;
						break;
					}
				}
				if(!sa.equals(uhe)&&err==0&&!(sa.equals("boolean"))) {
					System.err.println("Semantic error: line "+gyou[pinfo[a[j]].tnum]);
					err++;

				}
				if(sa.equals("boolean")) {

				}
			}
		}
	}

	void tetudukicheck() {
		int[] a = new int[1024];
		saikisearch("tetudukiyobidasi",0,a);
		for(int i=0;i<1024;i++) {
			if(a[i]>0) {
				int detekita=0;
				for(int j=0;j<hukuprosu;j++) {
					if(pinfo[a[i]].goku.equals(hukuproname[j])) {
						detekita++;
					}
				}
				if(detekita==0&&err==0) {
					System.err.println("Semantic error: line "+gyou[pinfo[a[i]].tnum]);
					err++;
				}
			}
		}
	}



	void casprogram() {
		cas=cas+"CASL	START	BEGIN	;\n";
		cas=cas+"BEGIN	LAD	GR6, 0	;\n";
		cas=cas+"	LAD	GR7, LIBBUF	;\n";
		int[] a = new int[1024];
		a=search("block",0);

		int[] b = new int[1024];
		b=search("complexStatement",0);
		cascomplexStatement(b[0]);
		cas=cas+"	RET		; \n";
		casblock(a[0]);
		if(mojiretukazu>0) {
			for(int i=0;i<mojiretukazu;i++) {
				cas=cas+mojiretusen[i]+"\n";
			}
		}
		if(huku[0].hensukazu>0) {
			cas=cas+varsen+"\n";
		}
		cas=cas+"LIBBUF	DS	256	; \n";
		cas=cas+"	END		; ";
	}

	void casblock(int b) {
		int[] a = new int[1024];
		a=search("hensusengen",b);
		int j=0;
		while(a[j]>0) {
			cashensusengen(a[j]);
			j++;
		}
		j=0;
		int[] sub = new int[1024];
		if(pinfo[b].ko[1]>0) {
			cashukuprogramsengengun(pinfo[b].ko[1]);
		}

	}
	void cashukuprogramsengengun(int b) {
		if(pinfo[b].ko[0]>0) {
			int j=0;
			while(pinfo[b].ko[j]>0) {
				/*for(int i=0;i<256;i++) {
					if(hukuprostuck[i]!=-1) {
						hukuprostuck[i]=imahuku;
						imahuku=j+1;
						break;
					}
				}*/
				imahuku=j+1;
				cashukuprogramsengen(pinfo[b].ko[j],j);
				imahuku=0;
				/*for(int i=255;i>=0;i--) {
					if(hukuprostuck[i]!=-1) {
						imahuku=hukuprostuck[i];
						hukuprostuck[i]=-1;

						break;
					}
				}*/
				j++;
			}
		}
	}

	void cashukuprogramsengen(int b,int proban) {
		int num=0;
		int[] karimem= new int[1024];
		for(int i=0;i<huku[proban+1].hensukazu;i++) {
			if(huku[proban+1].kari[i].equals("kari")) {
				karimem[num]=huku[proban+1].bango[i];
				num++;

			}
		}

		cas=cas+"PROC"+proban+"	NOP		; proc\r\n"
				+ "	LD	GR1, GR8	; proc	local-var\r\n";

		if(num>0) {
			for(int i=0;i<num;i++) {
				cas=cas+"	ADDA	GR1, ="+(i+1)+"	; proc	local-var\r\n"
						+ "	LD	GR2, 0, GR1	; proc	fparam	(x)\r\n"
						+ "	LD	GR3, ="+karimem[i]+"	; proc	fparam	(x)\r\n"
						+ "	ST	GR2, VAR, GR3	; proc	fparam	(x)\r\n"
						+ "	SUBA	GR1, ="+(i+1)+"	; proc	fparam	(x)\n";
			}
			cas=cas+"	LD	GR1, 0, GR8	; proc	fparam\r\n"
					+ "	ADDA	GR8, ="+num+"	; proc	fparam\r\n"
					+ "	ST	GR1, 0, GR8	; proc	fparam\n";
		}else {
			cas=cas+ "	ADDA	GR1, ="+num+"	; proc	local-var\n";
		}

		cascomplexStatement(pinfo[b].ko[2]);
		cas=cas+"	RET		; proc\n";
	}


	void cashensusengen(int b) {

	}

	void cascomplexStatement(int b) {

		int[] a = new int[1024];
		a=search("bunnnonarabi",b);
		int j=0;
		while(a[j]>0) {
			casbun(a[j]);
			j++;
		}

	}

	void casbun(int b) {

		int[] a = new int[1024];
		a=search("bun",b);
		int j=0;
		while(a[j]>0) {
			if(pinfo[a[j]].kokazu==1) {
				if(pinfo[pinfo[a[j]].ko[0]].teigi.equals("kihonbun")) {
					caskihonbun(pinfo[a[j]].ko[0]);
				}
			}
			else if(pinfo[a[j]].goku.equals("if") ) {
				casif(a[j]);
			}
			else {
				caswhile(a[j]);
			}
			j++;
		}
	}

	void casif(int b) {
		int nam=ifkazu;
		ifkazu++;
		casdainyusiki(pinfo[b].ko[0]);
		cas=cas+"	POP	GR1	; if\r\n"
				+ "	CPA	GR1, =#FFFF	; if\r\n"
				+ "	JZE	ELSE"+nam+"	; if\n";
		if(pinfo[b].ko[2]>0) {
			cascomplexStatement(pinfo[b].ko[1]);
			cas=cas+"	JUMP	ENDIF"+nam+"	; if\n";
			cas=cas+"ELSE"+nam+"	NOP		; if\n";
			cascomplexStatement(pinfo[b].ko[2]);
			cas=cas+"ENDIF"+nam+"	NOP		; if	else\n";
		}else {
			cascomplexStatement(pinfo[b].ko[1]);
			cas=cas+"ELSE"+nam+"	NOP		; if\n";
		}

	}

	void caswhile(int b) {
		int nam=whilekazu;
		whilekazu++;
		cas=cas+"LOOP"+nam+"	NOP		; while\n";
		casdainyusiki(pinfo[b].ko[0]);
		cas=cas+"	POP	GR1	; while\r\n"
				+ "	CPL	GR1, =#FFFF	; while\r\n"
				+ "	JZE	ENDLP"+nam+"	; while\n";
		cascomplexStatement(pinfo[b].ko[1]);
		cas=cas+"	JUMP	LOOP"+nam+"	; while\r\n"
				+ "ENDLP"+nam+"	NOP		; while\n";

	}

	void caskihonbun(int b) {
		if(pinfo[pinfo[b].ko[0]].teigi.equals("dainyuubun")) {
			casdainyuubun(pinfo[b].ko[0]);
		}
		else if(pinfo[pinfo[b].ko[0]].teigi.equals("tetudukiyobidasi")) {
			castetudukiyobidasi(pinfo[b].ko[0]);
		}
		else if(pinfo[pinfo[b].ko[0]].teigi.equals("nyuusyuturyokubun")) {
			casnyuusyuturyokubun(pinfo[b].ko[0]);
		}
		else{
			cascomplexStatement(pinfo[b].ko[0]);
		}
	}


	void casdainyuubun(int b) {
		casdainyusiki(pinfo[b].ko[1]);
		cassahen(pinfo[b].ko[0]);

	}
	void cassahen(int b) {
		cassahenhensu(pinfo[b].ko[0]);
	}

	void cassahenhensu(int b) {
		int num=-1;
		String kata="";
		for(int i=0;i<huku[imahuku].hensukazu;i++) {
			if(pinfo[b].goku.equals(huku[imahuku].namae[i])) {
				num=huku[imahuku].bango[i];

				kata=huku[imahuku].hyoujun[i];
			}
		}

		if(num==-1) {
			for(int i=0;i<huku[0].hensukazu;i++) {
				if(pinfo[b].goku.equals(huku[0].namae[i])) {
					num=huku[0].bango[i];
					kata=huku[0].hyoujun[i];
				}
			}
		}
		if(pinfo[b].ko[1]>0) {//左辺が配列の時
			casdainyusiki(pinfo[pinfo[b].ko[1]].ko[0]);
			cas=cas+"	POP	GR2	; assign	var	"+pinfo[b].goku+"idx\r\n"
					+ "	ADDA	GR2, ="+(num-1)+"	; assign	var\r\n"
					+ "	POP	GR1	; assign\r\n"
					+ "	ST	GR1, VAR, GR2	; assign\n";
		}
		else {
			cas=cas+"	LD	GR2, ="+num+"	; assign	var	("+pinfo[b].goku+")\n"
					+"	POP	GR1	; assign\n"
					+"	ST	GR1, VAR, GR2	; assign\n";

		}


	}

	void casdainyusiki(int b) {
		cas=cas+"; L"+gyou[pinfo[b].tnum]+"\n";
		casdainyutanjun(pinfo[b].ko[0]);
		if(pinfo[b].ko[1]>0) {
			casdainyutanjun(pinfo[b].ko[2]);
			caskankei(pinfo[b].ko[1]);
		}
	}

	void caskankei(int b) {
		if(pinfo[b].goku.equals("<=")) {
			cas=cas+"	POP	GR2	; while	comp\r\n"
					+ "	POP	GR1	; while	comp\r\n"
					+ "	CPA	GR1, GR2	; while	comp\r\n"
					+ "	JPL	TRUE"+kankeikazu+"	; while	comp	comp-op\r\n"
					+ "	LD	GR1, =#0000	; while	comp	comp-op\r\n"
					+ "	JUMP	BOTH"+kankeikazu+"	; while	comp	comp-op\r\n"
					+ "TRUE"+kankeikazu+"	LD	GR1, =#FFFF	; while	comp	comp-op\r\n"
					+ "BOTH"+kankeikazu+"	PUSH	0, GR1	; while	comp	comp-op\n";

		}else if(pinfo[b].goku.equals("=")) {
			cas=cas+"	POP	GR2	; if	comp\r\n"
					+ "	POP	GR1	; if	comp\r\n"
					+ "	CPA	GR1, GR2	; if	comp\r\n"
					+ "	JZE	TRUE"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "	LD	GR1, =#FFFF	; if	comp	comp-op\r\n"
					+ "	JUMP	BOTH"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "TRUE"+kankeikazu+"	LD	GR1, =#0000	; if	comp	comp-op\r\n"
					+ "BOTH"+kankeikazu+"	PUSH	0, GR1	; if	comp	comp-op\n";
		}
		else if(pinfo[b].goku.equals("<>")) {
			cas=cas+"	POP	GR2	; if	comp\r\n"
					+ "	POP	GR1	; if	comp\r\n"
					+ "	CPA	GR1, GR2	; if	comp\r\n"
					+ "	JNZ	TRUE"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "	LD	GR1, =#FFFF	; if	comp	comp-op\r\n"
					+ "	JUMP	BOTH"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "TRUE"+kankeikazu+"	LD	GR1, =#0000	; if	comp	comp-op\r\n"
					+ "BOTH"+kankeikazu+"	PUSH	0, GR1	; if	comp	comp-op\n";
		}
		else if(pinfo[b].goku.equals("<")) {
			cas=cas+"	POP	GR2	; if	comp\r\n"
					+ "	POP	GR1	; if	comp\r\n"
					+ "	CPA	GR1, GR2	; if	comp\r\n"
					+ "	JMI	TRUE"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "	LD	GR1, =#FFFF	; if	comp	comp-op\r\n"
					+ "	JUMP	BOTH"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "TRUE"+kankeikazu+"	LD	GR1, =#0000	; if	comp	comp-op\r\n"
					+ "BOTH"+kankeikazu+"	PUSH	0, GR1	; if	comp	comp-op\n";
		}
		else if(pinfo[b].goku.equals(">")) {
			cas=cas+"	POP	GR2	; if	comp\r\n"
					+ "	POP	GR1	; if	comp\r\n"
					+ "	CPA	GR1, GR2	; if	comp\r\n"
					+ "	JPL	TRUE"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "	LD	GR1, =#FFFF	; if	comp	comp-op\r\n"
					+ "	JUMP	BOTH"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "TRUE"+kankeikazu+"	LD	GR1, =#0000	; if	comp	comp-op\r\n"
					+ "BOTH"+kankeikazu+"	PUSH	0, GR1	; if	comp	comp-op\n";
		}
		else {
			cas=cas+"	POP	GR2	; if	comp\r\n"
					+ "	POP	GR1	; if	comp\r\n"
					+ "	CPA	GR1, GR2	; if	comp\r\n"
					+ "	JMI	TRUE"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "	LD	GR1, =#0000	; if	comp	comp-op\r\n"
					+ "	JUMP	BOTH"+kankeikazu+"	; if	comp	comp-op\r\n"
					+ "TRUE"+kankeikazu+"	LD	GR1, =#FFFF	; if	comp	comp-op\r\n"
					+ "BOTH"+kankeikazu+"	PUSH	0, GR1	; if	comp	comp-op\n";
		}
		kankeikazu++;
	}

	void casdainyutanjun(int b) {
		int[] kou=new int[1024];
		int[] puramai=new int[1024];
		int[] hugou=new int[1024];
		hugou=search("hugou",b);
		kou=search("kou",b);
		puramai=search("kahouenzansi",b);
		casdainyukou(kou[0]);
		if(pinfo[hugou[0]].goku.equals("-")) {
			cas=cas+"	POP	GR2	; if	minus\r\n"
					+ "	LD	GR1, =0	; if	minus\r\n"
					+ "	SUBA	GR1, GR2	; if	minus\r\n"
					+ "	PUSH	0, GR1	; if	minus\n";
		}
		if(puramai[0]>0) {
			int j=0;
			while(puramai[j]>0) {
				casdainyukou(kou[j+1]);
				casdainyupuramai(puramai[j]);
				j++;
			}
		}
	}
	void casdainyupuramai(int b){
		if(pinfo[b].goku.equals("+")) {
			cas=cas+"	POP	GR2	; assign	addition\n"+"	POP	GR1	; assign	addition\n"+"	ADDA	GR1, GR2	; assign	addition\n"+"	PUSH	0, GR1	; assign	addition\n";
		}
		else if(pinfo[b].goku.equals("-")) {
			cas=cas+"	POP	GR2	; assign	addition\r\n"
					+ "	POP	GR1	; assign	addition\r\n"
					+ "	SUBA	GR1, GR2	; assign	addition\r\n"
					+ "	PUSH	0, GR1	; assign	addition\n";
		}
		else {
			cas=cas+"	POP	GR2	; if	addition\r\n"
					+ "	POP	GR1	; if	addition\r\n"
					+ "	OR	GR1, GR2	; if	addition\r\n"
					+ "	PUSH	0, GR1	; if	addition\n";
		}
	}

	void casdainyukou(int b) {
		casdainyuinsi(pinfo[b].ko[0]);
		int[] kakewari=new int[1024];
		int[] insi=new int[1024];
		kakewari=search("jouhouenzansi",b);
		insi=search("insi",b);

		if(kakewari[0]>0) {
			int j=0;
			while(kakewari[j]>0) {
				casdainyuinsi(insi[j+1]);
				casdainyukakewari(kakewari[j]);
				j++;
			}
		}
	}
	void casdainyukakewari(int b) {
		if(pinfo[b].goku.equals("*")) {
			cas=cas+"	POP	GR2	; while	assign	multiple\r\n"
					+ "	POP	GR1	; while	assign	multiple\r\n"
					+ "	CALL	MULT	; while	assign	multiple\r\n"
					+ "	PUSH	0, GR2	; while	assign	multiple"+pinfo[b].tnum+"\n";
		}
		else if(pinfo[b].goku.equals("/")||pinfo[b].goku.equals("div")) {
			cas=cas+"	POP	GR2	; if	addition	multiple\r\n"
					+ "	POP	GR1	; if	addition	multiple\r\n"
					+ "	CALL	DIV	; if	addition	multiple\r\n"
					+ "	PUSH	0, GR2	; if	addition	multiple\n";
		}
		else if(pinfo[b].goku.equals("mod")) {
			cas=cas+"	POP	GR2	; if	addition	multiple\r\n"
					+ "	POP	GR1	; if	addition	multiple\r\n"
					+ "	CALL	DIV	; if	addition	multiple\r\n"
					+ "	PUSH	0, GR1	; if	addition	multiple\n";
		}
		else {
			cas=cas+"	POP	GR2	; if	multiple\r\n"
					+ "	POP	GR1	; if	multiple\r\n"
					+ "	AND	GR1, GR2	; if	multiple\r\n"
					+ "	PUSH	0, GR1	; if	multiple\n";
		}
	}

	void casdainyuinsi(int b) {
		if(pinfo[pinfo[b].ko[0]].teigi.equals("hensuu")) {
			casdainyuhensuu(pinfo[b].ko[0]);
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("teisuu")) {
			casdainyuteisuu(pinfo[b].ko[0]);
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("siki")) {
			casdainyusiki(pinfo[b].ko[0]);
		}else {
			casdainyuinsi(pinfo[b].ko[0]);
			cas=cas+"	POP	GR1	; if\r\n"
					+ "	XOR	GR1, =#FFFF	; if\r\n"
					+ "	PUSH	0, GR1	; if\n";

		}
	}
	void casdainyuteisuu(int b) {
		if(pinfo[pinfo[b].ko[0]].teigi.equals("integer")) {
			casdainyuinteger(pinfo[b].ko[0]);
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("char")) {
			cas=cas+"	LD	GR1, ="+pinfo[pinfo[b].ko[0]].goku+"	; proc	assign	const-str	('s')\r\n"
					+ "	PUSH	0, GR1	; proc	assign	const-str\n";
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("boolean")) {
			if(pinfo[pinfo[b].ko[0]].goku.equals("true")) {
				cas=cas+"	PUSH	#0000	; assign	const-bool\n";
			}else {
				cas=cas+"	PUSH	#FFFF	; assign	const-bool\n";
			}

		}
	}

	void casdainyuinteger(int b) {
		cas=cas+"	PUSH	"+pinfo[b].goku+"\n";
	}

	void casdainyuhensuu(int b) {

		int num=-1;
		String kata="";
		for(int i=0;i<huku[imahuku].hensukazu;i++) {
			if(pinfo[b].goku.equals(huku[imahuku].namae[i])) {
				num=huku[imahuku].bango[i];
				kata=huku[imahuku].hyoujun[i];
			}
		}
		if(num==-1) {
			for(int i=0;i<huku[0].hensukazu;i++) {
				if(pinfo[b].goku.equals(huku[0].namae[i])) {
					num=huku[0].bango[i];
					kata=huku[0].hyoujun[i];
				}
			}
		}
		if(pinfo[b].ko[1]>0) {//配列の時
				System.out.println(pinfo[pinfo[b].ko[1]].ko[0]);
				casdainyusiki(pinfo[pinfo[b].ko[1]].ko[0]);/////////////////////////////////////////////////
				cas=cas+"	POP	GR2	; assign	var	"+pinfo[b].goku+"idx\r\n"
						+ "	ADDA	GR2, ="+(num-1)+"	; assign	var\r\n"
						+ "	LD	GR1, VAR, GR2	; assign	left\r\n"
						+ "	PUSH	0, GR1	; assign	left\n";
		}else {
			cas=cas+"	LD	GR2, ="+num+"	; assign	left	var	("+pinfo[b].goku+")\r\n"
					+ "	LD	GR1, VAR, GR2	; assign	left\r\n"
					+ "	PUSH	0, GR1	; assign	left\n";
		}


	}

	void castetudukiyobidasi(int b) {
		if(pinfo[b].ko[0]>0) {
			int j=0;
			while(pinfo[pinfo[b].ko[0]].ko[j]>0) {
				j++;
			}
			for(int i=j-1;i>=0;i--) {
				casdainyusiki(pinfo[pinfo[b].ko[0]].ko[i]);
			}
		}
		int num=0;
		for(int i=0;i<prosu;i++) {
			if(pinfo[b].goku.equals(huku[i].proname)) {
				num=i-1;
			}
		}
		cas=cas+"	CALL	PROC"+num+"	; proc-call\n";

	}

	void casnyuusyuturyokubun(int b) {
		int[] a = new int[1024];
		if(pinfo[b].goku.equals("writeln")) {
			cassyuturyokusikinarabi(pinfo[b].ko[0]);
		}

		cas=cas+"	CALL	WRTLN	; output\n";
	}

	void cassyuturyokusikinarabi(int b) {
		int[] a = new int[1024];
		a=search("siki",b);
		int j=0;
		while(a[j]>0) {
			cassyuturyokusiki(a[j]);
			j++;
		}

	}

	void cassyuturyokusiki(int b) {
		int[] a = new int[1024];
		a=search("tanjunsiki",b);
		int j=0;
		while(a[j]>0) {
			cassyuturyokutanjunsiki(a[j]);
			j++;
		}
	}

	void cassyuturyokutanjunsiki(int b) {
		int[] a = new int[1024];
		a=search("kou",b);
		int j=0;
		while(a[j]>0) {
			cassyuturyokukou(a[j]);
			j++;
		}

	}
	void cassyuturyokukou(int b) {
		int[] a = new int[1024];
		a=search("insi",b);
		int j=0;
		while(a[j]>0) {
			cassyuturyokuinsi(a[j]);
			j++;
		}

	}

	void cassyuturyokuinsi(int b) {
		if(pinfo[pinfo[b].ko[0]].teigi.equals("hensuu")) {
			cassyuturyokuhensuu(pinfo[b].ko[0]);
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("teisuu")) {
			cassyuturyokuteisuu(pinfo[b].ko[0]);
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("siki")) {
			cassyuturyokusiki(pinfo[b].ko[0]);
		}else if(pinfo[pinfo[b].ko[0]].teigi.equals("insi")) {
			cassyuturyokuinsi(pinfo[b].ko[0]);
		}
	}
	void cassyuturyokuteisuu(int b){
		if(pinfo[pinfo[b].ko[0]].teigi.equals("char")) {
			mojiretusen[mojiretukazu]="CHAR"+mojiretukazu+"	"+"DC"+"	"+pinfo[pinfo[b].ko[0]].goku;
			mojiretunagasa[mojiretukazu]=pinfo[b].goku.length()-2;;


			cas=cas+"	LD	GR1, ="+ mojiretunagasa[mojiretukazu] +"	; output	const-str	("+pinfo[pinfo[b].ko[0]].goku+")\r\n"
					+ "	PUSH	0, GR1	; output	const-str\r\n"
					+ "	LAD	GR2, CHAR"+mojiretukazu+"	; output	const-str\r\n"
					+ "	PUSH	0, GR2	; output	const-str\r\n"
					+ "	POP	GR2	; output\r\n"
					+ "	POP	GR1	; output\r\n"
					+ "	CALL	WRTSTR	; output\r\n";

			mojiretukazu++;

		}

	}

	void cassyuturyokuhensuu(int b){
		int num=-1;
		String kata="";
		for(int i=0;i<huku[imahuku].hensukazu;i++) {
			if(pinfo[b].goku.equals(huku[imahuku].namae[i])) {
				num=huku[imahuku].bango[i];
				kata=huku[imahuku].hyoujun[i];
			}
		}
		System.out.println("pro"+imahuku+pinfo[b].goku);
		if(num==-1) {
			for(int i=0;i<huku[0].hensukazu;i++) {
				if(pinfo[b].goku.equals(huku[0].namae[i])) {
					num=huku[0].bango[i];
					kata=huku[0].hyoujun[i];
				}
			}
		}
		if(pinfo[b].ko[1]>0) {//配列の時
			casdainyusiki(pinfo[pinfo[b].ko[1]].ko[0]);
			if(kata.equals("integer")) {
				cas=cas+"	POP	GR2	; assign	var	idx\r\n"
						+ "	ADDA	GR2, ="+(num-1)+"	; assign	var"+pinfo[b].goku+"\r\n"
						+ "	LD	GR1, VAR, GR2	; output	left\r\n"
						+ "	PUSH	0, GR1	; output	left\r\n"
						+ "	POP	GR2	; output\r\n"
						+ "	CALL	WRTINT	; output\n";
			}
			else if(kata.equals("char")) {
				cas=cas+"	POP	GR2	; output	left	var	idx\r\n"
						+ "	ADDA	GR2, ="+(num-1)+"	; output	left	var"+pinfo[b].goku+"\r\n"
						+ "	LD	GR1, VAR, GR2	; output	left\r\n"
						+ "	PUSH	0, GR1	; output	left\r\n"
						+ "	POP	GR2	; output\r\n"
						+ "	CALL	WRTCH	; output\n";
			}
		}else {
			if(kata.equals("integer")) {
				cas=cas+"	LD	GR2, ="+num+"	; output	left	var	"+pinfo[b].goku+"\r\n"
						+ "	LD	GR1, VAR, GR2	; output	left\r\n"
						+ "	PUSH	0, GR1	; output	left\r\n"
						+ "	POP	GR2	; output\r\n"
						+ "	CALL	WRTINT	; output\n";
			}

			if(kata.equals("char")) {
				cas=cas+"	LD	GR2, ="+num+"	; output	left	var	"+pinfo[b].goku+"\r\n"
						+ "	LD	GR1, VAR, GR2	; output	left\r\n"
						+ "	PUSH	0, GR1	; output	left\r\n"
						+ "	POP	GR2	; output\r\n"
						+ "	CALL	WRTCH	; output\n";
			}
		}
	}




	void memo() {

		for(int i=0;i<prosu;i++) {
			for(int j=0;j<huku[i].hensukazu;j++) {
				if(i>0) {
					for(int k=0;k<i;k++) {
						for(int l=0;l<huku[k].hensukazu;l++) {
							huku[i].bango[j]=huku[i].bango[j]+huku[k].yousosu[l];
						}
					}
				}

				for(int k=0;k<j;k++) {
					huku[i].bango[j]=huku[i].bango[j]+huku[i].yousosu[k];

				}
				huku[i].hensumemo=huku[i].bango[j]+huku[i].yousosu[j];


				varsen="VAR	DS	"+huku[i].hensumemo;
			}
		}
	}




	public void run(final String inputFileName, final String outputFileName) {
		for(int j=0;j<2048;j++) {
			jiku[j]="sioeeoiofewoefw";
			tokenname[j]="sioeeoiofewoefw";
		}

		for(int i=0;i<255;i++) {
			hukuprostuck[i]=-1;
		}

		for(int i=0;i<255;i++) {
			mojiretusen[i]="";
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
				for(int s=0;s<10000;s++) {
					pinfo[s]=new point();
				}
				for(int s=0;s<100;s++) {
					huku[s]=new hensuse();
				}


				program();
				hensuumemory();
				kariparamemory();
				hensudeclare();
				soejicheck();
				arraycheck();
				joukensikicheck();
				hienzancheck();
				dainyucheck();
				tetudukicheck();
				memo();
				/*for(int i=0;i<prosu;i++) {
					for(int j=0;j<huku[i].hensukazu;j++) {
						System.out.println(huku[i].namae[j]);
						System.out.println(huku[i].hyoujun[j]);
						System.out.println(huku[i].kata[j]);
						System.out.println(huku[i].yousosu[j]);

					}
				}*/
				if(err==0) {
					System.out.println("OK");
					for(int s=0;s<pointsuu;s++) {
						System.out.println(pinfo[s].number);
						System.out.println(pinfo[s].goku);
						System.out.println(pinfo[s].tnum);
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

					if(prosu>1) {
						for(int i=1;i<prosu;i++) {
							huku[i].proname=hukuproname[i-1];
						}
					}
					for(int i=0;i<prosu;i++) {
						System.out.println(huku[i].proname);
						for(int j=0;j<huku[i].hensukazu;j++) {
							System.out.println(huku[i].namae[j]);
							System.out.println(huku[i].hyoujun[j]);
							System.out.println(huku[i].kata[j]);
							System.out.println(huku[i].yousosu[j]);
							System.out.println(huku[i].bango[j]);



						}
					}
					Path p = Paths.get(outputFileName);


					casprogram();
					System.out.println(cas);
					try {
						// FileWriterクラスのオブジェクトを生成する
						FileWriter file = new FileWriter(outputFileName);
						// PrintWriterクラスのオブジェクトを生成する
						PrintWriter pw = new PrintWriter(new BufferedWriter(file));
						cas=cas+"; lib.cas\r\n"
								+ ";============================================================\r\n"
								+ "; MULT: 掛け算を行うサブルーチン\r\n"
								+ "; GR1 * GR2 -> GR2\r\n"
								+ "MULT	START\r\n"
								+ "	PUSH	0,GR1	; GR1の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR3	; GR3の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR4	; GR4の内容をスタックに退避\r\n"
								+ "	LAD	GR3,0	; GR3を初期化\r\n"
								+ "	LD	GR4,GR2\r\n"
								+ "	JPL	LOOP\r\n"
								+ "	XOR	GR4,=#FFFF\r\n"
								+ "	ADDA	GR4,=1\r\n"
								+ "LOOP	SRL	GR4,1\r\n"
								+ "	JOV	ONE\r\n"
								+ "	JUMP	ZERO\r\n"
								+ "ONE	ADDL	GR3,GR1\r\n"
								+ "ZERO	SLL	GR1,1\r\n"
								+ "	AND	GR4,GR4\r\n"
								+ "	JNZ	LOOP\r\n"
								+ "	CPA	GR2,=0\r\n"
								+ "	JPL	END\r\n"
								+ "	XOR	GR3,=#FFFF\r\n"
								+ "	ADDA	GR3,=1\r\n"
								+ "END	LD	GR2,GR3\r\n"
								+ "	POP	GR4\r\n"
								+ "	POP	GR3\r\n"
								+ "	POP	GR1\r\n"
								+ "	RET\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; DIV 割り算を行うサブルーチン\r\n"
								+ "; GR1 / GR2 -> 商は GR2, 余りは GR1\r\n"
								+ "DIV	START\r\n"
								+ "	PUSH	0,GR3\r\n"
								+ "	ST	GR1,A\r\n"
								+ "	ST	GR2,B\r\n"
								+ "	CPA	GR1,=0\r\n"
								+ "	JPL	SKIPA\r\n"
								+ "	XOR	GR1,=#FFFF\r\n"
								+ "	ADDA	GR1,=1\r\n"
								+ "SKIPA	CPA	GR2,=0\r\n"
								+ "	JZE	SKIPD\r\n"
								+ "	JPL	SKIPB\r\n"
								+ "	XOR	GR2,=#FFFF\r\n"
								+ "	ADDA	GR2,=1\r\n"
								+ "SKIPB	LD	GR3,=0\r\n"
								+ "LOOP	CPA	GR1,GR2\r\n"
								+ "	JMI	STEP\r\n"
								+ "	SUBA	GR1,GR2\r\n"
								+ "	LAD	GR3,1,GR3\r\n"
								+ "	JUMP	LOOP\r\n"
								+ "STEP	LD	GR2,GR3\r\n"
								+ "	LD	GR3,A\r\n"
								+ "	CPA	GR3,=0\r\n"
								+ "	JPL	SKIPC\r\n"
								+ "	XOR	GR1,=#FFFF\r\n"
								+ "	ADDA	GR1,=1\r\n"
								+ "SKIPC	XOR	GR3,B\r\n"
								+ "	CPA	GR3,=0\r\n"
								+ "	JZE	SKIPD\r\n"
								+ "	JPL	SKIPD\r\n"
								+ "	XOR	GR2,=#FFFF\r\n"
								+ "	ADDA	GR2,=1\r\n"
								+ "SKIPD	POP	GR3\r\n"
								+ "	RET\r\n"
								+ "A	DS	1\r\n"
								+ "B	DS	1\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; 入力装置から数値データを読み込み，\r\n"
								+ "; その内容をGR2が指すアドレスに格納するサブルーチン\r\n"
								+ "RDINT	START\r\n"
								+ "	PUSH	0,GR1	; GR1の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR3	; GR3の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR4	; GR4の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR5	; GR5の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR6	; GR6の内容をスタックに退避\r\n"
								+ "	LD	GR5,GR2	; GR2が指す番地をGR5にコピー\r\n"
								+ "	LD	GR2,=0	; GR2を初期化\r\n"
								+ "	LD	GR3,=0	; GR3を初期化\r\n"
								+ "	IN	INAREA,INLEN	; 入力を受け取る\r\n"
								+ "	; 入力がnullかどうかのチェック\r\n"
								+ "	CPA	GR3,INLEN\r\n"
								+ "	JZE	ERROR\r\n"
								+ "	; 最初の文字が'-'かどうかのチェック\r\n"
								+ "	LD	GR4,INAREA,GR3\r\n"
								+ "	LAD	GR3,1,GR3\r\n"
								+ "	LD	GR6,GR4	; GR6に入力された先頭の文字を保存\r\n"
								+ "	CPL	GR4,=#002D	; '-'かどうか\r\n"
								+ "	JZE	LOOP\r\n"
								+ "	CPL	GR4,='0'	; 数値かどうかのチェック\r\n"
								+ "	JMI	ERROR\r\n"
								+ "	CPL	GR4,='9'\r\n"
								+ "	JPL	ERROR\r\n"
								+ "	XOR	GR4,=#0030	; 数値だったら変換\r\n"
								+ "	ADDA	GR2,GR4\r\n"
								+ "	; 「すでに読み込んだ数値を10倍して，新しく読み込んだ数値と足す」を繰り返す\r\n"
								+ "LOOP	CPA	GR3,INLEN\r\n"
								+ "	JZE	CODE	; 入力された文字数とGR3が同じであればループを抜ける\r\n"
								+ "	LD	GR1,=10\r\n"
								+ "	CALL	MULT	; GR2の値を10倍する\r\n"
								+ "	LD	GR4,INAREA,GR3\r\n"
								+ "	CPL	GR4,='0'	; 数値かどうかのチェック\r\n"
								+ "	JMI	ERROR\r\n"
								+ "	CPL	GR4,='9'\r\n"
								+ "	JPL	ERROR\r\n"
								+ "	XOR	GR4,=#0030	; GR4の内容を数値に変換\r\n"
								+ "	ADDA	GR2,GR4	; GR2にGR1の内容を足す\r\n"
								+ "	LAD	GR3,1,GR3	; GR3(ポインタ)をインクリメント\r\n"
								+ "	JUMP	LOOP\r\n"
								+ "	; 最初の文字が'-'であった場合は-1倍する\r\n"
								+ "CODE	CPL	GR6,=#002D\r\n"
								+ "	JNZ	END\r\n"
								+ "	XOR	GR2,=#FFFF\r\n"
								+ "	LAD	GR2,1,GR2\r\n"
								+ "	JUMP	END\r\n"
								+ "	; エラーを出力する\r\n"
								+ "ERROR	OUT	ERRSTR,ERRLEN\r\n"
								+ "END	ST	GR2,0,GR5	; GR2の内容をGR5が指す番地に格納する\r\n"
								+ "	LD	GR2,GR5	; GR5が指す番地をGR2に戻す\r\n"
								+ "	POP	GR6\r\n"
								+ "	POP	GR5\r\n"
								+ "	POP	GR4\r\n"
								+ "	POP	GR3\r\n"
								+ "	POP	GR1\r\n"
								+ "	RET\r\n"
								+ "ERRSTR	DC	'illegal input'\r\n"
								+ "ERRLEN	DC	13\r\n"
								+ "INAREA	DS	6\r\n"
								+ "INLEN	DS	1\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; 入力装置から文字を読み込み，\r\n"
								+ "; その内容をGR2が指すアドレスに格納するサブルーチン\r\n"
								+ "RDCH	START\r\n"
								+ "	IN	INCHAR,INLEN\r\n"
								+ "	LD	GR1,INCHAR\r\n"
								+ "	ST	GR1,0,GR2\r\n"
								+ "	RET\r\n"
								+ "INCHAR	DS	1\r\n"
								+ "INLEN	DS	1\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; 入力装置から，GR1の文字数を読み込む．\r\n"
								+ "; 読み込んだ文字列は，GR2 が指すアドレスから順に格納される\r\n"
								+ "RDSTR	START\r\n"
								+ "	PUSH	0,GR3	; GR3の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR4	; GR4の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR5	; GR5の内容をスタックに退避\r\n"
								+ "	LAD	GR4,0	; GR4を初期化\r\n"
								+ "	IN	INSTR,INLEN\r\n"
								+ "LOOP	CPA	GR4,GR1\r\n"
								+ "	JZE	END	; GR1で指定された文字数を超えたら終わり\r\n"
								+ "	CPA	GR4,INLEN\r\n"
								+ "	JZE	END	; 入力された文字数を超えたら終わり\r\n"
								+ "	LD	GR5,GR2\r\n"
								+ "	ADDA	GR5,GR4	; 文字の格納先番地を計算\r\n"
								+ "	LD	GR3,INSTR,GR4\r\n"
								+ "	ST	GR3,0,GR5\r\n"
								+ "	LAD	GR4,1,GR4\r\n"
								+ "	JUMP	LOOP\r\n"
								+ "END	POP	GR5\r\n"
								+ "	POP	GR4\r\n"
								+ "	POP	GR3\r\n"
								+ "	RET\r\n"
								+ "INSTR	DS	256\r\n"
								+ "INLEN	DS	1\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; 入力装置からの文字列を改行まで読み飛ばすサブルーチン\r\n"
								+ "RDLN	START\r\n"
								+ "	IN	INAREA,INLEN\r\n"
								+ "	RET\r\n"
								+ "INAREA	DS	256\r\n"
								+ "INLEN	DS	1\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; GR2の内容（数値データ）を出力装置に書き出すサブルーチン\r\n"
								+ "; このサブルーチンが呼ばれたとき，\r\n"
								+ "; GR7には，出力用番地の先頭アドレスが，\r\n"
								+ "; GR6には，現在出力用番地に入っている文字数が，\r\n"
								+ "; それぞれ格納されている．\r\n"
								+ "WRTINT	START\r\n"
								+ "	PUSH	0,GR1	; GR1の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR2	; GR2の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR3	; GR3の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR2	; 数値データをもう一度スタックに退避\r\n"
								+ "	LD	GR3,=0	; GR3はインデックスとして用いる\r\n"
								+ "	; 数値データが負数である場合は，正の数に変換\r\n"
								+ "	CPA	GR2,=0\r\n"
								+ "	JPL	LOOP1\r\n"
								+ "	XOR	GR2,=#FFFF\r\n"
								+ "	ADDA	GR2,=1\r\n"
								+ "	; 数値データを変換しながら，バッファに格納\r\n"
								+ "LOOP1	LD	GR1,GR2\r\n"
								+ "	LD	GR2,=10\r\n"
								+ "	CALL	DIV\r\n"
								+ "	XOR	GR1,=#0030\r\n"
								+ "	ST	GR1,BUFFER,GR3\r\n"
								+ "	LAD	GR3,1,GR3\r\n"
								+ "	CPA	GR2,=0\r\n"
								+ "	JNZ	LOOP1\r\n"
								+ "	; 数値データが負数であれば，'-'を追加\r\n"
								+ "	POP	GR2\r\n"
								+ "	CPA	GR2,=0\r\n"
								+ "	JZE	LOOP2\r\n"
								+ "	JPL	LOOP2\r\n"
								+ "	LD	GR1,='-'\r\n"
								+ "	ST	GR1,BUFFER,GR3\r\n"
								+ "	LAD	GR3,1,GR3\r\n"
								+ "	; BUFFERを逆順にたどりながら，出力用バッファに格納\r\n"
								+ "LOOP2	LAD	GR3,-1,GR3\r\n"
								+ "	LD	GR1,BUFFER,GR3\r\n"
								+ "	LD	GR2,GR7\r\n"
								+ "	ADDA	GR2,GR6\r\n"
								+ "	ST	GR1,0,GR2\r\n"
								+ "	LAD	GR6,1,GR6\r\n"
								+ "	CPA	GR3,=0\r\n"
								+ "	JNZ	LOOP2\r\n"
								+ "END	POP	GR3\r\n"
								+ "	POP	GR2\r\n"
								+ "	POP	GR1\r\n"
								+ "	RET\r\n"
								+ "BUFFER	DS	6\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; GR2の内容（文字）を出力装置に書き出すサブルーチン\r\n"
								+ "; このサブルーチンが呼ばれたとき，\r\n"
								+ "; GR7には，出力用番地の先頭アドレスが，\r\n"
								+ "; GR6には，現在出力用番地に入っている文字数が，\r\n"
								+ "; それぞれ格納されている．\r\n"
								+ "WRTCH	START\r\n"
								+ "	PUSH	0,GR1	; GR1の内容をスタックに退避\r\n"
								+ "	LD	GR1,GR7\r\n"
								+ "	ADDA	GR1,GR6	; GR1に次の文字を格納する番地を代入\r\n"
								+ "	ST	GR2,0,GR1\r\n"
								+ "	LAD	GR6,1,GR6\r\n"
								+ "	POP	GR1\r\n"
								+ "	RET\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; GR2の指すメモリ番地から，長さGR1の文字列を出力装置に書き出すサブルーチン\r\n"
								+ "; このサブルーチンが呼ばれたとき，\r\n"
								+ "; GR7には，出力用番地の先頭アドレスが，\r\n"
								+ "; GR6には，現在出力用番地に入っている文字数が，\r\n"
								+ "; それぞれ格納されている．\r\n"
								+ "WRTSTR	START\r\n"
								+ "	PUSH	0,GR3	; GR3の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR4	; GR4の内容をスタックに退避\r\n"
								+ "	PUSH	0,GR5	; GR5の内容をスタックに退避\r\n"
								+ "	LAD	GR3,0	; GR3は制御変数として用いる\r\n"
								+ "LOOP	CPA	GR3,GR1\r\n"
								+ "	JZE	END\r\n"
								+ "	LD	GR4,GR2\r\n"
								+ "	ADDA	GR4,GR3	; 出力する文字の格納番地を計算\r\n"
								+ "	LD	GR5,0,GR4	; 出力する文字をレジスタにコピー\r\n"
								+ "	LD	GR4,GR7\r\n"
								+ "	ADDA	GR4,GR6	; 出力先の番地を計算\r\n"
								+ "	ST	GR5,0,GR4	; 出力装置に書き出し\r\n"
								+ "	LAD	GR3,1,GR3\r\n"
								+ "	LAD	GR6,1,GR6\r\n"
								+ "	JUMP	LOOP\r\n"
								+ "END	POP	GR5\r\n"
								+ "	POP	GR4\r\n"
								+ "	POP	GR3\r\n"
								+ "	RET\r\n"
								+ "	END\r\n"
								+ ";============================================================\r\n"
								+ "; 改行を出力装置に書き出すサブルーチン\r\n"
								+ "; 実質的には，GR7で始まるアドレス番地から長さGR6の文字列を出力する\r\n"
								+ "WRTLN	START\r\n"
								+ "	PUSH	0,GR1\r\n"
								+ "	PUSH	0,GR2\r\n"
								+ "	PUSH	0,GR3\r\n"
								+ "	ST	GR6,OUTLEN\r\n"
								+ "	LAD	GR1,0\r\n"
								+ "LOOP	CPA	GR1,OUTLEN\r\n"
								+ "	JZE	END\r\n"
								+ "	LD	GR2,GR7\r\n"
								+ "	ADDA	GR2,GR1\r\n"
								+ "	LD	GR3,0,GR2\r\n"
								+ "	ST	GR3,OUTSTR,GR1\r\n"
								+ "	LAD	GR1,1,GR1\r\n"
								+ "	JUMP	LOOP\r\n"
								+ "END	OUT	OUTSTR,OUTLEN\r\n"
								+ "	LAD	GR6,0	; 文字列を出力して，GR6を初期化\r\n"
								+ "	POP	GR3\r\n"
								+ "	POP	GR2\r\n"
								+ "	POP	GR1\r\n"
								+ "	RET\r\n"
								+ "OUTSTR	DS	256\r\n"
								+ "OUTLEN	DS	1\r\n"
								+ "	END\r\n";

						//ファイルに書き込む
						pw.println(cas);

						//ファイルを閉じる
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				else {
					/*for(int s=0;s<pointsuu;s++) {
						System.out.println(pinfo[s].number);
						System.out.println(pinfo[s].goku);
						System.out.println(pinfo[s].tnum);
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
					for(int i=0;i<prosu;i++) {
						for(int j=0;j<huku[i].hensukazu;j++) {
							System.out.println(huku[i].namae[j]);
							System.out.println(huku[i].hyoujun[j]);
							System.out.println(huku[i].kata[j]);

						}
					}*/
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


