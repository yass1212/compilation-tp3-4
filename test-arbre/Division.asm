DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 20
	 push eax
	 mov eax, 4
	 pop ebx
	 div ebx, eax
	 mov eax, ebx
	 out eax
CODE ENDS
