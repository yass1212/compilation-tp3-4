DATA SEGMENT
	a DD
	double DD
DATA ENDS
CODE SEGMENT
	 in eax
	 mov a, eax
	 mov eax, a
	 push eax
	 mov eax, 2
	 pop ebx
	 mul eax, ebx
	 mov double, eax
	 mov eax, double
	 out eax
CODE ENDS
