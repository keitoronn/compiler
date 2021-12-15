package enshud.s3.checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Checker {

	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// normalの確認

		// synerrの確認
		new Checker().run("data/ts/normal12.ts");
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
		int hensukazu = 0;
		hensuse() {
			for(int i=0;i<1024;i++) {
				this.namae[i]="";
				this.hyoujun[i]="";
				this.hensukazu=0;
				this.tnum[i]=0;
				this.kata[i]="";
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
	int chou=0;
	int prosu=0;
	point[] pinfo = new point[100000];
	hensuse[] huku = new hensuse[100000];
	String[] hensu =new String[1024];
	String[] hukuproname = new String[1024];
	int hukuprosu=0;
	int hensusu=0;

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
												}
												else {
													huku[prosu].kata[hensusu]="hyoujungata";
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
			}
		}
	}
	void kariparamemory() {
		int[] a = new int[1024];
		saikisearch("kariparameterorder",0,a);
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
								huku[prosu].namae[hensusu]=pinfo[c[k]].goku;
								huku[prosu].tnum[hensusu]=pinfo[c[k]].tnum;
								huku[prosu].hyoujun[hensusu]=pinfo[hyo].goku;
								hensusu++;
							}
						}
					}
				}
				
				huku[prosu].hensukazu=hensusu+1;
				prosu++;
				hensusu=0;
			}
		}
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
				for(int s=0;s<100000;s++) {
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
				/*for(int i=0;i<prosu;i++) {
					for(int j=0;j<huku[i].hensukazu;j++) {
						System.out.println(huku[i].namae[j]);
						System.out.println(huku[i].hyoujun[j]);
						System.out.println(huku[i].kata[j]);

					}
				}*/
				if(err==0) {
					System.out.println("OK");
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
