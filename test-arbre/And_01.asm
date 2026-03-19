DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 0
	 sub eax, 0
	 jz faux_and_2
	 mov eax, 1
	 sub eax, 0
	 jz faux_and_2
	 mov eax, 1
	 jmp fin_and_2
faux_and_2:
	 mov eax, 0
fin_and_2:
	 out eax
CODE ENDS
