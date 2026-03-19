DATA SEGMENT
	res DD
DATA ENDS
CODE SEGMENT
	 mov eax, 2
	 push eax
	 mov eax, 3
	 push eax
	 mov eax, 4
	 pop ebx
	 mul eax, ebx
	 pop ebx
	 add eax, ebx
	 mov res, eax
	 mov eax, res
	 out eax
CODE ENDS
