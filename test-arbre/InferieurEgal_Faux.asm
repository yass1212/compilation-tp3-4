DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 7
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jle vrai_jle_16
	 mov eax, 0
	 jmp fin_jle_16
vrai_jle_16:
	 mov eax, 1
fin_jle_16:
	 out eax
CODE ENDS
