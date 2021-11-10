package enshud.s2.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	String[] jiku = new String[256];
	String[] tokenname = new String[256];
	int[] tokenID = new int[256];
	int[] gyou = new int[256];
	int n=0;

	void program() {
		if (!(jiku[n].equals("program"))) {
			System.out.println("err");
			// まず "program" という文字が出てこなければ構文エラー
		}
		n++;
		programName(); 
		if (!(jiku[n].equals(";"))) {
			System.out.println("err"); // プログラム名の次に ";" が出てこなければ構文エラー
		}
		block();            // 別EBNFメソッド
		complexStatement(); // 別EBNFメソッド

		if (!(jiku[n].equals("."))) {
			System.out.println("2err"); 
		}

	}


	void programName() {
		if (!(tokenname[n].equals("SIDENTIFIER"))) {
			System.out.println("err"); // プログラム名の次に ";" が出てこなければ構文エラー
		}
		n++;
	}


	void block() {
		hensusengen();
		hukuprogramsengengun();
	}


	void complexStatement() {

	}


	void hensusengen() {
		if ((jiku[n].equals("var"))) {
			n++;
			hensusengennarabi();
		}		
	}


	void hukuprogramsengengun() {
		if ((jiku[n].equals("procedure"))) {
			hukuprogramsengen();
			if (!(jiku[n].equals(";"))) {
				System.out.println("err");
			}
			n++;
		}
	}


	void hensusengennarabi() {
		while(true) {
			hensuumeinarabi();
			if (!(jiku[n].equals(":"))) {
				System.out.println("err");
			}
			n++;
			kata();
			if (!(jiku[n].equals(";"))) {
				System.out.println("err");
			}
			n++;
			if (!(tokenname[n].equals("SIDENTIFIER"))) {
				break;
			}
		}
	}


	void hukuprogramsengen() {
		
	}


	void hensuumeinarabi() {
		hensuumei();
		if(!(jiku[n].equals(","))) {
			while(true) {
				if(!(jiku[n].equals(","))) {
					System.out.println("err");
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
			System.out.println("err"); 
		}
		n++;
	}
	
	
	void hairetugata() {
		if (!(jiku[n].equals("array"))) {
			System.out.println("err");
		}
		n++;
		if (!(jiku[n].equals("["))) {
			System.out.println("err");
		}
		n++;
		seisuu();
		if (!(jiku[n].equals(".."))) {
			System.out.println("err");
		}
		n++;
		seisuu();
		if (!(jiku[n].equals("]"))) {
			System.out.println("err");
		}
		n++;
		if (!(jiku[n].equals("of"))) {
			System.out.println("err");
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
			System.out.println("err"); 
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
			System.out.println("err"); 
		}
	}


	public void run(final String inputFileName) {
		int tokensu=0;
		int n=0;
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
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		program();

		// TODO


	}
	// TODO

}
