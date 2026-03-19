DATA SEGMENT
	x DD
DATA ENDS
CODE SEGMENT
	 mov eax, 42
	 mov x, eax
	 mov eax, x
	 out eax
CODE ENDS
