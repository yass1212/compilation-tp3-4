DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 10
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 mov eax, ebx
	 out eax
CODE ENDS
