DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 2
	 push eax
	 mov eax, 3
	 pop ebx
	 mul eax, ebx
	 out eax
CODE ENDS
