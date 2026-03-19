DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 0
	 jz vrai_not_23
	 mov eax, 0
	 jmp fin_not_23
vrai_not_23:
	 mov eax, 1
fin_not_23:
	 out eax
CODE ENDS
