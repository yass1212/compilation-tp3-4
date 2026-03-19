DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 1
	 jz vrai_not_24
	 mov eax, 0
	 jmp fin_not_24
vrai_not_24:
	 mov eax, 1
fin_not_24:
	 out eax
CODE ENDS
