DATA SEGMENT
	x DD
	y DD
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 mov x, eax
	 mov eax, 10
	 mov y, eax
	 mov eax, x
	 push eax
	 mov eax, y
	 pop ebx
	 add eax, ebx
	 out eax
CODE ENDS
