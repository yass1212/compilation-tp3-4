DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 je vrai_je_8
	 mov eax, 0
	 jmp fin_je_8
vrai_je_8:
	 mov eax, 1
fin_je_8:
	 out eax
CODE ENDS
