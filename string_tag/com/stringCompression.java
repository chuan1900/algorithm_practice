/*
* input: aaaAbbccccc 
* output: a4b2c5
* @param: str
*
* (1) aaaAbbccccc
* step 1: countNum = 1;


* (2) aabcd
      output--> a2b1c1d1

*/



public String compressStr(String str){
	StringBuilder compress = new StringBuilder();
	int countNum = 0;

	//iterate through the intput str
	for(int i = 0; i < str.length; i++){
		countNum++;
		if(i + 1 >= str.length || str.charAt(i) != str.charAt(i + 1)){
			compress.append(str.charAt(i));
			compress.append(countNum);
			countNum = 0;
		}
		return compress.length() < str.length() ? compress.toString() : str;
	}
}