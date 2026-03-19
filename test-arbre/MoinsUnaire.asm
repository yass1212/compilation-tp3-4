DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 push eax
	 pop ebx
	 mov eax, 0
	 sub eax, ebx
	 out eax
CODE ENDS
