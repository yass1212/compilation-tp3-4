DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 1
	 jz vrai_not_22
	 mov eax, 0
	 jmp fin_not_22
vrai_not_22:
	 mov eax, 1
fin_not_22:
	 out eax
CODE ENDS
