DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 2
	 sub eax, 0
	 jz faux_and_5
	 mov eax, 1
	 sub eax, 0
	 jz faux_and_5
	 mov eax, 1
	 jmp fin_and_5
faux_and_5:
	 mov eax, 0
fin_and_5:
	 out eax
CODE ENDS
