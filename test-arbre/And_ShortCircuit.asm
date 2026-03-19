DATA SEGMENT
	x DD
DATA ENDS
CODE SEGMENT
	 mov eax, 10
	 mov x, eax
	 mov eax, 0
	 sub eax, 0
	 jz faux_and_7
	 mov eax, 20
	 mov x, eax
	 sub eax, 0
	 jz faux_and_7
	 mov eax, 1
	 jmp fin_and_7
faux_and_7:
	 mov eax, 0
fin_and_7:
	 mov eax, x
	 out eax
CODE ENDS
