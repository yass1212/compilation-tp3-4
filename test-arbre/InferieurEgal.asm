DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jle vrai_jle_15
	 mov eax, 0
	 jmp fin_jle_15
vrai_jle_15:
	 mov eax, 1
fin_jle_15:
	 out eax
CODE ENDS
