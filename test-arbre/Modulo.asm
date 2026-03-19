DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 10
	 push eax
	 mov eax, 3
	 pop ebx
	 mov ecx, eax
	 mov eax, ebx
	 div ebx, ecx
	 mul ebx, ecx
	 sub eax, ebx
	 out eax
CODE ENDS
