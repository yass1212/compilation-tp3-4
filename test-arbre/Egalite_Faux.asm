DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 push eax
	 mov eax, 3
	 pop ebx
	 sub ebx, eax
	 je vrai_je_9
	 mov eax, 0
	 jmp fin_je_9
vrai_je_9:
	 mov eax, 1
fin_je_9:
	 out eax
CODE ENDS
