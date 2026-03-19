DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jz vrai_je_10
	 mov eax, 0
	 jmp fin_je_10
vrai_je_10:
	 mov eax, 1
fin_je_10:
	 out eax
CODE ENDS
