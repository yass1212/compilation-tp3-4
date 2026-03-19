DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 0
	 sub eax, 0
	 jz else_12
	 mov eax, 10
	 jmp fin_if_12
else_12:
	 mov eax, 20
fin_if_12:
	 out eax
CODE ENDS
