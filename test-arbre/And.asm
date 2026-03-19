DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 1
	 sub eax, 0
	 jz faux_and_0
	 mov eax, 0
	 sub eax, 0
	 jz faux_and_0
	 mov eax, 1
	 jmp fin_and_0
faux_and_0:
	 mov eax, 0
fin_and_0:
	 out eax
CODE ENDS
