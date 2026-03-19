DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 3
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jle vrai_jle_18
	 mov eax, 0
	 jmp fin_jle_18
vrai_jle_18:
	 mov eax, 1
fin_jle_18:
	 out eax
CODE ENDS
