int main(){


	int val = tickets[p];
	int result = 0;
	for (int i = 0; i < tickets.length; ++i)
	{
		if( i<=p ){
			if(tickets[i]>val){
				result += val;
			}
			else{
				result += tickets[i];
			}
		}
		else{
			if(tickets[i]>(val-1)){
				result += (val-1);
			}
			else{
				result += tickets[i];
			}
		}

	}
	return result;
}