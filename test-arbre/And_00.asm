DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 0
	 sub eax, 0
	 jz faux_and_1
	 mov eax, 0
	 sub eax, 0
	 jz faux_and_1
	 mov eax, 1
	 jmp fin_and_1
faux_and_1:
	 mov eax, 0
fin_and_1:
	 out eax
CODE ENDS
