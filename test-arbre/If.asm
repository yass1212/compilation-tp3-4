DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 1
	 sub eax, 0
	 jz else_11
	 mov eax, 10
	 jmp fin_if_11
else_11:
	 mov eax, 20
fin_if_11:
	 out eax
CODE ENDS
