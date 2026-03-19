DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 10
	 push eax
	 mov eax, 5
	 pop ebx
	 add eax, ebx
	 out eax
CODE ENDS
