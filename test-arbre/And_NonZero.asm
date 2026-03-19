DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 sub eax, 0
	 jz faux_and_6
	 mov eax, 3
	 sub eax, 0
	 jz faux_and_6
	 mov eax, 1
	 jmp fin_and_6
faux_and_6:
	 mov eax, 0
fin_and_6:
	 out eax
CODE ENDS
